package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

// @Listeners(ChainTestListener.class)     //Add in TestNG Runner either or here before running test cases
public class BaseTest {
protected RestClient restClient;
	
	//********** API Base URLs*******//
	protected static String BASE_URL_GOREST;
	protected static String BASE_URL_CONTACTS;   
	protected static String BASE_URL_REQRES;     
	protected static String BASE_URL_BASIC_AUTH;    
	protected static String BASE_URL_PRODUCTS;   
	protected static String BASE_URL_OAUTH2_AMADEUS;
	protected static String BASE_URL_OAUTH2_SPOTIFY;
	protected static String BASE_URL_ALBUMS_SPOTIFY;
	protected static String BASE_URL_ERGAST_CIRCUIT;	 
		
	//********** API EndPoints*******//
	protected final static String GOREST_USERS_ENDPOINT = "/public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected final static String CONTACTS_ENDPOINT = "/contacts";
	protected final static String REQRES_ENDPOINT = "/api/users"; 
	protected final static String BASIC_AUTH_ENDPOINT = "/basic_auth"; 
	protected final static String PRODUCTS_ENDPOINT = "/products"; 
	protected final static String AMADEUS_OATUH2_TOKEN_ENDPOINT = "/v1/security/oauth2/token"; 
	protected final	static String AMADEUS_FLIGHT_DEST_ENDPOINT = "/v1/shopping/flight-destinations"; 
	protected final	static String SPOTIFY_OAUTH2_ENDPOINT = "/api/token"; 
	protected final	static String SPOTIFY_ALBUMS_ENDPOINT = "/v1/albums/4aawyAB9vmqN3uQ7FjRGTy"; 
	protected final static String ERGAST_CIRCUIT_ENDPOINT = "/api/f1/2017/circuits.xml";
	 
	
	@BeforeSuite
	public void initSetup() {
		RestAssured.filters(new AllureRestAssured());
		BASE_URL_GOREST= ConfigManager.get("baseurl.gorest").trim();
		BASE_URL_CONTACTS= ConfigManager.get("baseurl.contacts").trim();
		BASE_URL_REQRES= ConfigManager.get("baseurl.reqres").trim();
		BASE_URL_BASIC_AUTH= ConfigManager.get("baseurl.basic_auth").trim();
		BASE_URL_PRODUCTS= ConfigManager.get("baseurl.products").trim();
		BASE_URL_OAUTH2_AMADEUS= ConfigManager.get("baseurl.oauth2_amadeus").trim();
		BASE_URL_OAUTH2_SPOTIFY= ConfigManager.get("baseurl.oauth2_spotify").trim();
		BASE_URL_ALBUMS_SPOTIFY= ConfigManager.get("baseurl.albums_spotify").trim();
		BASE_URL_ERGAST_CIRCUIT= ConfigManager.get("baseurl.ergast_circuit").trim();
	}
	
	
	
	@BeforeTest
	public void setup() {
		restClient = new RestClient();
	}
  
  


}
