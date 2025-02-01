package Session4;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.HashMap;
public class RequestPayloadViaHashMap {
	
	@Test
	public void testCase_CreateStudent() {
		try {
			HashMap<Object, Object> data = new HashMap<>();

			data.put("name","Subhadip Das");
			data.put("age",30);
			data.put("grade","N/A");
			data.put("subjects",Arrays.asList("Computer Science", "Psycology"));
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Subhadip Das"))
				.body("age", equalTo(30))
				.body("grade",equalTo("N/A"))
				.body("subjects[0]", equalTo("Computer Science"))
				.body("subjects[1]", equalTo("Psycology"))
				.log().all();
			
			System.out.println("Test Case Execution Passed.");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test Case Execution Failed for testCase_CreateStudent. "+e.getMessage());
			Assert.fail();
		}
	}
}
