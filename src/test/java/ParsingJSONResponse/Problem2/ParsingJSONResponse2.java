package ParsingJSONResponse.Problem2;

import static io.restassured.RestAssured.when;

import org.json.JSONArray;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJSONResponse2 {

	private double getAverageSalary(JSONArray arr) {

		if (arr.length() == 0) {
			return 0;
		}

		int count = 0;
		double total = 0;
		for (int i = 0; i < arr.length(); i++) {
			double currSalary = arr.getJSONObject(i).getDouble("salary");
			total += currSalary;
			count += 1;
		}
		return total / count;
	}

	@Test
	public void testCase_ParsingJSONResponse() {

		Response res = when().get("http://localhost:3000/employees");

		JSONArray employee = new JSONArray(res.asString());

		double avgSalary = getAverageSalary(employee);

		for (int i = 0; i < employee.length(); i++) {
			
			String empName = employee.getJSONObject(i).optString("name");
			double empSal = employee.getJSONObject(i).optDouble("salary");
			String empDeptName = employee.getJSONObject(i).optJSONObject("department").optString("name");
			String empDeptLoc = employee.getJSONObject(i).optJSONObject("department").optString("location");
			JSONArray empSkills = employee.getJSONObject(i).optJSONArray("skills");
			
//			String finalSkills = "";
			StringBuilder finalSkills = new StringBuilder();
			
			for (int j = 0; j < empSkills.length(); j++) {
				String currSkill = empSkills.getString(j);
				if (j != empSkills.length() - 1) {
					finalSkills.append(currSkill);
					finalSkills.append(", ");
				} else {
					finalSkills.append(currSkill);
				}
			}

			System.out.println("Employee Name: " + empName + " Employee Salary: " + empSal + " Department Name: "
					+ empDeptName + " Department Location: " + empDeptLoc + " Employee Skills: " + finalSkills.toString());
		}

		System.out.println("Average Salary of All Employees: " + avgSalary);
	}
}
