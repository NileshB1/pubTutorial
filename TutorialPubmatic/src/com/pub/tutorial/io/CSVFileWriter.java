package com.pub.tutorial.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.pub.tutorial.constants.CommonConstants;
import com.pub.tutorial.pojo.Stock;

/**
 * @author nbarge CSVFileWriter is an implementation of interface IFileWriter
 *         that writers list of object of type T in file in CSV format.
 * @param <T>
 */
public class CSVFileWriter<T> implements IFileWriter<T> {

	private String FILE_HEADER = "Stock Symbol,Current Price,Year Target Price,Year High,Year Low";

	/*
	 * public static void main(String[] args) { String str =
	 * "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from"
	 * +"%20yahoo.finance.quotes%20where%20symbol%20in%20(%22%s"
	 * +"%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org"
	 * +"%2Falltableswithkeys";
	 * 
	 * System.out.println("formatted string is:");
	 * System.out.println(String.format(
	 * "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22%s%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
	 * , "GOOG")); Stock stock1 = new Stock("1", "20", "200", "150", "15");
	 * Stock stock2 = new Stock("2", "35", "220", "350", "25"); Stock stock3 =
	 * new Stock("3", "48", "208", "159", "16"); Stock stock4 = new Stock("4",
	 * "12", "308", "251", "56"); Stock stock5 = new Stock("5", "86", "458",
	 * "158", "74");
	 * 
	 * //Create a new list of student objects List<Stock> stocks = new
	 * ArrayList<Stock>(); stocks.add(stock1); stocks.add(stock2);
	 * stocks.add(stock3); stocks.add(stock4); stocks.add(stock5);
	 * 
	 * 
	 * IFileWriter<Stock> fileWriter = new CSVFileWriter<Stock>();
	 * fileWriter.writeToFile("student.csv", stocks);
	 * System.out.println("Written to file sucessfully..."); }
	 */
	@Override
	public void writeToFile(String fileName, List<T> listToWrite) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName, true);

			for (T t : listToWrite) {
				Stock stock = (Stock) t;
				if (stock.getCurrentPrice() == CommonConstants.NULL_VALUE_FROM_SERVER) {
					fileWriter.append(String
							.valueOf(CommonConstants.WRONG_STOCK_VAL));
				} else {
					fileWriter.append(t.toString());
				}
				fileWriter.append(CommonConstants.NEW_LINE);
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ioExp) {
			System.out.println("IO exception occured while writing to file: "
					+ fileName + ioExp.getMessage());
		} catch (Exception exp) {
			System.out.println("Exception occured while writing file: "
					+ fileName + exp.getMessage());
		}
	}

	@Override
	public void writeHeader(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// write CSV file header
			fileWriter.write(FILE_HEADER);
			fileWriter.append(CommonConstants.NEW_LINE);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ioExp) {
			System.out.println("IO exception occured while writing to file: "
					+ fileName + ioExp.getMessage());
		} catch (Exception exp) {
			System.out.println("Exception occured while writing file: "
					+ fileName + exp.getMessage());
		}

	}
}
