package com.lmei.rule;

/**
 * put all common necessary variables or methods here. We need an abstract class
 * because we cannot put non-static variables to the interface.
 * 
 * @author Thang Le
 *
 */

public abstract class BaseRule implements Rules {
	private String referencedInstrument;

	public void ruleForLastTradingDateField() {

	}

	public void ruleForDeliveryDateField() {

	}

	public void ruleForMarketField() {

	}

	public void ruleForLabelField() {

	}

	public String getReferencedInstrument() {
		return referencedInstrument;
	}

	public void setReferencedInstrument(String referencedInstrument) {
		this.referencedInstrument = referencedInstrument;
	}

}
