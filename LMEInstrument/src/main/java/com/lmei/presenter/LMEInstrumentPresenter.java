/**
 * 
 */
package com.lmei.presenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.lmei.model.BaseInstrument;
import com.lmei.model.InternalInstrument;
import com.lmei.model.LMEInstrument;
import com.lmei.rule.BaseRule;
import com.lmei.rule.LMERule;

/**
 * @author Thang Le.
 *
 */
public class LMEInstrumentPresenter extends InstrumentPresenter<LMEInstrument, LMERule> {
	private View view;
	private BlockingQueue<LMEInstrument> lmeInstrumentsQueue = new LinkedBlockingDeque<>(50);
	private LMERule lmeRule;
	private Thread thread;

	public LMEInstrumentPresenter(View view) {
		this.view = view;
		init();
	}

	public interface View {
		void printInternalInstrument(InternalInstrument internalInstrument);
	}

	protected void init() {
		super.init();
		startLMEThread();
	}

	public void startLMEThread() {
		if (thread == null) {
			thread = new LMEInstrumentThread();
			thread.start();
		}
	}

	@Override
	public void addRuleToPresenter(BaseRule rule) {
		if (rule == null) {
			// maybe there is a bug in JDK.
			return;
		}
		this.lmeRule = (LMERule) rule;
	}

	@Override
	public void addInstrumentToPresenterQueue(BaseInstrument instrument) {
		try {
			lmeInstrumentsQueue.put((LMEInstrument) instrument);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private class LMEInstrumentThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					LMEInstrument instrument = lmeInstrumentsQueue.take();
					if (instrument.getId() < 0) {
						// terminate thread
						break;
					}
					generateInternalInstrument(instrument, lmeRule);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Based on the input instrument and rule, we can implement logic for the
	 * Internal Instrument
	 * 
	 * @param instrument
	 * @param rule
	 */
	private void generateInternalInstrument(LMEInstrument instrument, LMERule rule) {

		InternalInstrument internalInstrument = new InternalInstrument();

		internalInstrument.setId(instrument.getId());
		internalInstrument.setLastTradingDate(instrument.getLastTradingDate());
		internalInstrument.setDeliveryDate(instrument.getDeliveryDate());
		internalInstrument.setMarket(instrument.getMarket());
		internalInstrument.setLabel(instrument.getLabel());

		internalInstrument.setTradable(rule.isTradableField());

		view.printInternalInstrument(internalInstrument);
	}

}
