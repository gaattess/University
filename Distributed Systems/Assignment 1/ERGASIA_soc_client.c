#include <stdio.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <strings.h>
#include <sys/socket.h>
#define SA struct sockaddr

int sendNumber(int fd, int num)
{
	if (send(fd, &num, sizeof(num), 0) < 0)
	{
		return -1;
	}
	return 0;
}

int sendFloat(int fd, float num)
{
	if (send(fd, &num, sizeof(float), 0) < 0)
		return -1;
	return 0;
}

int sendArray(int fd, int array)
{
	int flag = send(fd, &array, sizeof(array), 0);
	if (flag < 0)
	{
		return -1;
	}

	return 0;
}

int32_t readNumber(int fd)
{
	int32_t temp;
	char *buffer = (char *)&temp;
	int received = 0;
	int left = sizeof(temp);

	do
	{
		received = recv(fd, buffer, sizeof(temp), 0);

		if (received == -1)
		{
			fprintf(stderr, "Failed to receive data");
			close(fd);
			pthread_exit(NULL);
		}

		left -= received;
		buffer += received;

	} while (left > 0);

	return temp;
}

float readFloat(int fd)
{
	float temp;
	char *buffer = (char *)&temp;
	int received = 0;
	int left = sizeof(temp);

	do
	{
		received = recv(fd, buffer, sizeof(temp), 0);

		if (received == -1)
		{
			fprintf(stderr, "Failed to receive data");
			close(fd);
			pthread_exit(NULL);
		}

		left -= received;
		buffer += received;

	} while (left > 0);

	return temp;
}

int main(int argc, char *argv[])
{
	/*                       SOCKET INIT                            */
	if (argc < 3)
	{
		fprintf(stderr, "Usage %s hostname port\n", argv[0]); // Check if client has put the correct arguments
		exit(0);
	}

	int sockfd, connfd, portnum;
	struct sockaddr_in servaddr;

	// Socket create and verification
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd == -1)
	{
		printf("Socket creation failed...\n");
		exit(0);
	}
	else
		printf("Socket successfully created..\n");

	bzero(&servaddr, sizeof(servaddr)); // Zeroing the server adress

	portnum = atoi(argv[2]); // Converting the string to int

	// Assign IP, PORT
	servaddr.sin_family = AF_INET;
	inet_aton(argv[1], &servaddr.sin_addr); // Converting the argument to bytes and setting it to adress
	servaddr.sin_port = htons(portnum);		// Same thing with the port

	// Connect the client socket to server socket
	if (connect(sockfd, (SA *)&servaddr, sizeof(servaddr)) != 0) // Saving the result to sockfd so you can send to server
	{
		printf("Connection with the server failed...\n");
		exit(0);
	}
	else
		printf("Connected to the server..\n");

	/*                       USER MENU                             */
	int numChoice; // Menu choice

	while (1)
	{
		printf("Choose one of the following actions that you want to perform\n");
		printf("Select 1 to show the average of the array\n");
		printf("Select 2 to show the minimum and the maximum of the array\n");
		printf("Select 3 to multiply the array with a number of your choice\n");
		printf("Select 4 to exit program\n");

		printf("Input your choice: \n");
		scanf("%d", &numChoice);

		while (numChoice > 4 || numChoice < 1) // Check choice
		{
			printf("You must only input a number between 1 and 4. Please try again.\n");
			scanf("%d", &numChoice);
		}

		sendNumber(sockfd, numChoice); // Sending the choice to the server

		/*                       ARRAY MENU                             */
		int arrayLength; // n
		int *arrayY;	 // Y
		float numberA;	 // α

		arrayY = (int *)malloc(arrayLength * sizeof(int));

		if (numChoice == 4)
		{
			break;
		}

		printf("Enter the length of the array: \n");
		scanf("%d", &arrayLength);

		while (arrayLength <= 0)
		{
			printf("Array length can only be a positive number. Please try again: \n");
			scanf("%d", &arrayLength);
		}

		sendNumber(sockfd, arrayLength); // Sending the array length to the server
		printf("Array length: %d\n", arrayLength);

		printf("Input the numbers that you want in your array: \n");
		for (int i = 0; i < arrayLength; i++)
		{
			printf("Index number [%d]: \n", i);
			scanf("%d", arrayY + i); // Pointer sthn arxh tou array + oi theseis
			// printf("Scanned number: %d\n", arrayY[i]);
			sendArray(sockfd, arrayY[i]); // Sending the numbers inside the array to the server
		}

		if (numChoice == 3)
		{
			printf("Enter the number that you want the array to be multiplied with: \n");
			scanf("%f", &numberA);
			sendFloat(sockfd, numberA); // Sending the number a to the server
		}

		/*                       CHOICES                             */

		switch (numChoice)
		{
		case (1):
			float average = readFloat(sockfd);
			printf("Average of array is: %.2f\n", average);
			break;

		case (2):
			int min = readNumber(sockfd);
			printf("Min is: %d\n", min);
			int max = readNumber(sockfd);
			printf("Max is: %d\n", max);
			break;

		case (3):
			printf("The array after the multiplication is now as follows: \n");
			int length = readNumber(sockfd);

			for (int i = 0; i < length; i++)
			{
				float arrayNumber = readFloat(sockfd);
				printf("Index number [%d]: %.2f\n", i, arrayNumber);
			}
			break;

		case (4):
			printf("Goodbye\n");
			break;

		default:
			break;
		}
	}

	// Close the socket
	close(sockfd);

	return 0;
}
