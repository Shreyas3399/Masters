/**
 *Implementing a storage functionality
 *which behaves like a set except
 *for null values
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
import java.util.Arrays;

public class StoreEverything{
    //Placeholder to check if object is adding to itself
    static int placeHolder=0;
    //Position before starting
    int pos=-1;
    //temp object array for resizing
    static Object[] temp;
    //arrayCreation Object to store the elements
    Object[] arrayCreation=new Object[1];
    //To get className
    static String className;

    /**
     * Inserting element
     * @param element
     * @return true if added false if does not belong to same object type
     */
    public boolean add(Object element)	{
        //Increasing position
        pos++;
        String classBelongs;
        //If the object added is itself
        if(element instanceof StoreEverything)
        {
            //increase placeholder
            placeHolder++;
        }
        if(pos==0)
        {
            //Getting the type of Class
            className=element.getClass().getName();
            //Inserting element
            arrayCreation[pos]=element;
        }
        else
        {
            //Checking if the object already added matches th object added previously
            classBelongs=element.getClass().getName();
            if(classBelongs!=className)
            {
                return false;
            }
            temp=new Object[arrayCreation.length+1];
            for(int i=0;i<arrayCreation.length;i++)
            {
                temp[i]=arrayCreation[i];
            }
            arrayCreation=temp;
            //Using a flag
            int flag=0;
            for(int i=0;i<pos;i++)
            { //Sorting and inserting wrt hashcode
                if(arrayCreation[i].hashCode()>=element.hashCode())
                { //Shifting and inserting in array
                    for(int j=pos-1;j>=i;j--)
                    {
                        arrayCreation[j+1]=arrayCreation[j];
                    }
                    arrayCreation[i]=element;
                    flag=1;
                    break;
                }

            }
            //If not added ,add element in the last
            if(flag!=1)
            {
                arrayCreation[pos]=element;
            }

        }
        return true;
    }

    /**
     * Finding element
     * @param element
     * @return true if element passed
     */
    public boolean find(Object element)	{
        //Looping through and finding element in array
        for(int i=0;i<=pos;i++)
        {
            if(element==arrayCreation[i])
            {
                return true;
            }
        }
        //To check if object was added
        if(placeHolder>0)
        {
            return true;
        }
        return false;
    }

    /**
     * Deleting element from array
     * @param element
     * @return true if element exists and deleted
     */
    public boolean delete(Object element)
    {  //Checking for Object
        if(placeHolder>0)
        {
            temp=new Object[pos];
            for(int j=0;j<pos;j++)
            {
                temp[j]=arrayCreation[j];
            }
            arrayCreation=temp;
            pos--;
            placeHolder--;
            return true;
        }
        //Checking if exists in array
        for(int i=0;i<=pos;i++)
        {
            if(element==arrayCreation[i])
            { //If found deleting it
                int found=i;
                for(int j=found;j<pos;j++)
                {
                    arrayCreation[j]=arrayCreation[j+1];
                }
                temp=new Object[pos];
                for(int j=0;j<pos;j++)
                {
                   temp[j]=arrayCreation[j];
                }
                arrayCreation=temp;
                pos--;
                return true;
            }

        }
        return false;
    }
    @Override
    /**
     * If object itslef returns SToring itself else the storage
     * enviornment is printed
     */
    public String toString() {
        if(placeHolder>0)
        {
            return "Storing itself";
        }

        return Arrays.toString(this.arrayCreation);
    }

}
