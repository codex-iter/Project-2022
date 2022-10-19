# Importing given module to this program
import pyqrcode
 
# import all from tkinter
# to this program
from tkinter import *
 
# import messagebox widget
# to this program
from tkinter import messagebox
 
# Define a function for generating
# the QR code for given input text
def generateQR() :
 
    # Get a text from enterTextField
    # entry box using get() method
 
    # String which QR code need to create
    inputString = enterTextField.get()
 
    # Scale value
    scale = enterScaleField.get()
 
    # if scale is not given as input
    # then bydefault scale value
    # set to 5
    if len(scale) :
 
        # Handle Exception
        try :
             
            # type cast string into integer
            scale = int(scale)
 
        # type cast not possible then handle
        # exception and show error message
        except :
             
            # Error meassage will shown on screen
            # using showerror() method of
            # messagebox widget
            messagebox.showerror("Error",
            "Scale should be integer value ex: 1, 2 , 3 ..")
             
            # return back to the main
            return
             
    else :
         
        # set default value of scale
        scale = 5
         
    # if input text field is not empty
    # then we will create QR code
    # otherwise show error message
    if len(inputString) : 
       
        # Generating QR code object
        # using create() class of
        # pyqrcode module
        qrCode = pyqrcode.create(inputString)
         
        # creating the save path for QR code 
        savePath = "C:\\Users\\hp\\OneDrive\\Desktop\\" + inputString + "_"  + str(scale)
         
        # Save this qrCode object as a svg file  
        # at given location with scale 4 
        # using svg() method of qrCode object.
        # Scale attribute determine the dimension
        # of svg image created.
        qrCode.svg(savePath + ".svg",
                    scale = scale)
 
        # Save this qrCode object as a png file  
        # at given location with scale 4 
        # using png() method of qrCode object.
 
        # For using this png method,
        # you must install pypng module first
        qrCode.png(savePath + ".png",
                    scale = scale)
 
        # Success meassage will shown on screen
        # using showinfo() method of
        # messagebox widget
        messagebox.showinfo('Success',
        "Your QR code is generated and save at this path :"
        + savePath + ".png/.svg")
 
         
    # Error meassage will shown if input
    # text field is empty
    else :
         
        # Error meassage will shown on screen
        # using showerror() method of
        # messagebox widget
        messagebox.showerror("Error", "Text field is Empty")
 
# Define a function for clearing the 
# contents of text entry box
def clearAll() :
 
    # Clear the content of entry
    # box using delete() method
    enterTextField.delete(0, END)  
    
    # set focus on the given entry
    # box using focus_set() method
    enterTextField.focus_set() 
 
# Main code
if __name__ == "__main__" :
    
    # Create a window container
    window = Tk()
    
    # Set background colour of window 
    # container using configure() method 
    # with background attribute
    window.configure(background = 'light green')
    
    # Set the configuration of window
    # container using geometry() method
    # width X length
    window.geometry("600x100")
 
    # Set the title of window container
    # using title() method
    window.title("QR CODE GENERATOR")
 
    # Create a label using Label() widget
    enterTextLabel = Label(window, text = "Enter Text",
                   fg = 'black', bg = 'grey')
 
    # Placing the widgets at respective
    # positions in table like structure
    # using grid() method
 
    # Place this widget in grid at (2, 1)
    enterTextLabel.grid(row = 2, column = 1,
                        sticky ="E", padx = "10",
                        pady = "10")
     
    # Create a label for : Enter Scale
    enterScaleLabel = Label(window, text = "Enter Scale",
                   fg = 'black', bg = 'grey')
 
    # Place this widget in grid at (2, 4)
    enterScaleLabel.grid(row = 2, column = 4,
                        sticky ="E", padx = "10",
                        pady = "10")
 
    # Create a text entry box 
    # for filling or typing the information
    # using Entry() widget
    enterTextField = Entry(window)
 
    # Place this widget in grid at (2, 2)
    enterTextField.grid(row = 2, column = 2, sticky ="E",
                        ipadx ="60", pady = "10")
 
    # Create text entry box for : Enter Scale
    enterScaleField = Entry(window)
 
    # Place this widget in grid at (2, 5)
    enterScaleField.grid(row = 2, column = 5, sticky ="E",
                        pady = "10")
 
 
    # Create a Button and attached 
    # to  function using Button() widget
    generateButton = Button(window, text = "Generate",
                    bg = "red", fg = "black",
                    command = generateQR)
    
    # Create a Clear Button and attached 
    # to clear_all function 
    clearButton = Button(window, text = "Clear",
                  bg = "red", fg = "black",
                  command = clearAll)
  
    # Place button widget in grid at (3, 3)
    generateButton.grid(row = 3, column = 3)
 
    # Place button widget in grid at (4, 3)
    clearButton.grid(row = 4, column = 3, pady = "5")
  
 
    # Start the window,
    # waiting for events and
    # updating the GUI. 
    window.mainloop()