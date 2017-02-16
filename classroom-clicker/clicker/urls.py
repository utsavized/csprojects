from django.conf.urls.defaults import *

#Mappings for root URLs

urlpatterns = patterns('',
    (r'^', include('clicker.classroomclicker.urls')),
)