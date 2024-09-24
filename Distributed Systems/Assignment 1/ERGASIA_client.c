#include "ERGASIA.h"
#include <pthread.h>
#include <netinet/in.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <unistd.h>

struct ClientHandlerArguments
// Because we can't have 2 arguments inside the thread function, we create a struct
{
	int client_socket;
	CLIENT *rpc_client;
};

// Reads bytes from the client until it stops
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

int sendArray(int fd, float *array, int length)
{

	if (sendNumber(fd, length) < 0)
	{
		return -1;
	}

	for (int i = 0; i < length; i++)
	{
		sendFloat(fd, array[i]);
	}

	return 0;
}

// Function for client handling
// The threading library calls this function for us
void *
client_handling(void *argmnt)
{
	// Taking the arguments
	struct ClientHandlerArguments *args = (struct ClientHandlerArguments *)argmnt;

	int client_sock = args->client_socket;
	CLIENT *rpc_client = args->rpc_client;

	while (1)
	// Loop until it gives us the exit choice
	{
		int choice = readNumber(client_sock);
		// printf("Choice selected: %d\n", choice);

		if (choice == 4)
		// If the choice is exit stop the loop and proceed to close the connection
		{
			break;
		}

		int length = readNumber(client_sock);
		// printf("Array length: %d\n", length);

		int32_t *array = (int32_t *)malloc(length * sizeof(int32_t));

		for (int i = 0; i < length; i++)
		{
			array[i] = readNumber(client_sock);
			// printf("Number received: %d\n", array[i]);
		}

		float *result_1;
		vector_in average_y_1_arg;
		minmax *result_2;
		vector_in min_max_y_1_arg;
		product *result_3;
		vector_in product_y_1_arg;

		switch (choice)
		{
		case (1):
			average_y_1_arg.y.y_len = length;
			average_y_1_arg.y.y_val = array;

			result_1 = average_y_1(&average_y_1_arg, rpc_client);

			// printf("Result 1: %f\n", *result_1);
			sendFloat(client_sock, *result_1);
			break;
		case (2):
			min_max_y_1_arg.y.y_len = length;
			min_max_y_1_arg.y.y_val = array;

			result_2 = min_max_y_1(&min_max_y_1_arg, rpc_client);

			// printf("min: %d\nmax: %d\n", result_2->mima.mima_val[0], result_2->mima.mima_val[1]);
			sendNumber(client_sock, result_2->mima.mima_val[0]);
			sendNumber(client_sock, result_2->mima.mima_val[1]);

			break;
		case (3):
			float numberA = readFloat(client_sock);
			product_y_1_arg.a = numberA;
			product_y_1_arg.y.y_len = length;
			product_y_1_arg.y.y_val = array;

			result_3 = product_y_1(&product_y_1_arg, rpc_client);

			for (int i = 0; i < product_y_1_arg.y.y_len; i++)
			{
				// printf("product %d: %f\n", i, result_3->prod.prod_val[i]);
			}
			sendArray(client_sock, result_3->prod.prod_val, length);

		default:
			break;
		}
	}

	// Closes the file descriptor and exits the thread
	close(client_sock);
	pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
	char *host;

	if (argc < 4)
	{
		fprintf(stderr, "Usage: %s server_host sock_host sock_port\n", argv[0]); // Program inputs
		exit(1);
	}
	host = argv[1];
	int port = atoi(argv[3]);

	int sockfd, connfd, len;
	struct sockaddr_in servaddr, cli;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
	{
		fprintf(stderr, "ERROR opening socket");
		exit(1);
	}

	bzero((char *)&servaddr, sizeof(servaddr));

	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(port);
	servaddr.sin_addr.s_addr = INADDR_ANY;

	if (bind(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0)
	{
		fprintf(stderr, "ERROR on binding");
		exit(1);
	}

	// Now server is ready to listen and verification
	if ((listen(sockfd, 5)) != 0)
	{
		printf("Listen failed...\n");
		exit(0);
	}
	else
		printf("Server listening..\n");
	len = sizeof(cli);

	// Creating the rpc client to use from all the connections
	CLIENT *rpc_client = clnt_create(host, ERGASIA_PROG, ERGASIA_VERS, "udp");
	if (rpc_client == NULL)
	{
		clnt_pcreateerror(host);
		exit(1);
	}

	pthread_t thread_location; // It has to have a location even if you're not using it, or else it crashes

	// Accept the data packet from client and verification
	while (1)
	{
		connfd = accept(sockfd, (struct sockaddr *)&cli, &len);
		if (connfd < 0)
		{
			printf("Server accept failed...\n");
			exit(0);
		}
		else
			printf("Server accept the client...\n");

		// Creating a new thread to call our function
		struct ClientHandlerArguments args = {0};
		args.client_socket = connfd;
		args.rpc_client = rpc_client;
		pthread_create(&thread_location, NULL, client_handling, (void *)&args);
	}

	// ergasia_prog_1(host);
	exit(0);
}
