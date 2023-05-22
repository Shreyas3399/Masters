public class Testing {
    public static void main(String[] args){
        RedBull obj1 = new RedBull();
        Ferrari obj2 = new Ferrari();
        ArrowMclaren obj3 = new ArrowMclaren();
        GanassiRacing obj4 = new GanassiRacing();
        
        obj1.carRace();
        System.out.println();

        System.out.println("Printing from RedBull class: ");
        System.out.println("Drivers:-");
        obj1.drivers();
        System.out.println("Engines:-");
        obj1.engines();
        System.out.println("Location:-");
        obj1.location();
        System.out.println();

        System.out.println("Printing from Ferrari class: ");
        System.out.println("Drivers:-");
        obj2.drivers();
        System.out.println("Engines:-");
        obj2.engines();
        System.out.println("Location:-");
        obj2.location();
        System.out.println();

        System.out.println("Printing from ArrowMclaren class: ");
        System.out.println("Sponsors:-");
        obj3.sponsors();
        System.out.println("Engines:-");
        obj3.engines();
        System.out.println("Principals:-");
        obj3.principals();
        System.out.println();

        System.out.println("Printing from GanassiRacing class: ");
        System.out.println("Sponsors:-");
        obj4.sponsors();
        System.out.println("Principals:-");
        obj4.principals();
        System.out.println();

    }
}
