package Lecture4_DynamicJSON;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

public class DataProviderAnnotation {
	
	private String id;
	
	@Test(priority=1, dataProvider="bookData")
	public void addNewBook(Object isbn, Object isle) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String jsonStr = given()
			.header("Content-Type","application/json")
			.body(payload.addBook((String)isbn, (String)isle))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response().asString();
		
		JsonPath js = new JsonPath(jsonStr);
		id = js.getString("ID");
	}
	
	@AfterMethod
	public void deleteAddedBook() {
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
	
	@DataProvider(name="bookData")
	Object[][] getData() {
		Object[][] testData = {{"asdqwezxc","123"}, {"absdygasd","456"}, {"oxcizxlckn","7893"}, {"zxccyjzx","8362"}};
		return testData;
	}
}
