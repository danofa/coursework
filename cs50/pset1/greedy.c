#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    const int quarter = 25;
    const int dime = 10;
    const int nickel = 5;
    const int penny = 1;
    
    float change;
    int coinCount = 0;
    int intChange;
    
    printf("O hai! ");
    
    do
    {
        printf("How much change is owed? \n");
                change = GetFloat() * 100;
    }
    while(change < 0);

    intChange = (int) round(change);
    
    coinCount += intChange / quarter;
    intChange = intChange % quarter;
    
    coinCount += intChange / dime;
    intChange = intChange % dime;

    coinCount += intChange / nickel;
    intChange = intChange % nickel;

    coinCount += intChange / penny;
    intChange = intChange % penny;

    printf("%d\n", coinCount);
}
