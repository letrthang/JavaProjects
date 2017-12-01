/**
 * 
 */
package com.lmei.presenter;

import com.lmei.model.LMEInstrument;
import com.lmei.rule.LMERule;

/**
 * @author Thang Le
 *
 */
public class LMEInstrumentPresenter extends InstrumentPresenter<LMEInstrument, LMERule> {
	private View view;

	public interface View {
		void setLMEMappingRule();
	}
}
