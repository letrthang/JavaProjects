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

	void ruleForLastTradingDateField(RuleForField rule);

	void ruleForDeliveryDateField(RuleForField rule);

	void ruleForMarketField(RuleForField rule);

	void ruleForLabelField(RuleForField rule);
}
