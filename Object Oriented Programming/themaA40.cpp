#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class SHAPE{
private:

public:
    double length, width;

    virtual double emb() const = 0; // Thn kanoume pure virtual (dhladh abstract) giati den tha ftiaksoume objects typou shape
    virtual ~SHAPE(); // Ama kapoio object xrhsimopoiei dynamic memory tha xoume thema, opote vazoume enan mpampa destructor
/*
    virtual double getLength() const;
    virtual double getWidth() const;
    virtual double getSide() const;*/



};

// !!!!PROSOXH!!!! den ksexname na valoume to inheritance
class SQUARE : public SHAPE{
private:
    double side;
public:
    SQUARE(double); // Thelei constructor giati h methodos emvadon den pairnei parametrous

    double getSide() const;

    double emb() const override; // Prepei na einai akrivws to idio onoma me thn mama func

};

class PARALLELOGRAM : public SHAPE{
private:
    double length, width;
public:
    PARALLELOGRAM(double, double);

    double getLength() const;
    double getWidth() const;

    double emb() const override;

};

// Constructors

SQUARE::SQUARE(double side){
    cout << "Called constructor for square" << endl;
    this->side = side; // To kanoume etsi giati o malakakos mas den thelei inline
}

PARALLELOGRAM::PARALLELOGRAM(double length, double width){
    cout << "Called constructor for parallelogram" << endl;
    this->length = length;
    this->width = width;
}

SHAPE::~SHAPE(){
    cout << "Called destructor for shape" << endl;
}


// Override method

double SQUARE::emb() const{ // Prepei na kanei match AKRIVWS me thn mama func

    return side * side;
}

double PARALLELOGRAM::emb() const{

    return length * width;
}

// Getters

double SQUARE::getSide() const{
    return side;
}

double PARALLELOGRAM::getLength() const{
    return length;
}

double PARALLELOGRAM::getWidth() const{
    return width;
}

// Function shape vector outputting in stream

void vectorStream(vector<SHAPE*> vector, ostream &os){
    for (int i = 0; i <vector.size(); i++){
        SHAPE* shape = vector[i];

        // Gia na kanw to dynamic cast na vrw an einai square h parallelogram
        SQUARE* square = dynamic_cast<SQUARE*>(shape);
        PARALLELOGRAM* parallelogram = dynamic_cast<PARALLELOGRAM*>(shape);

        if (square != nullptr){
            os << "Length and width of cube: " << square->getSide() << "\n";
            os << "Area of cube: " << square->emb() << "\n";
        }else if (parallelogram != nullptr){
            os << "Length of parallelogram: " << parallelogram->getLength() << "\n";
            os << "Width of parallelogram: " << parallelogram->getWidth() << "\n";
            os << "Area of parallelogram: " << parallelogram->emb() << "\n";
        }
    }
}



int main(){

    // Prepei na kanw memory allocation giati den pianei ta paidia
    SHAPE* sqr1 = new SQUARE(4);
    SHAPE* sqr2 = new SQUARE(7);
    SHAPE* par1 = new PARALLELOGRAM(15, 5);
    SHAPE* par2 = new PARALLELOGRAM(8, 4);

    vector<SHAPE*> v1;

    // Add shapes to vector
    v1.push_back(sqr1);
    v1.push_back(sqr2);
    v1.push_back(par1);
    v1.push_back(par2);

    vectorStream(v1, cout); // To os stream einai to cout


    // Kanoume kai delete (I hope it works...)
    for(int i = 0; i < v1.size(); i++){
        delete v1.at(i);
    }


    return 0;
}
