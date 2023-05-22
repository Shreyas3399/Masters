/**
 * Child Classes for the Parent Abstract class implemented with abstract methods.
 */

public class Ferrari extends Formula1 implements Engine{
    public void drivers(){
        System.out.println("Charles Leclerc and Carlos Sainz");
    }
    public void engines(){
        System.out.println("Ferrari S.p.A");
    }
    public void location(){
        System.out.println("Located in Maranello");
    }
}
