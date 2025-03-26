/*
Andrew Achusim
03/26/2025
Purpose: To add two tegether two integer matricies by implementing concurrent processing through Java multi-threading.
For ThreadOperation.java, create an object class that takes matracies as input from the constructor and create a 
quadrant indicator.
Sources:
    1.) I learend about Threads and how to implement them @ https://www.w3schools.com/java/java_threads.asp.
    2.) I learned about switches and how to use them @ https://www.w3schools.com/java/java_switch.asp.
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
        // An integer array varibale with four spaces.
        int[] molk = new int[4];

        // An integer variable for containing the value midway through the rows.
        int rowMid = rows/2;
        // An integer variable for containing the value midway through the columns.
        int colMid = columns/2;

        // A switch statement that runs through one of the four cases
        // if the value of the String object matches one of the cases.
        switch(quadrant)
        {
            // Case 1: Collects the revealent data for the "upper left" of the given matrix.
            case "upper left":
                molk[0] = 0;
                molk[1] = rowMid;
                molk[2] = 0;
                molk[3] = colMid;
                break;
            // Case 2: Collects the revealent data for the "upper right" of the given matrix.
            case "upper right":
                molk[0] = 0;
                molk[1] = rowMid;
                molk[2] = colMid;
                molk[3] = columns;
                break;
            // Case 3: Collects the revealent data for the "lower left" of the given matrix.
            case "lower left":
                molk[0] = rowMid;
                molk[1] = rows;
                molk[2] = 0;
                molk[3] = colMid;
                break;
            // Case 4: Collects the revealent data for the "lower right" of the given matrix.
            case "lower right":
                molk[0] = rowMid;
                molk[1] = rows;
                molk[2] = colMid;
                molk[3] = columns;
                break;
        }

        // Returns the collected value of the integer array object.
        return molk;
    }
}