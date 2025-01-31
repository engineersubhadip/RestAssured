package Session3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class TestCase2_CreateUser {
	int id;

	@Test(priority = 1)
	public void testCase2_CreateUser() {

		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Subhadip");
		data.put("job", "Student");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
	}

	@Test(priority = 2, dependsOnMethods = { "testCase2_CreateUser" })
	public void testCase3_UpdateUser() {
		System.out.println("id " + id);
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Subhadip");
		data.put("job", "Working Professional");

		given().contentType("application/json").body(data)
		.when().put("https://reqres.in/api/users/" + id)
		.then().statusCode(200).log().all();
	}

	
	@Test(priority=3)
	public void testCase4_DeleteUser() {
		when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}
}
