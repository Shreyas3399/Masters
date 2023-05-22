/*
 * StringIntegerArrays.java
 *  
 * Version: $1.0$
 *
 */
import java.util.Arrays;
import java.util.Scanner;
/**
 * Solving the 9 questions
 * without using control structures
 * @author Shireen Maini
 * @author Shreyas Belkune
 */
public class StringIntegerArrays {
    /**
     * AFter being assigned the value we are using it solve the 9 questions without control structures
     * @param aString
     * @param bString
     * @param cString
     * @param dString
     * @param eString
     * @param fString
     * @param gString
     * @param hString
     * @param iString
     */
    public static void solve(String aString,String bString,String cString,String dString,String eString,String fString,String gString,String hString,String iString)
    {
        //Prints True if aString and bString share same memory location,else false
        System.out.println("aString==bString " + (aString==bString));
        //Prints True if aString and dString share same memory location,else false
        System.out.println("aString==dString " + (aString==dString));
        //Prints True if aString and bString share same content,else false
        System.out.println("aString equals bString " + (aString.equals(bString)));
        //Prints True if hString and iString share same memory location,else false
        System.out.println("hString==iString " + (hString==iString));
        //Prints True if hString and iString share same content,else false
        System.out.println("hString equals iString " + (hString.equals(iString)));
        //Prints True if aString and dString share same content,else false
        System.out.println("aString equals dString " + (aString.equals(dString)));
        //Extracts from dString according to positions given by aString and prints it
        System.out.println("Extracting digits from dString "+ (dString.substring((aString.charAt(0)-'0')-1,(aString.charAt(1)-'0'))));
        //Prints value at eString according to index of fString
        System.out.println("Char of eString " + (eString.charAt(fString.indexOf('*'))));
        //Sorting bString by 1st breaking it to a char array
        char[] charArr=bString.toCharArray();
        //Using Array sort function
        Arrays.sort(charArr);
        //Converting charArray to String
        String sortArray = String.valueOf(charArr);
        //Printing bString after Sort
        System.out.println("Sorting digits in bString " + sortArray);

    }
    /**
     * The main program
     * Initialised the variables
     * and calls the solve method
     */
    public static void main( String []args ) {
        //Declaring variable names
        String aString,bString,cString,dString,eString,fString,gString,hString,iString;
        //Initialising variables.If args array has length of 1 ,1st block will be initialised else 2nd
        if ( args.length == 1 ) {
            aString = "123425" + "25";
            bString = "12342525";
            cString = "5";
            dString = "25" + "2" + cString;
            eString = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "A, B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = aString + ( bString + cString ) + dString;
            hString = "2525" +  "+" + "2525";
            iString = "2525+2525";
        } else {
            aString = "12342" + new String("" + 5) +  "25";
            bString = "12342525";
            cString = "5";
            dString = "" + "25" + "" + "2" + cString;
            eString = "b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = ( ( aString + bString )  + "" + 5 ) + dString;
            hString = "2525" +  "+" + "2525";
            iString = "25" + ( 2 + 5 ) + "+2525";
        }
        //Passing the variables to method solve.Which answers the 9 questions asked
        solve(aString,bString,cString,dString,eString,fString,gString,hString,iString);

    }
}
