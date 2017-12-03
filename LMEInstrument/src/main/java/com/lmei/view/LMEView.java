/**
 * 
 */
package com.lmei.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmei.presenter.LMEInstrumentPresenter.View;

/**
 * @author Thang Le
 *
 */
public class LMEView implements View {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(LMEView.class);

	@Override
	public void printInternalInstrument() {
		LOG.debug("printInternalInstrument");

	}

}
