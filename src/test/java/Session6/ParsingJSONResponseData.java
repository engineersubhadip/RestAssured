package Session6;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	@Test(priority=1) // Approach-1 {Applicable to Small data set, and you want to validate some fields in JSON}
	public void testCase_TestJSONResponse1() {
		
		when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type","application/json")
			.body("book[4].title", equalTo("Harry Potter and the Sorcerer's Stone"));
	}
	
	@Test(priority=2) // Approach-2 {More widely used in the real time environment, suitable for more complex validations, and more number of validations}
	public void testCase_TestJSONResponse2() {
		
		Response res = when()
			.get("http://localhost:3000/store");
		
		
		Assert.assertEquals(200,res.getStatusCode());
		Assert.assertEquals("application/json", res.getHeader("Content-Type"));
		Assert.assertEquals("Harry Potter and the Sorcerer's Stone", res.jsonPath().get("book[4].title"));
	}
	
	@Test(priority=3) // Continuation of Approach 2
	public void testCase_TestJSONResponse3() {
		
		Response res = when()
				.get("http://localhost:3000/store");
		
		// From each JSON Object, I want to capture the "title" of the book.
		// To traverse or parse the entire Response into JSON, we have "JSONObject" class.
		
		JSONObject json = new JSONObject(res.asString()); // Converting the "res" from Response to "JSONObject" type.
		
		//Print the size of the JSON Array :-
		System.out.println(json.getJSONArray("book").length());
		
		for (int i=0; i<json.getJSONArray("book").length(); i++) {
			String currEle = json.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(currEle);
		}
	}
}
