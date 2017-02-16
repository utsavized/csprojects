from django.conf.urls.defaults import *

#URL Pattern listeners for all root/number/... URIs

urlpatterns = patterns('classroomclicker',
    (r'^clicker/','views.messaging'),
    (r'^$','views.index'),
)