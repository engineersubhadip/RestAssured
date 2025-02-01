package Session4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
public class RequestPayloadViaHashMap {
	
	private Object id;
	
	@Test(priority=1)
	public void testCase_CreateStudent() {
		try {
			HashMap<Object, Object> data = new HashMap<>();

			data.put("name","Subhadip Das");
			data.put("age",30);
			data.put("grade","N/A");
			data.put("subjects",Arrays.asList("Computer Science", "Psycology"));
			
			id = given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:3000/students")
				.jsonPath().getString("id");
			
			System.out.println("Test Case Execution Passed.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case Execution Failed for testCase_CreateStudent. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=2, dependsOnMethods= {"testCase_CreateStudent"})
	public void testCase_VerifyNewStudent() {
		try {
			when()
				.get("http://localhost:3000/students/"+(String)id)
			.then()
				.statusCode(200)
				.body("name", equalTo("Subhadip Das"))
				.body("age", equalTo(30))
				.body("grade",equalTo("N/A"))
				.body("subjects[0]", equalTo("Computer Science"))
				.body("subjects[1]", equalTo("Psycology"))
				.header("Content-Type","application/json")
				.log().all();
			
			System.out.println("Test Case execution passed for testCase_VerifyNewStudent.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case execution failed for testCase_VerifyNewStudent. "+e.getMessage());
			Assert.fail();
		}
	}
	
	

	@Test(priority=3, dependsOnMethods= {"testCase_CreateStudent"})
	public void testCase_DeleteStudent() {
		try {
			when()
				.delete("http://localhost:3000/students/"+(String)id)
			.then()
				.log().all();
			System.out.println("Test Case Exection Passed. testCase_DeleteStudent");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Failed to execute test case testCase_DeleteStudent. "+e.getMessage());
			Assert.fail();
		}
	}
}
