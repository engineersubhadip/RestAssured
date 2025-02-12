package Lecture3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.testng.Assert;

import file.payload;
import io.restassured.path.json.JsonPath;

public class ParseJSONResponse {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payload.getCoursePrice());
		
//		Print number of courses returned by the API.
		int courseCount = js.getInt("courses.size()");
		System.out.println(courseCount);
		
//		Print purchase amount.
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
//		Print Title of the first course.
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		System.out.println("--------------------------------");
		
//		Print All course titles and their respective Prices
		for (int i=0; i<courseCount; i++) {
			String currTitle = js.getString("courses["+i+"].title");
			int currPrice = js.getInt("courses["+i+"].price");
			
			System.out.println("Title: "+currTitle+" Price: "+currPrice);
		}
		
//		Print no of copies sold by RPA Course
		for (int i=0; i<courseCount; i++) {
			String currTitle = js.getString("courses["+i+"].title");
			if (currTitle.equalsIgnoreCase("rpa")) {
				int soldCopies = js.getInt("courses["+i+"].copies");
				System.out.println("Copies Sold: "+soldCopies);
			}
		}
		
//		 Verify if Sum of all Course prices matches with Purchase Amount
		int totalPrice = js.getInt("dashboard.purchaseAmount");
		int runTotal = 0;
		int arrSize = js.getInt("courses.size()");
		
		for (int i=0; i<arrSize; i++) {
			int runPrice = js.getInt("courses["+i+"].price");
			int copyCount = js.getInt("courses["+i+"].copies");
			int run = runPrice * copyCount;
			runTotal += run;
		}
		
		Assert.assertEquals(totalPrice, runTotal);
	}
}
