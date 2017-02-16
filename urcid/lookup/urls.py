from django.conf.urls.defaults import *

#Mappings for root URLs

urlpatterns = patterns('',
    (r'^api/', include('lookup.api.urls')),
    (r'^', include('lookup.users.urls')),
    (r'^users/(?P<username>\w+)/number/', include('lookup.number.urls')),
    (r'^users/(?P<username>\w+)/delete/', include('lookup.delete.urls')),
)