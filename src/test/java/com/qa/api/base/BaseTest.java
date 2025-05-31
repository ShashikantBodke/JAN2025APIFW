package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

// @Listeners(ChainTestListener.class)     //Add in TestNG Runner either or here before running test cases
public class BaseTest {
protected RestClient restClient;
	
	//********** API Base URLs*******//
	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_REQRES = "https://reqres.in";
	protected final static String BASE_URL_BASIC_AUTH ="https://the-internet.herokuapp.com"; 
	protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com"; 
	protected final static String BASE_URL_OAUTH2_AMADEUS = "https://test.api.amadeus.com";
	protected final static String BASE_URL_OAUTH2_SPOTIFY = "https://accounts.spotify.com";
	protected final static String BASE_URL_ALBUMS_SPOTIFY = "https://api.spotify.com"; 
	protected final static String BASE_URL_ERGAST_CIRCUIT = "http://ergast.com";
	 
		
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
	public void setupAllureReport() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	
	
	@BeforeTest
	public void setup() {
		restClient = new RestClient();
	}
  
  


}
