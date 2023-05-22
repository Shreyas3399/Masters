public class Test {

        public static void main(String[] args) {
            SortableStoreString sortableStrings = new SortableStoreString();
            String[] strings = {"apple", "peach", "orange", "melon", "blackberry"};
            for (String string : strings) sortableStrings.add(string);

            System.out.printf("sortableStorage must implement toString(): %s\n", sortableStrings);
            System.out.printf("sortableStorage should be unsorted by default: isSorted()==%b\n", sortableStrings.isSorted());
            System.out.printf("sortableStorage must implement find(): find(\"apple\")==%b", sortableStrings.find("apple"));
            System.out.printf("sortableStorage must implement delete(): delete(\"apple\")==%b", sortableStrings.delete("apple"));
            System.out.printf("sortableStorage state after delete: %s\n", sortableStrings);
            sortableStrings.sort();
            System.out.printf("sortableStorage must implement sort(): sort(); isSorted==%b\n", sortableStrings.isSorted());
            System.out.printf("sortableStorage state after sort: %s\n", sortableStrings);
            System.out.printf("sortableStorage must implement compareTo(): compareTo(strings[0],strings[1])==%d\n", sortableStrings.compareTo(strings[0], strings[1]));
            System.out.println();

            SortableStoreInteger sortableIntegers = new SortableStoreInteger();
            Integer[] ints = {Integer.valueOf(10), Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(4),Integer.valueOf(4)};
            for (Integer integer : ints) sortableIntegers.add(integer);

            System.out.printf("sortableStorage must implement toString(): %s\n", sortableIntegers);
            System.out.printf("sortableStorage should be unsorted by default: isSorted()==%b\n", sortableIntegers.isSorted());
            System.out.printf("sortableStorage must implement find(): find(\"3\")==%b", sortableIntegers.find(Integer.valueOf(3)));
            System.out.printf("sortableStorage must implement delete(): delete(\"2\")==%b", sortableIntegers.delete(Integer.valueOf(2)));
            System.out.printf("sortableStorage state after delete: %s\n", sortableIntegers);
            sortableIntegers.sort();
            System.out.printf("sortableStorage must implement sort(): sort(); isSorted==%b\n", sortableIntegers.isSorted());
            System.out.printf("sortableStorage state after sort: %s\n", sortableIntegers);
            System.out.printf("sortableStorage must implement compareTo(): compareTo(strings[0],strings[1])==%d\n", sortableIntegers.compareTo(ints[0], ints[1]));
            System.out.println();

            SortableStoreStore sortableObject = new SortableStoreStore();
            Object[] objects = {new SortableStoreStore(),new SortableStoreStore(),new SortableStoreStore()};
            for (Object object : objects) sortableObject.add(object);

            System.out.printf("sortableStorage must implement toString(): %s\n", sortableObject);
            System.out.printf("sortableStorage should be unsorted by default: isSorted()==%b\n", sortableObject.isSorted());
            System.out.printf("sortableStorage must implement find(): find(\"itself\")==%b", sortableObject.find(objects[0]));
            System.out.printf("sortableStorage must implement delete(): delete(\"itself\")==%b", sortableObject.delete(objects[1]));
            System.out.printf("sortableStorage state after delete: %s\n", sortableObject);
            sortableObject.sort();
            System.out.printf("sortableStorage must implement sort(): sort(); isSorted==%b\n", sortableObject.isSorted());
            System.out.printf("sortableStorage state after sort: %s\n", sortableObject);
            System.out.printf("sortableStorage must implement compareTo(): compareTo(strings[0],strings[1])==%d\n", sortableObject.compareTo(objects[0],objects[1]));
        }


    }

