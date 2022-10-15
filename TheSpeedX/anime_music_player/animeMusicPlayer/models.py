from django.db import models
from django.urls import reverse

# Create your models here.
class User(models.Model):
    username = models.CharField(max_length=256, blank=False, null=False)
    email = models.EmailField(blank=False, null=False)
    password = models.CharField(max_length=256, blank=False, null=False)
    confirmPassword = models.CharField(max_length=256, blank=False, null=False)

    def __str__(self):
        return self.username

    def get_absolute_url(self):
        return reverse('homePage', kwargs={'slug': self.username})

class Trackdetail(models.Model):
    songId = models.AutoField(primary_key=True)
    trackName = models.CharField(max_length=64, blank=False, null=False, unique=True)
    trackPath = models.CharField(max_length=256, unique=True)
    artworkPath = models.CharField(max_length=256, unique=True)
    artistName = models.CharField(max_length=64, blank=False, null=False)

class Songdetail(models.Model):
    songId = models.AutoField(primary_key=True)
    songName = models.CharField(max_length=64, blank=False, null=False, unique=True)
    songPath = models.CharField(max_length=256, null=True, unique=True)
    artworkPath = models.CharField(max_length=256, null=True, unique=True)
    artistName = models.CharField(max_length=64, blank=False, null=False)


class UserRewardGroupMap(models.Model):
    reward_user = models.ForeignKey(User, on_delete=models.CASCADE, db_column='column_name')