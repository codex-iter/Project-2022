import math
import re

import speech_recognition as s_r


def Speechtotext():
    while True:
        try:
            r = s_r.Recognizer()
            my_mic = s_r.Microphone(device_index=1)
            with my_mic as source:
                audio = r.listen(source)
                sentence = r.recognize_google(audio)
                print(sentence)
                return sentence
        except:
            continue

while True:
    try:
        initial = Speechtotext()
        initial1 = initial.upper()
        position = initial1.find("OPEN CALCULATOR")
        if position >= 0:
            print("So..What's Your Name?:- ")
            Name = Speechtotext()
            print("And, what's Your Age?:- ")
            Age = Speechtotext()
            greetbyeve = Name + ''', I'm
                Your Own cool Calculator
                .
                .
                .
                YOU CAN SAY EXIT WHENEVER YOU WANT TO LEAVE.
                '''


            def calculator_mode():
                flag = True
                while flag:
                    print("Say the Equation you want me to solve:- ")
                    equation = Speechtotext()
                    equation2 = re.sub('[" "=]', '', equation)
                    equation = re.sub('[x]', '*', equation2)
                    check_eq = re.sub('[a-zA-Z,.:" "]', '', equation)
                    if equation2.upper() == "EXIT":
                        print("Babye " + Name + " ,See ya!!")
                        break
                    elif equation == check_eq:
                        answer = eval(equation)
                        print(answer, " is your answer.")
                        print("What next?")
                    else:
                        print("Invalid Input")
                        print("Try Again")


            print("Say Hello to  your cool calculator. ")
            greeting = Speechtotext()
            if greeting.upper() == "HELLO":
                print('''
                Hey ''' + greetbyeve)
                calculator_mode()
            elif greeting.upper() == "HII":
                print('''
                Hello ''' + greetbyeve)
                calculator_mode()
            elif greeting.upper() == "HOLA":
                print('''
                    Hola ''' + greetbyeve)
                calculator_mode()
            elif greeting.upper() == "HEY":
                print('''
                       Hello ''' + greetbyeve)
                calculator_mode()
            elif greeting.upper() == "NAMASTE":
                print('''
                           Hey ''' + greetbyeve)
                calculator_mode()
            elif greeting.upper() == "EXIT":
                print("Bubye... Come back soon..")
                break
            else:
                print('''
                Well that's a new kinda Greeting...
                Never Mind..
                Hey ''' + greetbyeve)
                calculator_mode()
            break
        elif initial1 == "EXIT":
            break
        else:
            continue
    except:
        break
