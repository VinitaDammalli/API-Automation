 package net.tweet.APIAutomation1;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Weather 
{
	Properties p = new Properties();
	Logger log = Logger.getLogger(Weather.class);
    @Test
    
	public void Fourth() throws IOException
	{
    	FileInputStream fis = new FileInputStream("C:\\Maven\\APIAutomation1\\test-output\\data.Properties");
		p.load(fis);
		PropertyConfigurator.configure("C:\\Maven\\APIAutomation1\\Resources\\log4j.properties");
		String ApiKey = p.getProperty("ApiKey");
		String ApiSecretKey = p.getProperty("ApiSecretKey");
		String AccessToken = p.getProperty("AccessToken");
		String AccessTokenSecret = p.getProperty("AccessTokenSecret");
		
		RestAssured.baseURI = p.getProperty("weather1");
		Response r = given().auth().oauth(ApiKey, ApiSecretKey, AccessToken, AccessTokenSecret).
		queryParam("q", "#Weather").and().queryParam("q", "#Banglore").and().queryParam("geocode", "12.972442,77.580643").
		when().get("tweets.json").then().log().all().extract().response();
		String s1 = r.asString();
		System.out.println(s1);
		log.info("Weather report of Banglore");
	}

}
