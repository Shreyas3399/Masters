/*
 * Picture.java
 *
 * Version: $1.20$
 *
 */
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
/**
 * Two players are trying to guess
 * the word given to them letter by letter
 * and if they guess it correctly a percentage of picture appears
 * whoever guesses it first gets to see the entire image
 * Finding the missing number
 * @author Shireen Maini
 * @author Shreyas Belkune
 */

public class Picture {
    //Used the readWordsFromFile,readUserInput,getWord code as given by Professor HP in https://www.cs.rit.edu/~hpb/Lectures/2221/605/
    static int storeWords=0;
    static Vector<String> theWords = new Vector<String>(5);
    static final String[] words=new String[5];
    static final Scanner readGuess = new Scanner(System.in);//creating a scanner object to allow user to input
    static Vector<Vector<String>> pictureArray=new Vector<Vector<String>>(2);
    static Vector<String> pic1 = new Vector<>();
    static Vector<String> pic2= new Vector<>();
    static Vector<String> wordsPrevIteration=new Vector<>(2);

    /**
     * Reads all words from the filename and makes the words available.
     * @param  fileName the name of the file containing words which will be randomly alloted to players.
     * Each word on a new line.
     * This method will terminate the program if the file does not exist.
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName))
        ) {
            int counter = 0;//used as indexing while counting
            while  ( ( words[counter++] = input.readLine() )  != null ) //inputting the words from the file into theWords array
                theWords.add(words[counter-1]);
                storeWords ++;


        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() ); //throws exception if file not found
        }
    }
    /**
     * Reads a letter from user  and returns it.
     * @return The word read from the terminal.
     */
    public static String readUserInput() {
        String guess = "";
        System.out.print("> ");
        if  ( readGuess.hasNext() )	{
            guess = readGuess.nextLine();
        }
        return guess;
    }
    /**
     * Returns a randomly selected word read from file
     * @return A randomly selected word.
     */
    public static String getWord() {
        return theWords.get(new Random().nextInt(storeWords));
    }

