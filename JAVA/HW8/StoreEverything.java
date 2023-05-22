import java.util.Arrays;
/**
 *Implementing a storage functionality
 *which behaves like a set except
 *for null values
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class StoreEverything<T> implements StoreEverythingI<T> {
    int counterNull = 0;
    int pos = -1;//Position before starting
    T[] temp;//temp object array for resizing
    T[] arrayCreate = (T[]) new Object[1];//arrayCreation Object to store the elements
    int placeHolder=0;//Placeholder to check if object is adding to itself

    /**
     * Deleting element from array
     * @param o
     * @return true if element exists and deleted
     */
    @Override
    public boolean delete(T o) {
        if(placeHolder>0)
        {
            placeHolder--;
        }
        boolean trueOrFalse = find(o);//Checking if exists in array
        if (trueOrFalse == false) {
            return false;
        }
        for (int i = 0; i <= pos; i++) {
            if (o == arrayCreate[i]) { //If found deleting it
                int found = i;
                for (int j = found; j < pos; j++) {//shifting the array
                    arrayCreate[j] = arrayCreate[j + 1];
                }
                temp = (T[]) new Object[pos];
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
     * Finding element
     * @param o
     * @return true if element passed
     */
    @Override
    public boolean find(T o) {
        if (o == null)
        {
            return counterNull > 0 ? true : false;
        }
        //If x is null  check if exists and return true
        for (int index = 0; index <= pos; index++) {
            if (o.equals(arrayCreate[index]))//if found returns true
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Inserting element
     * @param o
     * @return true if added false if does not belong to same object type
     */
    @Override
    public boolean add(T o) {
        if(o==null)
        {
            counterNull++;
        }
        if(o instanceof StoreEverything)
        {
            placeHolder++;//increase placeholder
        }
        pos++;//updating position
        temp = (T [])new Object[pos + 1];//temporary array
        if(pos==0)
            {
                arrayCreate[pos]=o;
            }
        else {
            for (int i = 0; i < arrayCreate.length; i++) {
                temp[i] = arrayCreate[i];
            }
            arrayCreate = temp;
            int flag = 0; //Using a flag
            for (int i = 0; i < pos; i++) { //Sorting and inserting wrt hashcode
                if (arrayCreate[i].hashCode() >= o.hashCode()) { //Shifting and inserting in array
                    for (int j = pos - 1; j >= i; j--) {
                        arrayCreate[j + 1] = arrayCreate[j];
                    }
                    arrayCreate[i] = o;
                    flag = 1;
                    break;
                }
            }
            if (flag != 1) { //If not added ,add element in the last
                arrayCreate[pos] = o;
            }
        }
        return true;
    }
    /**
     * If object itself returns SToring itself else the storage
     * enviornment is printed
     */
    @Override
    public String toString() {
        if(placeHolder>0)
        {
            return "Storing itself";
        }

        return Arrays.toString(this.arrayCreate);

    }
    /*public static void main(String[] args)
    {
        StoreEverything<Integer> s1=new StoreEverything<>();
        StoreEverything<String> s2=new StoreEverything<>();
        s1.add(1);
        s1.add(50);
        s1.add("shireen");
        s2.add("Shireen");
        s2.add("Maini");
        s2.add(10);
        //s2.add(null);
        System.out.println(s1);
        System.out.println(s2);

    }*/
}

