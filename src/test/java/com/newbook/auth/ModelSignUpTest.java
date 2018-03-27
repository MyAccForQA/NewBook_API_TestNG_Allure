package com.newbook.auth;

import org.testng.annotations.Test;

import io.qameta.allure.Step;

public class ModelSignUpTest extends BeforeAndAfter {
	
	@Step("Test signUp {2} '@DataProvider'")
	@Test(dataProvider = "dataProviderForModelSignUp")
	public void testModelSignUp(String email, String pass, String mess, String exp) throws Exception {
		System.out.println("testModelSignUp");
		
		String act = new POST_ModelSignUp(email, pass, mess).getStatus();
		
		// save result
		resultTmp = new String[] { this.getClass().getName(), act, exp, email, pass, mess };
		
		// Assert.assertEquals(actual, expected, message);
		checkAssertEquals(act, exp, "Error massege");
	}
}