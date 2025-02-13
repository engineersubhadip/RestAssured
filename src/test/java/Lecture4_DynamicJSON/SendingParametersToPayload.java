package Lecture4_DynamicJSON;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/*
 * Whatever parameters I am passing into addBook(String isbn, String isle) -> This should automatically get captured in the parent JSON Request payload.
 */

public class SendingParametersToPayload {

	private String id;

	@Test(priority=1)
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";

		String jsonStr = given().header("Content-Type", "application/json").body(payload.addBook("qor","646")).log().all().when()
				.post("/Library/Addbook.php").then().statusCode(200)
				.extract().response().asString();

		JsonPath js = new JsonPath(jsonStr);
		id = js.getString("ID");

		System.out.println(id);
	}
	
	@Test(priority=2)
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		given()
			.header("Content-Type","application/json")
			.body(payload.deleteBook(id))
		.when()
			.post("/Library/DeleteBook.php")
		.then()
			.statusCode(200)
			.body("msg", equalTo("book is successfully deleted"))
			.log().body();
		
		id = null;
	}
}
