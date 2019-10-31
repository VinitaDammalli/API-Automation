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

public class BlockUser 
{
	Properties p = new Properties();
	Logger log = Logger.getLogger(BlockUser.class);
	@Test

	public void Seventh() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Maven\\APIAutomation1\\test-output\\data.Properties");
		p.load(fis);
		PropertyConfigurator.configure("C:\\Maven\\APIAutomation1\\Resources\\log4j.properties");
		String ApiKey = p.getProperty("ApiKey");
		String ApiSecretKey = p.getProperty("ApiSecretKey");
		String AccessToken = p.getProperty("AccessToken");
		String AccessTokenSecret = p.getProperty("AccessTokenSecret");
		
		RestAssured.baseURI = p.getProperty("blockuser1");
		Response r = given().auth().oauth(ApiKey, ApiSecretKey, AccessToken, AccessTokenSecret).queryParam("screen_name", "@AmreenDarga").when().
		post("create.json").then().assertThat().statusCode(200).extract().response();
		String s1 = r.asString();
		System.out.println(s1);
		log.info("Block user");
		JsonPath jp = new JsonPath(s1);
    }
}
