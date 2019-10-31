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

public class Username 
{
	Properties p = new Properties();
	Logger log = Logger.getLogger(Username.class);
	@Test

	public void Third() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Maven\\APIAutomation1\\test-output\\data.Properties");
		p.load(fis);
		PropertyConfigurator.configure("C:\\Maven\\APIAutomation1\\Resources\\log4j.properties");
		String ApiKey = p.getProperty("ApiKey");
		String ApiSecretKey = p.getProperty("ApiSecretKey");
		String AccessToken = p.getProperty("AccessToken");
		String AccessTokenSecret = p.getProperty("AccessTokenSecret");
		
		RestAssured.baseURI = p.getProperty("username1");
		Response r = given().auth().oauth(ApiKey, ApiSecretKey, AccessToken, AccessTokenSecret).
	    queryParam("q","#Qualitest").
	    when().get("tweets.json").then().extract().response();
		String s1 = r.asString();
		System.out.println(s1);
		log.info("Username");
		JsonPath jp = new JsonPath(s1);
		String s2 = jp.get("statuses[0].user.name").toString();
		System.out.println(s2);
	}

}
