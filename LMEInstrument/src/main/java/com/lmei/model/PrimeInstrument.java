package com.lmei.model;

public class PrimeInstrument extends BaseInstrument {

	private boolean tradable;

	public PrimeInstrument() {

	}

	public boolean isTradable() {
		return tradable;
	}

	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

}
