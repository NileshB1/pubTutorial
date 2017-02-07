package com.pub.tutorial.main;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONException;

import com.pub.tutorial.constants.URLConstant;
import com.pub.tutorial.exception.BadResponseException;
import com.pub.tutorial.io.CSVFileWriter;
import com.pub.tutorial.io.IFileReader;
import com.pub.tutorial.io.IFileWriter;
import com.pub.tutorial.io.TextFileReader;
import com.pub.tutorial.pojo.Stock;
import com.pub.tutorial.rest.IResponse;
import com.pub.tutorial.rest.IRestRequest;
import com.pub.tutorial.restimpl.RestRequestImpl;
import com.pub.tutorial.restimpl.RestResponseImpl;

/**
 * Main class to test whole application working
 * 
 * @author nbarge
 *
 */
public class Main {

	private static final String inputFileName = "Stocks.txt";
	private static final String outputFileName = "StocksOutput.csv";

	public static void main(String[] args) {
		double startTime = System.currentTimeMillis();
		double elapsedTime = 0;
		StringBuilder inputUrl = new StringBuilder("");
		IFileReader fileReader = new TextFileReader();
		IResponse<Stock> stockResponse = new RestResponseImpl<Stock>();
		IFileWriter<Stock> stockFileWriter = new CSVFileWriter<Stock>();
		// 1. read from file Stocks.txt
		List<String> inputContentList = fileReader.readFile(inputFileName);

		stockFileWriter.writeHeader(outputFileName);
		if (!inputContentList.isEmpty()) {
			try {
				for (String inputContent : inputContentList) {
					// 2. Construct url to fetch stocks
					inputUrl = new StringBuilder("");
					inputUrl.append(URLConstant.SERVER);
					inputUrl.append(URLConstant.QUERY1);
					inputUrl.append(inputContent);
					inputUrl.append(URLConstant.QUERY2);

					// 3. Create a client request
					IRestRequest request = new RestRequestImpl();
					ClientResponse<String> response = request
							.getRequest(inputUrl.toString());

					// 4. Fetch JSON from response

					String serverJSON = request.getResponse(response);
					// 5. Get list of stocks from response
					List<Stock> stockList = stockResponse
							.getCollectionFromJson(serverJSON);
					// 6. Write stocks to CSV
					stockFileWriter.writeToFile(outputFileName, stockList);
				} // for
				double stopTime = System.currentTimeMillis();
				elapsedTime = (stopTime - startTime)/1000;
				System.out.println("Written to file: " + outputFileName
						+ " successfully, in "+ elapsedTime + " seconds.");
			} catch (BadResponseException | IOException | JSONException exp) {
				System.out
						.println("Exception occured while fetching stocks from server: "
								+ exp.getMessage());
			}
		} else {
			System.out
					.println("Please check content of file: " + inputFileName);
		}
	}
}
