import java.io.FileReader;
/**
 *Implementing a Test Class to test LineR
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
public class LineNumberReaderDemo
{   
    public static void testSetLineNumber(){
        try(FileReader fr = new FileReader("test.txt");
            LineR lnr = new LineR(fr)){
            System.out.println("----Testing setLineNumber and getLineNumber");
            lnr.setLineNumber(0);
            System.out.println("--"+lnr.getLineNumber()+"--");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }      
    public static void testRead(){
        try(FileReader fr = new FileReader("test.txt");
            LineR lnr = new LineR(fr)){
                System.out.println("----Testing Character Read----");
                int str;
                char ch;
                while((str = lnr.read())!=-1){
                    ch = (char)str;
                    System.out.print("--"+ch+"--");
                }
                System.out.println("");
            }
            catch(Exception e){
                  e.printStackTrace();
                }
    }   
    public static void testReadCbuf(){
        try(FileReader fr = new FileReader("test.txt");
            LineR lnr = new LineR(fr)){
                System.out.println("----Testing Character Read with offset and Limit----");
                int str;
                char[] cbuf = new char[10];
                lnr.readLine();
                str = lnr.read(cbuf, 2, 3);
                System.out.println("Number of char read: "+str);
                for(int index = 0; index < cbuf.length; index++) {
                     // if char is empty
                    if(cbuf[index] == 0)
                        cbuf[index] = '-';
                    // prints char
                    System.out.print(cbuf[index]);
             }
             System.out.println("");
        }
        catch(Exception e){
              e.printStackTrace();
            }
    }
    public static void testReadLine(){
        try(FileReader fr = new FileReader("test.txt");
            LineR lnr = new LineR(fr)){
                System.out.println("----Testing ReadLine----");
                String str;
                while((str = lnr.readLine())!=null){
                    System.out.println("--"+str+"--");
                }
            }
        catch(Exception e){
            e.printStackTrace();
            }
    }
    public static void testSkip(){
        try(FileReader fr = new FileReader("test.txt");
            LineR lnr = new LineR(fr)){
                System.out.println("----Testing Skip----");
                long n = lnr.skip(1);
                System.out.println("--"+n+"--");
                }
        catch(Exception e){
            e.printStackTrace();
            }
    }
    public static void testMarkReset(){
        try(FileReader fr = new FileReader("test.txt");
                LineR lnr = new LineR(fr)){
                    System.out.println("----Testing Mark and Reset----");
                    for( int index = 0; index < 2; index++){
                        System.out.print("--"+(char)lnr.read()+"--");
                    }
                    lnr.mark(0);
                    System.out.println("");
                    System.out.println("--mark()--");
                    for( int index = 0; index < 4; index++){
                        System.out.print("--"+(char)lnr.read()+"--");
                    }
                    lnr.reset();    
                    System.out.println("");
                    System.out.println("--Reset--");
                    for( int index = 0; index < 4; index++){
                        System.out.print("--"+(char)lnr.read()+"--");
                    }
                    }
        catch(Exception e){
            e.printStackTrace();
            }
    }
    public static void main(String[] args) {
        testSetLineNumber();
        testRead();
        testReadCbuf();
        testReadLine();
        testSkip();
        testMarkReset();
    }
}