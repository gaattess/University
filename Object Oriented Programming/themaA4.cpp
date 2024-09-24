#include <iostream>
#include <cstring>

using namespace std;

class MyString{
private:
    char* str;
    int stringLength;

public:
    MyString();
    MyString(const char* cstr);
    MyString(const MyString& newString);

    bool operator==(const MyString);
    bool operator!=(const MyString);
    bool operator>(const MyString);
    bool operator<(const MyString);
    MyString operator+=(const char newCharacter);

    const char* get_Cstring() const;
    const int get_Length() const;

    ~MyString();

};

MyString::MyString(){
    str = new char[1]; // Mia thesh
    str[0] = '\0';
    stringLength = 0;
}


MyString::MyString(const char* cstr){
    stringLength = strlen(cstr); // Metraei poso einai kai krataei to length
    str = new char[stringLength + 1]; // Krataei thn mnhmh gia to character array kai mia thesh gia to null operator sto telos
    strcpy(str, cstr); // To kanei copy ekei
}

MyString::MyString(const MyString& newString){
    stringLength = newString.stringLength; // Pairnw to length apo to object
    str = new char[stringLength + 1]; // Krataei mnhmh gia to string
    strcpy(str, newString.str); // Kanei copy pali

}

bool MyString::operator==(const MyString newString){
    if(this->stringLength != newString.stringLength)
        {
            return false;

        }
    else
        {
            for(int i = 0; i < this->stringLength; i++){
                if (this->str[i] != newString.str[i]){
                    return false;
                }
            }
            return true;
        }
}

bool MyString::operator!=(const MyString newString){
    if(this->stringLength != newString.stringLength)
        {
            return true;

        }
    else
        {
            for(int i = 0; i < this->stringLength; i++){
                if (this->str[i] != newString.str[i]){
                    return true;
                }
            }
            return false;
        }
}

bool MyString::operator>(const MyString newString){
    return strcmp(this->str, newString.str) > 0;

}

bool MyString::operator<(const MyString newString){
    return strcmp(this->str, newString.str) < 0;
}

MyString MyString::operator+=(const char newCharacter){
    char* newString = new char[stringLength + 2]; // Krataw nea mnhmh gia to neo string
    strcpy(newString, str);
    newString[stringLength] = newCharacter; // Vazw to character sto telos
    newString[stringLength + 1] = '\0'; // Ekana overwrite to null byte otan grafw to newCharacter opote prepei na to valw pali sto telos

    stringLength += 1; // Kanw update to string length
    delete[] str; // Kanw delete to old string
    str = newString; // Evala to palio string me ton neo character se neo array, opote ama den deiksei o pointer sto neo apla tha xathei

    return *this;
}

const char* MyString::get_Cstring() const {
    return str;
}

const int MyString::get_Length() const {
    return stringLength;
}


MyString::~MyString(){
    delete []str;
}


int main(){

    MyString obj1();

    MyString obj2("Hello");

    MyString obj3(obj2);

    MyString obj4("Heli");

    // cout << "obj1 : " << obj1 << endl;
    cout << "obj2 : " << obj2.get_Length() << endl;

    bool equal = (obj2 == obj3);
    cout << "equal operator: " << equal << endl;

    bool equal2 = (obj2 == obj4);
    cout << "equal operator2: " << equal2 << endl;


    bool unequal = (obj2 != obj3);
    cout << "unequal operator: " << unequal << endl;

    bool unequal2 = (obj2 != obj4);
    cout << "unequal operator2: " << unequal2 << endl;


    bool compareBigger = (obj2 > obj3);
    cout << "compare operator > : " << compareBigger << endl;

    bool compareBigger2 = (obj3 > obj4);
    cout << "compare operator > : " << compareBigger2 << endl;


    bool compareSmaller = (obj2 < obj3);
    cout << "compare operator < : " << compareBigger << endl;

    bool compareSmaller2 = (obj3 < obj4);
    cout << "compare operator < : " << compareBigger2 << endl;

    MyString appendString("Test");

    cout << "unappended string: " << appendString.get_Cstring() << endl;

    appendString += 'y';

    cout << "appendString: " << appendString.get_Cstring() << endl;
    cout << "appendString length: " << appendString.get_Length() << endl;

    return 0;
}
