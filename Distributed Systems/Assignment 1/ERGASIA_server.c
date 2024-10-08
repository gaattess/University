/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "ERGASIA.h"

float *
average_y_1_svc(vector_in *argp, struct svc_req *rqstp)
{
	static float result;

	int sum = 0;
	u_int vector_length = argp->y.y_len; // Finding the length of the vector

	for (int i = 0; i < vector_length; i++)
	{
		sum += argp->y.y_val[i];
	}

	result = (float)sum / (float)vector_length;

	return &result;
}

minmax *
min_max_y_1_svc(vector_in *argp, struct svc_req *rqstp)
{
	static minmax result;

	int min = argp->y.y_val[0];
	int max = argp->y.y_val[0];

	u_int vector_length = argp->y.y_len;

	for (int i = 0; i < vector_length; i++)
	{
		if (argp->y.y_val[i] < min)
		{
			min = argp->y.y_val[i];
		}
		if (argp->y.y_val[i] > max)
		{
			max = argp->y.y_val[i];
		}
	}

	result.mima.mima_len = 2;

	int items[2] = {min, max};
	result.mima.mima_val = items;

	return &result;
}

product *
product_y_1_svc(vector_in *argp, struct svc_req *rqstp)
{
	static product result;
	u_int vector_length = argp->y.y_len;

	float a = argp->a;
	float *prod_y = malloc(sizeof(float) * vector_length);

	for (int i = 0; i < vector_length; i++)
	{
		prod_y[i] = a * argp->y.y_val[i];
	}

	result.prod.prod_len = vector_length;

	result.prod.prod_val = prod_y;

	return &result;
}
