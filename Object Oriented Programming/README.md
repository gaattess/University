(A-1). Write a class that represents a time of the day (hour, minutes, seconds). The object of the class will be initialized with the three values or with another object of the same class.
The class will support the following functions:
● Create a new time resulting from incrementing a
Create a new moment created by increasing an existing moment by a number of seconds (overloading of a '+' operator)
● Creation of a new moment in time resulting from incrementing an existing time by a "+" of one second.
creation of a new time instant by increasing an existing one by another time instant (overloading of a '+' operator)
● Return the time as a string
● Equality check between two objects of the class (overloading
operator "==").
Write a main function which demonstrates the operation of the class.

(A-4). Create the class "MyString" which will represent strings. The class will meet the following specifications:
● It will be initialized with the empty string, a C String, or another MyString object.
● It will overload the "==", "!=", ">" and "<" operators to perform the
corresponding checks.
● It will overload the "+=" operator to add a character to the
end of the string.
● It will return the string as a C String.
● It will return the length of the string.
Demonstrate the operation of the class via an appropriate main function. The STL string class will not be used to implement the above.

(A-7). Write a class that represents a date (day, month, year). The object of the class will be initialized with the three values or with another object of the same class.
The class will support the following functions:
● Creation of a new date resulting from incrementing an existing date by N months (overloading operator "+")
● Return the date as a string
● Overloading the ">" operator to return true if the date is later than another date.
Write a function that sorts a date table.
Write a main function which demonstrates the operation of the previous ones.

(A-8). Create a template class "Stack" which will support the
the following functions:
● Insert Object at the top of the stack. (Push)
● Extract Object from the top of the stack (Pop).
● Resize the stack.
● Save the stack to a text file.
The stack will have no size restrictions beyond those resulting from available RAM.
Demonstrate the operation of the stack with objects of a class that will have only two attributes: a real number and a C-String.

(A-31). Create a template class "Stack". The class will not set a limit on the
number of elements that can be stacked in it and will
provide, only, the following functionality:
● Insert an element into its top (push).
● Extract an element from its vertex (pop).
● Return its size (number of elements in the stack) 
Write a function, which is not a method of the class, that accepts a stack, creates a copy of it (of the entire stack) and returns it. To implement the function, no functionality will be added to the "Stack" class, nor will the rules of proper class design (public attributes, use of "friend", etc) be violated.
Mainly write a program that demonstrates the functionality of the previous ones with objects of the examinee's choice.

(A-34). Create the necessary classes to describe the entities "Bird species" and "Fish species". Each class will have 2 to 3 characteristics of the examinee's choice.
Create a single list of objects that includes both birds and fish.
To implement what is necessary so that the command: XXX << YYY? (where XXX is an instance of the ostream class and YYY is a single list of birds and fish) writes to the channel the names of the fish species included in the list.
Write a main program that demonstrates the operation of the previous ones.

(A-40). Write a class that represents squares with a unique
attribute the size of the side (real number).
Write a class that represents rectangular parallelograms with the length and width of the shape as the unique characteristics (real numbers).
The classes representing the two shapes will, among other things, each include a method (emb) that returns their area.
Write a function that takes as an argument a vector of squares and rectangles and writes to a channel the length, width and area of each rectangle. The channel will also be given as an argument to the function.
Demonstrate the operation of the preceding via an appropriate main function