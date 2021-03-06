package com.lmei.presenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.lmei.model.BaseInstrument;
import com.lmei.model.LMEInstrument;
import com.lmei.model.PrimeInstrument;
import com.lmei.rule.BaseRule;

/**
 * put some common necessary variables or methods for presenters
 * 
 * @author Thang Le
 *
 * @param <I>
 * @param <R>
 */
public abstract class InstrumentPresenter<I extends BaseInstrument, R extends BaseRule> {

	// this thread acts as a dispatcher to deliver different instruments to
	// respective presenters.
	private static Thread thread;

	// this queue stores all instruments from all sources
	private static BlockingQueue<BaseInstrument> instrumentsQueue = new LinkedBlockingDeque<>(50);
	// presenters want to subscribe to receive respective instruments
	@SuppressWarnings("rawtypes")
	private static Map<String, InstrumentPresenter> instrumentPresentersMap;

	abstract public void addInstrumentToPresenterQueue(BaseInstrument instrument);

	abstract public void addRuleToPresenter(BaseRule rule);

	protected void init() {
		addPresenter(this);
		startInstrumentThread();
	}

	@SuppressWarnings("rawtypes")
	public void addPresenter(InstrumentPresenter presenter) {
		if (instrumentPresentersMap == null) {
			instrumentPresentersMap = new HashMap<>();
		}

		instrumentPresentersMap.put(presenter.getClass().getSimpleName(), presenter);

	}

	public void setInstrumentsQueue(I instrument) {
		try {
			instrumentsQueue.put(instrument);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setRule(R rule) {
		addRuleToPresenter(rule);
	}

	private void startInstrumentThread() {
		if (thread == null) {
			thread = new InstrumentThread();
			thread.start();
		}
	}

	private class InstrumentThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					BaseInstrument instrument = instrumentsQueue.take();
					if (instrument.getId() < 0) {
						// terminate thread when receive a poison signal
						break;
					}

					if (instrument instanceof LMEInstrument) {
						LMEInstrumentPresenter lmePresenter = (LMEInstrumentPresenter) (instrumentPresentersMap
								.get(LMEInstrumentPresenter.class.getSimpleName()));

						// pass instrument to LMEInstrumentPresenter
						lmePresenter.addInstrumentToPresenterQueue(instrument);

					} else if (instrument instanceof PrimeInstrument) {
						PrimeInstrumentPresenter primePresenter = (PrimeInstrumentPresenter) (instrumentPresentersMap
								.get(PrimeInstrumentPresenter.class.getSimpleName()));

						// pass instrument to PrimeInstrumentPresenter
						primePresenter.addInstrumentToPresenterQueue(instrument);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
