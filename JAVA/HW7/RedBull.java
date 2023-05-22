/**
 * Child Class for the Parent Abstract class implemented with abstract methods.
 */

public class RedBull extends Formula1 implements Engine{
    public void drivers(){
        System.out.println("Max Verstappen and Sergio Perez");
    }
    public void engines(){
        System.out.println("Honda Racing Corporation");
    }
    public void location(){
        System.out.println("Located in Milton Keynes");
    }
    
}
