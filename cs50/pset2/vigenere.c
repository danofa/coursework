#include <stdio.h>
#include <cs50.h>
#include <string.h>
#include <ctype.h>

bool isabet(string str);

int main(int argc, string argv[])
{
    if (argc != 2 || !isabet(argv[1]))
    {   
        printf("Please enter a single alphabetical keyword \n");
        return 1;
    }
    
    string key = argv[1];
    string s = GetString();
    
    for (int i = 0, k = 0, n = strlen(s); i < n; i++)
    {   
        if (isalpha(s[i]))
        {
            int kmod = tolower(key[k % strlen(key)]) - 'a';
            if (isupper(s[i]))
            {
                printf("%c", (((s[i] - 'A') + kmod) % 26) + 'A');
            }
            else if (islower(s[i]))
            {
                printf("%c", (((s[i] - 'a') + kmod) % 26) + 'a');
            }
            k++;
        } 
        else 
        {
            printf("%c", s[i]);
        }

    }
    printf("\n");
    return 0;
}


bool isabet(string str)
{
    for (int i = 0; i < strlen(str); i++)
    {
        if (!isalpha(str[i]))
        {
            return false; 
        }
    }
    return true;
}

