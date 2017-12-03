package com.lmei.rule;

/**
 * extra rules of LME
 * 
 * @author Thang Le
 *
 */
public class LMERule extends BaseRule {
	private boolean TradableField;

	public boolean isTradableField() {
		return TradableField;
	}

	public void setTradableField(boolean tradableField) {
		TradableField = tradableField;
	}

	
	
}
