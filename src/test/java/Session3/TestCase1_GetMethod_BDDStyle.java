package Session3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class TestCase1_GetMethod_BDDStyle {
	
	@Test
	public void testCase1_GetMethod_BDDStyle() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200) // validate the statusCode
			.body("page",equalTo(2)) // validate the page value
			.log().all(); // to print the entire response
	}
}
