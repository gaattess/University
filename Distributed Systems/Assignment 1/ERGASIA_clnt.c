/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include <memory.h> /* for memset */
#include "ERGASIA.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

float *
average_y_1(vector_in *argp, CLIENT *clnt)
{
	static float clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, AVERAGE_Y,
		(xdrproc_t) xdr_vector_in, (caddr_t) argp,
		(xdrproc_t) xdr_float, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

minmax *
min_max_y_1(vector_in *argp, CLIENT *clnt)
{
	static minmax clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, MIN_MAX_Y,
		(xdrproc_t) xdr_vector_in, (caddr_t) argp,
		(xdrproc_t) xdr_minmax, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

product *
product_y_1(vector_in *argp, CLIENT *clnt)
{
	static product clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, PRODUCT_Y,
		(xdrproc_t) xdr_vector_in, (caddr_t) argp,
		(xdrproc_t) xdr_product, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}
