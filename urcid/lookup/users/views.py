#Imports
from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render_to_response
from couchdb import Database, Server
from django.views.decorators.csrf import csrf_exempt
from time import strftime
    
@csrf_exempt  
def login(request):
    auth = False
    registration = False
    
    #Connect to DB
    users = Database("https://wazza:whatever@wazza.cloudant.com/users")
    session = Database("https://wazza:whatever@wazza.cloudant.com/session")
    #This will always be a POST but just incase checking is always good :)
    if request.method =="POST":
        #Get username ,password and number
        u = request.POST['username']
        p = request.POST['password']
        try:
            newUser = request.POST['register']
            registration = True
        except:
            registration = False
    else:
        return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "POST Request Expected"></error>')
           
    if registration == True:
        if u == '' or p == '':
            return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "Both Username & Password Required"></error>')
        try:
            usr = users[u]
            return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "Username already taken"></error>')
        
        except:
            users[u] = {'username':u,'password':p}
            server = Server('https://wazza:whatever@wazza.cloudant.com/')
            server.create(u)
            try:
                session[u] = {'username':u,'timestamp':strftime("%Y-%m-%d %H:%M:%S")}
            except:
                session.delete(u)
                session[u] = {'username':u,'timestamp':strftime("%Y-%m-%d %H:%M:%S")}
            return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <success value = "Authenticated"></success>')

    else:
        for x in users:
            try:
                if users[u]:
                    user = users[u]
                    if user['password'] == p:
                        auth = True
                    else:
                        auth = False
                else:
                    auth = False
            except:
                auth = False
        
        if auth == True:
            try:
                session[u] = {'username':u,'timestamp':strftime("%Y-%m-%d %H:%M:%S")}
            except:
                delDoc = session[u]
                session.delete(delDoc)
                session[u] = {'username':u,'timestamp':strftime("%Y-%m-%d %H:%M:%S")}

            return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <success value = "Authenticated"></success>')
        else:
            return HttpResponse('<?xml version="1.0" Name="WS Project 2D"?> \n <error value = "Could not authenticate"></error>')

        return render_to_response('users/ulogin.html')

@csrf_exempt
def expired(request, username):
    return render_to_response('users/incorrect.html')

@csrf_exempt
def logout(request, username):
    session = Database("https://wazza:whatever@wazza.cloudant.com/session")
    delDoc = session[username]
    session.delete(delDoc)

    return HttpResponseRedirect('/')
