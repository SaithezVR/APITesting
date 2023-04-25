package com.apiautomation.firsttest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class FirstApiTest {
	@Test
	public void firstTest() {

		Response response =RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
	}

	@Test
	public void testGet(){

		baseURI ="https://reqres.in/api";

		given().
		 get("/users?page=2").
		then().
		 statusCode(200).
		 body("data[4].first_name", equalTo("George")).
		 body("data.first_name", hasItems("George","Rachel"));

	}
	@Test
	public void testPost()
	{
		//       Map<String,Object> map =  new HashMap<String, Object>();
		//       map.put("name", "Saithez");
		//       map.put("job","QA Engineer");
		//       
		//       System.out.println("map");

		JSONObject req = new JSONObject();
		req.put("name", "Saithez");
		req.put("job", "QA Lead");
		System.out.println(req.toJSONString());

		baseURI ="https://reqres.in/api";
        
		given().
			body(req.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
		
	}	    

}
