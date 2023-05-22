public class Grep 
{
    public static void ab()
    {
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        //Computing ^ab$
        for(int i = 0; i<len ; i++)
        {
            if(state==0)
            {
                if((pattern1.charAt(i)=='a'))
                {
                    state = 1;
                }
                else
                {
                    state = errorState;
                }
                continue;
            }
            if(state==1)
            {
                if((pattern1.charAt(i))=='b')
                {
                    if(i==len-1)
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        state = errorState;
                    }
                }
                else
                {
                    state = errorState;
                }
            }
            if(state==errorState)
            {
                System.out.println("False");
                break;
            }
        }
    }
    
    public static void dotaplusbdot()
    {

        // computing .a+b.
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        state = 1;
        int acount = 0;
        int bcount = 0;
        for(int i = 1; i < len; i++)
        {
            if(state==1)
            {
                if((pattern1.charAt(i))=='a')
                {
                    state = 1;
                    acount++;
                }
                if((pattern1.charAt(i))=='b')
                {
                    state = 2;
                    bcount++;
                }
            }
            if(state==2)
            {
                if((pattern1.charAt(i))=='a')
                {
                    state = 2;
                }
                if((pattern1.charAt(i))=='b')
                {
                    state = 2;
                }
            }
           
        }
        if(acount>1 || bcount>1)
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }

    public static void dotabdot()
    {
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        // Computing .ab.
        for(int i = 1; i < len; i++)
        {
            if(len%2!=0 || len<=2)
            {
                state = errorState;
            }
            if(state==0)
            {
                if((pattern1.charAt(i))=='a')    
                {
                    state = 1;
                }
                else 
                {
                    state = errorState;
                }
                continue;
            }
            if(state==1)
            {
                if((pattern1.charAt(i))=='b')
                {
                    state = 2;
                }
                else
                {
                    state = errorState;
                }
                continue;    
            }
            if(state==2)
            {
                if(i==len-1)
                {
                    System.out.println("True");
                }
                else if((pattern1.charAt(i))=='a')
                {
                    state = 1;
                }
            }
            if(state==errorState)
            {
                System.out.println("False");
                i = len;
            }
        }
    }
    

    public static void fourth()
    {
        // computing ^[ab]c$
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        state = 0;
        for(int i = 0; i < len; i++)
        {
            if(state==0)
            {
                if((pattern1.charAt(i))=='a' || (pattern1.charAt(i))=='b')
                {
                    state = 1;
                }
                else
                {
                    state = errorState;
                }
                continue; 
            }
            if(state==1)
            {
                if(i==len-1)
                {
                    if((pattern1.charAt(i)=='c'))
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        System.out.println("False");
                    }
                }
                else
                {
                    state = errorState;
                }
                continue;
            }
            if(state==errorState)
            {
                System.out.println("False");
            }
        }
    }    

    public static void fifth()
    {
        // computing ^[ab]?c$
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        for(int i = 0; i < len; i++)
        {
            if(state==0)
            {
                if((pattern1.charAt(i))=='a' || (pattern1.charAt(i))=='b')
                {
                    state = 1;
                }
                else if((pattern1.charAt(i)=='c'))
                {
                    if(i==len-1)
                    {
                        System.out.println("True");
                        i = len;
                    }   
                }
                else
                {
                    state = errorState;
                }
                continue; 
            }
            if(state==1)
            {
                if(i==len-1)
                {
                    if((pattern1.charAt(i)=='c'))
                    {
                        System.out.println("True");
                        i = len;
                    }
                    else
                    {
                        System.out.println("False");
                    }
                }
                else 
                {
                    state = errorState;
                }
                continue; 
            }
            if(state==errorState)
            {
                System.out.println("False");
                i = len;
            }
        }
    }
    
    public static void sixth()
    {
        // computing ^[ab]?|c?$
        String pattern1 = regExGen();
        int len = pattern1.length();
        int errorState = -1;
        int state = 0;
        for(int i = 0; i < len; i++)
        {
            if(state==0)
            {
                if((pattern1.charAt(i))=='a' || (pattern1.charAt(i))=='b')
                {
                    if(i==len-1)
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        state = 1;
                    }
                }
                else if((pattern1.charAt(i))=='c')
                {
                    if(i==len-1)
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        state = errorState;
                    }
                }
                else
                {
                    state=errorState;
                }
                continue;
            }
            if(state==1)
            {
                if(i==len-1)
                {
                    if((pattern1.charAt(i))=='c')
                    {
                        System.out.println("True");
                    }
                    else
                    {
                        System.out.println("False");
                    }
                }
            }
            if(state==errorState)
            {
                System.out.println("False");
                i=len;
            }
        }
    }
    
    public static String regExGen()
    {
        String rightWord = "";
        return rightWord;
    }
    public static void main(String[] args) 
    {        
            ab();
            dotaplusbdot();
            dotabdot();
            fourth();
            fifth();
            sixth();
    }
}
