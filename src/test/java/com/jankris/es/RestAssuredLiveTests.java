package com.jankris.es;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

// @SpringBootTest
class RestAssuredLiveTests {
	private static final String TMP_PATH = "/event/1";
	private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNzAxODk1NSwiaWF0IjoxNjE3MDAwOTU1fQ.fyK9V3_u0-dDssNcVq1iNvYJP2iFRfWPayitbkLsgysSo3TyeXfUMOYBGdei5aFlJUCn2qq8DZ-XAvQKUp4stw";
	
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
}
