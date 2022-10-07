import requests
import datetime

GENDER = "male"
WEIGHT = 72
HEIGHT = 190
AGE = 20

APP_ID = "YOUR_APP_ID"
API_KEY = "YOUR_API_KEY"
TOKEN = "YOUR_API_TOKEN"

BEARER_HEADERS = {
    "Authorization": f"Bearer {TOKEN}"
}

HEADERS = {
    "x-app-id": APP_ID,
    "x-app-key": API_KEY
}

NUTRITIONIX_END_POINT = "https://trackapi.nutritionix.com/v2/natural/exercise"
SHEETY_POST_END_POINT = "https://api.sheety.co/f354f5c5fff15ca46620da9e3e89f0ff/myDailyWorkouts/workouts"

# get user exercise data
exercise_text = input("Tell what exercise have you done today ! ")

today_date = datetime.datetime.now().strftime("%d/%m/%Y")
current_time = datetime.datetime.now().strftime("%X")

NUTRITIONIX_PARAMS = {
    "query": exercise_text,
    "gender": GENDER,
    "weight_kg": WEIGHT,
    "height_cm": HEIGHT,
    "age": AGE
}

response = requests.post(
    url=NUTRITIONIX_END_POINT,
    json=NUTRITIONIX_PARAMS,
    headers=HEADERS)

exercise_data = response.json()["exercises"]

for exercise in exercise_data:
    SHEETY_INPUTS = {
        "workout": {
            "date": today_date,
            "time": current_time,
            "exercise": exercise["name"],
            "duration": exercise["duration_min"],
            "calories": exercise["nf_calories"]
        }

    }

    sheet_response = requests.post(
        url=SHEETY_POST_END_POINT,
        json=SHEETY_INPUTS,
        headers=BEARER_HEADERS
    )

    print(sheet_response.text)
