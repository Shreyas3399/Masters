public class Test2 {
    public static void testString(String what, StoreEverything aStoreEverything, Object[] theObjects)	{

        System.out.println(what + ":  ");
        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("	add(" + theObjects[index] + "): "  + aStoreEverything.add(theObjects[index]));
        }
        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("	find(" + theObjects[index] + "): "  + aStoreEverything.find(theObjects[index]));
        }
        System.out.println("---------------------------------------");
       System.out.println("	" +  aStoreEverything);
       System.out.println("---------------------------------------");

        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("delete(" + theObjects[index] + "): "  + aStoreEverything.delete(theObjects[index]));
        }

        System.out.println(	"All should be deleted");
        System.out.println("	" +  aStoreEverything);
    }
    public static void testOne(String what, StoreEverything aStoreEverything, Object[] theObjects)	{

        System.out.println(what + ":  ");
        for (int index = 0; index < theObjects.length; index ++ )	{
            theObjects[index] = new StoreEverything() ;
        }
        for (int index = 0; index < theObjects.length; index ++ )	{
            aStoreEverything.add(theObjects[index] );
        }
        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("	find( + theObjects[index] + ): "  + aStoreEverything.find(theObjects[index]));
        }
        System.out.println("---------------------------------------");
        System.out.println("	" +  aStoreEverything);
        System.out.println("---------------------------------------");

        for (int index = 0; index < theObjects.length; index ++ )	{
            System.out.println("delete( + theObjects[index] + ): "  +aStoreEverything.delete(theObjects[index]) );
        }

        System.out.println(	"All should be deleted");
        System.out.println("	" +  aStoreEverything);
    }
    public static void testSelf(String what)	{

        System.out.println(what + ":  ");
        StoreEverything aStoreEverything = new StoreEverything() ;
        aStoreEverything.add(aStoreEverything);
        System.out.println("	" +  aStoreEverything);
    }
    public static void testAll2(StoreEverything[] aStoreEverythings) {
        String theStrings[] = { "3", "3", "2", "4" };
        aStoreEverythings[0] = new StoreEverything();
        testString("StoreEverything: String", aStoreEverythings[0], theStrings);

        Integer theIntegers[] = { Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(4) };
        aStoreEverythings[1] = new StoreEverything();
        testString("StoreEverything: Integer", aStoreEverythings[1], theIntegers);

        Object theObjects[] = { new Object(), new Object(), new Object(), new Object() };
        aStoreEverythings[2] = new StoreEverything();
        testString("StoreEverything: Objects", aStoreEverythings[2], theObjects);

        aStoreEverythings[2] = new StoreEverything();
        testString("StoreEverything: StoreEverything", aStoreEverythings[2], aStoreEverythings);

        aStoreEverythings[2] = new StoreEverything();
        testOne("StoreEverything", aStoreEverythings[2], aStoreEverythings);

        testSelf("StoreEverything stores itself");
    }
    public static void main(String args[] )	{
        testAll2( new StoreEverything[3]);
    }
}
