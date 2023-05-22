
public class Test2 {
    public static<T> void test(String what, StoreEverything aStoreEverything,T[] theObjects)	{

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
    public static void testSelf(String what)	{
        System.out.println(what + ":  ");
        StoreEverything<StoreEverything<?>> aStoreEverything = new StoreEverything<>() ;
        aStoreEverything.add(aStoreEverything);
        System.out.println("	" +  aStoreEverything);
    }
    public static void testAll1() {
        StoreEverything theStoreEverything[] = { new StoreEverything<>(), new StoreEverything<>(), new StoreEverything<>() };
        StoreEverything<StoreEverything<?>> aStoreEverythings = new StoreEverything<>();
        test("StoreEverything: Objects", aStoreEverythings, theStoreEverything);
    }
    public static void testAll2() {
        String theStrings[] = { "3", "3", "2", "4" };
        StoreEverything<String> aStoreEverythings1 = new StoreEverything<>();
        test("StoreEverything: String", aStoreEverythings1, theStrings);

        Integer theIntegers[] = { Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(4) };
        StoreEverything<Integer> aStoreEverythings2 = new StoreEverything<>();
        test("StoreEverything: Integer", aStoreEverythings2, theIntegers);

        Object theObjects[] = { new Object(), new Object(), new Object(), new Object() };
        StoreEverything<Object> aStoreEverythings3 = new StoreEverything<>();
        test("StoreEverything: Objects", aStoreEverythings3, theObjects);

    }
    public static void main(String args[] )	{
        testAll1();
        testAll2();
        testSelf("StoreEverything stores itself");
    }
}
