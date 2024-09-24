#include <iostream>
#include <cstring>
#include <list>

using namespace std;

class FISH{
private:
    double size;
    string family;
public:
    FISH(double, const string&);
    virtual ~FISH();

    const string& getFamily() const;

};

class BIRD : public FISH{
private:
    bool flying;
    string region;
public:
    BIRD(double, const string&, bool, const string&);
};


FISH::FISH(double size, const string& family){
    this->size = size;
    this->family = family;
}

FISH::~FISH(){
    cout << "Fish destructor called" << endl;
}


BIRD::BIRD(double size, const string& family, bool flying, const string& region) : FISH(size, family){
    this->flying = flying;
    this->region = region;
}

const string& FISH::getFamily() const{
    return family;
}


// Gyrnaei reference
ostream& operator<<(ostream& os, const list<FISH*>& animals) {
    for (FISH* animal: animals) {
        os << animal->getFamily() << endl;
    }
    return os;
}


int main(){

    // Pali gia to object slicing
    list<FISH*> animals;

    animals.push_back(new FISH(1.52, "Salmon"));
    animals.push_back(new BIRD(4.5, "Penguin", false, "Antarctica"));

    cout << animals;

    // Kanoume delete mhn klaiei o karaflas
    for (FISH* animal:animals) {
        delete animal;
    }

    return 0;
}
