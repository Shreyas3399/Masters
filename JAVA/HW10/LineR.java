/**
 *Implementing a verison of LineNumberReader
 *@author Shireen Maini
 *@author Shreyas Belkune
 */
import java.io.BufferedReader;
import java.io.Reader;
import java.io.IOException;

public class LineR extends BufferedReader{
    private int lineNumber; //Keeping track of the line number 
    private int stream_marked; // keeping track of the marked char in the stream

    /**
     * Create a new line-numbering reader, using the default input-buffer size.
     * @param in - reader object to provide the underlyfing stream
     */
    public LineR(Reader in){
        super(in);
    }

     /**
     * Create a new line-numbering reader, reading characters into a buffer of the given size.
     * @param in - reader object to provide the underlyfing stream
     * @param sz - An int specifying the size of the buffer
     */
    public LineR(Reader in, int size){
        super(in, size);
    }

    /**
     * Set the current line number.
     * @param lineNumber - An int specifying the line number
     */
    public void setLineNumber(int lineNumber){
        this.lineNumber = lineNumber;
    }

    /**
     * Get the current line number.
     * @return lineNumber - The current line number
     */
    public int getLineNumber(){
        return lineNumber;
    }

    /** 
     * Read a single character. 
     * Line terminators are compressed into single newline ('\n') characters. 
     * The current line number is incremented whenever a line terminator is read, or when the end of the stream
     *  is reached and the last character in the stream is not a line terminator.
     * @return The character read, or -1 if the end of the stream has been reached
     */
    @Override 
    public int read()throws IOException{
        int ch = super.read();
        if(ch=='\n'){
            lineNumber++;
            return -1;
        }
        return ch;
    }
    /** 
     * Reads characters into a portion of an array. 
     * This method will block until some input is available, an I/O error occurs,
     *  or the end of the stream is reached.
     * @param cbuf - Destination buffer
     * @param off - Offset at which to start storing characters
     * @param len - Maximum number of characters to read
     * @return num_ch - The number of characters read, or -1 if the end of the stream has been reached
     */
    @Override
    public int read(char[] cbuf, int off, int len)throws IOException{
        int num_ch = super.read(cbuf, off, len);
        int i = off;
        while(i < off+num_ch){
            int c = cbuf[i];
            if(c== '\n'){
                lineNumber++;
                break;
            }
            i++;
        }
        System.out.println("");
        return num_ch;
    }
            
    /**
     * Read a line of text. Line terminators are compressed into single newline ('\n') characters. 
     * The current line number is incremented whenever a line terminator is read, 
     * or when the end of the stream is reached and the last character in the stream is not a line terminator.
     * @return - A String containing the contents of the line, not including any line termination characters,
     *  or null if the end of the stream has been reached
     * */    
    @Override

    public String readLine()throws IOException{
        String inp = super.readLine();
        if (inp!=null){
            lineNumber++;
        }
        return inp;
    }

    /**
     * Skip characters.
     * @param n - The number of characters to skip
     * @return The number of characters actually skipped
     */
    @Override
    public long skip(long n)throws IOException{
        int num = (int) n;
        String str;
        int skipped_char = 0;
        str = this.readLine();
        if(n<0){
            System.out.println("Invalid");
        }
        else{
            int i = 0;
            while (i<str.length()){
                System.out.print("--"+str.charAt(i)+"--");
                i += 1;
                i += num;
                skipped_char++;
            }
            System.out.println("");
        }
        long skipped = str.length()-skipped_char;
        return skipped;
    }

    /**
     * Mark the present position in the stream. Subsequent calls to reset() will attempt
     * to reposition the stream to this point, and will also reset the line number appropriately.
     * @param readAheadLimit - Limit on the number of characters that may be read while still preserving the mark. 
     * After reading this many characters, attempting to reset the stream may fail.
     */
    @Override
    public void mark(int readAheadLimit)throws IOException{
        super.mark(readAheadLimit);
        stream_marked = lineNumber;
    }

    /**
     * Reset the stream to the most recent mark.
     */
    @Override
    public void reset()throws IOException{
        super.reset();
        lineNumber = stream_marked;
    }
}
