/**
 * recover.c
 *
 * Computer Science 50
 * Problem Set 5
 *
 * Recovers JPEGs from a forensic image.
 */
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[])
{

    const char jpegsig1[] = {0xff,0xd8,0xff,0xe0};
    const char jpegsig2[] = {0xff,0xd8,0xff,0xe1};

    int filecount;
    char * buffer;
    char filename[10];

    // attempt to open input file    
    FILE * infile = fopen("card.raw", "r");

    if(infile == NULL)
    {
        printf("Error opening input file 'card.raw' \n");
        return 1;
    }

    
    filecount = 0;
    buffer = (char*) malloc(512);
 
    FILE * outfile = NULL;    

    while(fread(buffer, 1, 512, infile))
    {
        if(feof(infile))
            break;
        
        if(memcmp(buffer, jpegsig1, sizeof(jpegsig1)) == 0 || memcmp(buffer, jpegsig1, sizeof(jpegsig2)) == 0)
        {
            if(outfile != NULL)
                fclose(outfile);
                
            sprintf(filename, "%03d.jpg", filecount);
            outfile = fopen(filename, "w");
  
            if(outfile == NULL)
            {
                printf("Error opening output file '%s' \n", filename);
                return 2;
            }

            filecount++;
        }

        if(outfile != NULL)
        {
            fwrite(buffer, 512, 1, outfile);
        }
    }   
    
    
    fclose(outfile);
    fclose(infile);            
 
    free(buffer);   
    
    return 0;    
}
