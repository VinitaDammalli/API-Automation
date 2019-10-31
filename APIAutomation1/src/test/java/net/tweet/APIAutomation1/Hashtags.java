package net.tweet.APIAutomation1;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Hashtags 
{
	Properties p = new Properties();
	Logger log = Logger.getLogger(Hashtags.class);
    @Test
    
	public void Sixth() throws IOException
	{
    	FileInputStream fis = new FileInputStream("C:\\Maven\\APIAutomation1\\test-output\\data.Properties");
		p.load(fis);
		PropertyConfigurator.configure("C:\\Maven\\APIAutomation1\\Resources\\log4j.properties");
		String ApiKey = p.getProperty("ApiKey");
		String ApiSecretKey = p.getProperty("ApiSecretKey");
		String AccessToken = p.getProperty("AccessToken");
		String AccessTokenSecret = p.getProperty("AccessTokenSecret");
		String woeid[]= {"2295383", "28218", "23424977", "23424852"}; 
		
		for(int i=0; i<4; i++)
		{
		   RestAssured.baseURI = p.getProperty("trends1");
		   Response r = given().auth().oauth(ApiKey, ApiSecretKey, AccessToken, AccessTokenSecret).queryParam("id", woeid[i]).when().
		   get("place.json").then().assertThat().statusCode(200).extract().response();
		   String s1 = r.asString();
		   System.out.println(s1);
			log.info("Hash tags of countries");
		   JsonPath jp = new JsonPath(s1);
		   
		   //display country_name 
		   if(i==0)
		   {
			   System.out.println("**********INDIA**********");
		   }
		   else if(i==1)
		   {
			   System.out.println("**********UK**********");
		   }
		   else if(i==2)
		   {
			   System.out.println("**********US**********");
		   }
		   else if(i==3)
		   {
			   System.out.println("**********ISRAEL**********");
		   }
		
		   //top 5 hashtags
		   String hashtag = jp.get("trends.name").toString();
		   for(int j=0; j<5; j++)
		   {
			   if(hashtag.startsWith("[[") || hashtag.startsWith("#"))
			   {
				   String[] splitted = hashtag.split(",");
				   System.out.println(splitted[j]);
			   }
		   }
		   System.out.println("-------------------------------");
		}

	}

}
