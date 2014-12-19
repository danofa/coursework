#include <stdio.h>
#include <cs50.h>
#include <string.h>
#include <ctype.h>

int main(int argc, string argv[])
{
    if (argc != 2 || atoi(argv[1]) <= 0)
    {   
        printf("Please enter a single, non negative value \n");
        return 1;
    }
    
    int r = atoi(argv[1]);
    string s = GetString();
    
    // shift rotation amount back into alphabet range if outside
    r = r % 26;

    for(int i = 0, n = strlen(s); i < n; i++)
    {   
        if (isalpha(s[i]))
        {
            if (isupper(s[i]))
            {
                printf("%c", (((s[i] - 'A') + r) % 26) + 'A');
            }
            else if (islower(s[i]))
            {
                printf("%c", (((s[i] - 'a') + r) % 26) + 'a');
            }
        } 
        else 
        {
            printf("%c", s[i]);
        }

    }
    printf("\n");
    return 0;
}

