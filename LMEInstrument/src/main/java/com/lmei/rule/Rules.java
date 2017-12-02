/**
 * 
 */
package com.lmei.rule;

/**
 * all base mapping/merge rules to put here.
 * 
 * @author Thang Le
 *
 */
public interface Rules {

	void ruleForLastTradingDateField();

	void ruleForDeliveryDateField();

	void ruleForMarketField();

	void ruleForLabelField();
}
