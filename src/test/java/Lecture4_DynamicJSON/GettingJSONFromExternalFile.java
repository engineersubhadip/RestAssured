package Lecture4_DynamicJSON;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GettingJSONFromExternalFile {
	
	@Test
	public void readExternalFile() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given()
			.header("Content-Type","application/json")
			.body(new String(Files.readAllBytes(Paths.get("K:\\RestAssured-API Automation\\RestAssuredAPIAutomation\\testData\\bookData.json.txt"))))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.statusCode(200)
			.log().all();
	}
}
