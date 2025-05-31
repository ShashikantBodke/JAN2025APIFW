package com.qa.api.gorest.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateUserTest extends BaseTest{
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken() {
		tokenId="62c8b5972f2ea2ffcc37740c218d38b3021cda69ad8f217cc4db6ee66f85f04d";
		ConfigManager.set("bearertoken", tokenId);
	}
	
	
	

//	@DataProvider
//	public Object[][] getUserData() {
//		return new Object[][] {
//			{"Sachin", "male", "active"},
//			{"Shreyas", "male", "inactive"},
//			{"Jayesh", "male", "active"}
//		};
//	}
	
	@DataProvider
	public Object[][] getUserExcelData() {
		return ExcelUtil.readData(AppConstants.CREATE_USER_SHEET_NAME);
	}
	
//	@Test(dataProvider = "getUserData")	
	@Test(dataProvider="getUserExcelData")
	public void createAUserTest(String name, String gender, String status) {
//		User user=new User(null, "Shashi",StringUtils.getRandomEmailId(),"Male","Active");
//		User user=new User("Shashi","Testuser@gmail.com","Male","Active");
		
		User user = new User(null, name, StringUtils.getRandomEmailId(), gender, status);		
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
//		Assert.assertEquals(response.jsonPath().get("name"),"Shashi");
//		Assert.assertNotNull(response.jsonPath().get("id"));
		
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertEquals(response.jsonPath().getString("status"), status);
		
		ChainTestListener.log("User ID ====> "+response.jsonPath().get("id")); //log ---Chain Test 
	}
	
	
	@Test(enabled = false)		
	public void createAUserTestWithJsonString() {
		String emailID=StringUtils.getRandomEmailId();
		
		String userJson="{\r\n"
				+ "\"name\":\"sachin\",\r\n"
				+ "\"gender\": \"male\",\r\n"
				+ "\"email\": \""+emailID+"\",\r\n"
				+ "\"status\": \"INACTIVE\"\r\n"
				+ "}";
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().get("name"),"sachin");
		Assert.assertNotNull(response.jsonPath().get("id"));
	}
	
	
	@Test(enabled = false)
	public void createAUserTestWithJsonFile() throws IOException {
		
		String emailID=StringUtils.getRandomEmailId();
		String rawJson=new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json"))) ;
		String updatedJson=rawJson.replace("{{email}}", emailID);
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, updatedJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().get("name"),"megha");
		Assert.assertNotNull(response.jsonPath().get("id"));
	}
	
	
	
}
