
public class Test3 extends Test2 {
    public static void testAll3(String what, StoreEverythingSetWithNulls aStoreEverythingSetWithNulls, Object[] theObjects)	{

        System.out.println(what + ":  ");
        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("	add(" + theObjects[index] + "): "  + aStoreEverythingSetWithNulls.add(theObjects[index]));
        }
        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("	find(" + theObjects[index] + "): "  + aStoreEverythingSetWithNulls.find(theObjects[index]));
        }
        System.out.println("	" +  aStoreEverythingSetWithNulls);
        System.out.println("	howManyNulls:  " +  aStoreEverythingSetWithNulls.howManyNulls());
        System.out.println("---------------------------------------");

        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("delete(" + theObjects[index] + "): "  + aStoreEverythingSetWithNulls.delete(theObjects[index]));
        }

        System.out.println(	"All should be deleted");
        System.out.println("	" +  aStoreEverythingSetWithNulls);
    }
    public static void test()	{
        StoreEverythingSetWithNulls[] aStoreEverythingSetWithNulls = new StoreEverythingSetWithNulls[3];

        // testAll2( new StoreEverythingSetWithNulls[3] );

        String theStrings[] = { "3","3", "3", "2", "4", null, null ,};
        aStoreEverythingSetWithNulls[0] = new StoreEverythingSetWithNulls();
        testString("StoreEverythingSetWithNulls", aStoreEverythingSetWithNulls[0], theStrings);

        Integer theIntegers[] = { Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(4) };
        aStoreEverythingSetWithNulls[1] = new StoreEverythingSetWithNulls();
        testString("StoreEverythingSetWithNulls", aStoreEverythingSetWithNulls[1], theIntegers);

    }
    public static void main(String args[] )	{
        test();
    }
}

