/**
 * 
 */
package com.lmei.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmei.model.InternalInstrument;
import com.lmei.presenter.LMEInstrumentPresenter.View;

/**
 * @author Thang Le
 *
 */
public class LMEView implements View {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(LMEView.class);

	@Override
	public void printInternalInstrument(InternalInstrument internalInstrument) {
		LOG.debug("publish Internal Instrument for LME:");
		System.out.println("LAST_TRADING_DATE | DELIVERY_DATE | MARKET | LABEL");
		System.out.println(internalInstrument.getLastTradingDate() + " | " + internalInstrument.getDeliveryDate()
				+ " | " + internalInstrument.getMarket() + " | " + internalInstrument.getLabel() + " | "
				+ internalInstrument.isTradable());
	}

}
