
public class TestInterfaceImplementation {
    public static void testStringSet(StorageInterface<String> aStorageInterfaceString)	{

        String three = new String("3");

        System.out.println("add(null) " + aStorageInterfaceString.add(null));
        System.out.println("add(null) " + aStorageInterfaceString.add(null));
        System.out.println("add(3) " + aStorageInterfaceString.add("3"));
        System.out.println("add(three) " + aStorageInterfaceString.add(three));
        System.out.println("add(1) " + aStorageInterfaceString.add("1"));
        System.out.println("add(2) " + aStorageInterfaceString.add("2"));
        System.out.println("add(2) " + aStorageInterfaceString.add("2"));
        System.out.println("1." + aStorageInterfaceString);
    }
    public static void testStringBuild(StorageInterface<String> aStorageInterfaceString)	{

        String three = new String("3");

        aStorageInterfaceString.add(null);
        aStorageInterfaceString.add(null);
        aStorageInterfaceString.add("3");
        aStorageInterfaceString.add(three);
        aStorageInterfaceString.add("1");
        aStorageInterfaceString.add("2");
        System.out.println("1." + aStorageInterfaceString);
    }
    public static void testStringManipulate(StorageInterface<String> aStorageInterfaceString)	{
        System.out.println("aStorageInterfaceString.find(null) " + aStorageInterfaceString.find(null) );
        System.out.println("aStorageInterfaceString.delete(null) " + aStorageInterfaceString.delete(null) );
        System.out.println("aStorageInterfaceString.delete(1) " + aStorageInterfaceString.delete("1") );
        System.out.println("aStorageInterfaceString.delete(0) " + aStorageInterfaceString.delete("0") );
        System.out.println("2." + aStorageInterfaceString);
    }
    public static void testInteger(StorageInterface<Integer> aStorageInterfaceInteger)	{

        aStorageInterfaceInteger.add(null);
        aStorageInterfaceInteger.add(null);
        aStorageInterfaceInteger.add(Integer.valueOf("55"));
        aStorageInterfaceInteger.add(Integer.valueOf("33"));
        aStorageInterfaceInteger.add(Integer.valueOf("66"));
        System.out.println("aStorageInterfaceInteger: " + aStorageInterfaceInteger);
    }
    public static void testAddress(StorageInterface<Address> aStorageInterfaceAddress)	{
        aStorageInterfaceAddress.add(  new Address(1600, "Pennsylvania Avenue NW", "Washington", "DC", 20500) );
        aStorageInterfaceAddress.add(  new Address(11, "Wall Street", "New York", "NY", 10118) );
        aStorageInterfaceAddress.add(  new Address(102, "Lomb Memorial Drive", "Rochester", "NY", 14623) );
        aStorageInterfaceAddress.add(  new Address(1, "A", "B", "C", 1) );
        System.out.println("aStorageInterfaceAddress: " + aStorageInterfaceAddress);
    }
    public static void testLP(StorageInterface<LP> aStorageInterfaceLP)	{
        aStorageInterfaceLP.add( new LP( 1960, "Deep Purple in Rock", "Deep Purple", (float)43.30, 7));
        aStorageInterfaceLP.add( new LP( 1973, "Dark Side of the Moon", "Pink Floyd ", (float)43.09, 10));
        System.out.println("aStorageInterfaceLP: " + aStorageInterfaceLP);
    }
    public static void main(String args[] )	{
        StorageInterface<String> aStorageInterfaceString = new SortedStorage<String>();
        StorageInterface<Integer> aStorageInterfaceInteger = new SortedStorage<Integer>();
        StorageInterface<Address> aStorageInterfaceAddress = new SortedStorage<Address>();
        StorageInterface<LP> aStorageInterfaceLP = new SortedStorage<LP>();
        testStringBuild(aStorageInterfaceString);
        testStringManipulate(aStorageInterfaceString);
        testInteger(aStorageInterfaceInteger);
        testAddress(aStorageInterfaceAddress);
        testLP(aStorageInterfaceLP);
    }
}
