package com.pub.tutorial.restimpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pub.tutorial.constants.CommonConstants;
import com.pub.tutorial.pojo.Stock;
import com.pub.tutorial.rest.IResponse;

public class RestResponseImpl<T> implements IResponse<T> {

	@Override
	public List<T> getCollectionFromJson(String jsonStr) throws JSONException {
		List<T> list = new ArrayList<T>();
		String stockSymbol = null;
		String currentPrice = null;
		String yearTargetPrice = null;
		String yearHigh = null;
		String yearLow = null;

		if (!jsonStr.isEmpty()) {
			JSONObject parentJson = new JSONObject(jsonStr);
			JSONObject query = parentJson.getJSONObject(CommonConstants.QUERY);
			JSONObject results = query.getJSONObject(CommonConstants.RESULTS);
			JSONArray quote = results.getJSONArray(CommonConstants.QUOTE);

			for (int index = 0; index < quote.length(); index++) {
				JSONObject jsonQuote = (JSONObject) quote.get(index);
				stockSymbol = jsonQuote.getString(CommonConstants.SYMBOL);
				currentPrice = jsonQuote
						.getString(CommonConstants.CURRENT_PRICE);
				yearTargetPrice = jsonQuote
						.getString(CommonConstants.TARGET_PRICE);
				yearHigh = jsonQuote.getString(CommonConstants.YEAR_HIGH);
				yearLow = jsonQuote.getString(CommonConstants.YEAR_LOW);

				Stock stock = new Stock(stockSymbol, currentPrice,
						yearTargetPrice, yearHigh, yearLow);
				list.add((T) stock);
			}

		} else {
			System.out.println("Received empty output from server.");
		}
		return list;
	}
}
