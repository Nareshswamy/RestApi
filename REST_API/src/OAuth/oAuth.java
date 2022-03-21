package OAuth;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
public class oAuth {
	public static void main(String[] args) {
		//Step1:- To get code first login to the url (which u generated from postman by passing mandatory query parameters..copy that url in broswer and login 
		// after logging in capture that url and store in string .This url will have code which we use to generate access token
		
		//Generating code
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g5JgWQF68RHL0xZGQ0-PBhaL_OodqdMmHPTuqSNXjIQjKGwEKPWwJnZKrntFqtH-g&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
        String partialcode = url.split("code=")[1];
        String code =partialcode.split("&scope")[0];
        System.out.println(code);
        
        //Generating AccessToken
       String accesstokenresponse= given().urlEncodingEnabled(false)
    	.queryParams("code",code)
        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
        .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
        .queryParams("grant_type","authorization_code")
            
        .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
       System.out.println(accesstokenresponse);
       JsonPath js = new JsonPath(accesstokenresponse);
       String accesstoken = js.get("access_token");
       System.out.println("The accesstoken is--->"+accesstoken);
       
       //Getting required response
       String response = given().queryParam("access_token", accesstoken)
	   .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
       System.out.println(response);
	}
	
}
