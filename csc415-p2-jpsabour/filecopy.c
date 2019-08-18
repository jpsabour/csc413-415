#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
//You may also add more include directives as well.

// THIS VALUE CANNOT BE CHANGED.
// You should use this value when creating your buffer.
// And also to limit the amount of bytes each read CAN do.
#define BUFF_MAX 13
// ABOVE VALUE CANNOT BE CHANGED //

int
main(int argc, char const *argv[])
{

    char buffer[BUFF_MAX];
    printf("Welcome to the File Copy Program by John Sabour!\n");

    //prompting user for source text file
    printf("Enter the name of the file to copy from:\n");
    //source array
    char copySource[255];
    scanf("%s", copySource);
    //prompt user for destination text file
    printf("Enter the name of the file to copy to:\n");
    //desinationarray
    char copyDestination[255];
    scanf("%s", copyDestination);
    //source file can't be opened error
    int source = open(copySource, O_RDONLY);
    if(source < 0){
        perror("ERROR:Cannot open source file!\n");
        return -1;
    }
    //destination file cant be properly copied to/opened error
    int destination = open(copyDestination, O_WRONLY|O_CREAT);
    if(destination < 0){
        perror("ERROR:File cannot be copied\n");
        return -1;
    }

    ssize_t readByte = 0;
    ssize_t writeByte = 0;
    int copyByte = 0;
    do{
        readByte = read(source, buffer, BUFF_MAX); //max is 13
        writeByte = write(destination, buffer, readByte);
        copyByte += writeByte;
    }while(readByte != 0);

    printf("File Copy Successful, %d bytes copied\n", copyByte);

    //close the 2 text files
    close(source);
    close(destination);

    return 0;
}