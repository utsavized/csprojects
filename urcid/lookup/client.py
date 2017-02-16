#Client-side to utilize the API
#Returns query in XML format

from urllib import FancyURLopener

class MyOpener(FancyURLopener):
    version = 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11'

def lookup_project2c(number, api_key):
    try:
        base_url = 'http://127.0.0.1:8000/api/'
        myopener = MyOpener()
        content = myopener.open(base_url + number + '_' + api_key + '/').read()
        print content
        
    except IOError:
        print IOError
        print 'Error!'
    
api_key = '37365581'
number = raw_input("Please enter the number: ")
lookup_project2c(number, api_key)

#http://127.0.0.1:8000/api/5636636950_37365581/