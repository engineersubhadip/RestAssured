package Lecture1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import file.payload;
import src.main.resources.*;

import io.restassured.RestAssured;

public class AddPlaceAPI {

	public static void main(String[] args) {
//		Base URI :-
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payload.addPlace())
			.log().all()
		.when() // resource and http methods will always go under when method
			.post("/maps/api/place/add/json")
		.then()
			.statusCode(200)
			.body("scope",equalTo("APP"))
			.header("Server", "Apache/2.4.52 (Ubuntu)")
			.log().all();
		
//		Add Place -> Update Place with new Address -> get Place to check if new address in shown or not
		
		
	}

}
