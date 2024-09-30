Educational mobile app using Kotlin and XML for the UI design.

- Introduction

The application developed for the course "E-learning" is an educational quiz, which contains the theory and exercises of the 2nd grade mathematics, in order to review and learn a part of the book. The "QuizApp" mobile application is intended for students in the 2nd grade, or older students, as younger students may not have the necessary knowledge to complete the exercises. 

- In summary, the app

The app contains user registration and login menus to better personalize and organize the educational content. There are three main parts of the app, the theory, which contains the material from chapters 1 and 2 of the book (8 sections), 77 multiple choice, true/false and matching questions, and statistics per completed chapter.  
There is no timer in the quizzes, as the app is not designed to "force" the student to solve the problem in a certain amount of time, but to develop their knowledge and strive until they complete each chapter of the app. By using the statistics, he can see which chapter he was "weak" in and focus on that, so that there are no "gaps" in the material of such a basic course.
For potential questions on the functioning of the app, there is a FAQ section for questions such as the order of starting the quiz, success, etc.
Finally, multiple students can play the quiz on one device, as there is an option to log in with each student's data separately, and their data is stored separately.

- Database

For communication with the base we use the "Room" library. Room is an abstraction layer on top of SQLite where it makes it easy to store and read data. 
We have defined some DAOs (data access objects) which have the queries we need to get data from the database, but also to import new data or update old data.
Beyond the DAOs we have defined Entities which represent the database rows in the code and make it easy to read the data we want in an object-oriented way.

- The application in detail

The app was implemented in android studio (2022.3.1) and xml was used for graphics and kotlin (1.9.0) for coding. The app has been tested on android emulators and on physical phones and works the same way.


 ![image](https://github.com/user-attachments/assets/17f840fb-0cc6-4ba1-83a5-1661a67c3ac8)
 
 - Introductory screen

![image](https://github.com/user-attachments/assets/4e3f23cc-b7c8-43db-883c-c8d9559e7418)
![image](https://github.com/user-attachments/assets/f648e9be-67a6-4998-b622-c4c12bc394d6)
![image](https://github.com/user-attachments/assets/17c38fc6-704b-446b-8a5e-53a0b5d7d706)
![image](https://github.com/user-attachments/assets/c9b7cc15-98cd-4538-a586-e2c10689d03f)
![image](https://github.com/user-attachments/assets/1dbcea44-995b-45e2-8b80-9fefcf58a8c3)

- Main Screen

  ![image](https://github.com/user-attachments/assets/96640d4a-d6d3-4c8e-8975-243f06735c0c)

- Exiting

  ![image](https://github.com/user-attachments/assets/74280423-b6ff-47ee-b235-c7c2924a8bb2)

- FAQ

![image](https://github.com/user-attachments/assets/00b4be6c-9397-440d-86ab-bf7acb012374)
![image](https://github.com/user-attachments/assets/48dada4c-a038-4ac5-82fe-8fe386469cfc)
![image](https://github.com/user-attachments/assets/8397834d-e527-4302-b0ab-4c8f48f2c498)
![image](https://github.com/user-attachments/assets/d015832b-1dd5-4880-b910-3aea1af22e67)

- Theory

![image](https://github.com/user-attachments/assets/d86dc142-0931-41e8-8a79-224304334eac)
![image](https://github.com/user-attachments/assets/2347d476-8085-4644-a7a1-86de2b2d68c0)
![image](https://github.com/user-attachments/assets/e7452443-a321-4226-b343-8cdf85677020)

- Quiz

![image](https://github.com/user-attachments/assets/73e29278-34c6-416d-b8e2-119a7bcd9caf)
![image](https://github.com/user-attachments/assets/0b3c0c9d-fd10-4e47-a4db-96247f1680ea)
![image](https://github.com/user-attachments/assets/e9fff86f-fbc0-41e2-b97e-8f044f82ec28)
![image](https://github.com/user-attachments/assets/bd2851de-6ae0-485c-9439-fb3d543f8e52)
![image](https://github.com/user-attachments/assets/a3a6a987-46ae-4865-9539-b76af32a67cb)
![image](https://github.com/user-attachments/assets/146f26d2-0bb1-412f-9da7-2876fdf82c69)
![image](https://github.com/user-attachments/assets/1184e38d-51da-4440-bed7-2bc32757c829)

- Statistics

![image](https://github.com/user-attachments/assets/2060a580-b26f-4e5b-b575-11fc2cf73955)
![image](https://github.com/user-attachments/assets/d90a8681-4e51-404c-a75c-034698662306)


