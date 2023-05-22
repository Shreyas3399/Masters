/*
 * Wordle.java
 *
 * Version: $1.3$
 *
 */
import java.io.*;
import java.util.Random;
import  java.util.Scanner;
/**
 * Wordle
 * @author Shireen Maini
 * @author Shreyas Belkune
 */
public class Wordle {
    //Used the readWordsFromFile,readUserInput,getWord code as given by Professor HP in https://www.cs.rit.edu/~hpb/Lectures/2221/605/
    static int soManyWordToPLayWith = 0; //used to keep word count
    static final String[] theWords = new String[10231]; //stores the word in this array
    static final Scanner readGuess = new Scanner(System.in);//creating a scanner object to allow user to input

    /**
     * Reads all words from the filename and makes the words available.
     * @param  fileName the name of the file containing words of length 5. Each words is on a line.
     * This method will terminate the program if the file does not exist.
     */
    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName))
        ) {

            int counter = 0;//used as indexing while counting
            while  ( ( theWords[counter++] = input.readLine() )  != null ) //inputting the words from the file into theWords array
                soManyWordToPLayWith ++; // counting of words in file
        }
        catch ( Exception e)	{
            System.out.println("ExceptionType occurred: " + e.getMessage() ); //throws exception if file not found
        }
    }

    /**
     * Reads a word of length 5 and returns this word.
     * The method will continue until a word of length 5 is provided.
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
        return theWords[new Random().nextInt(soManyWordToPLayWith)];
    }

    /**
     * Only 5 tries allowed at guessing the word.Hints would be given using _*x for you to guesshow close ou are to the word
     * if found Hurrah else try next time
     */
    public static void playWordle() {
        int flag=0;//used for checking no of tries if 5 done and word still not found will exit
        String chosen_word=getWord();//Choosing a word from file
       // System.out.println(chosen_word);
        System.out.println("_ indicates the letter is in the word but in the wrong spot.");
        System.out.println("* indicates the letter is in the word and correct spot.");
        System.out.println("x indicates the letter is not in the word.");
        System.out.println("Try to guess the word in 5 tries.");
        for(int i=0;i<5;i++) //Runs 5 times
        {
            String wordGuessed;
            int[] alphabet_count=new int[26]; //used to indicate frequency of letter in alphabet
            char[] word_output=new char[5];//used to store the directions i.e _*x
            /* Runs till we do  not enter a 5 letter word*/
           do {
                System.out.println("5 letter word only allowed");
                wordGuessed=readUserInput();
                wordGuessed=wordGuessed.toLowerCase();//converts to lowercase
                System.out.println(wordGuessed);
            }while(wordGuessed.length()!=5);
            //counting frequency of letter in chosen word
            for(int j=0;j<chosen_word.length();j++)
            {
                alphabet_count[chosen_word.charAt(j)-'a']++;
            }
            //Runs loop for every letter in word
            for(int index_check=0;index_check<wordGuessed.length();index_check++)
            { //if letter match we store * in word_output array
                if(wordGuessed.charAt(index_check)==chosen_word.charAt(index_check))
                {
                    word_output[index_check]='*';
                    alphabet_count[wordGuessed.charAt(index_check)-'a']--;
                }
                /*if letter exists in word but index does not match we store _ in word_output array
                  if duplicates exists in word written but the word chosen does not have
                  that many letters we return x on the remaining*/
                else if(chosen_word.indexOf(wordGuessed.charAt(index_check))!=-1)
                {
                    if( alphabet_count[wordGuessed.charAt(index_check) - 'a']!=0)
                    {
                        word_output[index_check]='_';
                        alphabet_count[wordGuessed.charAt(index_check) - 'a']--;//decreasing the count
                    }
                    else
                    {
                        word_output[index_check]='x';
                    }

                }
               else
                {
                    word_output[index_check]='x'; //if letter does not exist x

                }
            }
            int count=0;
            /*Printing the sequence generated and if all counting the  *'s */
            for(int check=0;check<5;check++)
            {
                System.out.print(word_output[check]);
                if(word_output[check]=='*')
                {
                    count++;
                }
            }
            System.out.println();
            /*If 5 *s exist break from the loop*/
            if(count==5)
            {
                System.out.println();
                System.out.println("YOU guessed the right word");
                flag=1;
                break;
            }
        }
        //if 5 tries are done we print this message
        if(flag==0)
        {
            System.out.println("Sorry 5 tries are over.Better luck next time");
        }
    }
    /**
     * The main program
     * Calls the methods to read file and play wordle
     */
    public static void main( String []args ) {
        readWordsFromFile("5_char_word.txt");		// reads the text file - this file has to be in the local directory
        playWordle();
    }
}
