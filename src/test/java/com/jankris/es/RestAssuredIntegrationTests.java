package com.jankris.es;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith (SpringJUnit4ClassRunner.class)
public class RestAssuredIntegrationTests {

	private static final String TMP_PATH = "/event/1";
	private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNjk4NDIyMSwiaWF0IjoxNjE2OTY2MjIxfQ.W6pn0ICurxHsF26RIi3zIWBR0RCFPmXLtwpmiiQqDADvZqCCP6TfOt58quMc07wYDHSV3iFTfdDfXJF8dTM0Sg";

    @Test
    public void test_add_event() {
    	given().header("Authorization", "Bearer " + TOKEN).get(TMP_PATH).then().statusCode(200).assertThat();
    }

    @Test
    public void test_get_event() {
    	given().header("Authorization", "Bearer " + TOKEN).get(TMP_PATH).then().statusCode(200).assertThat();
    }
    
    @Test
    public void test_get_event_free_seats() {
    	given().header("Authorization", "Bearer " + TOKEN).get("/event/1/free").then().statusCode(200).assertThat();
    }
    
    @Test
    public void test_get_event_status() {
    	given().header("Authorization", "Bearer " + TOKEN).get("/event/1/status").then().statusCode(200).assertThat();
    }
    
    @Test
    public void test_set_event_new_status() {
    	given().header("Authorization", "Bearer " + TOKEN).put("/event/1/status/cancelled").then().statusCode(200).assertThat();
    }
}
