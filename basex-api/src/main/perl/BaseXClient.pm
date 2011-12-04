# Perl client for BaseX.
# Works with BaseX 7.0 and later
#
# Documentation: http://docs.basex.org/wiki/Clients
# 
# (C) BaseX Team 2005-11, BSD License

use Digest::MD5;
use IO::Socket;
use warnings;
use strict;

package Session;

# see readme.txt
sub new {
  my $class = shift;
  my $host = shift;
  my $port = shift;
  my $user = shift;
  my $pw = shift;
  my $self = bless({}, $class);

  # create server connection
  $self->{sock} = IO::Socket::INET->new(
    PeerAddr => $host, PeerPort => $port, Proto => "tcp") or
    die "Can't communicate with the server.";

  # receive timestamp
  my $ts = $self->_receive();

  # send username and hashed password/timestamp
  my $pwmd5 = Digest::MD5->new()->add($pw)->hexdigest();
  my $complete = Digest::MD5->new()->add($pwmd5.$ts)->hexdigest();
  $self->send($user.chr(0).$complete);

  # evaluate success flag
  return $self if !$self->_read() or die "Access denied.";
}

# see readme.txt
sub execute {
  my $self = shift;
  my $cmd = shift;

  # send command to server and receive result
  $self->send($cmd);
  $self->{result} = $self->_receive();
  $self->{info} = $self->_receive();
  die $self->{info} if !$self->ok();
  return $self->{result};
}

# see readme.txt
sub query {
  return Query->new(shift, shift);
}

# see readme.txt
sub create {
  shift->sendInput(8, shift, shift);
}

# see readme.txt
sub add {
  shift->sendInput(9, shift, shift);
}

# see readme.txt
sub replace {
  shift->sendInput(12, shift, shift);
}

# see readme.txt
sub store {
  shift->sendInput(13, shift, shift);
}

# see readme.txt
sub info {
  return shift->{info};
}

# see readme.txt
sub close {
  my $self = shift;
  $self->send("exit");
  close($self->{sock});
}

# Receives a string from the socket.
sub _receive {
  my $self = shift;
  my $data = "";
  while($self->_read()) {
    $self->_read() if ord($_) == 255;
    $data .= $_;
  }
  return $data;
}

# Returns a single byte from the socket.
sub _read {
  shift->{sock}->recv($_, 1);
  return ord();
}

# Returns success check.
sub ok {
  return !shift->_read();
}

# Sends the specified string.
sub send {
  shift->{sock}->send((shift).chr(0));
}

# Sends the specified input.
sub sendInput {
  my $self = shift;
  my $code = shift;
  my $str = shift;
  my $input = shift;

  $self->send(chr($code).$str);
  foreach my $b(unpack("C*", $input)) {
    if($b == 0xFF || $b == 0x00) {
      $self->{sock}->send(0xFF);
    }
    $self->{sock}->send(chr($b));
  }
  $self->{sock}->send(chr(0));

  $self->{info} = $self->_receive();
  die $self->{info} if !$self->ok();
}

1;

package Query;

our $session;
our @cache;
our $pos = 0;
our $id;

# see readme.txt
sub new {
  my $class = shift;
  $session = shift;
  my $cmd = shift;
  my $self = bless({}, $class);
  $id = exc(chr(0), $cmd);
  return $self;
}

# see readme.txt
sub bind {
  shift;
  my $name = shift;
  my $value = shift;
  exc(chr(3), $id.chr(0).$name.chr(0).$value.chr(0));
}

# see readme.txt
sub execute {
  return exc(chr(5), $id);
}

# see readme.txt
sub more {
  if(!@cache) {
    $session->send(chr(4).$id.chr(0));
    push(@cache, $session->_receive()) while $session->_read();
    die $session->{info} if !$session->ok();
  }
  return $pos < @cache;
}

# see readme.txt
sub next {
  return more() && $cache[$pos++];
}

# see readme.txt
sub info {
  return exc(chr(6), $id);
}

# see readme.txt
sub options {
  return exc(chr(7), $id);
}

# see readme.txt
sub close {
  exc(chr(2), $id);
}

# see readme.txt
sub exc {
  my $cmd = shift;
  my $arg = shift;
  $session->send($cmd.$arg);
  my $s = $session->_receive();
  if (!$session->ok()) {
    die $session->_receive();
  }
  return $s;
}

1;