balance = 999999
annualInterestRate = 0.18

def pay(b, a, m):
    '''
    b: total balance to be paid
    a: amount to be paid monthly 
    m: monthly interest rate
    returns remaining balance based on monthly payments
    '''
    for i in range(0, 12):
        b = b - a
        b = b * (m + 1)
    return b

mir = annualInterestRate / 12
eps = 1.0
l = balance / 12
h = pay(balance,0,mir) / 12
monthlyPayment = ((h-l) / 2) + l

while abs(pay(balance, monthlyPayment, mir)) > eps:
    if pay(balance, monthlyPayment, mir) > 0:
        l = monthlyPayment
    else:
        h = monthlyPayment
    monthlyPayment = ((h-l) / 2) + l

print "Lowest Payment: " + str(round(monthlyPayment,2))
