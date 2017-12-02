package com.lmei.rule;

/**
 * put all common necessary variables or methods here. We need an abstract class
 * because we cannot put non-static variables to the interface.
 * 
 * @author Thang Le
 *
 */

public abstract class BaseRule implements Rules {
	// ID of referenced instrument if have
	private String referencedInstrumentId;

	public void ruleForLastTradingDateField(RuleForField rule) {

	}

	public void ruleForDeliveryDateField(RuleForField rule) {

	}

	public void ruleForMarketField(RuleForField rule) {

	}

	public void ruleForLabelField(RuleForField rule) {

	}

	public String getReferencedInstrumentId() {
		return referencedInstrumentId;
	}

	public void setReferencedInstrumentId(String referencedInstrumentId) {
		this.referencedInstrumentId = referencedInstrumentId;
	}

}
