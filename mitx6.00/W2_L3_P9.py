high = 100
low = 0
guess = 0

print "Please think of number between 0 and 100!"

while True:
    guess = ((high - low) / 2) + low
    
    print "Is your secret number " + str(guess) + "?"
    r = raw_input("Enter 'h' to indicate the guess is too high. "+
                "Enter 'l' to indicate the guess is too low. "+
                "Enter 'c' to indicate I guessed correctly. ")

    if(r not in "hlc"):
        print "Sorry, I did not understand your input."
    elif r == "h":
         high = guess
    elif r == "l":
        low = guess        
    elif r == "c":
        print "Game over. Your secret number was: " + str(guess)
        break