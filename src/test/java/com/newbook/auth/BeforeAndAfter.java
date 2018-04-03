package com.newbook.auth;

import org.testng.annotations.DataProvider;

public class BeforeAndAfter extends com.newbook.BeforeAndAfter {

	@DataProvider
	public Object[][] dataProviderForClientSignUp() {
		return readCSVFile(POST_ClientSignUp.CLIEN_SIGNUP_URL + "testClientSignUp.csv");
	}

	@DataProvider
	public Object[][] dataProviderForModelSignUp() {
		return readCSVFile(POST_ModelSignUp.MODEL_SIGNUP_URL + "testModelSignUp.csv");
	}

	@DataProvider
	public Object[][] dataProviderForClientSignIn() {
		return readCSVFile(POST_SignIn.SIGNIN_URL + "testClientSignIn.csv");
	}

	@DataProvider
	public Object[][] dataProviderForModelSignIn() {
		return readCSVFile(POST_SignIn.SIGNIN_URL + "testModelSignIn.csv");
	}
}