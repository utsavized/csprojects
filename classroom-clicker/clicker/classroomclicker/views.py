#Imports
from django.shortcuts import render_to_response
from django.views.decorators.csrf import csrf_exempt
import MessageListener
import MessagePublisher

#Some tracking
status70 = '0'
status71 = '0'
status72 = '0'
rangeV70 = 5
rangeV71 = 5
rangeV72 = 5

#Index is the first method called when info is submitted
@csrf_exempt  
def index(request):
    return render_to_response('classroomclicker/index.html')


#Accessors and Mutators for range and status
def getStatus(channel):
    if channel == '70':
        global status70
        return status70
    elif channel == '71':
        global status71
        return status71
    elif channel == '72':
        global status72
        return status72

def setStatus(st, channel):
    if channel == '70':
        global status70
        status70 = st
    elif channel == '71':
        global status71
        status71 = st
    elif channel == '72':
        global status72
        status72 = st
    
def getRange(channel):
    if channel == '70':
        global rangeV70
        return rangeV70
    elif channel == '71':
        global rangeV71
        return rangeV71
    elif channel == '72':
        global rangeV72
        return rangeV72

def setRange(rg, channel):
    if channel == '70':
        global rangeV70
        rangeV70 = rg
    elif channel == '71':
        global rangeV71
        rangeV71 = rg
    elif channel == '72':
        global rangeV72
        rangeV72 = rg

#Messaging part   
@csrf_exempt
def messaging(request):
    rangeVal = 0
    rangeList = []
    channel = ""
    clicker = ""
    choice = ""
    message = ""
    status = ""
    count = 0
    #Recieve info from POST
    if request.method =="POST":
        channel = str(request.POST['channel'])
        clicker = str(request.POST['clicker'])
        choice = str(request.POST['choice'])
        count = int(request.POST['count'])
        rangeVal = int(request.POST['range'])
        status = request.POST['status']
        
        #If first post, or channel has just been opened
        count = count + 1
        if count == 1:
            #Subscribe to the new channel
            MessageListener.client.subscribe("/queue/" + channel, MessageListener.frame_received)
            #If professor
            if clicker == "Professor":
                #set new status (Open/Close)
                setStatus(status, channel)
                #set new range of possible responses
                setRange(rangeVal, channel)
        
        #Get previous message dequeded by listener
        if channel == '70':
            message = MessageListener.message70
        
        elif channel == '71':
            message = MessageListener.message71
        
        elif channel == '72':
            message = MessageListener.message72
        
        #Publish new messages
        MessagePublisher.send(clicker, channel, choice, getStatus(channel), count, getRange(channel))

        #To get values for range (for HTML Purpose)
        for i in range(0,getRange(channel)):
            rangeList.append(i)
    
    #Render to HTML with necessary information to be printed out    
    return render_to_response('classroomclicker/clicker.html', {'response': message, 'channel':channel, 'range':rangeList, 'rangeVal':rangeVal, 'clicker':MessageListener.previousChannel, 'count':count, 'status':status})    