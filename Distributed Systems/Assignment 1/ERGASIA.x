struct vector_in{
	int y<>;
	float a;
};

struct minmax {
	int mima<2>;
};

struct product {
	float prod<>;
};


program ERGASIA_PROG {
	version ERGASIA_VERS {
		float AVERAGE_Y(vector_in) = 1;
		minmax MIN_MAX_Y(vector_in) = 2;
		product PRODUCT_Y(vector_in) = 3; 
	} = 1;
} = 0x23451111;
