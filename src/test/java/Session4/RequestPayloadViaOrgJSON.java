package Session4;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.json.JSONObject;
public class RequestPayloadViaOrgJSON {
	
	private String id;
	
	@Test(priority=1)
	public void testCase_CreateNewStudent() {
		try {
			JSONObject json = new JSONObject();
			json.put("name", "Jhilik");
			json.put("age", 28);
			json.put("grade", "N/A");
			json.put("subjects", Arrays.asList("Applied Mathematics", "Calculus"));
			
			id = given()
				.contentType("application/json")
				.body(json.toString())
			.when()
				.post("http://localhost:3000/students")
				.jsonPath().getString("id");
			
			System.out.println("Test Case Execution Passed for testCase_CreateNewStudent. ");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case Execution failed for testCase_CreateNewStudent. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=2, dependsOnMethods= {"testCase_CreateNewStudent"})
	public void testCase_VerifyCreatedStudent() {
		try {
			when()
				.get("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200)
				.body("name",equalTo("Jhilik"))
				.body("age",equalTo(28))
				.body("grade",equalTo("N/A"))
				.body("subjects[0]", equalTo("Applied Mathematics"))
				.body("subjects[1]", equalTo("Calculus"))
				.log().all();
			
			System.out.println("Test Case execution passed for testCase_VerifyCreatedStudent.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case execution failed for testCase_VerifyCreatedStudent. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=3, dependsOnMethods= {"testCase_CreateNewStudent"})
	public void testCase_DeleteCreatedStudent() {
		try {
			when()
				.delete("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200);
			System.out.println("Test Case execution passed for testCase_VerifyCreatedStudent.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case execution failed for testCase_DeleteCreatedStudent. "+e.getMessage());
			Assert.fail();
		}
	}
}
