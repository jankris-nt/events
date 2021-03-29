package com.jankris.es;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
class RestAssuredLiveTests {
	private static final String TMP_PATH = "/event/1";
	private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNjk4NDIyMSwiaWF0IjoxNjE2OTY2MjIxfQ.W6pn0ICurxHsF26RIi3zIWBR0RCFPmXLtwpmiiQqDADvZqCCP6TfOt58quMc07wYDHSV3iFTfdDfXJF8dTM0Sg";
	
    @Before
    public void setup(){
        RestAssured.baseURI = "localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void whenMeasureResponseTime_thenOK(){
        Response response = RestAssured.get("/event/1");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);
        
        assertEquals(timeInS, timeInMS/1000);
    }
    
    @Test
    public void whenValidateResponseTime_thenSuccess(){
        given().header("Authorization", "Bearer " + TOKEN).when().get(TMP_PATH).then().time(lessThan(5000L));
    }
    
//    @Test
//    public void whenUseQueryParam_thenOK(){
//        given().queryParam("q", "john").when().get("/search/users").then().statusCode(200);
//        given().param("q", "john").when().get("/search/users").then().statusCode(200);
//    }
}
