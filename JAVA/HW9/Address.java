import java.util.Comparator;
/**
 *Implementing Address class which extends Comparable
 *and overriding compareTO
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class Address implements Comparable<Address> {
    int houseNumber;
    String streetName;
    String nameOfTown;
    String state;
    int zipCode;
    Address(int houseNumber, String streetName, String nameOfTown, String state, int zipCode)
    {
        this.houseNumber=houseNumber;
        this.streetName=streetName;
        this.nameOfTown=nameOfTown;
        this.state=state;
        this.zipCode=zipCode;
    }
    public String toString(){//overriding the toString() method
        return houseNumber+" "+streetName+" "+nameOfTown+" "+state+" "+zipCode;
    }


    @Override
    public int compareTo(Address o) {
        //Comparing based on house number
        return this.houseNumber -o.houseNumber;
    }
}
