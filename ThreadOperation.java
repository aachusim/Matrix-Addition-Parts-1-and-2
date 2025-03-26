/*
Andrew Achusim
03/24/2025
Purpose: To add two tegether two integer matricies by implementing concurrent processing through Java multi-threading.
For ThreadOperation.java, create an object class that takes matracies as input from the constructor and create a 
quadrant indicator.
Sources:
    1.) I learend about Threads and how to implement them @ https://www.w3schools.com/java/java_threads.asp.
*/

public class ThreadOperation extends Thread
{
    // private variables
    private int[][] milk;
    private int[][] malk;
    private int[][] butter;
    private int rowStart;
    private int rowEnd;
    private int colStart;
    private int colEnd;

    // constructor
    public ThreadOperation(int[][] milk, int[][] malk, int[][]butter, int rowStart, int rowEnd, int colStart, int colEnd)
    {
        this.milk = milk;
        this.malk = malk;
        this.butter = new int[rowEnd - rowStart][colEnd - colStart];
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
        this.colEnd = colEnd;
    }

    // An override of the run method found in Thread.
    @Override
    public void run()
    {
        // A for loop that loops for the value of rowEnd.
        for(int i = rowStart; i < rowEnd; i++)
        {
            // A for loop that loops for the value of colEnd.
            for(int j = colStart; j < colEnd; j++)
            {
                // Sets butter to the sum of milk and malk.
                butter[i - rowStart][j - colStart] = milk[i][j] + malk[i][j];
            }
        }
    }

    // getter
    public int[][] getButter(){return butter;}

    // A static integer array method that determines the indexes needed to iterate over one of the four quadrants.
    public static int[] getQuadrantIndexes(int rows, int columns, String quadrant)
    {
        // Filler for javac
        int[] molk = new int[rows];
        return molk;
    }
}