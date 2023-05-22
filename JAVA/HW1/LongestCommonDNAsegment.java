  /*
   * LongestCommonDNAsegment.java
   *
   * Version: $2.3$
   *
   */
   import java.lang.String;
   /**
   *Finding the longest
   *common sub-sequence given
   *two strings
   *
   * @author Shireen Maini
   * @author Shreyas Belkune
   */
   public class LongestCommonDNAsegment {
    /**
     * Finds longest common Dna segment
     * present in both DNA
     * @param dnaOne I/p of characters
     * @param dnaTwo I/p of characters
     * @return maximum common segment
     */
    static int longestCommon(char[] dnaOne,char[] dnaTwo)
    {
        int maxCount=0; // initializing the maximum count

        for (int i=0;i<dnaOne.length;i++) // running the loop till the first DNA seq length
        {
            for(int j=0;j<dnaTwo.length;j++) // running the loop till the second DNA seq length
            {
                int k=0,count=0;
                if(dnaOne[i]==dnaTwo[j]) // if there's a match
                {
                    //Checking the length is not exceeding in both cases and the sequence is matching
                    while((i+k)<dnaOne.length && ((j+k)<dnaTwo.length) && dnaOne[i+k]==dnaTwo[j+k])
                    {
                        count++;//Counting if there is a match
                        k++;
                    }
                    if(count>maxCount)
                    {
                        maxCount=count;
                    }
                }
            }
        }

        return maxCount;
    }
      /**
       * The main program
       * Calls the function
       * and prints the value
       */

    public static void main(String[] args) {
        char[] dnaOne = {'t', 'a', 'c', 'g', 't'};
        char[] dnaTwo = {'a', 'c', 'g', 't'};
        int count=longestCommon(dnaOne,dnaTwo);
        System.out.print("Longest in common:	"+count);
    }
}