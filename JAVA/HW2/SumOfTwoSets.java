/*
 * SumSubset.java
 *
 * Version: $1.5$
 *
 */
import java.lang.String;
import java.lang.Math;
/**
 *Finding the subsets
 *corresponding to the
 *target
 * @author Shireen Maini
 * @author Shreyas Belkune
 */
public class SumOfTwoSets {

    static int count=0,fincount=0;/*global variables used for keeping count of elements being added
                                     and removed from array which helps in finally printing of the array*/
    /**
     * We are checking if the
     * value exists in the subset2
     * from which we have to form values.
     * If it does we use the number as it is
     * @param num  the number to find
     * @param setTwo the set from which we need to form the values
     *               for our target number
     * @return 1/0 1 if number exists in set
     */
    static int findnum(int num,int []setTwo)
    {
        for(int i=0;i< setTwo.length;i++) //Traverses till length Two of array
        {
            if(setTwo[i]==num)//if the number is present in set return 1
            {
                return 1;
            }
        }
        return 0; //If number not present in set returns 0
    }
    /**
     * We are taking two cases
     * either including the coin
     * or not including it and recursively
     * running it
     *
     * @param index  initially 0
     * @param combSetThree takes the set and also includes negative values of itself
     * @param target Sum left to find
     * @param curSeq used for keeping a track of our seq
     * @param res final sequence stored in array
     */
    static void sumSubsetFinder(int index,int[] combSetThree,int target,int[] curSeq,int count,int[] res)
    {
      =  if(target==0)
        {
            int flag=0; //Since the sets can't use the same value(duplicates) we are using the flag to check the same

            for(int i=0;i<count;i++)
            {
                for(int j=i+1;j<count;j++)
                {
                    if(Math.abs(curSeq[i])==Math.abs(curSeq[j]))
                    {
                        flag=1;
                        break;
                    }
                }
            }
            if(flag==0) //If set does not have same pair we store the sequence in res array
            {
                for(int i=0;i<count;i++)
                {
                    res[i]=curSeq[i];//Storing the curSeq in result
                }
                fincount=count;//Stores the length of sequence res array
                return;
            }

        }
        if(index>=combSetThree.length) //If the index exceeds the array length we return to the previous stack call
        {
            return;
        }
        if(target-combSetThree[index]>=0) /*If the difference between the target and the value in array
                                            greater than 0 we add the value to our seq */
        {
            curSeq[count]=combSetThree[index]; //including the value in our array
            count++; //increasing the count
            sumSubsetFinder(index+1,combSetThree,target-combSetThree[index],curSeq,count,res);//recursive call including the value
            count--;//decreasing the count
        }
        sumSubsetFinder(index+1,combSetThree,target,curSeq,count,res);//recursive call to exclude the value

    }
    /**
     * The main program
     * Calls the above method
     * and prints the final sequence
     */
    public static void main(String[] args)
    {
        int[] setOne={1,2,3,4,5,6,7,8,9,10,11,12};//Target set
        int[] setTwo={2,3,7};//Set used to form above values
        /*Forming a set3 with positive and negative values of set Two*/
        int [] setThree=new int[2*setTwo.length];
        for( int k=0;k<setTwo.length;k++)
        {
            setThree[k]= -setTwo[setTwo.length-k-1];
        }
        for(int k=0;k<setTwo.length;k++)
        {
            setThree[setTwo.length+k]=setTwo[k];
        }

        /*Calling our recursive function if number does not exist in our set Three*/
        for(int i=0;i<setOne.length;i++)
        {
            count=0;
            fincount=0;
            int[] curSeq=new int[setOne.length];
            int[] res=new int[setOne.length];
            int num=setOne[i];
            if(findnum(num,setTwo)==1) //Finding if the target value already exists in set2
            {
                System.out.print(setOne[i] + " is ");
                System.out.println(num);
            }
            else {

                sumSubsetFinder(0, setThree, setOne[i], curSeq, count, res);//recursive function
                //Printing the final sequence if it exists else prints no sequence
                if (fincount > 0) {
                    System.out.print(setOne[i] + " is ");
                    for (int j = 0; j < fincount; j++) {
                        System.out.print(" " + res[j]);
                    }
                    System.out.println();
                } else {
                    System.out.println("NoSequence");
                }
            }

        }
    }
}
