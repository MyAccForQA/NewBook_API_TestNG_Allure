package com.newbook.auth;

import org.testng.annotations.Test;

import io.qameta.allure.Step;

public class SignInTest extends BeforeAndAfter {

	@Step("Test SignIn {2} '@DataProvider'")
	@Test(dataProvider = "dataProviderForClientSignIn")
	public void testClientSignIn(String email, String pass, String mess, String exp) throws Exception {
		System.out.println("testClientSignIn");

		String act = new POST_SignIn(email, pass, mess).getStatus();
		// Assert.assertEquals(actual, expected, message);
		checkAssertEquals(act, exp, "Error massege");
	}

	@Step("Test SignIn as model with '@DataProvider'")
	@Test(dataProvider = "dataProviderForModelSignIn")
	public void testModelSignIn(String email, String pass, String mess, String exp) throws Exception {

		System.out.println("testModelSignIn");

		String act = new POST_SignIn(email, pass, mess).getStatus();

		// save result
		resultTmp = new String[] { this.getClass().getName(), act, exp, email, pass, mess };

		// Assert.assertEquals(actual, expected, message);
		checkAssertEquals(act, exp, "Error massege");
	}
}