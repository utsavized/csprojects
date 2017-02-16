#Views for the API. No rendering is dont in the web. Only when accessed by a script
#Results in XML format

from django.http import HttpResponse
from couchdb.http import ResourceNotFound
from django.shortcuts import render_to_response
from couchdb import Database
from django.views.decorators.csrf import csrf_exempt
import random
import string
from urllib import FancyURLopener
import re

#This is not necessary, but just put here in case we decide to use screen scraping
class MyOpener(FancyURLopener):
    version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'

#This gives out the api key to our website when a user registers for a new api
@csrf_exempt
def render_api(api):
    return render_to_response('api/api_key.html',{'api':api})

#This lets new users sign up to our API and gives out randomized numerical API keys
@csrf_exempt  
def index(request):
    rand = str(random.randint(0,99999999))
    docs = Database("https://wazza:whatever@wazza.cloudant.com/api_keys")
    if request.method == "POST":
        username = request.POST['username']
        name = request.POST['name']
        email = request.POST['email']
        phone = request.POST['phone']
        docs[rand] = {'api':rand,'name':name, 'username':username, 'email': email, 'phone': phone}
        return render_api(docs[str(rand)].get('api'))
    return render_to_response('api/signup.html',{'api':docs})

#This is where our API Listens to requests then uses the WP API to get info and supply it out
def lookup(number):
    docs = Database("https://cantlessintseedifterseen:IM8XVvpM7P2GhhpYGjSIPPuW@wazza.cloudant.com/docs")
    look = []
    result = ""
    try:
        record = docs[number]
        result = result + '<?xml version="1.0" Name="WS Project 2C API"?> \n <name value = "' + record.get('name') + '"></name> \n <number value = "' + number + '"></number>'
        return HttpResponse(result)
    except ResourceNotFound:
        api_key = '1ae485ea2b55d91d888138ae624063e4'
        base_url_whiteP = 'http://api.whitepages.com/reverse_phone/1.0/?phone='
        myopener = MyOpener()
        content_whiteP = myopener.open(base_url_whiteP + number + ';api_key=' + api_key).read()
        
        fName = re.search('wp:firstname>.*?<', content_whiteP)
        lName = re.search('wp:lastname>.*?<', content_whiteP)
        error = re.search('wp:errormessages',content_whiteP)
        
        if fName:
            look.append('Found')
            look.append(fName.group(0)[13:len(fName.group(0))-1])
            look.append(lName.group(0)[12:len(lName.group(0))-1])
            
        elif error:
            look.append('Error')
            
        else:
            look.append('NotFound')
        
        #XML Outputs
        if(look[0]=='Found'):
            #Found
            result = result + '<?xml version="1.0" Name="WS Project 2C API"?> \n <name value = "' + look[1] + ' ' + look[2] + '"></name> \n <number value = "' + number + '"></number>'
            return HttpResponse(result)
    
        elif(look[0]=='Error'):
            #Error
            result = result + '<?xml version="1.0" Name="WS Project 2C API"?> \n <error value = "Incorrect Number"></error>'
            return HttpResponse(result)   
        
        else:
            #Not Found
            result = result + '<?xml version="1.0" Name="WS Project 2C API"?> \n <error value = "Number not found"></error>'
            return HttpResponse(result) 

#This authenticates the API user for his/her API Key
def authenticate(request,id):
    result = ""
    docs = Database("https://cantlessintseedifterseen:IM8XVvpM7P2GhhpYGjSIPPuW@wazza.cloudant.com/api_keys")
    sep = "_"
    list = string.split(id, sep)
    authenticate = docs.get(str(list[1]))
    if authenticate == None:
        result = result + '<?xml version="1.0" Name="WS Project 2C API"?> \n <error value = "Invalid API Key"></error>'
        return HttpResponse(result)
    else:
        return HttpResponse(lookup(str(list[0])))
        
    
    
