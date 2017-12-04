package com.lmei.rule;

/**
 * extra rules of Primes
 * 
 * @author Thang Le
 *
 */
public class PrimeRule extends BaseRule {
	private boolean TradableField;
	private RuleForField ExchangeCodeField;

	public boolean isTradableField() {
		return TradableField;
	}

	public void ruleForTradableField(boolean tradableField) {
		TradableField = tradableField;
	}

	public RuleForField getExchangeCodeField() {
		return ExchangeCodeField;
	}

	public void ruleForExchangeCodeField(RuleForField exchangeCodeField) {
		ExchangeCodeField = exchangeCodeField;
	}

}
