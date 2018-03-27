package com.newbook.model;

import org.testng.annotations.Test;

import com.newbook.auth.POST_SignIn;

import io.qameta.allure.Step;

public class ModelGetSelfDataTest extends BeforeAndAfter {

	@Step("Test get self data {2} '@DataProvider'")
	@Test(dataProvider = "dataProviderForModeltGetSelfData")
	public void testModelGetSelfData(String email, String pass, String mess, String exp) throws Exception {
		System.out.println("testModelGetSelfData");

		POST_SignIn signIn = new POST_SignIn(email, pass, mess);
		String status = signIn.getStatus();
		String act = null;
		if (status.equals("200"))
			act = new GET_ModelGetSelfData(signIn.getToken(), mess).getStatus();
		else
			act = status;

		// save result
		resultTmp = new String[] { this.getClass().getName(), act, exp, email, pass, mess };

		// Assert.assertEquals(actual, expected, message);
		checkAssertEquals(act, exp, "Error massege");
	}
}