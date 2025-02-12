package Lecture2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class EndToEndTest {

	public static void main(String[] args) {
		
//		1. Capture the Place ID :-
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given()
			.queryParam("key", "qaclick123")
			.header("content-type","application/json")
			.body(payload.addPlace())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.statusCode(200)
			.header("content-type",	"application/json;charset=UTF-8")
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response); // JSONPath takes a String as input and converts that to JSON format and also parse it
		
		String placeID = js.getString("place_id");
		
//		2. Update the Place ID with different location
		
		given()
			.queryParam("key", "qaclick123")
			.header("content-type","application/json")
			.body("{\r\n"
					+ "    \"place_id\":\""+placeID+"\",\r\n"
					+ "    \"address\":\"70 Summer walk, USA\",\r\n"
					+ "    \"key\":\"qaclick123\"\r\n"
					+ "}")
		.when()
			.put("/maps/api/place/update/json")
		.then()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		.log().all();
		
//		3. Check whether the address has been updated for the particular Place ID :-
		
		given()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", placeID)
		.when()
			.get("/maps/api/place/get/json")
		.then()
			.statusCode(200)
			.log().all();
	}

}
