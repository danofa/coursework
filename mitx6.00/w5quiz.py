#quiz week 5

#problem 4

def myLog(x, b):
    '''
    x: a positive integer
    b: a positive integer; b >= 2

    returns: log_b(x), or, the logarithm of x relative to a base b.
    '''
    results = []
    for i in range(0, x):
        if b ** i <= x:
            results.append(i)
            
    return results[-1]
    
    
def laceStrings(s1, s2):
    """
    s1 and s2 are strings.

    Returns a new str with elements of s1 and s2 interlaced,
    beginning with s1. If strings are not of same length, 
    then the extra elements should appear at the end.
    """
    newString = ""
    for i in range(0, max(len(s1), len(s2))):
        if len(s1) > i:
            newString += s1[i]
        
        if len(s2) > i:
            newString += s2[i]

    return newString
    
def laceStringsRecur(s1, s2):
    """
    s1 and s2 are strings.

    Returns a new str with elements of s1 and s2 interlaced,
    beginning with s1. If strings are not of same length, 
    then the extra elements should appear at the end.
    """
    def helpLaceStrings(s1, s2, out):
        if s1 == '':
            return out + s2
        if s2 == '':
            return out + s1
        else:
            return helpLaceStrings(s1[1:], s2[1:], out + s1[0] + s2[0])
            
    return helpLaceStrings(s1, s2, '')

# problem 7

def McNuggets(n):
    """
    n is an int

    Returns True if some integer combination of 6, 9 and 20 equals n
    Otherwise returns False.
    """
    # return straight away if we are lucky to find an exact match
    if n % 6 == 0 or n % 9 == 0 or n % 20 == 0:
        return True
    
    # get maximums for each pack in n
    max6 = n / 6
    max9 = n / 9
    max20 = n / 20

    # loop through all possibilities until a match is found
    for c in range(max20+1):
        for b in range(max9+1):
            for a in range(max6+1):
                if (a*6) + (b*9) + (c*20) == n:
                    return True

    # no match found
    return False



#  problem 8   
            
def fixedPoint(f, epsilon):
    """
    f: a function of one argument that returns a float
    epsilon: a small float
  
    returns the best guess when that guess is less than epsilon 
    away from f(guess) or after 100 trials, whichever comes first.
    """
    guess = 1.0
    for i in range(100):
        if f(guess) - guess < epsilon:
            return guess
        else:
            guess = f(guess)
    return guess
    
    
def f(number):
    return 1.1