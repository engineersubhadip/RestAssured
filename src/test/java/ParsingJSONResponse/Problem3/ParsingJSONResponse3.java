package ParsingJSONResponse.Problem3;

import static io.restassured.RestAssured.when;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJSONResponse3 {
	
	private String companyName;
	private String location;
	
	@Test(priority=1)
	public void testCase_captureCompanyName() {
		try {
			Response res = when()
				.get("http://localhost:3000/company");				
			companyName = res.asString().trim();
		} catch (Exception e) {
			System.out.println("Test case execution failed. "+e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority=2)
	public void testCase_captureLocation() {
		try {
			Response res =  when()
				.get("http://localhost:3000/location");
			location =  res.asString().trim();
		} catch (Exception e) {
			System.out.println("Test case execution failed. "+e.getMessage());
			Assert.fail();
		}
	}

	
	private void printEmployeeProjects(JSONArray arr) {
		
		for (int i=0; i<arr.length(); i++) {
			JSONObject currObj = arr.getJSONObject(i);
			String currPr = currObj.optString("projectName");
			String currStatus = currObj.optString("status");
			StringBuilder ans = new StringBuilder();
			ans.append("Project: "+currPr);
			ans.append(" Status: "+currStatus);
			System.out.println(ans.toString());
		}
	}
	
	
	@Test(priority=3)
	public void testCase_parseEmployeeData() {
		try {
			Response res = when()
				.get("http://localhost:3000/employees");
			
			JSONArray employee = new JSONArray(res.asString());
			int index = 0;
			
			while (index < employee.length()) {
				JSONObject currObj = employee.getJSONObject(index);
				String empName = currObj.optString("name");
				int empAge = currObj.optInt("age");
				String email = currObj.optJSONObject("contact").optString("email");
				String phone = currObj.optJSONObject("contact").optString("phone");
				
				JSONArray empProject = currObj.optJSONArray("projects");
				
				StringBuilder ans = new StringBuilder();

				ans.append("CompanyName: "+companyName);
				ans.append(" Location: "+location);
				ans.append(" EmployeeName: "+empName);
				ans.append(" Age: "+empAge);
				ans.append(" Email: "+email);
				ans.append(" Phone: "+phone);
				
				System.out.println(ans.toString());
				printEmployeeProjects(empProject);
				System.out.println("----------------------");
				
				index += 1;
			}
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			System.out.println("Test case execution failed. "+e.getMessage());
			Assert.fail();
		}
	}
}
