# This example shows how queries can be executed in an iterative manner.
# Iterative evaluation will be slower, as more server requests are performed.
#
# Documentation: http://docs.basex.org/wiki/Clients
#
# (C) BaseX Team 2005-11, BSD License

import BaseXClient, time

try:
  # create session
  session = BaseXClient.Session('localhost', 1984, 'admin', 'admin')
  
  try:
    # create query instance
    input = "for $i in 1 to 10 return <xml>Text { $i }</xml>"
    query = session.query(input)

    # loop through all results
    while query.more():
      print query.next()
  
    # close query object  
    query.close()
  
  except IOError as e:
    # print exception
    print e
    
  # close session
  session.close()

except IOError as e:
  # print exception
  print e
