package com.sedona.api.testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.sedona.api.testbase.BaseTest;
import com.sedona.api.utility.TestUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Functionality1 extends BaseTest{
	public  String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\com\\sedona\\api\\testdata";
	@DataProvider(name="accountCreate")
	public Object[][] getData(){
		String fileName ="abc.xls";
		String sheetName = "user_create";
		Object[][] data=null;
		try {
			 data = TestUtil.getDataFromExcel(TESTDATA_SHEET_PATH,fileName,sheetName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	@Test(dataProvider = "accountCreate")
	public void accountCreation(String api,String url,String method,String requestJson,String expectedResponse) {
		test= extent.createTest(api);
		System.out.println("url "+url);
		System.out.println("requestJson: "+requestJson);
		System.out.println("expectedResponse:  "+expectedResponse);
		System.out.println("uri "+prop.getProperty("baseURI"));
		System.out.println("token "+prop.getProperty("token"));
		
		RestAssured.baseURI=prop.getProperty("baseURI");
		//RestAssured.basePath = url;
		//change
		System.out.println("last url "+url);
		Response actualResponse = given()
				.header("Authorization", "Bearer "+prop.getProperty("token"))
				.header("Content-type", "application/json")
				.and()
                .body(requestJson)
                .when()
                .post(url)
                .then()
                .extract().response();
		System.out.println("Response of Post : "+actualResponse.asString());
		System.out.println("response message "+actualResponse.jsonPath().getString("message"));
		Assert.assertEquals("success", actualResponse.jsonPath().getString("status")," status message as success ");
		//Assert.assertTrue(1 > 0);
	}
	
	/*	
	 * int sizeofCourse=jpath.getInt("courses.size()");
for(int i=0;i<sizeofCourse;i++) 
{
String alltitle=jpath.getString("courses["+i+"].title");
System.out.print(alltitle+", ");
	
} 





JSONObject jsonObject = new JSONObject(jsonString);
    JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
    JSONArray tsmresponse = (JSONArray) myResponse.get("listTsm");

    ArrayList<String> list = new ArrayList<String>();

    for(int i=0; i<tsmresponse.length(); i++){
        list.add(tsmresponse.getJSONObject(i).getString("name"));
    }

    System.out.println(list);
*/
}
