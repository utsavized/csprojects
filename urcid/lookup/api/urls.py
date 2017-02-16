from django.conf.urls.defaults import *

#Listener for root/api/ URIs

urlpatterns = patterns('api',
    (r'^(?P<id>\w+)/','views.authenticate'),
    (r'^','views.index'),
)