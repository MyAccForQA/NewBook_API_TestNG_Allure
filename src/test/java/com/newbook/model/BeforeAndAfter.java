package com.newbook.model;

import org.testng.annotations.DataProvider;

public class BeforeAndAfter extends newbook.BeforeAndAfter {

	@DataProvider
	public Object[][] dataProviderForModeltGetSelfData() {
		return readCSVFile(GET_ModelGetSelfData.SELFUSERDATA_URL + "testModelGetSelfData.csv");
	}

	@DataProvider
	public Object[][] dataProviderForModelUpdateSelfData() {
		return readCSVFile(GET_ModelGetSelfData.SELFUSERDATA_URL + "testModelUpdateSelfData.csv");
	}
}