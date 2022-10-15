from xml.etree.ElementInclude import include
from django.shortcuts import render, redirect
from django.views.generic import CreateView
from django.http import HttpResponse, HttpResponseRedirect
from animeMusicPlayer.form import UserForm
from django import forms
from django.views.generic import TemplateView
from django.views import View
import bcrypt
from django.urls import reverse
from animeMusicPlayer.models import User, Songdetail, UserRewardGroupMap
from django.http import JsonResponse
from . import songDownloader
import json
# Create your views here.

class HomePage(TemplateView):

    template_name = "animeMusicPlayer/home/homePage.html"

class SignUpPage(CreateView):
    form_class = UserForm
    template_name = 'animeMusicPlayer/signUp.html'

    def form_valid(self, form):
        obj = form.save(commit=False)

        obj.password = get_hashed_password(obj.password)
        obj.confirmPassword = obj.password
        username = obj.username
        obj.save()
        self.request.session['USERNAME'] = username
        return super().form_valid(form)

class LoginPage(View):
    def get(self, request, *args, **kwargs):
        return render(request, 'animeMusicPlayer/login.html')

    def post(self, request, *args, **kwargs):
        data = request.POST.copy()
        username = data.get('username')
        password = data.get('password')

        get_user = User.objects.all().filter(username__exact=username)
        if len(get_user) == 0:
            error = {'error': "username or password doesn't match."}
            return render(request, 'animeMusicPlayer/login.html', error)

        if check_password(password, get_user[0].password):
            request.session['USERNAME'] = username
            return HttpResponseRedirect(reverse('homePage', kwargs={'slug': username}))
        else:
            error = {'error': "username or password doesn't match."}
            return render(request, 'animeMusicPlayer/login.html', error)


def get_track_name(request):
    if request.method == 'POST':
        trackName = request.POST['trackName']

        if (' ' in trackName) == True:
            trackName = trackName.split(' ')
            underscore = "_"
            for i in range(len(trackName)):
                trackName[i] = trackName[i].capitalize()
            trackName = underscore.join(trackName)
        else:
            trackName = trackName.capitalize()



        get_songName = Songdetail.objects.filter(songName__exact=trackName)
        if len(get_songName) == 0:
            songDownloader.doStuff(trackName)
            newSong = Songdetail(songName=trackName, songPath=f"music/{trackName}.opus", artistName="alan walker")
            newSong.save()
            trackPath = {'trackPath': newSong.songPath}
            print(trackPath)
        else:
            trackPath = {'trackPath': get_songName[0].songPath}
            print(trackPath)

    json_data = json.dumps(trackPath)

    return HttpResponse(json_data, content_type="application/json")

def get_table_name(Model):
    return [f.attname  for f in Model._meta.get_fields(include_hidden=True)]

print("[+] User Table: ", get_table_name(UserRewardGroupMap))

def get_hashed_password(plain_text_passowrd):
    #print(plain_text_passowrd)
    return bcrypt.hashpw(plain_text_passowrd, bcrypt.gensalt())

def check_password(plain_text_passowrd, hashed_password):
    return bcrypt.checkpw(plain_text_passowrd, hashed_password)
