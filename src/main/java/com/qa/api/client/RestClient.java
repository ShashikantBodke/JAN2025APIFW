package com.qa.api.client;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Map;
import java.util.Base64;

//import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient { //q
	
	//define Response Specs:

	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
//	private ResponseSpecification responseSpec422 = expect().statusCode(422);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);

	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	
	private RequestSpecification setupRequest(String baseUrl, AuthType authType, ContentType contentType) {
//		ChainTestListener.log("API Base URL: "+baseUrl);   	//log
//		ChainTestListener.log("Auth Type: "+authType); 
//		ChainTestListener.log("Conent Type: "+contentType); 
		
		RequestSpecification request = RestAssured.given().log().all()
										.baseUri(baseUrl)
										.contentType(contentType)
										.accept(contentType);
		
		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization","Bearer "+ ConfigManager.get("bearertoken"));
			break;		
		case BASIC_AUTH:
			request.header("Authorization", "Basic "+ generateBasicAuthToken());
			break;		
		case API_KEY:
			request.header("x-api-key" , "Basic "+ "api key");
		break;	
		case NO_AUTH:
			System.out.println("Auth is not required....");
		break;	
		
		default:
			System.out.println("this auth is not supported...please pass the right AuthType...");
			throw new APIException("========Invalid Auth===========");
		}
		return request;						
	}
	
	
	private String generateBasicAuthToken() {
		String credentials = ConfigManager.get("basicauthusername") +":"+ ConfigManager.get("basicauthpassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		//admin:admin ====>YWRtaW46YWRtaW4=  (base64 encoded value)
	}
	
	private void applyParams(RequestSpecification request, Map<String,String> queryParams, Map<String,String> pathParams) {
		
		if(queryParams!=null) {
			request.queryParams(queryParams);
		}
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
	}
	
	  
	/**
	 * This method is used to call GET APIs
	 * @param baseUrl
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return it returns the GET API call response
	 */
	//====================== GET ===============================
	public Response get(String baseUrl, String endPoint, 
			Map<String,String>queryParams, 
			Map<String,String>pathParams, 
			AuthType authType, 
			ContentType contentType){
		
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		
	 return response;
	}
	
	// ===================== POST ==============================	
	public <T> Response post(String baseUrl,String endPoints,T body,
				Map<String,String>queryParams, 
				Map<String,String>pathParams,
				AuthType authType,
				ContentType contentType) {
			
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body).post(endPoints).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		
	return response;
	}
	
	public Response post(String baseUrl,String endPoints,File file,
			Map<String,String>queryParams, 
			Map<String,String>pathParams,
			AuthType authType,
			ContentType contentType) {
		
	RequestSpecification request = setupRequest(baseUrl, authType, contentType);
	applyParams(request, queryParams, pathParams);
	Response response = request.body(file).post(endPoints).then().spec(responseSpec200or201).extract().response();
	response.prettyPrint();
	
	return response;
	}
	
	public Response post(String baseUrl, String endPoint, String clientId, String clientSecret, String grantType, ContentType contentType) {

		Response response = RestAssured.given()
				.contentType(contentType)
				.formParam("grant_type", grantType)
				.formParam("client_id", clientId)
				.formParam("client_secret", clientSecret)
				.when()
					.post(baseUrl+endPoint);
		response.prettyPrint();
		return response;
	}
	// ===================== PUT ==============================
	public <T> Response put(String baseUrl,String endPoints,T body,
			Map<String,String>queryParams, 
			Map<String,String>pathParams,
			AuthType authType,
			ContentType contentType) {
		
	RequestSpecification request = setupRequest(baseUrl, authType, contentType);
	applyParams(request, queryParams, pathParams);
	Response response = request.body(body).put(endPoints).then().spec(responseSpec200).extract().response();
	response.prettyPrint();
	
	return response;
	}

	// ===================== PATCH ==============================
	public <T> Response patch(String baseUrl,String endPoints,T body,
				Map<String,String>queryParams, 
				Map<String,String>pathParams,
				AuthType authType,
				ContentType contentType) {
			
	RequestSpecification request = setupRequest(baseUrl, authType, contentType);
	applyParams(request, queryParams, pathParams);
	Response response = request.body(body).patch(endPoints).then().spec(responseSpec200).extract().response();
	response.prettyPrint();
		
	return response;
	}
	
	// ===================== DELETE ==============================
	public <T> Response delete(String baseUrl,String endPoints,
				Map<String,String>queryParams, 
				Map<String,String>pathParams,
				AuthType authType,
				ContentType contentType) {
			
	RequestSpecification request = setupRequest(baseUrl, authType, contentType);
	applyParams(request, queryParams, pathParams);
	Response response = request.delete(endPoints).then().spec(responseSpec204).extract().response();
	response.prettyPrint();
		
	return response;
	}
	
	
	
	
	
}
