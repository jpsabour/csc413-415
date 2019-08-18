#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
//You may also add more include directives as well.

#define name "John Sabour"  //store name for future use

int
main(int argc, char const *argv[])
{
    char buffer[100]; //array storage
    sprintf(buffer, "CSC 415, This program has been written by %s!\n", name); //specified string to be printed
    write(1, buffer, 54);//the whole string is 54 characters so need ot allocate that many bytes
	return 0;
}