package com.lmei.presenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.lmei.model.BaseInstrument;
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

	private I instrument;
	private R rule;
	private Thread thread;
	private BlockingQueue<String> viewRequestUpdateDeviceQueue = new LinkedBlockingDeque<>(50);

	
	public I getInstrument() {
		return instrument;
	}

	public void setInstrument(I instrument) {
		this.instrument = instrument;
	}

	public R getRule() {
		return rule;
	}

	public void setRule(R rule) {
		this.rule = rule;
	}
	
}
