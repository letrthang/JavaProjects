/**
 * 
 */
package com.lmei.model;

/**
 * Internal instrument.
 * 
 * @author Thang Le
 *
 */
public class InternalInstrument extends BaseInstrument {

	private boolean tradable;

	public boolean isTradable() {
		return tradable;
	}

	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

}
