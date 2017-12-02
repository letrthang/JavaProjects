package com.lmei.model;

public class PrimeInstrument extends BaseInstrument {

	private boolean tradable;
	// reference to the instrument ID
	private String exchangeCode;

	public PrimeInstrument() {

	}

	public boolean isTradable() {
		return tradable;
	}

	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

}
