== Project 3: Classroom Clicker README ==

=== Programming Bits ===
Language: Python 2.7, Django 1.2.5 Framework
IDE: Eclipse Helios Service Release 1 /w PyDev Django Plugin
OS: HP Pavilion dv4 (4 GB RAM, 320 GB HDD, 2.2 GHz Intel Dual Code), Windows 7 Ultimate 64-Bit

=== External Dependencies ===
- Django (easy_install django)
- StompClient (easy_install stompclient)
- Apache ActiveMQ 5.5.0

**Dependencies are included in the "packages" folder of this project as well. The packages
can simply be unzipped to C:\{Python}\Lib\site-packages\

=== Project Details ===
RUNNING THE PROJECT

- Register the Stomp Protocol in ActiveMQ
  - Open {ApacheMQ}\conf\activemq.xml
  - Add the following:
    <transportConnectors>
	    <transportConnector name="stomp" uri="stomp://localhost:61613"/>
	</transportConnectors>
  - Remove any other protocol that already exists
- Run ActiveMQ
- Run Django Server (django-admin runserver)

In the browser, type http://127.0.0.1:8000

OPERATING THE CLICKER

- Select channel and clicker and hit submit to register to a classroom.
- Select channel, number of responses and status to Open/Close a classroom.

MULTIPLE CLICKERS/CLASSROOM

To operate multiple clickers or channels, a separate window needs to openened with the address http://127.0.0.1:8000 so that 

the new clicker/channel does not open in the same window of the channel that is already open.