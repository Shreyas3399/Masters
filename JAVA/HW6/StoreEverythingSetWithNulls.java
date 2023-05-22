/**
 *Implementing a storage functionality
 *which behaves like a set except
 *for null values
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class StoreEverythingSetWithNulls extends StoreEverything{
    //Counter for null value
    static int counterNull=0;

    /**
     * Checks number of nulls in the storage enviornment
     * @return counter null that is count of nulls in our storage
     */
    public int howManyNulls()
    {
       return counterNull;
    }

    /**
     * Adding object making sure it behaves like a set and can have null values too
     * @param element the object passed
     * @return true if element added false if it already exists
     */
    public boolean add(Object element)
    {
        boolean trueOrFalse;
        //If null counter is null
        if(element==null)
        {
                counterNull++;
                return true;

        }
        //Checking if element already exists in array if it does false
        for(int i=0;i<arrayCreation.length;i++)
        {
            if(element==arrayCreation[i])
            {
                return false;
            }
        }
        //Calling super.add to add element if it does not exist
        trueOrFalse=super.add(element);
        return trueOrFalse;
    }

    /**
     * Deleting object if it exists
     * @param element the object passed
     * @return true if deleted else false
     */
    public boolean delete(Object element)
    {
        boolean trueOrFalse;
        //if element null is to be deleted
        if(element==null)
        { //If counterNull is greater thatn 0 null exists
            if(counterNull>0)
            {
                //decease counter
                counterNull--;
                return true;
            }
            return false;
        }
        //Calling delete if it's an element
        trueOrFalse=super.delete(element);
        return trueOrFalse;
    }
    /**
     * Find object if it exists
     * @param element the object passed
     * @return true if found else false
     */
    public boolean find(Object element)
    {
        boolean trueOrFalse;
        //if null
        if(element==null)
        { //If counterNull >0 then null exists
            if(counterNull>0)
            {
                return true;
            }
            return false;
        }
        //Searching for element
        trueOrFalse=super.find(element);
        return trueOrFalse;
    }

    /**
     * Prints null count value and storage
     * @return String
     */
   public String toString()
   {
       String value="Null count "+counterNull+ " ";
       value+=super.toString();
       return value;
   }

}