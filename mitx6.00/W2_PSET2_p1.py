balance = 4213
annualInterestRate = 0.2
monthlyPaymentRate = 0.04


totalPaid = 0
paid = 0

for m in range(1,13):
    paid = balance * monthlyPaymentRate
    totalPaid += paid
    
    print "Month: " + str(m)
    print "Minimum monthly payment: " + \
        str(round(paid, 2))
    
    balance = balance - paid
    balance += (balance * annualInterestRate) / 12
    print "Remaining balance: " + str(round(balance,2))

print "Total paid: " + str(round(totalPaid,2))
print "Remaining balance: " + str(round(balance,2))