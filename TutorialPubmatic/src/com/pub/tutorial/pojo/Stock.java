package com.pub.tutorial.pojo;

/**
 * POJO class representing Stock information to be written in CSV file
 * 
 * @author nbarge TODO candidate for builder pattern
 */
public class Stock {
	private String stockSymbol;
	private String currentPrice;
	private String yearTargetPrice;
	private String yearHigh;
	private String yearLow;

	public Stock(String symbol, String currPrice, String yTargetPrice,
			String yHigh, String yLow) {
		this.stockSymbol = symbol;
		this.currentPrice = currPrice;
		this.yearTargetPrice = yTargetPrice;
		this.yearHigh = yHigh;
		this.yearLow = yLow;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getYearTargetPrice() {
		return yearTargetPrice;
	}

	public void setYearTargetPrice(String yearTargetPrice) {
		this.yearTargetPrice = yearTargetPrice;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	@Override
	public String toString() {
		return stockSymbol + "," + currentPrice + "," + yearTargetPrice + ","
				+ yearHigh + "," + yearLow;
	}
}
