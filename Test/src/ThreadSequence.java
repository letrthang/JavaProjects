
public class ThreadSequence {
	Integer lock = 1;
	int expectedOrder = 1;

	class AppThread extends Thread {
		String name;
		int order;

		AppThread(String name, int order) {
			this.name = name;
			this.order = order;
		}

		@Override
		public void run() {
			try {
				synchronized (lock) {
					while (expectedOrder != order) {
						lock.wait();
					}

					System.out.println("name: " + name + ". Order: " + order);
					expectedOrder++;
					lock.notifyAll();

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ThreadSequence threadSequence = new ThreadSequence();
		AppThread t1 = threadSequence.new AppThread("T1", 1);
		AppThread t2 = threadSequence.new AppThread("T2", 2);
		AppThread t3 = threadSequence.new AppThread("T3", 3);

		try {

			t3.start();
			t2.start();
			t1.start();

			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
