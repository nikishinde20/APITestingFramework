package Excel;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.dataDriven;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

public class excelDriven {

	@Test
	public void addBook() throws IOException 
	{
		dataDriven d = new dataDriven();
		ArrayList data = d.getData("RestAddbook","RestAssured");
		HashMap<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", data.get(1));
		jsonAsMap.put("isbn", data.get(2));
		jsonAsMap.put("aisle", data.get(3));
		jsonAsMap.put("author", data.get(4));
		
		
		
//		HashMap<String, Object> jsonAsMap1 = new HashMap<>();
//		jsonAsMap.put("lat", "12");
//		jsonAsMap.put("lng", "24");
//		jsonAsMap.put("location", jsonAsMap1);
		
		RestAssured.baseURI="http://216.10.245.166";
		Response resp= given()
				.header("Content-Type","application/json")
				.body(jsonAsMap)
				.when()
				.post("/Library/Addbook.php")
				.then().assertThat().statusCode(200)
				.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
	}
}