    /**
     * Checks if random function is not giving duplicates
     * @param arr contains randomly generated numbers
     * @param num checks if the no generated is not already present in array
     * @return true if it already exists in array
     */
    public static boolean contains(int[] arr,int num)
    {  //Runs the entire length of array
        for(int i=0;i<arr.length;i++)
        {
            //Checks if number is present
            if(num==arr[i])
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes the file name reads picture and stores it as vector os strings
     * @param pic Vector of Strings
     * @param filename
     * @return the generated vector of strings i.e the pic
     */
    private static Vector<String> readPicturePercentage(Vector<String> pic,String filename) {
        try(
                BufferedReader input = new BufferedReader( new FileReader(filename))
        ){
            //Reads line till null is encountered
            String line=input.readLine();
            while(line!=null) {
                pic.add(line);
                line = input.readLine();
            }
            //return pic;
        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() ); //throws exception if file not found
        }
        return pic;
    }

    /**
     * Prints a percentage of the picture alloted
     * @param percentage guessed right
     * @param i the index of player
     */
    private static void printPicture(int percentage,int i) {
        Random random=new Random();
        //Looping through the picture size
        for (int picIndex = 0; picIndex < pictureArray.get(i).size(); picIndex++)
        {
            //length of each row of pic
            int lengthPic = pictureArray.get(i).get(picIndex).length();
            //the row of the pic
            String toPrint = pictureArray.get(i).get(picIndex);
            //No of shapes to be displayed
            int value = (percentage * lengthPic) / 100;
            int[] randomArray = new int[value];
            //Filling values with -1
            Arrays.fill(randomArray, -1);
            //Generating random values used as index to print
            for (int j = 0; j < value; j++)
            {
                int randomNum;
                do {
                    randomNum = random.nextInt(lengthPic);
                } while (contains(randomArray, randomNum) == true);
                randomArray[j] = randomNum;
            }
            //Printing out those positions equal to random index else just ...
            for (int index = 0; index < lengthPic; index++)
            {
                int flag = 1;
                for (int index2 = 0; index2 < value; index2++)
                {
                    if (index == randomArray[index2])
                    {
                        System.out.print(toPrint.charAt(index));
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1)
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    /**
     * Taking wordChosen and counting the no of letters in each word
     * @param wordsChosen the words
     * @param alphabetPlayer1Count used to store the count of letters in that word for Player 1
     * @param alphabetPlayer2Count used to store count of letters in that word for Player 2
     */
    private static void alphacount(String[] wordsChosen, int[] alphabetPlayer1Count, int[] alphabetPlayer2Count) {
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<wordsChosen[i].length();j++)
            {
                if(i==0) {
                    alphabetPlayer1Count[wordsChosen[i].charAt(j) - 'a']++;
                }
                else {
                    alphabetPlayer2Count[wordsChosen[i].charAt(j) - 'a']++;

                }
            }
        }
    }

    /**
     * Updating the word such that whatever progress user had made in last chance he can see it
     * @param wordToPush the part identified
     * @param i index
     * @param wordsPrevIteration keeps a track of the players latest guesses
     * @param wordsChosen the words player has to guess
     */
    private static void updating(String wordToPush, int i, Vector<String> wordsPrevIteration, String[] wordsChosen) {
        //If the 0th index is not updated we update it first then add 1
        if(i==1&&wordsPrevIteration.isEmpty())
        {
            wordsPrevIteration.add(i-1,".".repeat(wordsChosen[i].length()));
            wordsPrevIteration.add(i,wordToPush);

        }
        //else if size is exactly two we remove previous progress and add new one)
        else if(wordsPrevIteration.size()==2){
            wordsPrevIteration.remove(wordsPrevIteration.get(i));
            wordsPrevIteration.add(i, wordToPush);
        }
        //else we just push it
        else {
            wordsPrevIteration.add(i, wordToPush);
        }

    }


    /**
     * Each player gets a chance to guess
     * a picture is shown partly if guessed right
     * and the one who guesses first completely wins
     * @param args used for Command line arguments
     */
    public static void GuessWord(String [] args){
        //flag to check if any person has won
        int won=0;
        //keeps a count of no of letters guessed for both players
        int[] count=new int[2];
        //letter entered by player
        String letterGuessed;
        //words alloted to each player
        String[] wordsChosen= new String[2];
        //tracks the progress of each player
        Vector<String> track=new Vector<>();
        //Used for keeping a count of no of letters in each chosen word
        int[] alphabetPlayer1Count= new int[26];
        int[] alphabetPlayer2Count= new int[26];
        //Alotting words to each
        for(int i=0;i<2;i++)
        {
            wordsChosen[i]=getWord();
            theWords.remove(wordsChosen[i]);
            wordsChosen[i]=wordsChosen[i].toLowerCase();
        }
        String wordToPush=".".repeat(wordsChosen[0].length());
        //Reading the pictures according to word chosen
        pictureArray.add(0, readPicturePercentage(pic1,(wordsChosen[0]+".txt")));
        pictureArray.add(1, readPicturePercentage(pic2,(wordsChosen[1]+".txt")));
        //Used for keeping a count of no of letters in each chosen word
        alphacount(wordsChosen,alphabetPlayer1Count,alphabetPlayer2Count);
        //loop runs till any player does not win
        do {
            //Two players can only play
            for(int i=0;i<2;i++) {
                System.out.println("Chance of Player"+(i+1));
                do {
                    //Runs till single letter not entered
                    if(wordsPrevIteration.size()<2)
                    {
                        System.out.print("Enter a letter to guess your word " + " ( " +".".repeat( wordsChosen[i].length() )+ ")");

                    }
                    else {
                        System.out.print("Enter a letter to guess your word " + " ( " + wordsPrevIteration.get(i) + ")");
                    }
                    //Taking user input
                    letterGuessed = readUserInput();
                    //Converting to lowercase
                    letterGuessed = letterGuessed.toLowerCase();//converts to lowercase
                    System.out.println(letterGuessed);
                } while (letterGuessed.length() != 1);
                //Used for strong word guessed
                char[] wordGuessed= new char[wordsChosen[i].length()];
                //Checking if letter is present in word
                if(wordsChosen[i].indexOf(letterGuessed)!=-1)
                {
                    if(track.size()!=0&& track.size()-1>=i)
                    {
                        wordGuessed=track.remove(i).toCharArray();
                    }
                    String wordChosen=wordsChosen[i];
                    System.out.println("Right one!");
                    //If it is it runs through the length of loop
                    for(int j=0;j<wordChosen.length();j++)
                    {   //If letter matches the correct position
                        if(letterGuessed.charAt(0)==wordChosen.charAt(j))
                        {   //For player 1 count increases and it's stored that he guessed it
                            if(i==0 && alphabetPlayer1Count[letterGuessed.charAt(0)-'a']!=0) {
                            wordGuessed[j] = letterGuessed.charAt(0);//Storing the letter at that index
                            count[i]++;//increasing count of letters guessed
                            alphabetPlayer1Count[letterGuessed.charAt(0)-'a']--;//decreasing count to check if letter done or still exists
                           }
                            //For player 1 count increases and it's stored that he guessed it
                            else if(i==1 && alphabetPlayer2Count[letterGuessed.charAt(0)-'a']!=0)
                            {
                                wordGuessed[j] = letterGuessed.charAt(0);//Storing the letter at that index
                                count[i]++;//increasing count of letters guessed
                                alphabetPlayer2Count[letterGuessed.charAt(0)-'a']--;//decreasing count to check if letter done or still exists
                            }
                        }
                        //If already there continue
                        else if(wordGuessed[j]!=0)
                        {
                            continue;
                        }
                        //If not store .
                        else {
                            wordGuessed[j]='.';
                        }
                    }
                    //calculates percentage guessed right
                    int percentage = (count[i] * 100) / (wordsChosen[i].length());
                    //Calls printPicture
                    printPicture(percentage,i);
                    //To print what is guessed converting array to String
                    wordToPush= String.valueOf(wordGuessed);
                    System.out.println(wordToPush);
                    updating(wordToPush,i,wordsPrevIteration,wordsChosen);
                    //Tracking the word progress
                    String word="";
                    if(i==1&&track.size()==0) {
                        for(int k=0;k<wordsChosen[0].length();k++)
                        {
                            word+=".";

                        }
                        track.add(0,word);
                        track.add(i, wordToPush);
                    }
                    else {
                        track.add(i,wordToPush);
                    }
                    System.out.println();
                    //If word is guessed we make use of flag to exit the loops and declare the winner
                    if(wordToPush.equals(wordChosen))
                    {
                        won=1;
                        System.out.println("Player" + (i+1)+"won");
                        break;
                    }
                }

            }
        }while(won==0);
    }


    /**
     * The main program
     * Calls the file
     */
    public static void main(String args[])
    {
        // reads the text file - this file has to be in the local directory
        readWordsFromFile(args[2]);
        //Game Starts here
        GuessWord(args);
    }

}
