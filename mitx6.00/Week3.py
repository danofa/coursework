#week 3 definitions
def iterPower(base, exp):
    '''
    base: int or float.
    exp: int >= 0
 
    returns: int or float, base^exp
    '''
    result = 1
    while exp > 0:
        result *= base
        exp -= 1
    return result
    

def recurPower(base, exp):
    '''
    base: int or float.
    exp: int >= 0
 
    returns: int or float, base^exp
    '''
    if exp > 0:
        return base * recurPower(base, exp-1)
    else: 
        return 1
        
        
def isIn(char, aStr):
    '''
    char: a single character
    aStr: an alphabetized string
    
    returns: True if char is in aStr; False otherwise
    '''
    midCharIndex = len(aStr) / 2
    if aStr == '':
        return False
    if len(aStr) == 1:
        return aStr == char

    if char == aStr[midCharIndex]:
        return True
    elif char > aStr[midCharIndex]:
        return isIn(char, aStr[midCharIndex+1:])
    else:
        return isIn(char, aStr[0:midCharIndex])
        
def semordnilap(str1, str2):
    '''
    str1: a string
    str2: a string
    
    returns: True if str1 and str2 are semordnilap;
             False otherwise.
    '''
    if len(str1) != len(str2):
        return False
    elif len(str1) == 1:
        return str1 == str2
    
    if str1[0] == str2[-1]:
        return semordnilap(str1[1:], str2[:-1])
    else:
        return False
        
        
        
        