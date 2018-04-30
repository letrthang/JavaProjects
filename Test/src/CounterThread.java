

public class CounterThread extends Thread {
	Integer in;
	Object obj;

	CounterThread(Integer in, Object obj) {
		this.in = in;
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized (obj) {
			while (true) {
				try {
					if (ThreadNotify.i < 100) {
						ThreadNotify.i++;
					} else if (ThreadNotify.i == 100) {
						System.out.println("count thread i: " + ThreadNotify.i);
						obj.notify();
						obj.wait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
