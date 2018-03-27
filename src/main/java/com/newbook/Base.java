package com.newbook;

import io.qameta.allure.Step;

public class Base {

	private final String BASE_URL = "https://stage-api.newbookmodels.com/api/v1";

	// constructor START!!!!!!!!!!!!!!!!!!!!!!!
	public Base() {
	}
	// constructor END!!!!!!!!!!!!!!!!!!!!!!!

	////////////////////////////// private START//////////////////////////////
	// private void setOn() {}
	////////////////////////////// private END//////////////////////////////

	////////////////////////////// public START//////////////////////////////
	@Step("getBaseURL")
	public String getBaseURL() {
		return BASE_URL;
	}
	////////////////////////////// public EDN//////////////////////////////
}