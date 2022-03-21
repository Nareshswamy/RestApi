import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if addplace is working
		//given - all input details
		//when -submit api--resourse,httpmethod
		//then - validate response
		//ADD PLACE
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		System.out.println("ADD CODE");
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json") //give req for add (qparam,header,body)
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Balaji medicals\",\r\n"
				+ "  \"phone_number\": \"(+91) 987456321\",\r\n"
				+ "  \"address\": \"pamur\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		.when().post("maps/api/place/add/json")            //give resource to hit
		.then().log().all().assertThat().statusCode(200)   //Validating status code.....for validating use assertThat
		.body("scope",equalTo("APP")).header("server",equalTo("Apache/2.4.18 (Ubuntu)"))//Validating things in response 
		.extract().response().asString();                 //extracting response and storing in string
		 System.out.println("The response is...."+response);
		 JsonPath js = new JsonPath(response); //For Parsing JSON 
		 String placeid = js.getString("place_id");//and getting a value from response like placeid
		 System.out.println("Placeid is = "+placeid);
		
		//PUTPLACE (updating)
		 System.out.println("PUT CODE");
		  String actualaddress="nellor";
		  given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		 .body("{\r\n"
		 		+ "\"place_id\":\""+placeid+"\",\r\n"
		 		+ "\"address\":\""+actualaddress+"\",\r\n"
		 		+ "\"key\":\"qaclick123\"\r\n"
		 		+ "}")
		 .when().put("maps/api/place/update/json")
		 .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		 
		  //GETPLACE
		  System.out.println("GET CODE");
		  String getresponse =  given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		  .when().get("maps/api/place/get/json")
		  .then().log().all().assertThat().statusCode(200)
		  .extract().response().asString();
		  JsonPath jsp = new JsonPath(getresponse);
		  String updatedaddress = jsp.getString("address");
		  Assert.assertEquals(actualaddress, updatedaddress);

	}

}
