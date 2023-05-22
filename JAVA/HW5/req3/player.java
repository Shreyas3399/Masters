public class player {
    final static int PLAYER_1=0;
    final static int PLAYER_2=1;
    static String wordsFile;
    /**
     * Two players are trying to guess
     * the word given to them letter by letter
     * and if they guess it correctly a percentage of picture appears
     * whoever guesses it first gets to see the entire image
     * Finding the missing number
     * @author Hans Peter Bischof
     * @modified by Shireen Maini and Shreyas Belkune
     */

    /**
     * Reads what's given in terminal
     * @param args
     */
    private static void parseArgs(String[] args)	{
        for ( int index = 0; index < args.length; index += 2 )	{
            if ( args[index].equals("-player1")  )	{
                Picture.fillThePicture( args[index + 1], 0);
            } else if ( args[index].equals("-player2")  )	{
                Picture.fillThePicture( args[index + 1], 1);
            } else if ( args[index].equals("-words" ) )	{
                wordsFile = args[index+1];
            }
        }
    }
    private void startTheGame(String[] args)	{
        parseArgs(args);
        new game().playTheGame();
    }

    public static void main(String[] args ) {
        player Player=new player();
        Player.startTheGame(args);
    }

}
