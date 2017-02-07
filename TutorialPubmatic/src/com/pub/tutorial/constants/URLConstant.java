package com.pub.tutorial.constants;

public class URLConstant {
	public static final String SERVER = "https://query.yahooapis.com/v1/public/yql";
	public static final String QUERY1 = "?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
	public static final String QUERY2 = "%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
}
