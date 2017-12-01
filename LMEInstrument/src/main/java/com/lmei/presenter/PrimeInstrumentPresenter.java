package com.lmei.presenter;

import com.lmei.model.PrimeInstrument;
import com.lmei.rule.PrimeRule;

public class PrimeInstrumentPresenter extends InstrumentPresenter<PrimeInstrument, PrimeRule> {

	public PrimeInstrumentPresenter(PrimeInstrument instrument, PrimeRule rule) {
		super(instrument, rule);
	}

}
