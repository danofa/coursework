/****************************************************************************
 * dictionary.c
 *
 * Computer Science 50
 * Problem Set 6
 *
 * Implements a dictionary's functionality.
 ***************************************************************************/

#include "dictionary.h"

    node* rootnode;
    int wordcount = 0;
    int nodeid = 0;

/**
 * Returns true if word is in dictionary else false.
 */
bool check(const char* word)
{
	node* curr = rootnode;
	int len = strlen(word);
    for(int i = 0; i < len; i++)
    {
    	node* next = findChild(curr, tolower(word[i]));
		
		if(next == NULL)
			return false;
		else
			curr = next;    	

    	// hit end of string and it is a word in dictionary.
    	if(i == len -1 && isWord(curr))
    	{
    		return true;
    	}	
    	
    }
    
    return false;
}

/**
 * Loads dictionary into memory.  Returns true if successful else false.
 */
bool load(const char* dictionary)
{
	rootnode = createNode('_');

    FILE* dic = fopen(dictionary, "r");
    if(dic == NULL)
    {
        printf("failed to open dictionary file");
        return false;
    }
    
	char str[LENGTH+1];
	
	while(fgets (str, LENGTH+1, dic) != NULL ) {
		addWord(str);	
   	}
	
//	dictionaryTraverser(rootnode, wordcount);
	
    fclose(dic);
    return true;
}

/**
 * Returns number of words in dictionary if loaded else 0 if not yet loaded.
 */
unsigned int size(void)
{
    return wordcount;
}

/**
 * Unloads dictionary from memory.  Returns true if successful else false.
 */
bool unload(void)
{
	node* curr = rootnode;
	node* prev;

	while(true)
	{
		if(curr->c == '_' && !hasChildren(curr))
		{
			free(curr);
			break;
		}
			
		if(hasChildren(curr))
		{
			curr = curr->child;
//			printf("traversing child from [%d] \n", curr->id);
			continue;
		}
		else if(curr->next != 0)
		{
			curr = curr->next;	
//			printf("traversing sibling from [%d] \n", curr->id);
			continue;
		}
		else if(curr->next == 0 && !hasChildren(curr))
		{
//			printf("remove node, no children or sibling [%d] \n", curr->id);

			prev = curr->prev;
			
			if(prev->child != 0 && curr == prev->child)
				prev->child = 0;
				
			else if(prev->next != 0 && curr == prev->next)
				prev->next = 0;
					
			free(curr);
			curr = prev;
		}
	}	


    wordcount = 0;
    return true;
}

// *********************

/**
    adds new word to dictionary data structure;
*/

void addWord(char* w)
{
	if(w[0] == '\n' || w[0] == '\r')
		return;
			
	wordcount++;	
	
	//printf("Adding word[%d]: %s \n", wordcount, w);

	node* curr = rootnode;
	 	
	for(int i = 0; i < strlen(w); i++)
	{
		
		if(w[i] == '\n' || w[i] == '\0')
			continue;
			
		node* next = findChild(curr, w[i]);
		if(next == NULL)
		{
			next = createNode(w[i]);
			addChild(curr, next);
		}
		curr = next;
	}
	curr->isEndofWord = true;
}

void addChild(struct node* p, struct node* n)
{
	if(p->child == 0)
	{
		p->child = n;
		n->prev = p;
		return;
	}
	else
	{
		node *curr = p->child;
		 
		while(curr->next != 0)
		{
			curr = curr->next;
		}
	
		n->prev = curr;			
		curr->next = n;
		
	}	
}

struct node* createNode(char c)
{
	node* newnode = malloc(sizeof(*newnode));
	
	if(newnode == NULL)
	{
		printf("Unable to allocate memory, exiting.. \n");
		exit(1);
	}
	
	newnode->c = c;
	newnode->prev = 0;
	newnode->next = 0;
	newnode->child = 0;
	newnode->isEndofWord = false;
	newnode->id = nodeid;
	nodeid++;
	
	return newnode;
}

/**
    checks children for EndofWord.
*/
bool isWord(node* n)
{
	return n->isEndofWord;
}

bool hasChildren(node * n)
{
	if(n->child != 0)
		return true;
	else
		return false;
}


/**
    finds an immediate child node of p 
    with value c;
*/

struct node* findChild(struct node* p, char c)
{
	if(p->child == 0)
	{
		return NULL;
	}
	
	node* curr = p->child;
			
	while(curr != 0)
	{
		if(curr->c == c)
		{
			return curr;
		}
		else
			curr = curr->next;
	}
	
    return NULL;
}

//eof
