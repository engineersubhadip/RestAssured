package Session5.CookiesAndHeaders;

import static io.restassured.RestAssured.when;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
public class HeadersAndCookies {
	
//	@Test
	public void testCase_CookiesAndHeaders() {
		try {
			when()
				.get("https://www.google.com/")
			.then()
				.cookie("AEC","AVcja2cgV_32vijN5uusykAZ79ZBO3rmEwU1UUnQBkEbif1L7xDwGqTiXQ");
			
		} catch (Exception e) {
			System.out.println("Test case execution failed.");
			Assert.fail();
		}
	}
	
	@Test
	public void testCase_CaptureCookieValue() {
		try {
			Response res = when()
				.get("https://www.google.com/");
			
			//Capture single cookie information :-
			String cookie1 = res.getCookie("AEC");
			System.out.println(cookie1);
			//Capture all cookies information :-
			Map<String,String> data = res.getCookies();
			
			for (String currData : data.keySet()) {
				System.out.println(currData+" "+data.get(currData));
			}
		} catch (Exception e) {
			System.out.println("Test case execution failed.");
			Assert.fail();
		}
	}
}
