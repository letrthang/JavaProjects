package com.lmei.model;

public abstract class BaseInstrument {
	String id;
	String lastTradingDate;
	String deliveryDate;
	String market;
	String label;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the lastTradingDate
	 */
	public String getLastTradingDate() {
		return lastTradingDate;
	}

	/**
	 * @param lastTradingDate
	 *            the lastTradingDate to set
	 */
	public void setLastTradingDate(String lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}

	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @param market the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}
}
