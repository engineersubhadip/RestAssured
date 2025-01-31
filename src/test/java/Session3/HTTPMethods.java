/*
 * 1. https://reqres.in/api/users?page=2 total page count will be 2
 * 2. 
 * {
    "name": "Subhadip",
    "job": "Software Test Engineer"
	}
	Create this resource in https://reqres.in/api/users
	3. Update the existing resource and change "job" to "Software Development Engineer in Test"
	4. Delete the particular user
 */

package Session3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPMethods {
	
	private int id;
	
	@Test(priority=1)
	public void testCase_ValidatePageCount() {
		when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("total_pages", equalTo(2));
	}
	
	@Test(priority=2)
	public void testCase_CreateResource() {
		
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Subhadip");
		data.put("job","Software Test Engineer");
		
		id = given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");		
	}
	
	@Test(priority=3)
	public void testCase_UpdateResource() {
		
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Subhadip");
		data.put("job", "Software Development Engineer in Test");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	public void testCase_DeleteResource() {
		when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204);
	}
}
