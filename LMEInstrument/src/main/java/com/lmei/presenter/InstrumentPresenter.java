package com.lmei.presenter;

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

	I instrument;
	R rule;

	public InstrumentPresenter(I instrument, R rule) {
		this.instrument = instrument;
		this.rule = rule;
	}
}
