/*
Andrew Achusim
03/24/2025
Purpose: To add two tegether two integer matricies by implementing concurrent processing through Java multi-threading.
For Main.java, start and join four ThreadOperatiion objects, write a static method called print2dArray.
Sources:
    1.) I answered the commented question @ https://stackoverflow.com/questions/3330430/does-java-have-support-for-multicore-processors-parallel-processing.
    2.) I learend about Threads and how to implement them @ https://www.w3schools.com/java/java_threads.asp.
    3.) I learend about all the Thread class has to offer @ https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html.
    4.) I learned how to read in files from the command line @ https://stackoverflow.com/questions/1055318/using-command-line-argument-for-passing-files-to-a-program.

This code is provided to give you a
starting place. It should be modified.
No further imports are needed.
To earn full credit, you must also
answer the following question:

Q1: One of the goals of multi-threading
is to minimize the resource usage, such
as memory and processor cycles. In three
sentences, explain how multi-threaded
code accomplishes this goal. Consider
writing about blocking on I/O, multicore 
machines, how sluggish humans are,
threads compared to processes, etcetera,
and connect these issues to 
multi-threading.

A1: Multi-threading allows programs to run multiple tasks concurrently, 
enabling efficient utilization of multicore processors by executing 
different threads on separate cores, which speeds up execution.
It also minimizes resource usage by allowing threads to share memory space,
avoiding the need of creating separate processes, thus reducing the 
consumption of memory and processor cycles. Multi-threading can also
handle blocking I/O operations like waiting for file data by 
allowing other threads to continue executing while one thread waits,
thus improving overall system responsiveness and efficiency.

*/
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
        // A String object that contains the data from the command-line.
        String fileName = args[0];
        // A file object that contains the data from fileName.
        File file = new File(fileName);

        try(Scanner fileReader = new Scanner(file))
        {
            // An integer variable that reads the number of rows from the file.
            int rows = fileReader.nextInt();
            // An integer variable that reads the number of columns from the file.
            int columns = fileReader.nextInt();

            // A pair of integer array varaiables that reads the matrices from the file.
            int[][] milk = matrixFromFile(rows, columns, fileReader);
            int[][] malk = matrixFromFile(rows, columns, fileReader);

            // Four ThreadOperation objects for submatrix addition.
            ThreadOperation t1 = new ThreadOperation(milk, malk, new int[rows / 2][columns / 2], 0, rows / 2, 0, columns / 2);
            ThreadOperation t2 = new ThreadOperation(milk, malk, new int[rows / 2][columns - columns / 2], 0, rows / 2, columns / 2, columns);
            ThreadOperation t3 = new ThreadOperation(milk, malk, new int[rows - rows / 2][columns / 2], rows / 2, rows, 0, columns / 2);
            ThreadOperation t4 = new ThreadOperation(milk, malk, new int[rows - rows / 2][columns - columns / 2], rows / 2, rows, columns / 2, columns);


            // Starts all threads.
            t1.start();
            t2.start();
            t3.start();
            t4.start();

            // Joins all threads to ensure they are complete before printing the result.
            t1.join();
            t2.join();
            t3.join();
            t4.join();

            // Combines the results into one matrix.
            int[][] result = new int[rows][columns];
            combineResults(result, t1.getButter(), 0, 0);
            combineResults(result, t2.getButter(), 0, columns / 2);
            combineResults(result, t3.getButter(), rows / 2, 0);
            combineResults(result, t4.getButter(), rows / 2, columns / 2);

            // Print the final result.
            print2dArray(result);

        }
        // What is executed when an exception is found.
        catch(IOException | InterruptedException e)
        {
            e.printStackTrace();
			System.out.println("An error has occured.");
			System.exit(1);
        }

	}

    // A static void method to print a 2D array with formatted output.
    public static void print2dArray(int[][] array)
    {
        // A for loop that loops for the value of array.legnth.
        for(int i = 0; i < array.length; i++)
        {
            // A for loop that prints out the 2D array.
            for(int j = 0; j < array[i].length; j++){System.out.printf("%4d", array[i][j]);}
            // Prints out a line break.
            System.out.println();
        }
    }

    // A static integer array method to read a matrix from the file.
    public static int[][] matrixFromFile(int rows, int columns, Scanner fileReader)
    {
        // An intger array object.
        int[][] matrix = new int[rows][columns];
        // A for loop that loops for the value of row.
        for(int i = 0; i < rows; i++)
        {
            // A for loop that loops for the value of columns.
            for(int j = 0; j < columns; j++)
            {
                // Sets the data from the Scanner object to an index in the integer array object.
                matrix[i][j] = fileReader.nextInt();
            }
        }
        // Returns teh value of the integer array object.
        return matrix;
    }

    // A static void method to combine submatrix results into the final matrix.
    public static void combineResults(int[][] result, int[][] submatrix, int startRow, int startCol)
    {
        // A for loop that loops for the value of submatrix.length.
        for(int i = 0; i < submatrix.length; i++)
        {
            // A for loop that loops for the value of submatrix[i].length.
            for(int j = 0; j < submatrix[i].length; j++)
            {
                // Sets the data from the one integer array object to another interger array object.
                result[startRow + i][startCol + j] = submatrix[i][j];
            }
        }
    }

}
