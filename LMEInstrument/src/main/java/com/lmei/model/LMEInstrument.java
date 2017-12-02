/**
 * 
 */
package com.lmei.model;

/**
 * @author Thang Le
 *
 */
public class LMEInstrument extends BaseInstrument {

	private boolean tradable;
	
	
	public LMEInstrument() {
		
	}


	public boolean isTradable() {
		return tradable;
	}


	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

}
