#include <iostream>
#include <vector>
#include <cstring>
#include <fstream>

using namespace std;

template <class T>
class STACK{
private:
    vector<T> St; // Apothikeytikos xwros tou stack
public:
    bool push(const T &item); // The function takes a constant reference to a value of type T
    bool pop();
    int getSize();
    bool saveFile(const string &fileName);
};

template <class T>
bool STACK<T>::push(const T &item){
    this->St.push_back(item);
    return true;
}

template <class T>
bool STACK<T>::pop(){
    if(this->St.empty()){
        return false;
    }else{
        this->St.pop_back();
        return true;
    }

}

template <class T>
int STACK<T>::getSize(){
    return this->St.size();
}

template <class T>
bool STACK<T>::saveFile(const string &fileName){
    fstream writer(fileName, ios::out);
    if(!writer.is_open()){
        cerr << "Error opening file" << endl;
        return false;
    }

    for (int i = 0; i < St.size(); i++){
        writer << St[i] << endl;
    }

    writer.close();
    return true;
}

int main(){

    STACK<double> stack1;

    stack1.push(564.7868);
    stack1.push(3425.786);
    stack1.push(324.456);

    cout << "Stack1 size before pop: " << stack1.getSize() << endl;

    stack1.pop();

    cout << "Stack1 size after pop: " << stack1.getSize() << endl;

    STACK<string> stack2;

    stack2.push("Hi");
    stack2.push("Hello");
    stack2.push("Bye");
    stack2.push("Goodbye");

    cout << "Stack2 size before pop: " << stack2.getSize() << endl;

    stack2.pop();

    cout << "Stack2 size after pop: " << stack2.getSize() << endl;

    stack2.saveFile("stringA8.txt");
    stack1.saveFile("doubleA8.txt");
    stack1.saveFile("stringA8.txt");


    return 0;
}
