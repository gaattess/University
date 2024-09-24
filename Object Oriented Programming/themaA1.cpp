#include <iostream>
#include <cstring>

using namespace std;

class TimeofDay { // Class
private:
    int secs, mins, hours;

public:
    std::string returnString();
    TimeofDay(int, int, int);
    TimeofDay operator+(int);
    TimeofDay operator+(TimeofDay);
    bool operator==(TimeofDay);
};


TimeofDay::TimeofDay(int secs, int mins, int hours){ // Constructor
    this->secs = secs;
    this->mins = mins;
    this->hours = hours;
}

std::string TimeofDay::returnString(){
    return "seconds: " + to_string(this->secs) + " minutes: " + to_string(mins) + " hours: " + to_string(hours);

}


TimeofDay TimeofDay::operator+(int seconds){ // Operator overload

    int secondsTemp = this->secs + seconds;
    int minutesTemp = this->mins;
    int hoursTemp = this->hours;

    if(secondsTemp / 60 >= 1){ // If new seconds are more than 60
        minutesTemp += secondsTemp / 60;
        secondsTemp = secondsTemp % 60;
    }

    if(minutesTemp / 60 >= 1){ // If new minutes are more than 60
        hoursTemp += minutesTemp / 60;
        minutesTemp = minutesTemp % 60;
    }

     return TimeofDay(secondsTemp, minutesTemp, hoursTemp); // New object creation and return

}

TimeofDay TimeofDay::operator+(TimeofDay newTime){

    int convertSeconds = (newTime.hours * 60 * 60) + (newTime.mins * 60) +newTime.secs; // Converting hours to seconds and minutes to seconds

    return *this + convertSeconds; // Returning the final calculation by calling the previous Operator overload, so it is not hard
}

bool TimeofDay::operator==(TimeofDay newTime){
    return ((this->hours == newTime.hours) && (this->mins == newTime.mins) && (this->secs == newTime.secs));
}


int main(){

    TimeofDay obj = TimeofDay(30, 50, 0);

    TimeofDay obj2 = TimeofDay(65, 20, 1);

    TimeofDay obj3 = TimeofDay(30, 50, 0);


    TimeofDay newObj = obj + obj2;

    cout << newObj.returnString() << endl;

    cout << obj.returnString() << endl;

    cout << obj3.returnString() << endl;

    bool equation = (obj == obj3);

    cout << equation << endl;

    return 0;
}
