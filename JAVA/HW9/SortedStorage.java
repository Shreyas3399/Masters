import java.util.Arrays;
/**
 *Implementing a sorted storage functionality
 *using generics
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class SortedStorage <E extends Comparable<E>> implements StorageInterface<E>{
    int counterNull = 0;
    int pos = -1;//Position before starting
    E[] temp;//temp object array for resizing
    E[] arrayCreate = (E[]) new Comparable[1];
    /**
     * Inserting element
     * @param x
     * @return true if added false if does not belong to same object type
     */
    @Override
    public boolean add(E x) {
        if(x==null) {//For null condition
            counterNull++;
            return true;
        }
        pos++;//updating position
        temp = (E [])new Comparable[pos + 1];//temporary array
        if(pos==0)
        {
            arrayCreate[pos]=x;
        }
        else {
            for (int i = 0; i < arrayCreate.length; i++) {
                temp[i] = arrayCreate[i];
            }
            arrayCreate = temp;
            int flag = 0; //Using a flag
            for (int i = 0; i < pos; i++) { //Sorting and inserting wrt hashcode
                if (compareTo(arrayCreate[i],x)==1) { //Shifting and inserting in array
                    for (int j = pos - 1; j >= i; j--) {
                        arrayCreate[j + 1] = arrayCreate[j];
                    }
                    arrayCreate[i] = x;
                    flag = 1;
                    break;
                }
            }
            if (flag != 1) { //If not added ,add element in the last
                arrayCreate[pos] = x;
            }
        }
        return true;

    }
    public int compareTo(E x, E y) {return x.hashCode()>y.hashCode()?1:(x.hashCode()==y.hashCode())?0:-1;}

    /**
     * Finding element
     * @param x
     * @return true if element passed
     */
    @Override
    public boolean find(E x) {
        if (x == null)
        {
            return counterNull > 0 ? true : false;
        }
        //If x is null  check if exists and return true
        for (int index = 0; index <= pos; index++) {
            if (x.compareTo(arrayCreate[index])==0)//if found returns true
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean includesNull() {
        //If counterNull greater than 0 null is present
        if(counterNull>0)
        {
            return true;
        }
        return false;
    }
    /**
     * Deleting element from array
     * @param x
     * @return true if element exists and deleted
     */
    @Override
    public boolean delete(E x) {
        boolean trueOrFalse = find(x);//Checking if exists in array
        if (trueOrFalse == false) {
            return false;
        }
        for (int i = 0; i <= pos; i++) {
            if (x == arrayCreate[i]) { //If found deleting it
                int found = i;
                for (int j = found; j < pos; j++) {//shifting the array
                    arrayCreate[j] = arrayCreate[j + 1];
                }
                temp = (E[]) new Comparable[pos];
                for (int j = 0; j < pos; j++) {
                    temp[j] = arrayCreate[j];
                }
                arrayCreate = temp;
                pos--;//Decrementing position
            }
        }
        return true;
    }
    /**
     * Printing the storage environment
     */
    @Override
    public String toString() {
        return Arrays.toString(this.arrayCreate);
    }
}
