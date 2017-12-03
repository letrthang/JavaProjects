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
	private int referencedInstrumentId = -1;
	private RuleForField LastTradingDateField;
	private RuleForField DeliveryDateField;
	private RuleForField MarketField;
	private RuleForField LabelField;

	public void ruleForLastTradingDateField(RuleForField rule) {
		LastTradingDateField = rule;
	}

	public void ruleForDeliveryDateField(RuleForField rule) {
		DeliveryDateField = rule;
	}

	public void ruleForMarketField(RuleForField rule) {
		MarketField = rule;
	}

	public void ruleForLabelField(RuleForField rule) {
		LabelField = rule;
	}

	public int getReferencedInstrumentId() {
		return referencedInstrumentId;
	}

	public void setReferencedInstrumentId(int referencedInstrumentId) {
		this.referencedInstrumentId = referencedInstrumentId;
	}

	public RuleForField getLastTradingDateField() {
		return LastTradingDateField;
	}

	public RuleForField getDeliveryDateField() {
		return DeliveryDateField;
	}

	public RuleForField getMarketField() {
		return MarketField;
	}

	public RuleForField getLabelField() {
		return LabelField;
	}

}
