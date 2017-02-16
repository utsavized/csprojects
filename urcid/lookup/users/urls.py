from django.conf.urls.defaults import *

#URL Pattern listeners for all root/number/... URIs

urlpatterns = patterns('users',
       (r'^$','views.login'),
       (r'^sessionExpired/(?P<username>\w+)/','views.expired'),
       (r'^logout/(?P<username>\w+)/','views.logout'),
       #(r'^register/','views.register')
       #(r'^register/number/','views.redirect')
)

#(r'^details/(?P<id>\w+)/','views.detail'),
#    (r'^incorrect/(?P<id>\w+)/','views.incorrect'),
#    (r'^notFound/(?P<id>\w+)/','views.notFound'),
#    (r'^(?P<id>\w+)/','views.look'),
#    (r'^delete/(?P<id>\w+)/','views.delete'),
 