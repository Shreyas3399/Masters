    /*
    * NumberEqualCube.java
    *
    * Version: $1.1$
    *
    */
    import java.lang.String;
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
  public class NumberEqualCube {
    /**
     * Finding the sum of digits
     * raised to the power(which is the
     * length of number)
     * Returning that sum.
     * @param index It is the number being passed
     * @param lenNumber Number of digits the no has
     * @param nAsCharcters An array of digits of the number
     * @return sum The sum of digits raised to power
     */

    static int numberPower(int index,int lenNumber,char []nAsCharcters)
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
    /**The main program
     *Calls the above function
     *and prints the result.
     */

    public static void main(String[] args)
    {
        /*A loop for running all the cases from
           1 to 100000*/
        for(int i=1;i<100000;i++)
        {
            String strNum = String.valueOf(i);//String rep of number passed

            int lenNumber = strNum.length();//length of the string i.e no of digits

            //seperating digits
            char[] nAsCharcters = ("" + i).toCharArray();

            //calling the function
            int value=numberPower(i,lenNumber,nAsCharcters);

            /*If sum return equals the number run
              It prints the number and the pattern
             */
            if(value==i)
            {
                System.out.print(i+" = " );
                for(int iter=0;iter<lenNumber;iter++)
                {
                    if(iter!=lenNumber-1)

                    {
                        System.out.print(nAsCharcters[iter]+"^"+lenNumber+"+");
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
