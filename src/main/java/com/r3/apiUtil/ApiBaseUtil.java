package com.r3.apiUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApiBaseUtil {

	public com.mashape.unirest.http.HttpResponse<String> makePostServiceCall(String baseUrl, String payload)
			throws UnirestException {
		return Unirest.post(baseUrl).header("content-type", "application/json").body(payload).asString();
	}

	public com.mashape.unirest.http.HttpResponse<String> makeGetServiceCall(String baseUrl) throws UnirestException {
		return Unirest.get(baseUrl).header("content-type", "application/json").asString();
	}

}
