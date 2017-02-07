package com.pub.tutorial.rest;

import java.util.List;

import org.json.JSONException;

public interface IResponse<T> {
	List<T> getCollectionFromJson(String jsonStr) throws JSONException;
}
