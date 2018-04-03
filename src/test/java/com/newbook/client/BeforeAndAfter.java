package com.newbook.client;

import org.testng.annotations.DataProvider;

public class BeforeAndAfter extends com.newbook.BeforeAndAfter {

	@DataProvider
	public Object[][] dataProviderForClientGetSelfData() {
		return readCSVFile(GET_ClientGetSelfData.SELFUSERDATA_URL + "testClientGetSelfData.csv");
	}
	
	@DataProvider
	public Object[][] dataProviderForClientUpdateSelfData() {
		return readCSVFile(GET_ClientGetSelfData.SELFUSERDATA_URL + "testClientUpdateSelfData.csv");
	}
}