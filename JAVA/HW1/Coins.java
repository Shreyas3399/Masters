    /*
    * Coins.java
    *
    * Version: $6.3$
    *
    */
    import java.lang.String;
    /**
     *Finding the maximum
     *number of coins that
     *can be used to fulfil
     *condition of paying
     *the amount
     * @author Shireen Maini
     * @author Shreyas Belkune
     */

public class Coins {
    /**
     * We are taking two cases
     * either including the coin
     * or not including it and recursively
     * running it
     *
     * @param index  initially 0
     * @param coins the coins we have
     * @param pendingAmount Amount pending
     * @param curSeq used for keeping a track of our seq
     * @param j indic for curSeq
     */
    static int count=0,maxCount=0;
    static void findLongestSequence(int index,int []coins,int pendingAmount,int[] curSeq,int j)
    {

        if(pendingAmount==0)
        {
            if(count>maxCount)
            {
                maxCount=count;
                int k=0;
                while(curSeq[k]!=0)      //Printing the final sequence
                {
                    System.out.print(curSeq[k]+" ");
                    k++;
                }
                return ;
            }
        }


        if(index >= coins.length)
        {
            return;
        }

        if((pendingAmount-coins[index])>=0) //Checking if the amount can be subtracted from the next value
        {
            count++;//Counting the coin
            curSeq[j++]=coins[index];//storing the coins value and incrementing j
            findLongestSequence(index+1,coins,pendingAmount-coins[index],curSeq,j); //including the coin
            count--;//Decreasing the count
            curSeq[j--]=0;//Removing the coin and decreasing index
        }
        findLongestSequence(index+1,coins,pendingAmount,curSeq,j);//excluding the count

    }
    /**
     * The main program
     * Calls the function
     * and prints the max number of
     * coins that can be used to Pay
     */
    public static void main(String[] args) {
        int[] coins={1,4,4,5,8};
        int[] toPay={9,6,4,6,7,8};
        for(int i=0;i<toPay.length;i++)
        {
            int[] curSeq=new int[coins.length];
            int[] res=new int[coins.length];
            findLongestSequence(0,coins,toPay[i],curSeq,0);
            System.out.println("Max no of coins that can be used " + maxCount);
            count=0;
            maxCount=0;
        }
    }
}
