package com.newbook.model;

import org.testng.annotations.Test;

import com.newbook.auth.POST_SignIn;

import io.qameta.allure.Step;

public class ModelUpdateSelfDataTest extends BeforeAndAfter {

	@Step("Test Update self data {2} '@DataProvider'")
	@Test(dataProvider = "dataProviderForModelUpdateSelfData")
	public void testModelUpdateSelfData(String email, String pass, String mess, String key, String value, String exp)
			throws Exception {
		System.out.println("testModelUpdateSelfData");

		POST_SignIn signIn = new POST_SignIn(email, pass, mess);
		String status = signIn.getStatus();

		String act = null;
		if (status.equals("200")) {
			String valueOld = signIn.getUserDate().getString(key);
			act = new PATCH_ModelUpdateSelfUserData(signIn.getToken(), key, value, mess).getStatus();
			if (act.equals("200"))
				// return old value for next test
				new PATCH_ModelUpdateSelfUserData(signIn.getToken(), key, valueOld, "return old value");
		} else
			act = status;

		// save result
		resultTmp = new String[] { this.getClass().getName(), act, exp, email, pass, mess, key, value };

		// Assert.assertEquals(actual, expected, message);
		checkAssertEquals(act, exp, "Error massege");
	}
}