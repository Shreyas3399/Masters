/**
 *Implementing a storage functionality for ints
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
import java.util.Arrays;

public class SortableStoreInteger extends Storage implements sortable{
    int intArray[]=new int[1];//int Array for storing ints
    int pos=-1;//initialising position
    int [] temp;//temporary array
    String classBelongs="java.lang.Integer";//Integer class
    /**
     *Sorting array
     */
    @Override
    public void sort() {
        for(int i=0;i<pos;i++)//iterates from start to end
        {
            for(int j=0;j<pos-i;j++)//iterates from start to end-i
            {
                if(compareTo(intArray[j],intArray[j+1])>0)//Comparing values ,if smaller index greater then swap
                {
                    int temp=intArray[j];
                    intArray[j]=intArray[j+1];
                    intArray[j+1]=temp;
                }
            }
        }
        sortedState=true;
    }
    /**
     * Checking if element is sorted
     * @return true if sorted else fals
     */
    public boolean isSorted() {
        if(sortedState==true) {//If sorted
            for (int j = 0; j < pos; j++) {
                if (intArray[j] > intArray[j + 1]) {//If value at lower index greater than value at next index,swap
                    sortedState = false;//setting sorted state to false
                    return sortedState;
                }
            }
        }
        sortedState=true;
        return sortedState;//returns value of sorted state
    }
    /**
     * Comparing int
     * @param x,y
     * @return 1 if x>y,-1 if x<y else 0
     */
    @Override
    public int compareTo(Object x, Object y) {return (int)x>(int)y?1:((int)x==(int)y)?0:-1;}//Comparing values and returning accordingly
    /**
     * finding element
     * @param x
     * @return true if x found
     */
    public boolean find(Object x) {
        if(x==null) {return counterNull>0?true:false;}//If x is null check counter null
        for(int index=0;index<=pos;index++)//iterating through array
        {
            if((int)x==intArray[index])//if found returns true
            {
                return true;
            }
        }
        return false;//else returs false
    }
    /**
     * Adding element
     * @param x
     * @return true if successfully added else false
     */
    public boolean add(Object x) {
        if(x==null) {//For null condition
            counterNull++;
            return true;
        }
        if(x.getClass().getName()==classBelongs) {//Checks class name
            boolean trueOrFalse = find(x);//Checking if it exists
            if (trueOrFalse == true) {return false;} //If already exists return false
            else {
                pos++;//updating position
                temp = new int[pos + 1];//temporary array
                for (int i = 0; i < intArray.length; i++) {
                    temp[i] = intArray[i];//copying array to temporary array
                }
                temp[pos] = (int) x;//adding the value to that position
                intArray = temp;//pointing array to temp
            }
            sortedState = isSorted();//Checking if sorted and setting the  value
        }
            return true;//returns true if added
    }
    /**
     * Deleting element
     * @param x
     * @return true if deleted else false
     */
    public boolean delete(Object x) {
        if(x==null) {//If null
            if(counterNull>0){//If null exists
                counterNull--;//then delete
                return true;
            }
            return false;
        }
        boolean trueOrFalse = find(x);//finding if element exists
        if (trueOrFalse == true) {
            for (int index = 0; index <= pos; index++) {//Traversing array
                if ( (int)x == intArray[index]) {//If found
                    int found = index;//Storing index position
                    for(int j=found;j<pos;j++)
                    {
                        intArray[j]=intArray[j+1];//Shifting array
                    }
                    temp=new int[pos];
                    for(int j=0;j<pos;j++)
                    {
                        temp[j]=intArray[j];//Copying temp to intArray
                    }
                    intArray=temp;//Pointing intArray to temp
                    pos--;//Decreasing position
                    return true;//returning true
                }
            }
            if(pos!=0) {sortedState = isSorted();}//Defining sortedState
        }
        return false;
    }
    /**
     *printing storage enviornment
     */
    public String toString() {return Arrays.toString(this.intArray);}//Returning the array
}