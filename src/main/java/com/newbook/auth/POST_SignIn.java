package com.newbook.auth;

import com.newbook.Base;

import org.json.JSONObject;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_SignIn extends Base {

	public final static String SIGNIN_URL = "/auth/signin/";
	// for save
	private RequestSpecification request = null;
	private Response response = null;

	// constructor START!!!!!!!!!!!!!!!!!!!!!!!
	public POST_SignIn(String email, String pass, String mess) {
		startRun(email, pass, mess);
	}
	// constructor END!!!!!!!!!!!!!!!!!!!!!!!

	////////////////////////////// private START//////////////////////////////
	// RUN all methods
	@Step("SignIn {2} - [POST " + SIGNIN_URL + "]...")
	private void startRun(String email, String pass, String mess) {

		JSONObject jsonTmp = createJsonForResp(email, pass);

		createResponse(jsonTmp);
	}

	// Create JSON
	@Step("Create json for signin with email=[{0}] and password=[{1}]")
	private JSONObject createJsonForResp(String email, String pass) {
		String params = "{\"email\":\"" + email + "\",\"password\":\"" + pass + "\"}";
		JSONObject jsonTmp = new JSONObject(params);
		return jsonTmp;
	}

	@Step("Set up email=[{0}]")
	private void setEmail(String email) {
		// this.email = email;
	}

	@Step("Set up password=[{0}]")
	private void setPass(String pass) {
		// this.pass = pass;
	}

	// Create response
	@Step("Create response for signin  with json=[{0}]")
	private void createResponse(JSONObject json) {
		creatRequest();

		addHeaderInRequest("X-Newbook-Client", "test");
		addHeaderInRequest("X-Newbook-Version", "v1");
		addHeaderInRequest("Accept", "application/json");
		addHeaderInRequest("Content-Type", "application/json");
		addBodyInRequest(json);

		sendPostRequest(getBaseURL() + getSignInURL());
	}

	@Step("Create new request")
	private void creatRequest() {
		request = RestAssured.given();
	}

	@Step("Add in header key=[{0}] and value=[{1}]")
	private void addHeaderInRequest(String key, String value) {
		request.header(key, value);
	}

	@Step("Add in body - [{0}] in request")
	private void addBodyInRequest(JSONObject json) {
		request.body(json.toString()).when().contentType(ContentType.JSON);
	}

	@Step("Send post request wirh url=[{0}]")
	private void sendPostRequest(String url) {
		response = request.post(url);
	}

	@Step("getSignInURL")
	private String getSignInURL() {
		return SIGNIN_URL;
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
				return response.then().contentType(ContentType.JSON).extract().path("detail[0]");
			} catch (Exception e) {
				return "" + response.getStatusCode();
			}
		return status;
	}

	@Step("Get token from response")
	public String getToken() {
		try {
			return response.then().contentType(ContentType.JSON).extract().path("token_data.token").toString();
		} catch (Exception e) {
			return null;
		}
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