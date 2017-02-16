#Imports
from stompclient import PublishSubscribeClient
import threading    #To allow multiple consumptions from different clients

#Just to keep track of things
message70 = []
message71 = []
message72 = []
stat70 = {}
stat71 = {}
stat72 = {}

#Keep track of previous channel number
previousChannel = ""

#Format the frame received from the queue
def frame_received(frame):
    payload = frame.body
    headers = frame.headers
    channel = headers['channel']
    count = headers['count']
    clicker = headers['clicker']
    rangeVal = headers['range']
    choice = headers['choice']
    status = headers['status']

    #Keep track of channel number
    if not(clicker) == "Professor":
        global previousChannel
        previousChannel = clicker
    
    #If channel is open, keep track of responses for individual channels
    if clicker == "Professor" and status == 'Open':
        if channel == '70':
            for i in range(0,int(rangeVal)):
                global stat70
                stat70[i] = 0
        elif channel == '71':
            for i in range(0,int(rangeVal)):
                global stat71
                stat71[i] = 0
        elif channel == '72':
            for i in range(0,int(rangeVal)):
                global stat72
                stat72[i] = 0
    
    #If the channel is closed, calculate responses       
    elif clicker == "Professor" and status == 'Closed':
        if channel == '70':
            msg = "responses: { "
            for k,v in stat70.iteritems():
                msg = msg + str(k) + "=" + str(v) + " "
            msg = msg + "}"
            global message70
            message70.append(msg)
        elif channel == '71':
            msg = "responses: { "
            for k,v in stat71.iteritems():
                msg = msg + str(k) + "=" + str(v) + " "
            msg = msg + "}"
            global message71
            message71.append(msg)
        elif channel == '72':
            msg = "responses: { "
            for k,v in stat72.iteritems():
                msg = msg + str(k) + "=" + str(v) + " "
            msg = msg + "}"
            global message72
            message72.append(msg)
    
    #New registration
    if count == '1': 
        if channel == '70':
            global message70
            message70.append(payload)
        elif channel == '71':
            global message71
            message71.append(payload)
        elif channel == '72':
            global message72
            message72.append(payload)
    
    #Continued response
    else:
        if channel == '70':
            #If closed, do not accept any more answers
            if headers['status'] == 'Closed':
                message70.append('-- channel ' + channel + ' is not accepting answers.')
            else:
                global stat70
                val = stat70[int(choice)]
                val = val + 1
                stat70[int(choice)] = val
                global message70
                message70.append(payload)
        elif channel == '71':
            if headers['status'] == 'Closed':
                global message71
                message71.append('-- channel ' + channel + ' is not accepting answers.')
            else:
                global stat71
                val = stat71[int(choice)]
                val = val + 1
                stat71[int(choice)] = val
                global message71
                message71.append(payload)
        elif channel == '72':
            if headers['status'] == 'Closed':
                global message72
                message72.append('-- channel ' + channel + ' is not accepting answers.')
            else:
                global stat72
                val = stat72[int(choice)]
                val = val + 1
                stat72[int(choice)] = val
                global message72
                message72.append(payload)
        
#Make connection to liten to ActiveMQ       
client = PublishSubscribeClient('127.0.0.1', 61613)
#Operate on threads so no message overlap occurs, listen forever until app closes
listener = threading.Thread(target=client.listen_forever)
#Start listening
listener.start()
#Wait till something in enqueued
client.listening_event.wait()

client.connect()