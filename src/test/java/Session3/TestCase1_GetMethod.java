package Session3;
/*
 * We will print the following parameters :-
 * 1. Status Code.
 * 2. Response Payload.
 * 3. Response Time.
 * 4. Respose Header.
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestCase1_GetMethod {
	
	@Test
	public void testCase1_GetMethod() {
		try {
			Response res = RestAssured.get("https://reqres.in/api/users?page=2");
//			1. Response Code:-
			System.out.println("Response Code: "+res.getStatusCode());
//			2. Response Body:-
			System.out.println("Response Body: "+res.asString());
//			3. Response Time:-
			System.out.println("Response Time: "+res.getTime());
//			4. Response Header:-
			System.out.println("Response Header: "+res.getHeader("Content-Type"));
			
//			1. Validate Status Code (Expected 200):-
			Assert.assertEquals(res.getStatusCode(), 200);
			
		} catch (Exception e) {
			System.out.println("testCase1_GetMethod execution failed. "+e.getMessage());
			Assert.fail();
		}
	}
}
