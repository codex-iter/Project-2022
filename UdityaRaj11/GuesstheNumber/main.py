import random
print("Please, Enter your name:- ")
user_name = str(input())
print("Hello ", user_name, ", Welcome to the Guess The Number Game.")
start = True
while start:
    secretnum = random.randint(1, 20)
    print('''So, Are you ready to Guess the Number:- 
                Press Y to start
                Press Q to exit''')
    initial = str(input())

    if initial.upper() == "Y":
        for totalguesses in range(1, 6):
            print("Take a guess:- ")
            guess = int(input())
            if guess < secretnum:
                print("Your Guess is too Low.")
            elif guess > secretnum:
                print("Your Guess is too High.")
            else:
                break
        if guess == secretnum:
            print("Well Done, ", user_name, "You took a total of ", totalguesses, " tries to guess the number.")
        else:
            print("Nope the number i was thinking about was ", secretnum)
    elif initial.upper() == "Q":
        print("Will be waiting, Come back soon!!")
        start = False
    else:
        print("Inappropriate response, Please try again:- ")








