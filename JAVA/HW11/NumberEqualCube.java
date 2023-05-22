import java.util.Scanner;
/**
 *
 * Finding the numbers whose sum
 * of digits raise to the power
 * (number of digits) is equal
 * to the number itself
 *
 * @author Shireen Maini
 * @author Shreyas Belkune
 *
 *
 */
class Threads extends Thread{
    int i,value,start;
    NumberEqualCube numberEqualCube=new NumberEqualCube();
    Threads(int i,int start,int value)//Thread Constructor
    {
        this.i=i;
        this.value=value;
        this.start=start;
    }
    public void run()
    {
            for(int j=start+1;j<(start+value);j++)
            {
                String strNum = String.valueOf(j);//String rep of number passed

                int lenNumber = strNum.length();//length of the string i.e no of digits

                //seperating digits
                char[] nAsCharcters = ("" + j).toCharArray();

                //calling the function
                int value=numberEqualCube.numberPower(j,lenNumber,nAsCharcters);

            /*If sum return equals the number run
              It prints the number and the pattern
             */
                if(value==j)
                {
                    System.out.print("Thread no "+i+" performed this "+j+" = " );
                    for(int iter=0;iter<lenNumber;iter++)
                    {
                        if(iter!=lenNumber-1)

                        {
                            System.out.print(nAsCharcters[iter]+"^"+lenNumber+"+");//Printing 
                        }
                        else
                        {
                            System.out.print(nAsCharcters[iter]+"^"+lenNumber);
                        }
                    }
                    System.out.println();
                }
            }

        }



}
class NumberEqualCube
{
    public int numberPower(int index,int lenNumber,char []nAsCharcters)
    {
        int num = index;
        int sum=0;

        //Loop runs according to number of digits

        for (int j=0;j<lenNumber;j++)
        {
            int digit=Integer.parseInt(String.valueOf(nAsCharcters[j]));
            int power=lenNumber;
            int exponentialRes=1;
            //Finding the power of the digits
            while(power!=0)
            {
                exponentialRes*=digit;
                power--;
            }

            //sum of the above digits raise to power

            sum+=exponentialRes;

        }
        return sum;

    }

    public static void main(String args[])
    {
        System.out.println("Enter number of threads");//Asking user to i/p no of threads
        Scanner myObj=new Scanner(System.in);
        int numThreads=myObj.nextInt();
        System.out.println("Enter number till where you want to print");
        myObj=new Scanner(System.in);//Entering number till where we want to see result
        int vals=myObj.nextInt();
        int value=vals/(numThreads);
        int rem=vals%(numThreads);
        int start=0;
        for (int i=0;i<numThreads;i++)
        {
            if(i==numThreads-1) {Threads threadNum = new Threads(i,start,value+rem);}
            Threads threadNum = new Threads(i,start,value);//Creating threads
            threadNum.start();
            try {
                threadNum.join();//Joining
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            start+=value;//next start position
        }
    }
}