/**
 * This class shows the picture generated
 */

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.util.Vector;
import java.util.Random;
import java.io.FileNotFoundException;

public class Picture {
    final static String DOT = ".";
    final static Random randomNumberGenerator 	= new Random();
    final static Vector[]thePictures 		= new Vector[2];
    static int[] correctGuessedInPrecentage 	= new int[2];

    /**
     * Displaying picture enerates random numbers
     * @param id
     */
    protected static void printThePicture(int id)	{
        Vector<String> thePicture = thePictures[id];
        for ( int index = 0; index <  thePictures[id].size(); index++ )	{
            System.out.print("       ");
            for ( int xOuter = 0; xOuter < thePicture.elementAt(index).length(); xOuter++ )	{
                if ( randomNumberGenerator.nextInt(101) <= correctGuessedInPrecentage[id] )
                    System.out.print(thePicture.elementAt(index).charAt(xOuter) );
                else
                    System.out.print(DOT);
            }
            System.out.println();
        }
    }

    /**
     * Fills the picture
     * @param fileName Takes filename as i/p
     * @param id of player
     */
    protected static void fillThePicture(String fileName, int id)     {
        thePictures[id] = new Vector();
        try {
            Scanner aScanner = new Scanner(new File( fileName) );
            while ( aScanner.hasNextLine() )        {
                thePictures[id].add( aScanner.nextLine() );
            }
        } catch ( Exception e ) {}
    }

    /**
     * Calculates percentage of picture to be printed
     * @param id
     */
    protected static void calculateCorrecntessAndPrint(int id )	{
        int soManyGuessed = game.player1Words[id][1].length();
        String tmpString = game.player1Words[id][1];
        for (int position = 0; position < game.player1Words[id][1].length(); position ++ )	{
            if  ( ("" + game.player1Words[id][1].charAt(position)).equals(DOT) )
                soManyGuessed--;
        }
        correctGuessedInPrecentage[id] = (int)( 100.0 *
                ( (double)soManyGuessed / (double) game.player1Words[id][1].length() ) );
    }

}
