from django.conf.urls.defaults import *

#Listeners for root/delete/ URIs

urlpatterns = patterns('delete',
    (r'^$','views.index'),
    (r'^(?P<id>\w+)/','views.detail'),
)