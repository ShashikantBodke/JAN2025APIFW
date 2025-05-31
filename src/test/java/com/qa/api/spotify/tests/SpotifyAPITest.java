package com.qa.api.spotify.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyAPITest extends BaseTest{

	private String accessToken;

	@BeforeMethod
	public void getOAuth2Token() {
	Response response = restClient.post(BASE_URL_OAUTH2_SPOTIFY, SPOTIFY_OAUTH2_ENDPOINT,
			ConfigManager.get("spotifyclientid"), ConfigManager.get("spotifyclientsecret"), ConfigManager.get("granttype"),ContentType.URLENC);

		accessToken = response.jsonPath().getString("access_token");
		System.out.println("Access Token: " + accessToken);
		ConfigManager.set("bearertoken", accessToken);
	}

	@Test
	public void getAlbumsTest() {

		Response response = restClient.get(BASE_URL_ALBUMS_SPOTIFY, SPOTIFY_ALBUMS_ENDPOINT, null, null,
				AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
