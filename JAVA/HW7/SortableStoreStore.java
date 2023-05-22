/**
 *Implementing a storage functionality for ints
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class SortableStoreStore extends Storage implements sortable{
    Object [] storeStore=new Object[1];//Object Array for storing strings
    int pos=-1;//initialising position
    Object[] temp;//temporary array
    String classBelongs="java.lang.Object";
    /**
     *Sorting array
     */
    @Override
    public void sort() {
        for(int i=0;i<pos;i++)//iterates from start to end
        {
            for(int j=0;j<pos-i;j++)//iterates from start to end-i
            {
                if(compareTo(storeStore[j],storeStore[j+1])>0)//Comparing values ,if smaller index greater then swap
                {
                    Object temp=storeStore[j];
                    storeStore[j]=storeStore[j+1];
                    storeStore[j+1]=temp;
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
    public boolean isSorted()
    {
        for (int j = 0; j < pos; j++) {
            if (sortedState == true) {//If sorted
                if (storeStore[j].hashCode() > storeStore[j + 1].hashCode()) {//Checking based on hashcode
                    sortedState = false;//setting sorted state to false
                    return sortedState;
                }
            }
        }
        sortedState=true;
        return sortedState;
    }
    /**
     * Comparing object
     * @param x,y
     * @return 1 if x>y,-1 if x<y else 0
     */
    @Override
    public int compareTo(Object x, Object y) {return x.hashCode()>y.hashCode()?1:(x.hashCode()==y.hashCode())?0:-1;}
    /**
     * finding element
     * @param x
     * @return true if x found
     */
    public boolean find(Object x) {
        if(x==null) {return counterNull>0?true:false;}//If x is null  check if exists and return true
        for(int index=0;index<=pos;index++)
        {
            if(x.hashCode()==storeStore[index].hashCode())//if found returns true
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
        if(x instanceof SortableStoreStore) {//Checks class name
            boolean trueOrFalse = find(x);//Checking if it exists
            if (trueOrFalse == true) {return false;}
            else {
                pos++;//updating position
                temp = new Object[pos + 1];//temporary array
                for (int i = 0; i < storeStore.length; i++) {
                    temp[i] = storeStore[i];//copying array to temporary array
                }
                temp[pos] = x;//adding the value to that position
                storeStore = temp;//pointing array to temp
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
        if (trueOrFalse == true) {//If found
            for (int index = 0; index <= pos; index++) {
                if ( (x).hashCode() == storeStore[index].hashCode()) {
                    int found = index;
                    for(int j=found;j<pos;j++)
                    {
                        storeStore[j]=storeStore[j+1];//Shifting array
                    }
                    temp=new Object[pos];
                    for(int j=0;j<pos;j++)
                    {
                        temp[j]=storeStore[j];//Copying temp to intArray
                    }
                    storeStore=temp;//Pointing intArray to temp
                    pos--;//Decreasing position
                    return true;//returning true
                }
            }
            if(pos!=0) {sortedState = isSorted();}//Defining sortedState
        }
        return false;
    }
    /**
     *printing iself
     */
    public String toString() {return "Storing Itself";}//Store itself
}
