package com.spiraxcalibration.models;

public class CostPricesData {
	
	private String costPrice;
	private String costMonth;
	
	private String costScrappedCount;
	private String costInstrumentCount;
	
	/**
	 * @return the costInstrumentCount
	 */
	public String getCostInstrumentCount() {
		return costInstrumentCount;
	}
	/**
	 * @param costInstrumentCount the costInstrumentCount to set
	 */
	public void setCostInstrumentCount(String costInstrumentCount) {
		this.costInstrumentCount = costInstrumentCount;
	}
	/**
	 * @return the costScrappedCount
	 */
	public String getCostScrappedCount() {
		return costScrappedCount;
	}
	/**
	 * @param costScrappedCount the costScrappedCount to set
	 */
	public void setCostScrappedCount(String costScrappedCount) {
		this.costScrappedCount = costScrappedCount;
	}
	/**
	 * @return the costPrice
	 */
	public String getCostPrice() {
		return costPrice;
	}
	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * @return the costMonth
	 */
	public String getCostMonth() {
		return costMonth;
	}
	/**
	 * @param costMonth the costMonth to set
	 */
	public void setCostMonth(String costMonth) {
		this.costMonth = costMonth;
	}
	
}
