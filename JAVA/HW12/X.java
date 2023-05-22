public class X extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public X(int id)	{
	this.id = id;
	o       = new Object();
    try {
        sleep((int)(Math.random()*10));
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }
    public void run () {
	if ( id == 0 )	{
		new X(1).start();
		new X(2).start();
	}
	synchronized ( o ) {
		try {
			System.err.println(id + " --->");
			o.notifyAll();
			o.wait();
			o.notifyAll();
			System.err.println(id + " <---");
		}
		catch (  InterruptedException e ) {
		}
	    }
    }
    public static void main (String args []) {
        new X(0).run();
    }
}