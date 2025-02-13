package Lecture4_DynamicJSON;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ParsingDynamicJSON {
	
	private String id;
	@Test
	public void addBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String jsonStr = given()
			.header("Content-Type","application/json")
			.body(payload.addBook())
			.log().all()
		.when()
			.post("/Library/Addbook.php")
		.then()
			.statusCode(200)
			.body("Msg", equalTo("Book Already Exists"))
			.extract().response().asString();
		
		JsonPath js = new JsonPath(jsonStr);
		id = js.getString("ID");
		
		System.out.println(id);
	}
	
}
