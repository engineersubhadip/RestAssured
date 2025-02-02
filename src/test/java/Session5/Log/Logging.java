package Session5.Log;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Logging {
	
//	@Test(priority=1) // log only response body
	public void testCase_PrintResponseBody() {
		try {
			when()
				.get("https://www.google.com/")
			.then()
				.log().body();
			
		} catch (Exception e ) {
			System.out.println("Test case testCase_PrintResponseBody failed.");
			Assert.fail();
		}
	}
	
//	@Test(priority=2)
	public void testCase_PrintCookies() {
		try {
			when()
				.get("https://www.google.com/")
			.then()
				.log().cookies();
			
		} catch (Exception e ) {
			System.out.println("Test case testCase_PrintResponseBody failed.");
			Assert.fail();
		}
	}
	
	@Test(priority=3)
	public void testCase_PrintHeaders() {
		try {
			when()
				.get("https://www.google.com/")
			.then()
				.log().headers();
			
		} catch (Exception e ) {
			System.out.println("Test case testCase_PrintResponseBody failed.");
			Assert.fail();
		}
	}
}
