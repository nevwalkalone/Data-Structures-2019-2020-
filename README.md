# Stack and FIFO Queue Implementation

This project was completed for the Data Structures course of the [Department of Computer Science of the Athens University of Economics and Business](https://www.dept.aueb.gr/el/cs), during the Fall semester of 2019-2020.

The goal of this assignment was to get acquainted with basic abstract data types such as stacks and FIFO queues. This project consists of a StringStack implementation, a StringQueue implementation, as well as an application based on the StringStack. **No built in functions were allowed**, these 2 data types were implemented from scratch, using linked lists.

The application is a client program called **Thiseas.java** which is based on the StringStack implementation, as mentioned above, and which crosses a maze in order to find an exit. The maze is in the form of a .txt file and must be given as an argument to the program. For further details follow the project assignment link listed below.

## Useful Reads

For a full overview it is highly suggested that you read:

- [Project Assignment](https://github.com/nevwalkalone/Stack-and-FIFO-Queue/blob/main/assignment-report/project1-assignment.pdf), that has all the details about the data types implementation and the application.
- [Project Report](https://github.com/nevwalkalone/Stack-and-FIFO-Queue/blob/main/assignment-report/project1-report.pdf), that contains the analysis of the code.

## Dependencies

- [Java (jdk13)](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html).

## Program Execution

To run the Thiseas program:

1. **Clone** repository and change directory to src.

   ```console
   git clone https://github.com/nevwalkalone/Stack-and-FIFO-Queue.git
   cd src
   ```

2. **Compile** the program.

   ```console
   javac Thiseas.java
   ```

3. **Run** the program. Entire path for the .txt file that represents the maze, must be specified. maze-example found in this repo can be used as an example of a maze.

   ```console
   java Thiseas path_to_file/filename.txt
   ```

Program displays the exact path and moves from the entrance to the exit of the maze, if any exit exists. If not, an appropriate message will be printed.

## Contributions

If you want to contribute, you can always create a pull request or open an issue.
