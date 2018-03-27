package com.newbook.client;

import org.json.JSONObject;

import com.newbook.Base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_ClientGetSelfData extends Base {

	public final static String SELFUSERDATA_URL = "/client/self/";
	// for save
	private RequestSpecification request = null;
	private Response response = null;

	// constructor START!!!!!!!!!!!!!!!!!!!!!!!
	public GET_ClientGetSelfData(String token, String mess) {
		startRun(token, mess);
	}
	// constructor END!!!!!!!!!!!!!!!!!!!!!!!

	////////////////////////////// private START//////////////////////////////
	// RUN all methods
	@Step("Get self data {1} - [GET " + SELFUSERDATA_URL + "]...")
	private void startRun(String token, String mess) {
		setToken(token);

		createResponse(token);
	}

	@Step("Set up token=[{0}]")
	private void setToken(String token) {
		// this.token = token;
	}

	// Create response
	@Step("Create response  with token=[{0}]")
	private void createResponse(String token) {
		creatRequest();

		addHeaderInRequest("Authorization", token);
		addHeaderInRequest("X-Newbook-Client", "test");
		addHeaderInRequest("X-Newbook-Version", "v1");
		addHeaderInRequest("Accept", "application/json");
		addHeaderInRequest("Content-Type", "application/json");

		sendGetRequest(getBaseURL() + getSelfUserDataSignInURL());
	}

	@Step("Create new request")
	private void creatRequest() {
		request = RestAssured.given();
	}

	@Step("Add in header key=[{0}] and value=[{1}]")
	private void addHeaderInRequest(String key, String value) {
		request.header(key, value);
	}

	@Step("Send get request wirh url=[{0}]")
	private void sendGetRequest(String url) {
		response = request.get(url);
	}

	@Step("getSelfUserDataSignInURL")
	private String getSelfUserDataSignInURL() {
		return SELFUSERDATA_URL;
	}
	////////////////////////////// private END//////////////////////////////

	////////////////////////////// public START//////////////////////////////
	@Step("Return JSON as string")
	public String toString() {
		return new JSONObject(response.asString()).toString();
	}

	@Step("getResponse")
	public Response getResponse() {
		return response;
	}

	@Step("getStatus")
	public String getStatus() {
		String status = "" + response.getStatusCode();
		if (!status.equals("200"))
			try {
				return response.then().contentType(ContentType.JSON).extract().path("detail");
			} catch (Exception e) {
				return "" + response.getStatusCode();
			}
		return status;
	}

	@Step("Get user's data from response")
	public JSONObject getUserDate() {
		try {
			return new JSONObject(response.asString()).getJSONObject("user");
		} catch (Exception e) {
			return null;
		}
	}
	////////////////////////////// public EDN//////////////////////////////
}