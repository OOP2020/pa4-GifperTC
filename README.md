# KU COVID-19 Queue Runner

 This project is a prototype graphical UI idea for simulate a giveaway event for COVID-19

 In the current COVID-19 situation there are many faculties that have free facemask or alcohol bottle giveaways for those who can’t afford to buy them in the market. But there was a problem about people who pretend they haven’t received the giveaway yet and giveaway items left is not enough for everyone. 

 So I got the idea to manage giveaways by letting each person type their ID number 

(for this project I will simulate the app for KU student ID input)

And the application will run a queue number for the user to receive the giveaway item. If someone uses the ID again, the application will decline the request, which will help solve the problem.

*This application is a part of Programming assignment Final project*

## Getting Started

- Download the jar file and unzip it to any particular folder you like
- type this command to the command line in the directory you unzipped the files

*For Java 8*
```
java -jar KUCovidQueueRunner.jar
```
*For Java 11 or upper*
```
java --module-path /path/to/your/JavaFX/lib --add-modules javafx.controls,javafx.fxml -jar KUCovidQueueRunner.jar
```
## Application components
1. Menu page
2. Id input page
3. Queue card display pop up window

## Basic instructions for the application

(1) Select an item you want to claim from the combobox.

![alt text](https://i.imgur.com/epY1Vhj.jpg=50x)
![alt text](https://i.imgur.com/A172usI.png=50x)

(2) Press the proceed button, and it will lead you to the id page, then type your KU student ID like in the example picture below.

![alt text](https://i.imgur.com/4bq7Y7E.png=50x)

(3) Press the Run queue button, and the Queue card display will popup.

![alt text](https://i.imgur.com/qWwOuZ0.png=50x)

(4) Proceed to claim your item from the staff members. (simulation complete)

#####Note

- If you run queue again with the same id number, an error message will come up

![alt text](https://i.imgur.com/Q4rvyc3.png=50x)

### Bulit with
- [JavaFX](https://gluonhq.com/products/javafx/) - Application platform
- [Scene Builder](https://gluonhq.com/products/scene-builder) - Graphical UI design

## Author
- Nutta Sittipongpanich ID: 6210545475

- SKE 17 Kasetsart University