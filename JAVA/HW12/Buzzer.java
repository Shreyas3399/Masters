/**
 *Implementing a Buzzer game
 *with the help of threads and 
 *to understand synchronised blocks
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
public class Buzzer extends Thread	{
    static int thread_count; // taking input for number of player
    static int max_rounds; // number of rounds to be played
	private int info; // the thread id
    private int hits; // number of hits a thread has
	static Object aObject = new Object(); // object for entering synchronised blocks
    static int hitlist[]; // array to store the number of hits for a particular player
	static int counter; // internal counter to mark winner
    static int control = 0; // helper variable for execution
    static int rounds = 0; // keeping track of current round number


	public Buzzer (int info) {
		this.info = info;
	}
    /**
     * Create threads and print the Output once game finishes
     */
    public static void init(){
        control = 0;
        counter = 0;
        for(int index = 1; index <= thread_count; index++){
            new Buzzer(index).start();            
        }
        if(rounds == max_rounds){
            for(int i = 1; i<=thread_count ; i++){
                System.out.println("Player "+i+" has hit "+hitlist[i]+" times");
            }     
            int max = 0;
            int pos = 0;
            for(int i = 1; i<thread_count;i++){
                if (max< hitlist[i]){
                    max = hitlist[i];
                    pos = i;
                }
            }
            System.out.println("======================================");
            System.out.println("Player "+pos+" has highest hits :"+max);
        }
    }
	/**
     * Giving each player a chance to hit the buzzer
     * and restart the round once everyone has had a chance
     */
 	public void hit_buzzer() {
	   synchronized ( aObject )	{

		counter ++;
		if ( counter ==  thread_count)	{
            int temp = thread_count+1;
			new Buzzer(temp).start();
		}
		try {
			//System.err.println("Player "+info + " is ready!");
			aObject.wait();
            control++;
            if(control==1){
                hits++;
                hitlist[info] = hitlist[info]+hits;
			    //System.err.println("Player "+info + " has hit the button : "+hitlist[info]+" times");
                rounds++;
            }
            else{
                if(rounds<=max_rounds && control == thread_count){
                    init();
            }
        }
            
		} catch ( IllegalMonitorStateException  e )	{
			System.err.println(info +
			  ": IllegalMonitorStateException");
		} catch ( InterruptedException  e )	{
			System.err.println(info +
			  ": InterruptedException");
		}
	  }
	}
    /**
     * Making sure all the threads are ready to hit the buzzer and releasing them
     */
	public void all_ready () {
		synchronized ( aObject )	{
			try {
				aObject.notifyAll();
			} catch ( IllegalMonitorStateException  e )	{
				System.err.println(info +
				  ": IllegalMonitorStateException");
			}
		}
	}
    /**
     * Deciding whether the round is ready to be played or not
     */
	public void run () {
	   	synchronized ( aObject )	{
			if (  info!=(thread_count+1)){ 
				    hit_buzzer();
            }
			else
				all_ready();
	   	}
	}
    /**
     * Taking user input for number of players and number of rounds to be played
     * @param args
     */
	public static void main (String args []) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of players: ");    
        thread_count = scan.nextInt();
        System.out.print("Enter number of rounds you wanna play: ");    
        max_rounds = scan.nextInt();
        scan.close();
        int array_size = thread_count + 1;
        hitlist = new int[array_size];
        init();
        }
     }
