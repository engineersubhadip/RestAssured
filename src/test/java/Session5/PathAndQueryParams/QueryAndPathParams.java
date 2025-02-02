package Session5.PathAndQueryParams;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class QueryAndPathParams {
	//https://reqres.in/api/users?page=2&id=5
	//We specify the query and path params in the given()
	@Test
	public void testCase_QueryAndPathParams() {
		try {
			given()
				.pathParam("myPath", "users")
				.queryParam("page",2)
				.queryParam("id",5)
			.when()
				.get("https://reqres.in/api/{myPath}") //queryParams will go along with the request
			.then()
				.statusCode(200)
				.log().all();
			
		} catch (Exception e) {
			System.out.println("Test case execution failed");
			Assert.fail();
		}
	}
}
