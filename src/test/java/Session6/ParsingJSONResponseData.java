package Session6;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {
	
	@Test(priority=1) // Approach-1 {Applicable to Small data set, and you want to validate some fields in JSON}
	public void testCase_TestJSONResponse() {
		
		when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type","application/json")
			.body("book[4].title", equalTo("Harry Potter and the Sorcerer's Stone"));
	}
}
