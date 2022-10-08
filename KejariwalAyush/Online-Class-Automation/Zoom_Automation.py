from tkinter import *
import keyboard
import time
import subprocess
import pandas as pd
from datetime import datetime
import pyautogui

# Automate funtion where every thing is done


def automate():
    print('Automation Started')
    # reading the meeting details from csv file
    df = pd.read_csv('meetingschedule.csv')
    df_new = pd.DataFrame()

    # reading zoom path details
    pt = open('path.txt', 'r+')
    pth = pt.readline()
    pth = pth.replace('/', '\\\\')

    while(True):
        # convert current time system to text
        timestr = datetime.now().strftime("%H:%M")

        # Check if the current time is mentioned in the Dataframe
        if timestr in df.Time.values:
            df_new = df[df['Time'].astype(str).str.contains(timestr)]

            # Open the Zoom app with the path provided
            subprocess.Popen(pth)
            time.sleep(5)
            # Locate the position of the join button on the screen
            position = pyautogui.locateOnScreen("buttons\\join_button_1.png")
            print(position)
            # Move the cursor to the position of the button
            pyautogui.moveTo(position)
            # Perform click operation
            pyautogui.click()
            # Waitng for above process to complete
            time.sleep(5)

            # Write the meeting ID from the dataframe onto the Zoom App
            keyboard.write(str(df_new.iloc[0, 1]))
            time.sleep(5)

            # For tapping the Turn off audio option on Zoom app
            position = pyautogui.locateOnScreen("buttons\\capture.png")
            pyautogui.moveTo(position)
            pyautogui.click()
            time.sleep(5)

            # For tapping the Turn off video option on Zoom app
            position = pyautogui.locateOnScreen("buttons\\capture.png")
            pyautogui.moveTo(position)
            pyautogui.click()
            time.sleep(5)

            # For tapping on the Join button
            position = pyautogui.locateOnScreen("buttons\\join_button_2.png")
            pyautogui.moveTo(position)
            pyautogui.click()
            time.sleep(15)

            # Reads the Meeting Passcode from the dataframe and enters into the zoom app
            keyboard.write(str(df_new.iloc[0, 2]))
            time.sleep(5)

            # For finally joining the meeting
            position = pyautogui.locateOnScreen("buttons\\join_meeting.png")
            pyautogui.moveTo(position)
            pyautogui.click()

            # Wait for two minute before the next iteration starts
            time.sleep(120)


# for importing data from meetingschedule.csv file
df = pd.read_csv('meetingschedule.csv')
df_new = pd.DataFrame(df)

# for extracting the path of zoom app from path.txt
pt = open('path.txt', 'r+')
pth = pt.readline()
pth = pth.replace('/', '\\\\')

# making a screen with title and size (w x h)
root = Tk()
root.title('Online Classes Automation')
root.geometry('525x700')


# we cant use exit method directly so we use this function to end the program
def end():
    root.destroy()


# Creating the Menu Bar
menu = Menu(root)
# Adding Exit button on Menubar
menu.add_cascade(label='Exit', command=end)
root.config(menu=menu)

# Display important info
aut = 'As you start the automation \nEvery thing will go under a process.\nSo dont press any button just minimize it'
lb2 = Label(root, text=aut, fg='red')
lb2.grid(column=0, row=0)

# Display zoom app Path
lb0 = Label(root, text='Your Currnt Path for ZOOM app is :\n'+pth)
lb0.grid(column=0, row=3)

# Display Meeting Data to User
lb = Label(root, text=df.head())
lb.grid(column=0, row=1)

# leaving away a line
l = Label(
    root, text='', font='50')
l.grid(column=0, row=5)

# display info on how to add path
lb3 = Label(
    root, text='Add Zoom App Path in "path.txt" file. \n\n'
    'To get path right click on ZOOM\nclick on "Open File Location"\n'
    'Copy Path from above and past it in "path.txt file"\n', fg='blue', font='40')
lb3.grid(column=0, row=6)

# display info on adding meeting details
lb3 = Label(
    root, text='Add Meeting Details in "meetingsschedule.csv" file.', font='50')
lb3.grid(column=0, row=7)

lb3 = Label(
    root, text='Add time in format: hh:mm,\n Ex. - 13:00 for 1 PM', font='40')
lb3.grid(column=0, row=8)

lb4 = Label(root, text='Keep All this Files together to run the app\n- meetingschedule.csv\n- path.txt\n- Automation.exe\n- buttons(folder)')
lb4.grid(column=0, row=9)
# function which gets called when you click to start automation


def clicked():
    button.configure(text='Stop Automation', command=end)
    automate()


# button to allow user to run the automation
button = Button(root, text='Run Automation',
                command=clicked, background='orange',)
button.grid(column=0, row=2)

# to continue running the appliation until its terminated manually
root.mainloop()


# Developed By Ayush Kejariwal
