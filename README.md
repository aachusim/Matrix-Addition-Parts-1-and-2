# Matrix-Addition-Parts-1-and-2
# Instructions for Part 1
For part 1 you need to create both of the above classes.

    1. In the main method of Main, instantiate four ThreadOperation objects, start them, and join them. Each ThreadOperation will take as input (through the constructor) two matrices and a quadrant indicator. The indicator could be a String, an int, an enum or a set of indexes. It’s up to you.
    2. In Main.java, write a static method named print2dArray that takes a two-dimensional array as input and prints it out with the rows and columns lined up. You must use System.out.printf.
    3. Instantiate a test 2d array with any values you like in main and use it to verify that print2dArray works.
    4. The filename should be given through the command prompt and passed into main via String[] args
    5. Open and connect to the file using a Scanner.
    6. Read in the number of rows and columns and save these in local variables in main.
    7. Read in the first and second matrices (two-dimensional arrays) from the file. I recommend writing a method to accomplish this task and calling the method twice (once for each matrix). Consider using this method header: 

public static int[][] matrixFromFile(int rows, int columns, Scanner file_reader)

NOTE: if you are using a static scanner or an object-oriented approach then you may not need to pass these arguments to the method.

Information on the file format
    1) the first line has two numbers, R and C (R rows, C columns), the size of both matrices A and B
    2) the next R lines each has C elements for one of the rows of A
    3) the next R lines each has C elements for one of the rows of B
Example:
4 6
2 3 1 2 5 1
3 1 2 2 2 4
1 2 3 2 7 2
3 6 1 5 1 3
6 5 4 1 4 3
3 3 2 2 1 1
7 5 4 3 2 5
2 1 8 4 8 4

For the above example, 4 is the number of rows, 6 is the number of columns. The first matrix values are highlighted in green and the second matrix is highlighted in red. The result of the sum should be as follow:
8 8 5 3 9 4
6 4 4 4 3 5
8 7 7 5 9 7
5 7 9 9 9 7

Example: The upper left quadrants of the corresponding matrices (highlighted in yellow) will be added together
4 6
2 3 1 2 5 1
3 1 2 2 2 4
1 2 3 2 7 2
3 6 1 5 1 3
6 5 4 1 4 3
3 3 2 2 1 1
7 5 4 3 2 5
2 1 8 4 8 4

For your convenience, three test cases are provided: matrix1.txt, matrix2.txt, and matrix3.txt.


One of the goals is to minimize the resource usage, such as memory and processor cycles. Explain how multi-threaded code accomplishes this goal in your document. YOU MUST ANSWER THIS QUESTION IN A COMMENT AT THE TOP OF YOUR Main CLASS. Tell me about blocking on I/O, multicore machines, how sluggish humans are, etcetera, and then tell me how multi-threading helps. Compare threads to processes and tell me the advantages of multi-threading. It doesn’t have to be long. Three sentences will suffice if they are good sentences.

# Instructions for Part 2
Put it all together to read in the data from file, spawn four threads, and allocate the task of summing each quadrant pair to a separate thread.

Consider which methods should be relocated from Main to ThreadOperation. What tools does ThreadOperation need to have access to in order to do its job?

After the threads complete their computation, the results need to be stored in the matrix C, another 2-dimensional array variable in main.

Your program should work for any size matrices.

In ThreadOperation write a method named getQuadrantIndexes that determines the indexes needed to iterate over one of the four quadrants. For instance, your method might take as input the row count, column count, and a quadrant String, and then return 4 numbers in an array: row start, row end, column start, column end. Although I’m demonstrating this method using a String to indicate the quadrant, an integer would also work fine, and an enum with four values would be best.

public static int[] getQuadrantIndexes(int rows, int columns, String quadrant)

Called as int[] indexes = getQuadrantIndexes(rows, columns, “upper left”);

There are many different (and some better) ways to get the indexes, but this is the way that I think will make sense to the most people.

So how do you actually calculate the indexes needed? You will need four conditions (if, elseif, elseif, else) for the four quadrants. Figure out the pattern based on the following examples:
Example 1
	6 columns
8 rows 	Row indexes from 0 to 3
Column indexes from 0 to 2	Row indexes from 0 to 3
Column indexes from 3 to 5
	Row indexes from 4 to 7
Column indexes from 0 to 2	Row indexes from 4 to 7
Column indexes from 3 to 5
Example 2
	12 columns
7 rows	Row indexes from 0 to 2
Column indexes from 0 to 5	Row indexes from 0 to 2
Column indexes from 6 to 11
	Row indexes from 3 to 6
Column indexes from 0 to 5	Row indexes from 3 to 6
Column indexes from 6 to 11

Example 3
	5 columns
9 rows	Row indexes from 0 to 3
Column indexes from 0 to 1	Row indexes from 0 to 3
Column indexes from 2 to 4
	Row indexes from 4 to 8
Column indexes from 0 to 1	Row indexes from 4 to 8
Column indexes from 2 to 4

Example 4 Fill-in-the-blanks
	C columns
R rows	Row indexes from 0 to _____
Column indexes from 0 to _____	Row indexes from 0 to _____
Column indexes from _____ to _____
	Row indexes from _____ to _____
Column indexes from 0 to _____	Row indexes from _____ to _____
Column indexes from _____ to _____

Main.java should be organized as follows (Strongly consider using the following notes as comments):
    • Your main method opens a text file using the file name from the command line, and reads in the number of rows, the number of columns, and two matrices, A and B, into two 2-dimensional array variables.
    • Instantiate four ThreadOperation objects and pass them the information they need to sum up paired quadrants, including a reference to a result matrix C. Note that C should have the same dimensions as A and B.
    • Start up all the threads and use join to make sure they finish before printing.
    • Print out the summed matrix.

ThreadOperation.java Organization
I recommend formatting the ThreadOperation constructor as follows:
<<constructor>>ThreadOperation(A : int[][], B : int[][], C : int[][], String quadrant)
run() : void

A, B, and C all refer to complete matrices (no sub-matrices) of the same size. As long as the Thread is not accessing the same row and column as another Thread, there’s no problem!
