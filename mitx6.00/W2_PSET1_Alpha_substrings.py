s = 'abcdefghijklmnopqrstuvwxyz'
current = ""
longest = ""

for x in s:
    if len(current) != 0 and x >= current[-1]:
        current += x
    else:
        current = x

    if len(current) > len(longest):
        longest = current
        
print "Longest substring in alphabetical order is: " + longest