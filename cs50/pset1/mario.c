#include <stdio.h>
#include <cs50.h>

int main(void)
{
    int height;
    
    do
    {
        printf("Height: ");
        height = GetInt();
    } 
    while(height < 0 || height > 23);
    
    for(int i = 0; i < height; i++)
    {
        for(int y = 0; y <= height - i - 2; y++)
        {
            printf(" ");
        }
        
        for(int x = 0; x < i + 2; x++)
        {
            printf("#");
        }
        printf("\n");
    }
}
