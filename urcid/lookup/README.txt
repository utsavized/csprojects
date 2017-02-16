== Project 2C: Reverse Lookup API (Client/Server) README ==

=== Programming Bits ===
Language: Python 2.7, Django 1.2.5 Framework
IDE: Eclipse Helios Service Release 1 /w PyDev Django Plugin
OS: HP Pavilion dv4 (4 GB RAM, 320 GB HDD, 2.2 GHz Intel Dual Code), Windows 7 Ultimate 64-Bit

=== External Dependencies ===
- Django (easy_install django)
- CouchDB (easy_install couchdb)

== Internal Dependencies ===
- Urllib, re, string, time, random

=== Project Details ===
*General App File Structure*

Root
- settings.py
- manage.py
- urls.pu
- App
  - models.py
  - tests.py
  - views.py
  - urls.py
- App2
  ...
- App3
  ...
- Templates
  - App1
    - index.html
    - ....

=== Apps for Project 2C ===

1. Numbers

10-digits
Area code, then number
Example: 5636636950

We went with this format because WP API does not list a leading 1.

Parent: /numbers/; return Home  with content indicating how to use the system and the required number format including country code; Lookup of an unsupported number: /numbers/15658234567/; return NOT_FOUND with content indicating the required number format; Cached lookup of a name by number: /numbers/7735551234/; look for a mapping in the cache; if there is an unexpired cache hit, return the cached entry; otherwise look up the number in the configured sources (WP etc.); if successful, cache the entry with the (externally configured) expiration date (1 day); return OK and the desired representation of the entry if unsuccessful, return NOT_FOUND; Report correct entry; increments the pertinent attribute in the domain object corresponding to /numbers/7735551234/; Report incorrect entry; increments the pertinent attribute in the domain object corresponding to /numbers/7735551234/; 

2. Delete

Delete cache for a specific number; requires admin authorization; Delete cache for all numbers; requires admin authorization

3. API

Use client-side code to access server. Have to login to register to get a new api key, then use the key to access out api.

=== Directory Structure === 

lookup
 - number
 - api
 - delete
 - templates
   - number
   - api
   - delete

=== INSTALLATION INSTRUCTION ===

- Download files to hard drive
- Make sure Django is installed
- Navigate to root project folder where manage.py is located
- >manage.py runserver
- Then open a browser and type the URL: //http://127.0.0.1:8000/number/
- The project should be up and running

- To test client code to access as server API
- Fire up Eclipse or IDLE and run client.py (locate on project root folder) while the django server is still running

=== Mappings ===

/number/

- Lookup number, list of all cached numbers, instructions to enter valid number, option to delete numbers

/number/{number}/

- Redirects to /number/details/{number}/

/number/details/{number}/

- Detailed view of the number
- Sample page render: 
  Number 7733761651
  Name	Adam Smith
  Last Updated - WP 2011-03-03 17:47:19
  #Correct Reports	0
  #Incorrect Reports	0

- Ability to report accuracy: Correct or Incorrect

/number/notfound/{number}/

- When a number is not found in cache or in WP, prompt is rendered in this URI

/number/incorrect/{number}/

- When a enetered number is invalid, prompt & number validity instructions are rendered in this URI

/delete/
/delete/{number}/

- Login credentials
- List of cached numbers
- Option to delete each indivial number or all numbers
- Incorrect credential prompts error msg and allows user to reenter credentials
- Successful deletion displays success msg with option to delete more numbers or lookup new number

/api/

- Registration details for new API key
- username, full name, email and phone
- Then generates a random numerical api key that can be used to connect to the api
- Sample output:
  Your API Key
  Store in a safe place.
  api key: 56467608

=== USING THE API ===

- Connection: {address}/api/{number}_{apiKey}/
- Returns:
  - Found -- Name, Number
  - Error -- Not found
  - Error -- Incorrect Number

- Sample client-side code to access API found at project/client.py`
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

- Sample outputs
- FOUND
	Please enter the number: 7733761651
	<?xml version="1.0" Name="WS Project 2C API"?> 
 		<name value = "Adam Smith"></name> 
		<number value = "7733761651"></number>
- NOT FOUND
	Please enter the number: 5636636950
	<?xml version="1.0" Name="WS Project 2C API"?> 
		 <error value = "Number not found"></error>
- INCORRECT NUMBER
	Please enter the number: 112344555
	<?xml version="1.0" Name="WS Project 2C API"?> 
 		<error value = "Incorrect Number"></error>

=== TEMPLATES ===

/api/api_key.html
- Renders new API key after client registers


/api/signup.html
- Client registration page to obtain new api key

/delete/delete.html
- Authentication
- List of cached number to delete
- Delete all

/delete/login.html
- Failed authentication prompt

/delete/success.html
- Successful deletion prompt
- Option to lookup more numbers, or delete more

/number/index.html
- Home
- New lookup option
- Cached results
- Number validity instructions
- Option to goto delete section

/number/detail.html
- Deteil view of number entry with name, last update cache timestamp, correct/incorrect count
- Option to mark correct/incorrect
- Option to lookup new number

/number/incorrect.html
- Error msg when incorrect number is entered
- Instruction to enter number correctly
- Option to look up new number

/number/notFound.html
- Error msg when number not found, or entry does not have a name listed or is a business number
- Option to look up a new number

/number/style.css
- CSS Style tags for all templates

=== Known Glitches ===

/ very important at the end or ALL URIs