#Imports
from stompclient import PublishClient
import time

#Method to publish messages
def send(clicker, channel, choice, status, count, rangeVal):
    statusCode = ""
    client = PublishClient('127.0.0.1', 61613)
    client.connect()
    #Keep track of whether channel is opened or closed
    if status == '0':
        statusCode = 'Closed'
    else:
        statusCode = 'Open'
        
    try:
        if count == 1:
            if clicker == "Professor":
                #Classroom Open/Close
                client.send("/queue/" + channel, '-- channel ' + channel + ' is ' + statusCode, extra_headers={'channel': channel, 'status':statusCode, 'count': count, 'clicker':clicker, 'range':rangeVal, 'choice':choice})
            else:
                #Register clicker
                client.send("/queue/" + channel, '-- clicker ' + clicker + ' registered on channel ' + channel, extra_headers={'channel': channel, 'status':statusCode, 'count': count, 'clicker':clicker, 'range':rangeVal, 'choice':choice})
        else:
            #Queue response in ActiveMQ
            client.send("/queue/" + channel, "received a valid response from clicker " + clicker, extra_headers={'channel': channel, 'status':statusCode, 'count': count, 'clicker':clicker, 'range':rangeVal, 'choice':choice})  
        #Short sleep so that all messages are sent
        time.sleep(1.0)
    finally:
        #Disconnect client once all messeges are queued
        client.disconnect()