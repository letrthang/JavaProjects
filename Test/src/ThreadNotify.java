
public class ThreadNotify extends Thread {
	public static int i = 0;
	Object obj = new Object();
	
	CounterThread counterThread = null;

	public ThreadNotify() {

	}

	public static void main(String[] args) {
		ThreadNotify main = new ThreadNotify();
		main.start();
	}

	@Override
	public void run() {

		synchronized (obj) {

			while (true) {
				try {
					if (i >= 100) {
						System.out.println("main thread i: " + i);
						i = 0;
						obj.notify();
					} else {
						if (counterThread == null) {
							counterThread = new CounterThread(i, obj);
							counterThread.start();
						}
						obj.wait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
