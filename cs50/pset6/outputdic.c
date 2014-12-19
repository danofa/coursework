#include "dictionary.h"

void printChildren(node * p)
{
	printf("children of %c[%d]: ", p->c, p->id);
	node* curr = p->child;
	while(curr != 0)
	{
			printf("%c[%d], ", curr->c, curr->id);
			curr = curr->next;
	}
	printf("\n");
}

void printSiblings(node * p)
{
	printf("Siblings of %c[%d]: ", p->c, p->id);
	node* curr = p->next;
	while(curr != 0)
	{
			printf("%c[%d], ", curr->c, curr->id);
			curr = curr->next;
	}
	printf("\n");
}

void dictionaryTraverser(node * root, int count)
{
	printf("\n------------------- %d dictionary words loaded into memory \n", count);
	
	node* curr = root;
	
	while(true)
	{
		int val;
		
		printChildren(curr);
		printSiblings(curr);
		printf("curr node is: %c %d [%d] \n", curr->c, curr->isEndofWord, curr->id);
		printf("1: next sibling node, 2: next child node, 3: return root \n");
		scanf("%d", &val);
		switch(val){
			case 1:
				if(curr->next == 0){
					printf(" invalid ");
					break; }
				curr = curr->next;
				break;
			case 2:
				if(curr->child == 0){
					printf(" invalid ");
					break; }
				curr = curr->child;
				break;
			case 3:
				curr = root->child;
				break;
		}
	}
		
	
	
	printf("\n- done -------------------\n");
}
