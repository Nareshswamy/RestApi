package Serialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Addplace;
import pojo.Courses;
import pojo.Location;
import pojo.Web;

public class Place {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ADD PLACE
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				System.out.println("ADD CODE");
				Addplace a = new Addplace();
				a.setAccuracy(50);
				a.setAddress("Pamur");
				a.setLanguage("Telugu");
				a.setName("Naresh");
				a.setWebsite("nareshit.com");
				
				Location loc = new Location();
				loc.setLat(-38.383494);
				loc.setLng(33.427362);
				a.setLocation(loc);
				
				HashMap<String, Object> map = new HashMap<String, Object>(); 
				map.put("coursename", "selenium");
				map.put("price", "55");
				HashMap<String, Object> h = new HashMap<String, Object>(); 
				h.put("web", map);
				//List L = new ArrayList();
				//L.add(h);
				
		        //Set<Entry<String, Object>> entrySet = map.entrySet(); 
		    	//List<Entry<String, Object>> mylist2 = new ArrayList<Entry<String,Object>>(entrySet);
		    	
		    	
		    	Courses c = new Courses();
		    	c.setWeb(h);
		    	
				
				List L = new ArrayList();
				L.add(c);
				a.setCourses(L);
				 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json") //give req for add (qparam,header,body)
				.body(a)
				.when().post("maps/api/place/add/json")            //give resource to hit
				.then().log().all().assertThat().statusCode(200)   //Validating status code.....for validating use assertThat
				.extract().response().asString();                 //extracting response and storing in string
				 System.out.println("The response is...."+response);
				 
	}

}
