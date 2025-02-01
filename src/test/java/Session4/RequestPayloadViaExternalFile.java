package Session4;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestPayloadViaExternalFile {
	
	private String id;
	
	@Test(priority=1)
	public void testCase_CreateNewStudent() {
		try {
			File file = new File(System.getProperty("user.dir")+"\\Body.json");
			FileReader fr = new FileReader(file);
			JSONTokener jt = new JSONTokener(fr);
			JSONObject data = new JSONObject(jt);
			
			id = given()
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("http://localhost:3000/students")
					.jsonPath().getString("id");
				
				System.out.println("Test Case Execution Passed for testCase_CreateNewStudent. ");
				Assert.assertTrue(true);
				
		} catch (Exception e) {
			System.out.println("Test case execution failed for testCase_RequestPayloadViaExternalFile. "+e.getMessage());
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
