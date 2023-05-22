import java.io.*;
import java.util.Scanner;
/**
 *External person uses it to tamper
 *with file
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class Modify implements Serializable {
    public void deserializeObject() {
        try (//try with resource
                FileInputStream istream = new FileInputStream("secret.s");
                ObjectInputStream p = new ObjectInputStream(istream);
        ) {
            String word=(String)p.readObject();//reads message
            int number=p.readInt();//reads hascode
            try (//try with resource
                    FileOutputStream ostream = new FileOutputStream("secret.s",false);//Does not append
                    ObjectOutputStream p_old = new ObjectOutputStream(ostream);
            )
            {
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("You've managed to gain access..You can now modify it...");
                word = myObj.nextLine();//User input
                p_old.writeObject(word);//Serialise string
                p_old.writeInt(number);//Serialises hashcode of actual message

            }
            catch (IOException ie) {//Catches exception
                System.out.println(ie.getMessage());
            }

        } catch (IOException | ClassNotFoundException ie) {//Catches Exception
            System.out.println(ie.getMessage());
        }

    }
    public static void main(String args[])
    {
        Modify modify=new Modify();
        modify.deserializeObject();
    }
}
