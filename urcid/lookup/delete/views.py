# Views for the root/delete/ URIs

from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render_to_response
from couchdb import Database
from couchdb.http import ResourceNotFound
from couchdb.http import Unauthorized
from django.views.decorators.csrf import csrf_exempt
from time import strftime
from urllib import FancyURLopener
import re
import string

#Listens to all root/delete/ URIs and redirects appropriately
@csrf_exempt  
def index(request, username):
    #Connect to DB
    docsc = Database("https://wazza:whatever@wazza.cloudant.com/"+username+"/")
    #Check if direct URI or form
    if request.method == "POST":
        id = request.POST['id'].replace(' ','')
        return detail(request,id,username)
    elif request.method == "DELETE":
        return HttpResponse('is delete here')
    else:
        return HttpResponse("not post")
        #return render_to_response('delete/delete.html',{'rows':docsc,'username':username})

def checkSession(username):
    session = Database('https://wazza:whatever@wazza.cloudant.com/session/')
    for user in session:
        if(user == username):
            sessionDoc = session[user]
            sessionDate = string.split(sessionDoc['timestamp'],' ')
            currentDate = strftime("%Y-%m-%d")
            if not(sessionDate[0] == currentDate):
                return True
            else:
                return False
    else:
        return True


#This is the backend of the Delete page
@csrf_exempt
def detail(request,username,id):
    if request.method == "DELETE":
        session = checkSession(username)
        if session == False:
            docs = Database("https://wazza:whatever@wazza.cloudant.com/"+username+"/")
            deldocs = docs
            #If delete all is checked
            if id=='deleteall':
                #Try to delete with given credientials
                try:
                    for x in deldocs:
                        temp = deldocs[x]
                        docs.delete(temp)
                #Or report incorrect credentials
                except Unauthorized:
                    return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "Could not authenticate"></error>')
            else:
                #Delete individial doc that is checked
                try:    
                    doc = docs[id]
                #Once again prompt if invalid credentials
                except:
                    return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "No number/authentication"></error>')
                
                try:
                    #This is the actual deletion of the doc
                    docs.delete(doc)
                    return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <success value = "Number Deleted"></success>')
                #This will never happen as the document will be there if it is listed for deletion
                #But just in case :)
                except ResourceNotFound:
                    return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "Number not found"></error>')    
        else:
            return HttpResponse('<?xml version="1.0" Name="WS Project 2C API"?> \n <error value = "Session Expired"></error>')
    else:
        return HttpResponse('none')
        #return render_to_response('delete/delete.html',{'rows':docs,'username':u})
    
           
    #Render success message
    return HttpResponse('nothing happened')

#Will never reach here but just incase
@csrf_exempt
def notFound(request,id, username):
    return render_to_response('number/notFound.html', {'username':username})

#Same here
@csrf_exempt
def incorrect(request,id, username):
    return render_to_response('number/incorrect.html', {'username':username})

#This is where the API Key is generated when you signup to request an API
@csrf_exempt
def login(request, id, username):
    return render_to_response('delete/login.html', {'username':username})    