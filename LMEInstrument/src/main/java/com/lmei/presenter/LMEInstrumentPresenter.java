/**
 * 
 */
package com.lmei.presenter;

import com.lmei.model.LMEInstrument;
import com.lmei.model.LMERule;

/**
 * @author Thang Le
 *
 */
public class LMEInstrumentPresenter extends InstrumentPresenter<LMEInstrument, LMERule> {

	public LMEInstrumentPresenter(LMEInstrument instrument, LMERule rule) {
		super(instrument, rule);
	}

	public interface View {
		void setLMEMappingRule();
	}
}
