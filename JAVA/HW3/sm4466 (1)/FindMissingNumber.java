/*
 * FindMissingNumber.java
 *
 * Version: $1.3$
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Reading a file with all n bit integers
 * from 0 in any order
 * and one of the num is missing
 * Finding the missing number
 * @author Shireen Maini
 * @author Shreyas Belkune
 */
import static java.lang.Math.pow;

public class FindMissingNumber {
    //This stores the no of bits digits we are entering
    public static int no_bits;

    /**
     * After reading from the file we are converting it
     * to binary and adding it up bit by bit to the arr
     * @param line line we are reading from fil
     * @param arr  Summing up the bits at every position
     */
    public static void binary(String line,int[] arr)
    {
        //used for indexing position
        int k=0;
        //Converting the number as String to long
        Long num=Long.parseLong(line);
        //Converting the number to binary
        while(num!=0)
        {
            //Summing up the remainder bit position
            arr[k]+=num%2;
            //increasing index for next position
            k+=1;
            //Reducing no by half
            num/=2;
        }
    }

    /**
     * Finding the missing binary number and converting it to decimal
     * @param arr
     */
    public static void solve(int []arr)
    {
        //Used for finding the missing binary number
        long[] ans=new long[no_bits];
        //Sum of all bits at each position is value
        long value=(long)(pow(2,no_bits-1));
        //used for converting binary to decimal
        long sum=0;
        //Finding the missing no
        for(int i=0;i<no_bits;i++)
        {
            ans[i]=value-arr[i];
        }
        //Converting binary number to decimal
        for(int i=no_bits-1;i>=0;i--)
        {
            sum+=(int)pow(2,i)*ans[i];
        }
        //printing the number
        System.out.println(sum);
    }

    /**
     * Reading from file calling binary function and solve function
     * @param filename
     */
    public static void readFromFile(String filename) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(filename));
        ) {
            //Reading no of bits from file name
            no_bits=Integer.parseInt(String.valueOf(filename.charAt(0)));
            //Creating an array that can hold that many bits
            int[] arr=new int[no_bits];
            //Reading i/p from file
            String line= input.readLine();
            //Reading file till null
            while(line!=null)
            {
                binary(line,arr);
                line= input.readLine();
            }
            //Calling solve function for guessing missing number and converting it to decimal no
            solve(arr);

        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() ); //throws exception if file not found
        }

    }
    /**
     * The main program
     * Calls the file
     */
    public static void main(String args[])
    {
        readFromFile(args[0]);
    }
}
