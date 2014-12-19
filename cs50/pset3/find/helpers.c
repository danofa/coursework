/**
 * helpers.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Helper functions for Problem Set 3.
 */
       
#include <cs50.h>
#include <stdio.h>
#include "helpers.h"

/**
 * Returns true if value is in array of n values, else false.
 */
bool search(int value, int values[], int n)
{
/*    // TODO: implement a searching algorithm
    for(int i = 0; i < n; i++)
    {
        if(values[i] == value) return true;
    }
    return false; */

    int l = 0, r = n - 1; // define left and right indicies

    int itercount = 0;

    while(l < r)
    {
        itercount++;
        
        int m = ((r - l) / 2) + l;
        printf("m:%d, l:%d, r:%d \n -- lv:%d, rv:%d, mv:%d \n", m, l, r, values[l], values[r], values[m]);
        
        if(values[m] == value || values[l] == value || values[r] == value)
        {
            printf("loop count: %d\n", itercount);
            return true;
        }
        
        if(value < values[m])
        {
            printf(" (s) ");
            r = m - 1;
        }
        else if(value > values[m])
        {
            printf(" (b) ");
            l = m + 1;
        }
        

        if(value < values[l] || value > values[r])
        {   
            printf("loop count: %d\n", itercount);        
            printf("extreme value case \n");
            return false;
        }  
    }
    
    printf("loop count: %d\n", itercount);
    return false;
}

/**
 * Sorts array of n values.
 */
void sort(int values[], int n)
{
    // TODO: implement an O(n^2) sorting algorithm
    int sortedIndex = 0;

    while(sortedIndex < n)
    {
        int smallestValIndex = sortedIndex;
        
        for(int i = sortedIndex; i < n; i++)
        {
            if(values[i] < values[smallestValIndex])
            {
                smallestValIndex = i;
            }
        }         
        int t = values[sortedIndex];
        values[sortedIndex] = values[smallestValIndex];
        values[smallestValIndex] = t;
        sortedIndex++;
    }
    
    //printArray(values, n);
    
    return;
}

void printArray(int values[], int n){
    printf("Sorted values ------");
    for(int i = 0; i < n; i++){
        printf(" %d ", values[i]);
    }
    printf("Sorted values ------");
    return;
}
