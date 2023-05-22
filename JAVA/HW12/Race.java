public class Race extends Thread	{

	private String info;
	static Object aObject = new Object();	//
	static int counter = 0;

	public Race (String info) {
		this.info = info;
	}
	
 	public void race() {
	   synchronized ( aObject )	{

		counter ++;		//
		if ( counter == 3 )	{
			new Race("ref").start();
		}
		try {
			System.err.println(info + " is ready to race!");
			aObject.wait();
			System.err.println(info + " is racing!");
		} catch ( IllegalMonitorStateException  e )	{
			System.err.println(info +
			  ": IllegalMonitorStateException");
		} catch ( InterruptedException  e )	{
			System.err.println(info +
			  ": InterruptedException");
		}
	  }
	}
	public void goRacing () {
		synchronized ( aObject )	{
			try {
				System.err.println(info + " is notifying!");
				aObject.notifyAll();
			} catch ( IllegalMonitorStateException  e )	{
				System.err.println(info +
				  ": IllegalMonitorStateException");
			}
			System.err.println(info + " is awake!");
		}
	}
	public void run () {
	   	synchronized ( aObject )	{
			if ( ! info.equals("ref") )
				race();
			else
				goRacing();
	   	}
	}

	public static void main (String args []) {
		new Race("first").start();
		new Race("second").start();
        new Race("third").start();
	}
}