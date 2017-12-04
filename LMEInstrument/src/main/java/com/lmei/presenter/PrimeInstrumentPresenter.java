package com.lmei.presenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.lmei.model.BaseInstrument;
import com.lmei.model.InternalInstrument;
import com.lmei.model.PrimeInstrument;
import com.lmei.rule.BaseRule;
import com.lmei.rule.PrimeRule;

public class PrimeInstrumentPresenter extends InstrumentPresenter<PrimeInstrument, PrimeRule> {

	private BlockingQueue<PrimeInstrument> primeInstrumentsQueue = new LinkedBlockingDeque<>(50);
	private PrimeRule primeRule;
	private Thread thread;
	private View view;

	public interface View {

		void printInternalInstrument(InternalInstrument internalInstrument);
	}

	public PrimeInstrumentPresenter(View view) {
		this.view = view;
		init();
	}

	protected void init() {
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
		if (rule == null) {
			return;
		}
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
					if (instrument.getId() < 0) {
						// terminal thread
						break;
					}
					generateInternalInstrument(instrument, primeRule);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * implement logic for internal prime instrument.
	 * 
	 * @param instrument
	 * @param rule
	 */
	private void generateInternalInstrument(PrimeInstrument instrument, PrimeRule rule) {
		// if we design a database, then it is easy to get a referenced instrument. Here
		// for simple, we not implement that DB.
		InternalInstrument internalInstrument = new InternalInstrument();
		internalInstrument.setId(instrument.getId());
		// By right we use "rule.getReferencedInstrumentId()" to get LME instrument for
		// the 2 fields of PrimeInstrument: "LastTradingDate" and "DeliveryDate".
		// however this implement not done this part yet.
		internalInstrument.setLastTradingDate(instrument.getLastTradingDate());
		internalInstrument.setDeliveryDate(instrument.getDeliveryDate());
		internalInstrument.setMarket(instrument.getMarket());
		internalInstrument.setLabel(instrument.getLabel());

		internalInstrument.setTradable(rule.isTradableField());

		view.printInternalInstrument(internalInstrument);
	}

}
