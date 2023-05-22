import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class word {
    final static String DOT = ".";

    static String theWords[];
    private game Game;
    private player Player;
    //Normal Constructor
    public word()
    {

    }
    //Parameterized constructore
    public word(game Game,player Player) {
        this.Game=Game;
        this.Player=Player;

    }

    /**
     * Counts words in file
     * @return
     */
    protected int countWordsInFile()	{
        Scanner aScanner = null;
        int counter = 0;

        try {
            aScanner = new Scanner(new File(Player.wordsFile) );
        } catch ( FileNotFoundException e ) {}

        while ( aScanner.hasNext() )	{
            aScanner.nextLine();
            counter ++;
        }
        aScanner.close();
        return counter;
    }

    /**
     * Allots words to person and creates ... so that it is updated a and when player plays
     */
    protected  void initWords()	{
        Scanner aScanner = null;

        int counter = countWordsInFile();

        try {
            aScanner = new Scanner(new File(Player.wordsFile) );
        } catch ( FileNotFoundException e ) {}

        theWords = new String[counter];
        counter = 0;
        while ( aScanner.hasNext() )	{
            theWords[counter++] = aScanner.nextLine();
        }
        aScanner.close();
        game Game= new game();
        Game.player1Words[0][0]  = theWords[new Random().nextInt(counter )];
        Game.player1Words[1][0]  = theWords[new Random().nextInt(counter )];

        for ( int id = player.PLAYER_1;id <= player.PLAYER_2; id++ )	{
            Game.player1Words[id][1] = Game.player1Words[id][0].replaceAll(".", DOT);

        }
    }

    /**
     * Guessing the word ,If guessed word matches Then success
     * @param input input entered
     * @param id of Player
     * @return
     */
    protected  boolean guess(Scanner input, int id)	{
        String theGuess;
        boolean rValue = false;
        int 	position = 0;
        System.out.print("player " + ( 1 + id ) + " turn (" + Game.player1Words[id][1] + "): ");
        if ( input.hasNext() )	{
            theGuess = input.next();
            if ( rValue = ( ( position = Game.player1Words[id][0].indexOf(theGuess) ) >= 0 ) )	{
                Game.player1Words[id][1] = Game.player1Words[id][1].substring(0, position ) +
                        theGuess + Game.player1Words[id][1].substring(position + 1);
                System.out.println( "================================================");
                System.out.println( "The guess was correct: " + Game.player1Words[id][1] );
                System.out.println( "================================================");
                Picture.calculateCorrecntessAndPrint(id);
                Picture.printThePicture(id);
            }
        }
        return rValue;
    }
}
