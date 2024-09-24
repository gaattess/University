#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

class DATES{
private:
    int day, month, year; //TODO Mhpws na tis valw unsigned int?

public:
    DATES(int, int, int);
    DATES(const DATES& newDates);

    DATES operator+(int);

    std::string returnString();

    bool operator>(DATES);

    static void SortDates(vector<DATES>& datesVector);

};

DATES::DATES(int day, int month, int year){
    if (day >= 1 && day <= 31){
        this->day = day;
    }else{
        cout << "Days have to be between 1 and 31" << endl;
    }

    if (month >= 1 && month <= 12){
         this->month = month;
    }else{
        cout << "Months have to be between 1 and 12" << endl;
    }

    if (year >= 1){
        this->year = year;
    }else{
        cout << "Years have to be a positive number" << endl;
    }

}

DATES::DATES(const DATES& newDates){
    this->day = newDates.day;
    this->month = newDates.month;
    this->year = newDates.year;
}

DATES DATES::operator+(int months){
    int monthTemp = this->month + months;
    int yearTemp = this->year;

    if (monthTemp > 12){ // If new months are more than 12
        yearTemp += monthTemp / 12;
        monthTemp = monthTemp % 12;
        if (monthTemp == 0){
            monthTemp = 12;
            yearTemp--;
        }
    }

    return DATES(this->day, monthTemp, yearTemp);
}

bool DATES::operator>(const DATES newDates){
    if (this->year > newDates.year) return true;
    else if (this->year == newDates.year && this->month > newDates.month) return true;
    else if (this->year == newDates.year && this->month > newDates.month && this->day > newDates.day) return true;

    return false;
}

std::string DATES::returnString(){
    return to_string(this->day) + "/" + to_string(this->month) + "/" + to_string(this->year);
}

// Me lambda, den tha to proteina tbh
// void DATES::SortDates(vector<DATES>& datesVector){
//     sort(datesVector.begin(), datesVector.end(), [](const DATES& a, const DATES& b){
//         return !(a > b);
//     });
//
// }

void DATES::SortDates(vector<DATES>& datesVector) {
    int n = datesVector.size();
    bool swapped;

    for (int i = 0; i < n - 1; i++) {
        swapped = false;
        for (int j = 0; j < n - i - 1; j++) {
            if (datesVector[j] > datesVector[j + 1]) {
                // Swap ama einai anapoda
                DATES temp = datesVector[j];
                datesVector[j] = datesVector[j + 1];
                datesVector[j + 1] = temp;
                swapped = true;
            }
        }
        // Ama den egine tipota swap sto inner loop, einai sorted
        if (!swapped)
            break;
    }
}



int main(){

    DATES obj1(12, 8, 2001);

    cout << obj1.returnString() << endl;

    DATES obj2(obj1);

    cout << obj2.returnString() << endl;

    DATES obj3(32, 13, 0);

    DATES obj4(obj3);

    obj2 = obj2 + 8;

    cout << obj2.returnString() << endl;

    cout << "obj1 > obj2: " << (obj1 > obj2) << "\nobj2 > obj1: " << (obj2 > obj1) << endl;

    vector<DATES> datesVector = {
        DATES(12, 8, 2001),
        DATES(5, 6, 1999),
        DATES(23, 11, 2010),
        DATES(1, 1, 2000)
    };

    cout << "Before sorting:" << endl;
    for (size_t i = 0; i < datesVector.size(); i++) {
        cout << datesVector[i].returnString() << endl;
    }

    DATES::SortDates(datesVector);

    cout << "\nAfter sorting:" << endl;
    for (size_t i = 0; i < datesVector.size(); i++) {
        cout << datesVector[i].returnString() << endl;
    }

    return 0;
}
