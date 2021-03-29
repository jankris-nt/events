package com.jankris.es;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith (SpringJUnit4ClassRunner.class)
public class RestAssuredIntegrationTests{

    private static final String APPLICATION_JSON = "application/json";
	private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNzAxODk1NSwiaWF0IjoxNjE3MDAwOTU1fQ.fyK9V3_u0-dDssNcVq1iNvYJP2iFRfWPayitbkLsgysSo3TyeXfUMOYBGdei5aFlJUCn2qq8DZ-XAvQKUp4stw";
    private static String postEventBody = "{\n" +
            "  \"id\": \"1\",\n" +
            "  \"name\": \"Room1\",\n" +
            "  \"seats\": \"11\",\n" +
            "  \"status\": \"available\" \n}";
    
    private static String postEvent2Body = "{\n" +
            "  \"id\": \"2\",\n" +
            "  \"name\": \"Room2\",\n" +
            "  \"seats\": \"12\",\n" +
            "  \"status\": \"available\" \n}";
    
    private static String postPersonBody = "{\n" +
            "  \"id\": \"1\",\n" +
            "  \"name\": \"Leo\" \n}";	

    private static String postPerson2Body = "{\n" +
            "  \"id\": \"2\",\n" +
            "  \"name\": \"Tom\" \n}";
	
	@Test
    public void test_event_actions() {	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postEventBody)
    	.post("/event").then().statusCode(201).assertThat();	

    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postEvent2Body)
    	.post("/event").then().statusCode(201).assertThat();

    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPersonBody)
    	.post("/event/{id}/person", 1).then().statusCode(200).assertThat();

    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.post("/event/{id}/person", 1).then().statusCode(200).assertThat();
    	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.delete("/event/{id}/person/{name}", 1, "Leo").then().statusCode(200).assertThat();
    	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.post("/event/{id}/person", 1).then().statusCode(404).assertThat();
    	
    	given().header("Authorization", "Bearer " + TOKEN)
    	.get("/event/1").then().statusCode(200).assertThat();    	

    	given().header("Authorization", "Bearer " + TOKEN)
    	.get("/event/1/free").then().statusCode(200).assertThat();

    	given().header("Authorization", "Bearer " + TOKEN)
    	.get("/event/1/status").then().statusCode(200).assertThat();

    	given().header("Authorization", "Bearer " + TOKEN)
    	.put("/event/1/status/cancelled").then().statusCode(200).assertThat();
    }
	
	@Test
    public void test_check_event_status_notexist() {	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.get("/event/{id}/status", 101).then().statusCode(404).assertThat();
    }

	@Test
    public void test_check_event_availability_notexist() {	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.get("/event/{id}/free", 101).then().statusCode(404).assertThat();
    }
	
	@Test
    public void test_delete_person_from_event_notexist() {	
    	given().header("Authorization", "Bearer " + TOKEN).contentType(APPLICATION_JSON).body(postPerson2Body)
    	.delete("/event/{id}/person/{name}", 1, "Leo2").then().statusCode(404).assertThat();
    }
}
