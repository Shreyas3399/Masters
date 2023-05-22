import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
/**
 *Implementing a file to enter secret from terminal
 * and serialize it to file
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class CreateSecret implements Serializable {
    private static final long SerialVersionUID = 10l;
    public void serializeObject() {
        try (//try with resource
                FileOutputStream ostream = new FileOutputStream("secret.s");
                ObjectOutputStream p = new ObjectOutputStream(ostream);
        ) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the secret...");
            String s = myObj.nextLine();//User input
            p.writeObject(s);//Serializing message
            p.writeInt(s.hashCode());//Serialising hashcode
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }

    }
    public static void main(String args[])
    {
        CreateSecret createSecret=new CreateSecret();
        createSecret.serializeObject();
    }


}
