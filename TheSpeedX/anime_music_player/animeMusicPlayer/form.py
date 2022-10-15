from django import forms
from . import models
import bcrypt


class UserForm(forms.ModelForm):
    class Meta:
        model = models.User
        fields = ['username', 'email', 'password', 'confirmPassword']
        widgets = {
            'password': forms.PasswordInput(),
            'confirmPassword': forms.PasswordInput(),
        }

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['username'].label = 'Profile Name'
        self.fields['email'].label = 'Email Address'

        self.fields['username'].widget.attrs.update({'class': 'formInput'})
        self.fields['email'].widget.attrs.update({'class': 'formInput'})
        self.fields['password'].widget.attrs.update({'class': 'formInput'})
        self.fields['confirmPassword'].widget.attrs.update({'class': 'formInput'})


    def clean(self):
        cleaned_data = super(UserForm, self).clean()
        password = cleaned_data.get("password")
        confirmPassword = cleaned_data.get("confirmPassword")
        email = cleaned_data.get("email")

        if password != confirmPassword:
            raise forms.ValidationError(
                "password and confirm password doesn't match."
            )
        if len(password) < 5:
            raise forms.ValidationError(
                "your password is very short."
            )

        return cleaned_data
