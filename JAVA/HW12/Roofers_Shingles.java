/**
 *Implementing a Shingles and
 *Roofers with the help of threads
 *to understand synchronised blocks
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
import java.util.Scanner;
/*Modified Professor Hp's code 11.22*/
class Roofers_Shingles extends Thread    {
    private int id;//Shingle number
    static Object[]  synchronizedOn;
    static int SHINGLES ;//Number of Shingles
    static int ROOFERS;//Number of Roofers
    static int counter = 1;//Counter

    boolean roofers_shingles = false;

    Object first;
    Object second;

    public static void init(int roofers,int shingles)    {
        /*
        Initialising shingles and roofers creating Objects
         */
        SHINGLES=shingles;
        ROOFERS=roofers;
        synchronizedOn = new Object[ROOFERS + 1];
        for ( int index = 0; index < ROOFERS + 1; index ++ )
            synchronizedOn[index] = new Object();
    }
    public Roofers_Shingles(int id, Object first, Object second) {
        /*
        Constructor for Roofers and Shingles
         */
        this.id         = id;
        this.first      = first;
        this.second     = second;
    }

    public void run() {
        for ( int index = 0; index <SHINGLES; index ++ ) {
            synchronized ( first ) {//Synchronizing Object1
                synchronized ( second ) {//Synchronising Object2
                    second.notify();
                    if(id==0){//Helper picks up first
                        System.out.println("Helper picks up next shingle ,shingle number "+(index+1)+" is handed to next roofer "+(id+1));
                    }
                    else if(id==ROOFERS-1){//Last one keeps it on the roof
                        System.out.println("shingle number " + (index + 1) + " is handed to next roofer " + (id+1) );
                        System.out.println("and the shingle is put on the roof");
                        System.out.println();
                    }
                    else {
                        System.out.println("shingle number " + (index + 1) + " is handed to next roofer " + (id+1) );
                    }

                    try {
                        if ( id < ROOFERS )    {
                            if ( ! roofers_shingles )    {
                                if ( counter == ROOFERS - 1 ){
                                    ( new Roofers_Shingles(counter, synchronizedOn[ROOFERS - 1], synchronizedOn[0]) ).start();//Last to first
                                }
                                else
                                {
                                    ( new Roofers_Shingles(counter, synchronizedOn[counter], synchronizedOn[counter + 1] ) ).start();}
                                roofers_shingles = true;
                            }
                        }
                        counter ++;
                    } catch ( Exception e ) { }
                }

                try {
                    if(index==SHINGLES-1)//Killing all prevous threads on the last Shingle
                    {
                        first.notifyAll();
                    }
                    else {//else wait
                        first.wait();
                    }

                } catch ( Exception e ) { }
            }
        }
    }
    public static void main (String args []) {
        System.out.println("Enter number of roofers");
        Scanner obj=new Scanner(System.in);//No of roofers needed
        int roofers=obj.nextInt();

        System.out.println("Enter number of shingles");//Number of objects to put
        int shingles=obj.nextInt();

        init(roofers,shingles);//initilaising roofers and shingles
        new Roofers_Shingles(0, synchronizedOn[0], synchronizedOn[1]).start();//Starting threads
        obj.close();
    }
}