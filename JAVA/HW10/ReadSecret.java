import java.io.*;
/**
 *Checks if secret word exists
 * and if file has been tampered with
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class ReadSecret {
    public void deserializeObject() {
        try (//try with resource
                FileInputStream istream = new FileInputStream("secret.s");
                ObjectInputStream p = new ObjectInputStream(istream);
        ) {
            String word=(String)p.readObject();//deserialising message
            int number=p.readInt();//deserialising int
            boolean val=(word.contains("Rochester")||word.contains("rochester"));//Checking if secret word is contained
            if(!val)
            {
                System.out.println("Secret word is not present in the file");
                System.exit(0);
            }
            else
            {
                System.out.println("Secret word is present in the file...");
                System.out.println("Now lets check file accuracy...");
                System.out.println();

            }
           if(number==word.hashCode())//Checking if original string is modified
           {
               System.out.println("Your file is accurate!");
           }
           else
           {
               System.out.println("Unfortunately your file has been modified...");
               System.out.println();

           }
            System.out.println(word);

        } catch (IOException | ClassNotFoundException ie) {
            System.out.println(ie.getMessage());
        }

    }
    public static void main(String args[])
    {
        ReadSecret readSecret=new ReadSecret();
        readSecret.deserializeObject();
    }
}
