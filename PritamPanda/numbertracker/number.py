import numbers
import phonenumbers
from phonenumbers import timezone,geocoder,carrier
number=input("Enter your NO with +__: ")
phone=phonenumbers.parse(number)
valid =phonenumbers.is_valid_number(phone)
if valid:
        print('It is Valid')
else:
    print('It is not Valid') 
time=timezone.time_zones_for_number(phone)
car=carrier.name_for_number(phone,'en')
reg=geocoder.description_for_number(phone,'en')
print(phone)
print(f'Your timezone is:',time)
print(f'Your carrier name is:',car)
print(f'Your region is:',reg)