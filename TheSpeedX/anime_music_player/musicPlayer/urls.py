"""musicPlayer URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url, include
from animeMusicPlayer import views as animeViews

urlpatterns = [
    url(r"^signUp/$", animeViews.SignUpPage.as_view(), name="signup"),
    url(r"^(?P<slug>[-\w\d]+)$", animeViews.HomePage.as_view(), name="homePage"),
    url(r"^login/$", animeViews.LoginPage.as_view(), name="login"),
    url(r"^ajax/get_track_name/$", animeViews.get_track_name, name='getTrackName'),
    path('admin/', admin.site.urls),
]
