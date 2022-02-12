# COP3330_vanderzalm
<div id="top"></div>

## About The Class

Object oriented programming concepts (classes, objects, methods, encapsulating, inheritance, interfaces) and the expression of these concepts in the programming language Java. Some course objectives include:

1. Design and implement an object-oriented solution to meet a given set of user requirements
2. Evaluate an object-oriented solution to meet a given set of user requirements
3. Apply computer science theory to produce object-oriented solutions
4. Apply software development fundamentals to produce object-oriented solutions
5. Understand current industry best practices and standards and their application in the context of object-oriented programming

## Project Descriptions

### Project 1 - Getting Familiar with Java

A company that wants to send data over the Internet has asked you to write a program that will encrypt it so that it may be transmitted more securely. All the data is transmitted as four-digit integers. Your application should read a four-digit integer entered by the user and encrypt it as follows: Replace each digit with the result of adding 7 to the digit and getting the remainder after dividing the new value by 10. Then swap the first digit with the third, and swap the second digit with the fourth. Then print the encrypted integer. Your application should also allow the user to enter an encrypted four-digit integer and then decrypt it (by reversing the encryption scheme) to form the original number.

Your solution should consist of two classes: Encrypter and Decrypter.

Encrypter should contain an instance method called `encrypt` that accepts a String of 4 digits representing a single integer and performs the following actions:

1. Replace each digit with the result of adding 7 to the digit and getting the remainder after dividing the new value by 10.
2. Swap the first digit with the third, and swap the second digit with the fourth.
3. Return the encrypted integer as a String.


Decrypter should contain an instance method called `decrypt` that accepts a String of 4 digits representing a single encrypted integer and performs the following actions:

1. Decrypt the string by inverting the encrypt function.
2. Return the decrypted integer as a String.

### Project 2 - BMI Calculator
Create a BMI calculation program that reads a set of user’s weight and height (as imperial units), calculates and displays each user’s body mass index, and calculates and displays the average body mass index of all users. Additionally, display the BMI categories for each user from the National Heart Lung and Blood Institute: http://www.nhlbi.nih.gov/health/educational/lose_wt/BMI/bmicalc.htm 

Additional design constraints are as follows:

A class called `App` will be used as the application entry point for your program.
A class called `BodyMassIndex` will be used to calculate the BMI scores and categories.
A class called `BodyMassIndexTest` will be used to test the public methods of your BodyMassIndex class (This should be a JUnit 5 test class).

### Project 3 - Abstraction
Create the code to make the following test cases pass: `ShapeTest.java`. This test case essentially is calling different abstract methods for multiple shape classes. The methods call for the shapes to return their area or volume based on the parameter. 

Ex:

Shape3D shape1 = new Sphere(10);

Shape3D shape2 = new Cube(10);

double area1 = shape1.getVolume();

double area2 = shape2.getVolume();

Even though both shapes are apart of the Shape3D abstract class, calling the same method getVolume() will still return their respective volumes.


### Project 4 - Task List Management Application
Design a console-based Task List Management Application that allows the user to create a new task that contains a title, description, and due date. The user can create, remove, view, edit, load or save a task. With the exception of load and save, the user works in the internal RAM data; however, whenever the user exits the program the load and save methods allow them to view tasks they worked on previously. A text file is created whenever the user saves a task list and that file can be read if the user chooses to load that task list.

`TaskList.java` creates a new list of tasks

`TaskItem.java` creates a new item into the list that has a Title, Description, and a Due Date.

`App.java` run this to start program.

`TaskListTest.java` and `TaskItemTest.java` are JUnit test cases used to grade this project.

### Project 5 - Task List and Contact List Management Application
This is an extension of Project 4 so refer to that description where the only difference is now users can create a list of contacts where they can store somebody's full name, phone number, and email.

A main focus of Project 4 and 5 was to be able to handle many different exceptions and cases where everything make sense to the user and their commands don't crash the entire program.
