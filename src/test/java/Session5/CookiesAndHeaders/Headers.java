package Session5.CookiesAndHeaders;

import static io.restassured.RestAssured.when;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.Header;
import io.restassured.response.Response;
public class Headers {
	
	@Test(priority=1)
	public void testCase_ValidateHeaders() {
		try {
			when()
				.get("https://www.google.com/")
			.then()
				.statusCode(200)
				.headers("Content-Type","text/html; charset=ISO-8859-1")
				.headers("Content-Encoding","gzip")
				.headers("Server","gws");
			
		} catch (Exception e) {
			System.out.println("Test case execution failed.");
			Assert.fail();
		}
	}
	
	@Test(priority=2)
	public void testCase_CaptureHeaderInfo() {
		try {
			Response res = when()
				.get("https://www.google.com/");
			
			//Capture single Header info
			System.out.println("Content-Type: "+res.getHeader("Content-Type"));
			//Capture all Headers info
			io.restassured.http.Headers myHeaders = res.getHeaders();
			
			for (Header hd : myHeaders) {
				System.out.println(hd.getName()+" -> "+hd.getValue());
			}
		} catch (Exception e) {
			System.out.println("Test case execution failed.");
			Assert.fail();
		}
	}
}
