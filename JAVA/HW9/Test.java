import java.util.Comparator ;
import java.util.* ;

public class Test {
    public static void main(String args[]) {
        List<Address> aListOfAddresses 	= new ArrayList<Address>();
        List<LP> aListOfLPs 		= new ArrayList<LP>();
        aListOfAddresses.add(  new Address(1600, "Pennsylvania Avenue NW", "Washington", "DC", 20500) );
        aListOfAddresses.add(  new Address(11, "Wall Street", "New York", "NY", 10118) );
        aListOfAddresses.add(  new Address(102, "Lomb Memorial Drive", "Rochester", "NY", 14623) );
        aListOfAddresses.add(  new Address(1, "A", "B", "C", 1) );
        aListOfAddresses.add(  new Address(2, "A", "B", "C", 1) );
        aListOfAddresses.add(  new Address(3, "A", "B", "C", 1) );
        aListOfAddresses.add(  new Address(4, "A", "B", "C", 1) );

        aListOfLPs.add( new LP( 1960, "Deep Purple in Rock", "Deep Purple", (float)43.30, 7));
        aListOfLPs.add( new LP( 1973, "Dark Side of the Moon", "Pink Floyd ", (float)43.09, 10));
        aListOfLPs.add( new LP( 1, "A", "B ", (float)3, 4));
        aListOfLPs.add( new LP( 2, "A", "B ", (float)3, 4));
        aListOfLPs.add( new LP( 3, "A", "B ", (float)3, 4));
        aListOfLPs.add( new LP( 0, "A", "B ", (float)3, 4));


        Collections.sort(aListOfAddresses);
        Collections.sort(aListOfLPs);
        System.out.println(aListOfAddresses);
        System.out.println(aListOfLPs);

    }
}
