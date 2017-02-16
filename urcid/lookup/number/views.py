#Views for Root/numbers/
#Server could be specified at settings.py which would be more flexible but this is easier to debug
#All methods are exempt from csrf authentication
#Just using very basic ones now ... advanced authentication is a headache -- I'll look into it
#when I have more time

#Imports
from django.http import HttpResponse
from couchdb import Database
from couchdb.http import ResourceNotFound
from django.views.decorators.csrf import csrf_exempt
from time import strftime
from urllib import FancyURLopener
import re
import string

#Not really required as we are using WP API. However just put here in case we decide to use screen
#scraping
class MyOpener(FancyURLopener):
    version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'

#Performs the reverse lookup though WP API
def search(docs,id,username):
    sessionExpired = False  #Session tracker
    look = []
    result = ""
    try:
        number = docs[id]
        result = number['name']
        session = number['updated']
        date = string.split(session, ' ')
        #Check Session, Currently lasts for 1 day regardless of begin time
        if date[0] == strftime("%Y-%m-%d"):
            sessionExpired = False
            return HttpResponse('1',docs['id'], docs['incorrect'], docs['correct'],docs['updated'], docs['name'])
        else:
            sessionExpired = True
            raise ResourceNotFound
    except:
        #Connect to WP API
        result = ""
        api_key = '1ae485ea2b55d91d888138ae624063e4'
        base_url_whiteP = 'http://api.whitepages.com/reverse_phone/1.0/?phone='
        myopener = MyOpener()
        content_whiteP = myopener.open(base_url_whiteP + id + ';api_key=' + api_key).read()
        
        #Parse the XML
        fName = re.search('wp:firstname>.*?<', content_whiteP)
        lName = re.search('wp:lastname>.*?<', content_whiteP)
        error = re.search('wp:errormessages',content_whiteP)
        
        #If name found, parse it further
        if fName:
            look.append('Found')
            look.append(fName.group(0)[13:len(fName.group(0))-1])
            look.append(lName.group(0)[12:len(lName.group(0))-1])
            
        #Invalid number
        elif error:
            look.append('Error')
        
        #Number either not found, is a business number or did not return any value for name
        else:
            look.append('NotFound')
        
        #Return appropriate renderings based on case above
        if(look[0]=='Found'):
            name = look[1] + " " + look[2]
            try:
                number = docs[id]
                if sessionExpired == True:
                    docs[id] = {'id':id,'name':name, 'updated':strftime("%Y-%m-%d %H:%M:%S"), 'correct': 0, 'incorrect': 0}
                result = result + '<?xml version="1.0" Name="WS Project 2C"?> \n <name value = "' + name + '"></name> \n <number value = "' + str(number['id']) + '"></number>'
            except:
                docs[id] = {'id':id,'name':name, 'updated':strftime("%Y-%m-%d %H:%M:%S"), 'correct': 0, 'incorrect': 0}
                result = result + '<?xml version="1.0" Name="WS Project 2C"?> \n <success value = "Number Added"></success>'
            
            #document = docs[id]
            #docs.delete(document)
            
            return HttpResponse(result)
            
              
        elif(look[0]=='Error'):
            result = result + '<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Incorrect Number"></error>'
            return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Incorrect Number"></error>')   
        
        else:
            #Not Found
            result = result + '<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Number not found"></error>'
            return HttpResponse(result)

    return HttpResponse('Oops. Something went wrong.')

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


#Listens to all requests in root/
@csrf_exempt  
def index(request, username):
    #Connect to our DB with API Key - Read/Update rights
    docs = Database("https://wazza:whatever@wazza.cloudant.com/"+username+"/")
    if request.method == "POST":
        id = request.POST['id'].replace(' ','')
        if id == '':
            #Number incorrect
            return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Incorrect Number"></error>')
           
        else:
            #Lookup number
            session = checkSession(username)
            if session == False:
                return search(docs,id,username)
            else:
                return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Session Expired"></error>')
    else:
        #Lookup number
        session = checkSession(username)
        if session == False:
            result = '<?xml version="1.0" Name="WS Project 2C"?> \n'
            for x in docs:
                result = result + '<number value = "' + x + '"</number> \n'
            return HttpResponse(result)
        else:
   
            return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Session Expired"></error>')
          
    #Render all caches results at home (root/)
    return HttpResponse(docs)


#Listens to root/{number} URI
#Added this extra to avoid URI duplication
#as index is already listening to root/
@csrf_exempt  
def look(request,id, username):
    #Connect to DB
    docs = Database("https://wazza:whatever@wazza.cloudant.com/"+username+"/")
    session = checkSession(username)
    if session == False:
        return search(docs,id,username)
    else:
     
        return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Session Expired"></error>')
      
    #Perform lookup
    return search(docs,id,username)

#Renders a detail view of any cached entry with the option to mark correct/incorrect
@csrf_exempt
def detail(request,id, username):
    #Connect to DB
    docs = Database("https://wazza:whatever@wazza.cloudant.com/"+username+"/")
    #Check if number exists in cache
    #Second layer of exception handling, as this is already done by search
    try:
        doc = docs[id]
    #This should never happen here. But just incase :)
    #If we are in detail page means that the entry exists
    except ResourceNotFound:
        return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Number not found"></error>')
        #return render_to_response('number/notFound.html')        
    #Check whether form or direct URI was used
    if request.method =="POST":
        #See if correct/incorrect were checked
        if request.POST['correct_incorrect'] == '1':
            doc['correct'] = doc['correct'] + 1
        elif request.POST['correct_incorrect'] == '0':
            doc['incorrect'] = doc['incorrect'] + 1
        #Update count
        docs[id] = doc
    else:
        result = '<?xml version="1.0" Name="WS Project 2C"?> \n'
        for x in docs:
            result = result + '<number value = "' + x + '"</number> \n'
        return HttpResponse(result)
    #Render page again
    result = '<?xml version="1.0" Name="WS Project 2C"?> \n'
    for x in docs:
        result = result + '<number value = "' + x + '"</number> \n'
    return HttpResponse(result)
    #return render_to_response('number/detail.html',{'row':doc, 'username':username})

#Number either not found, is a business number or did not return any value for name
@csrf_exempt
def notFound(request,id, username):
    #Render error page
    return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Number not found"></error>')
    #return render_to_response('number/notFound.html', {'username':username})

#In number entry is incorrect
@csrf_exempt
def incorrect(request,id, username):
    #Render error page
    return HttpResponse('<?xml version="1.0" Name="WS Project 2C"?> \n <error value = "Incorrect Number"></error>')
    #return render_to_response('number/incorrect.html', {'username':username})

