package Lecture1;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class AddPlaceAPI {

	public static void main(String[] args) {
//		Base URI :-
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "  \"location\": {\r\n"
					+ "    \"lat\": -38.383494,\r\n"
					+ "    \"lng\": 33.427362\r\n"
					+ "  },\r\n"
					+ "  \"accuracy\": 50,\r\n"
					+ "  \"name\": \"Roadhouse Madness\",\r\n"
					+ "  \"phone_number\": \"(+5) 983 893 3937\",\r\n"
					+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
					+ "  \"types\": [\r\n"
					+ "    \"shoe park\",\r\n"
					+ "    \"shop\"\r\n"
					+ "  ],\r\n"
					+ "  \"website\": \"http://roadhousemadness.uk\",\r\n"
					+ "  \"language\": \"French-IN\"\r\n"
					+ "}")
			.log().all()
		.when() // resource and http methods will always go under when method
			.post("/maps/api/place/add/json")
		.then()
			.assertThat()
			.statusCode(200)
			.log().all();
	}

}
