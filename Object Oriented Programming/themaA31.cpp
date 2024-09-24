#include <iostream>
#include <vector>

using namespace std;


template <class T>
class STACK{
private:
    vector<T> S;
public:
    bool push(const T &item);
    bool pop();
    int getSize();
};

// Thelei se kathe function tou template na to grafeis panw
template <class T>
bool STACK<T>::push(const T &item){ // Den ksexname to typename<T>
    this->S.push_back(item);
    return true;
}

template <class T>
bool STACK<T>::pop(){
    this->S.pop_back();
    return true;
}

template <class T>
int STACK<T>::getSize(){
    return this->S.size();
}

// Thn syntaksh!!!
template <class T>
STACK<T> getCopy(const STACK<T> &stack){
    STACK<T> copy = stack;
    return copy;

}

int main(){

    STACK<int> stack1;

    stack1.push(23);
    stack1.push(25);

    STACK<double> stack2;

    stack2.push(4.56);
    stack2.push(546.589);
    stack2.push(456.324);

    cout << "Stack 1 size: " << stack1.getSize() << endl;
    cout << "Stack 2 size: " << stack2.getSize() << endl;

    STACK<double> stack3 = getCopy(stack2); // Den einai function ths klashs mpainei monh ths

    cout << "Stack 3 size: " << stack3.getSize() << endl;

    stack3.push(4436.675856);

    cout << "Stack 3 size: " << stack3.getSize() << endl;

    return 0;
}
