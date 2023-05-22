import java.util.Scanner;

public class game {
    /**
     * Each player gets a chance to guess
     * a picture is shown partly if guessed right
     * and the one who guesses first completely wins
     */
    final static String DOT = ".";
    static String[][] player1Words 			= new String[2][2];
    protected void playTheGame()	{
        Scanner userGuessInput           = new Scanner(System.in);
        boolean oneIsDone  = false;
        new word().initWords();
        do {
            for (int id = player.PLAYER_1; id <= player.PLAYER_2; id++ )	{
                new word().guess(userGuessInput, id);
                oneIsDone |=  ( player1Words[id][1].indexOf(DOT) < 0 );
            }

        } while ( ! oneIsDone );
        for (int id = player.PLAYER_1; id <= player.PLAYER_2; id++ )	{
            if (  player1Words[id][1].indexOf(DOT) < 0 ) {
                System.out.println("This word guessed correctly was: " + player1Words[id][0]);
            }
        }
        userGuessInput.close();
    }


}
