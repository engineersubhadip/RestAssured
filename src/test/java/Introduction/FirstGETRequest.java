package Introduction;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstGETRequest {
	
	@Test
	public void testCase_FirstGetRequest() {
		try {
			Response res = RestAssured.get("https://reqres.in/api/users/2");
			System.out.println("Response Body: "+res.asString());
			System.out.println("Response Status Code: "+res.getStatusCode());
		} catch (Exception e) {
			System.out.println("Test Case testCase_FirstGetRequest execution failed. "+e.getMessage());
			Assert.fail();
		}
	}
}
