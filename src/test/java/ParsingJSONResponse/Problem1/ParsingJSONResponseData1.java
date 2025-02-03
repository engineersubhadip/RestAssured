package ParsingJSONResponse.Problem1;

import static io.restassured.RestAssured.when;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class ParsingJSONResponseData1 {
	
	@Test
	public void testCase_ValidateJSONResponse() {
		
		Response res = when()
			.get("http://localhost:3000/store");
		
		JSONObject json = new JSONObject(res.asString());
		
		String storeName = json.getString("name");
		JSONObject address =  json.getJSONObject("address");
		String street = address.getString("street");
		String city = address.getString("city");
		String zipCode = address.getString("zipcode");
		
		JSONArray arr = json.getJSONArray("books");
		int total = 0;
		
		for (int i=0; i<arr.length(); i++) {
			int currPrice = arr.getJSONObject(i).getInt("price");
			total += currPrice;
		}
		System.out.println("Store Name: "+storeName);
		System.out.println("Street Name: "+street);
		System.out.println("City Name: "+city);
		System.out.println("ZipCode Name: "+zipCode);
		System.out.println("Total Price: "+total);
	}
}
