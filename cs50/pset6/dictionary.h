/****************************************************************************
 * dictionary.h
 *
 * Computer Science 50
 * Problem Set 6
 *
 * Declares a dictionary's functionality.
 ***************************************************************************/

#ifndef DICTIONARY_H
#define DICTIONARY_H

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// maximum length for a word
// (e.g., pneumonoultramicroscopicsilicovolcanoconiosis)
#define LENGTH 45

/**
 * Returns true if word is in dictionary else false.
 */
bool check(const char* word);

/**
 * Loads dictionary into memory.  Returns true if successful else false.
 */
bool load(const char* dictionary);

/**
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */
unsigned int size(void);

/**
 * Unloads dictionary from memory.  Returns true if successful else false.
 */
bool unload(void);


/************************************************************/

typedef struct node
{
    char c;
    struct node* prev;
    struct node* next;
    struct node* child;
    int id;
    bool isEndofWord;
}
node;

// creates and returns pointer to new node with default values and char c
struct node* createNode(char c);

// adds chil
void addChild(struct node* p, struct node* n);

bool hasChildren(node * n);

//   Adds node n to parent node p in lexigraphical order
struct node* addNode(struct node* p, char c);

//   adds new word to dictionary data structure;
void addWord(char* w);

//  checks children nodes of n for EndofWord.
bool isWord(struct node* n);

//  finds an immediate child node of p with value c;
struct node* findChild(struct node* p, char c);

#endif // DICTIONARY_H
