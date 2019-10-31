package net.tweet.APIAutomation1;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateNewTweet 
{
	Properties p = new Properties();
	Logger log = Logger.getLogger(CreateNewTweet.class);
	@Test
	
	public void First() throws IOException  
	{
		FileInputStream fis = new FileInputStream("C:\\Maven\\APIAutomation1\\test-output\\data.Properties");
		p.load(fis);
		PropertyConfigurator.configure("C:\\Maven\\APIAutomation1\\Resources\\log4j.properties");
		String ApiKey = p.getProperty("ApiKey");
		String ApiSecretKey = p.getProperty("ApiSecretKey");
		String AccessToken = p.getProperty("AccessToken");
		String AccessTokenSecret = p.getProperty("AccessTokenSecret");
		
		RestAssured.baseURI = p.getProperty("create");
		Response r = given().auth().oauth(ApiKey, ApiSecretKey, AccessToken, AccessTokenSecret).
		queryParam("status", "I am learning API testing using Java12 #Qualitest").
		when().post("update.json").then().extract().response();
		String s1 = r.asString();
		System.out.println(s1);
		log.info("Create new tweet");
		JsonPath js = new JsonPath(s1);
	    String s2 = js.getString("id").toString();
		System.out.println(s2);

	}

}
