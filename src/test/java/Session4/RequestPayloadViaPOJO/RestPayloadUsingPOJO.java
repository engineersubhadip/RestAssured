package Session4.RequestPayloadViaPOJO;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestPayloadUsingPOJO {
	
	private String id;
	POJO_PostRequest pojo = null;
	
	@Test(priority=1)
	public void testCase_CreateNewUser() {
		try {
			pojo = new POJO_PostRequest();
			pojo.setName("Jhilik");
			pojo.setAge(28);
			pojo.setGrade("N/A");
			pojo.setSubjects(Arrays.asList("Applied Mathematics", "Calculus"));
			
			id = given()
				.contentType("application/json")
				.body(pojo)
			.when()
				.post("http://localhost:3000/students")
				.jsonPath().getString("id");
			System.out.println("Test Case execution passed for testCase_CreateNewUser.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case execution failed for testCase_CreateNewUser. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=2)
	public void testCase_VerifyCreatedUser() {
		try {
			when()
				.get("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200)
				.body("name",equalTo(pojo.getName()))
				.body("age", equalTo(pojo.getAge()))
				.body("grade", equalTo(pojo.getGrade()))
				.body("subjects[0]", equalTo(pojo.getSubjects().get(0)))
				.body("subjects[1]", equalTo(pojo.getSubjects().get(1)))
				.header("Content-Type","application/json")
				.log().all();
			
			System.out.println("Test Case Execution Passed for testCase_VerifyCreatedUser.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case Execution failed for testCase_VerifyCreatedUser. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=3)
	public void testCase_DeleteCreatedUser() {
		if (pojo != null) {
			pojo = null;
		}
		try {
			when()
				.delete("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200);
			
			System.out.println("Test Case Execution Passed for testCase_DeleteCreatedUser.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test case execution failed for testCase_DeleteCreatedUser");
			Assert.fail();
		}
	}
	
}
