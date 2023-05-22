import java.io.*;
import java.util.Scanner;

/**
 *
 * Finding the count of every
 * number in pi
 *
 * @author Shireen Maini
 * @author Shreyas Belkune
 *
 *
 */
public class PiEvenOddImprovement extends Thread{
    static int[] store_count=new int[10];
    private int start;
    private int end;
    static String line;

    public PiEvenOddImprovement(int start,int end)//Thread Constructor
    {
        this.start=start;
        this.end=end;
    }
    public void run()
    {
        synchronized (this)
        {
             String line_block=line.substring(this.start,this.end+1);
             for(int i=0;i<line_block.length();i++)
             {
                char index=line_block.charAt(i);
                if(index=='.')
                {
                    continue;
                }
                int ind=Character.getNumericValue(index);
                store_count[ind]+=1;
             }

        }
    }
    public static void readFromFile_process(String fileName) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(fileName));//Reading file
        ) {
            input.readLine();//Skipping line 1
            String strline;
            while ((strline = input.readLine()) != null) {
                line=strline;//storing line
                PiEvenOddImprovement[] threads = new PiEvenOddImprovement[10];//Creating 10 threads
                int start=0;//start index
                int end=start+(line.length()/10)-1;//end index
                for(int i=0;i< 10;i++)
                {
                    threads[i]=new PiEvenOddImprovement(start,end);//Every thread works on it's task
                    threads[i].start();//Starting a thread
                    start=end+1;//Position of new Start
                    end=start+(strline.length()/10)-1;//Position of new end
                }
                for(int i=0;i<10;i++)
                {
                    try
                    {
                        threads[i].join();//Joining threads
                    }catch(InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("ExceptionType occurred: " + e.getMessage());
        }
    }
    public static void main(String[] args)
    {
       PiEvenOddImprovement piEvenOddImprovement=new PiEvenOddImprovement(0,0);//Creating object
        System.out.println("Enter file name");
        Scanner obj=new Scanner(System.in);
        String name=obj.next();
       piEvenOddImprovement.readFromFile_process(name);//Reading and processing from file
       for(int i=0;i<store_count.length;i++)
       {
           if(i==3)
           {
               System.out.println(i + " "+(store_count[i]+1));//Printing count of every index

           }
           else {
               System.out.println(i + " " + store_count[i]);//Printing count of every index
           }
       }


    }


}