package Handling_JIRA_API;
import java.io.File;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class Jira_API {
	public static void main(String[] args) {
		//BASEURI
		RestAssured.baseURI="http://localhost:8080";
		//logging in to jira and creating session
		SessionFilter session=new SessionFilter();
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
		.body("{\r\n" +
		"    \"username\": \"nareshswamy\",\r\n" +
		"    \"password\": \"Nareshswamy\"\r\n" +
		"}").log().all().filter(session).
				when().post("/rest/auth/1/session").
				then().log().all().extract().response().asString();
		System.out.println("The  response for creating session-->"+response);
		//creating issue
		String createissuerespose = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"fields\":{\r\n"
				+ "        \"project\":\r\n"
				+ "        {\r\n"
				+ "            \"key\": \"RES\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Creating issue from RESTautomation\",\r\n"
				+ "        \"description\":\"My first issue created from restapi\",\r\n"
				+ "        \"issuetype\":{\r\n"
				+ "            \"name\":\"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").log().all().filter(session)
		.when().post("/rest/api/2/issue")
		.then().log().all().extract().response().asString();
		System.out.println(createissuerespose);
		JsonPath js =new JsonPath(createissuerespose);
		String id =js.get("id").toString();
		System.out.println("The id of created issue-->"+id);
		
		
		//ADDING COMMENT
		String expectedcomment ="created a comment from restapi automation";
		String addCommentResponse = given().pathParam("key", id).log().all().header("Content-Type","application/json")
		.body("{\r\n" +
		"    \"body\": \""+expectedcomment+"\",\r\n" +
		"    \"visibility\": {\r\n" +
		"        \"type\": \"role\",\r\n" +
		"        \"value\": \"Administrators\"\r\n" +
		"    }\r\n" +
		"}").filter(session).
				when().post("/rest/api/2/issue/{key}/comment").
				then().log().all()
		.assertThat().statusCode(201).extract().response().asString();
		JsonPath js1=new JsonPath(addCommentResponse);
		String commentId= js1.getString("id");
		System.out.println("The comment id-->"+commentId);
		
		//Add Attachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", id)
		.header("Content-Type","multipart/form-data")
		.multiPart("file",new File("jira.txt"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//Get Issue
		String issueDetails=given().filter(session).pathParam("key", id)
		.queryParam("fields", "comment")
		.log().all().when().get("/rest/api/2/issue/{key}").
		then().log().all().extract().response().asString();
		System.out.println(issueDetails);
		JsonPath js11 =new JsonPath(issueDetails);
		int commentsCount=js11.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentsCount;i++)
		{
		String commentIdIssue =js11.get("fields.comment.comments["+i+"].id").toString();
		if (commentIdIssue.equalsIgnoreCase(commentId))
		{
		String message= js11.get("fields.comment.comments["+i+"].body").toString();
		System.out.println(message);
		Assert.assertEquals(message, expectedcomment);
		}
		}
		}
}

