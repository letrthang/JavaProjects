package com.lmei.presenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.lmei.model.BaseInstrument;
import com.lmei.model.PrimeInstrument;
import com.lmei.rule.BaseRule;
import com.lmei.rule.PrimeRule;

public class PrimeInstrumentPresenter extends InstrumentPresenter<PrimeInstrument, PrimeRule> {

	private BlockingQueue<PrimeInstrument> primeInstrumentsQueue = new LinkedBlockingDeque<>(50);
	private PrimeRule primeRule;
	private Thread thread;

	public interface View {
		void setPrimeMappingRule();

		void setPrimeInstrument();

		void printInternalInstrument();
	}
	
	public void init() {
		super.init();
		startPrimeThread();
	}

	public void startPrimeThread() {
		if (thread == null) {
			thread = new PrimeInstrumentThread();
			thread.start();
		}
	}

	@Override
	public void addRuleToPresenter(BaseRule rule) {
		this.primeRule = (PrimeRule) rule;

	}

	@Override
	public void addInstrumentToPresenterQueue(BaseInstrument instrument) {
		try {
			primeInstrumentsQueue.put((PrimeInstrument) instrument);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private class PrimeInstrumentThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					PrimeInstrument instrument = primeInstrumentsQueue.take();
					generateInternalInstrument(instrument, primeRule);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void generateInternalInstrument(PrimeInstrument instrument, PrimeRule rule) {

	}

}
