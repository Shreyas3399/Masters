import java.util.Arrays;
import java.lang.*;
/**
 *Implementing a storage functionality for string
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class SortableStoreString extends Storage implements sortable{
    String stringArray[]=new String[1];//String Array for storing strings
    int pos=-1;//initialising position
    String [] temp;//temporary array
    String classBelongs="java.lang.String";//String class
    /**
     *Sorting array
     */
    @Override
    public void sort() {
        for(int i=0;i<pos;i++)//iterates from start to end
        {
            for(int j=0;j<pos-i;j++)//iterates from start to end
            {
                if(compareTo(stringArray[j],stringArray[j+1])>0)//Comparing values ,if smaller index greater then swap
                {
                    String temp=stringArray[j];
                    stringArray[j]=stringArray[j+1];
                    stringArray[j+1]=temp;
                }
            }
        }
        sortedState=true;
    }
    /**
     * Checking if element is sorted
     * @return true if sorted else fals
     */
    @Override
    public boolean isSorted() {
        if(sortedState==true) {//If sorted
            for (int j = 0; j < pos; j++) {
                if ((stringArray[j].compareTo(stringArray[j + 1])) > 0) {//If value at lower index greater than value at next index,swap
                    sortedState = false;//setting sorted state to false
                    return sortedState;
                }
            }
        }
        sortedState = true;
        return sortedState;
    }
    /**
     * Comparing strings
     * @param x,y
     * @return greater than 0if x>y,less than 0if x<y else 0
     */
    public int compareTo(Object x, Object y) {return ((String)x).compareTo((String)y);}//USing compare to function to compare
    /**
     * finding element
     * @param x
     * @return true if x found
     */
    public boolean find(Object x) {
        if(x==null) {return counterNull>0?true:false;}//If x is null  check if exists and return true
        for(int index=0;index<=pos;index++)//iterating through array
        {
            if(x.equals(stringArray[index]))//if found returns true
            {
                return true;
            }
        }
        return false;
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
            if (trueOrFalse == true) {return false;}
            else {
                pos++;//updating position
                temp = new String[pos + 1];//temporary array
                for (int i = 0; i < stringArray.length; i++) {
                    temp[i] = stringArray[i];//copying array to temporary array
                }
                temp[pos] = (String) x;//adding the value to that position
                stringArray = temp;//pointing array to temp
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
            for (int index = 0; index <= pos; index++) {
                if ( (x) == stringArray[index]) {//If found
                    int found = index;
                    for(int j=found;j<pos;j++)
                    {
                        stringArray[j]=stringArray[j+1];//Shifting array
                    }
                    temp=new String[pos];
                    for(int j=0;j<pos;j++)
                    {
                        temp[j]=stringArray[j];//Copying temp to intArray
                    }
                    stringArray=temp;//Pointing intArray to temp
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
    public String toString() {return Arrays.toString(this.stringArray);}//Returning the array
}