
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {
	// Thread pool
	static private ExecutorService executor;

	public ExecutorServiceTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ExecutorServiceTest executorServiceTest = new ExecutorServiceTest();
		executor = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			executor.submit(executorServiceTest.new InboundMessageThread(i));
		}

		try {
			Thread.sleep(1000);
			executor.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public class InboundMessageThread implements Runnable {
		int threadid;

		public InboundMessageThread(int id) {
			threadid = id;
		}

		@Override
		public void run() {
			System.out.println("=====ThreadID:  " + threadid + " name : " + Thread.currentThread().getName());

		}

	}

}
