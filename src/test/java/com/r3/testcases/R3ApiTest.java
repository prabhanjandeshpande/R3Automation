package com.r3.testcases;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.r3.apiUtil.ApiBaseUtil;
import com.r3.base.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * @author Prabhanjan Deshpande
 * 
 * 
 * */


public class R3ApiTest extends TestBase {

	ApiBaseUtil apiBaseUtil = new ApiBaseUtil();
	JsonParser parser = new JsonParser();
	String API_URI = "https://open.er-api.com/v6/latest/USD";
	String ExpecetdBaseCode= "USD";
	int ExpecetdTotalCurrecnies = 162;


	@Test
	public void apiHappyFlow() throws InterruptedException, IOException, UnirestException, ParseException {

		// making GET call and fetching response
		com.mashape.unirest.http.HttpResponse<String> httpresponse = apiBaseUtil.makeGetServiceCall(API_URI);
		String responseStr = httpresponse.getBody().toString();

		// parse the response using google gson parser.
		JsonElement je = parser.parse(responseStr);
		String baseCode = je.getAsJsonObject().get("base_code").getAsString();
		Double currencyAED = je.getAsJsonObject().get("rates").getAsJsonObject().get("AED").getAsDouble(); 
		int totalCurrencies = je.getAsJsonObject().get("rates").getAsJsonObject().size();
		
		
		
		// different assertions as per the Acceptance Criteria.

		Assert.assertEquals(httpresponse.getStatus(), 200, "status code is not 200 !");
		Assert.assertTrue(httpresponse.getStatusText().equals("OK"), "status is not OK ! ");

		Assert.assertFalse(responseStr.isEmpty(), "API response is empty ! ");
		Assert.assertTrue(responseStr.toLowerCase().contains("success"), "API call is not successful !");
		Assert.assertFalse(responseStr.toLowerCase().contains("error"), "API response has errors ! ");
		Assert.assertFalse(responseStr.toLowerCase().contains("exception"), "API response has exception ! ");

		Assert.assertTrue(baseCode.equals(ExpecetdBaseCode), "BaseCode value is not as expcted ! ");
		Assert.assertEquals(totalCurrencies, ExpecetdTotalCurrecnies, "total nunmber of currencies are not as expected!");
		Assert.assertTrue(3.6 <= currencyAED && currencyAED <= 3.7 , "currency AED is not in required range ! ");

	}
}
