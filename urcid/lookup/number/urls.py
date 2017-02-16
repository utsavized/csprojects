from django.conf.urls.defaults import *

#URL Pattern listeners for all root/number/... URIs

urlpatterns = patterns('number',
    (r'^details/(?P<id>\w+)/','views.detail'),
    (r'^incorrect/(?P<id>\w+)/','views.incorrect'),
    (r'^notFound/(?P<id>\w+)/','views.notFound'),
    (r'^(?P<id>\w+)/','views.look'),
    (r'^delete/(?P<id>\w+)/','views.delete'),
    (r'^$','views.index'),
)