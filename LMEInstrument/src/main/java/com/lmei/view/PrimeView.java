package com.lmei.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmei.model.InternalInstrument;

public class PrimeView implements com.lmei.presenter.PrimeInstrumentPresenter.View {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(PrimeView.class);

	@Override
	public void printInternalInstrument(InternalInstrument internalInstrument) {
		LOG.debug("publish Internal Instrument for Prime:");
		System.out.println("LAST_TRADING_DATE | DELIVERY_DATE | MARKET | LABEL");
		System.out.println(internalInstrument.getLastTradingDate() + " | " + internalInstrument.getDeliveryDate()
				+ " | " + internalInstrument.getMarket() + " | " + internalInstrument.getLabel() + " | "
				+ internalInstrument.isTradable());
	}

}
