package com.jankris.es;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jankris.es.conf.EventApplication;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EventApplication.class)
@SpringBootTest
class EsApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("TesssssssssssssssssssssSSSSSSSSSSSSSSSSSSSSSSSSSSSSStin!!");
	}
	
//	@Test public void
//	lotto_resource_returns_200_with_expected_id_and_winners() {
//		given().
//        parameters("firstName", "John", "lastName", "Doe").
//	    when().
//	            get("/lotto/{id}", 5).
//	    then().
//	            statusCode(200).
//	            body("lotto.lottoId", equalTo(5),
//	                 "lotto.winners.winnerId", hasItems(23, 54));
//
//	}
//	@Test
//	public void getResponseBody() {
//		get("http://rfweb.local:8080/persons/5").then().log()
//		.then()
//		.assertThat()
//		.statusCode(200)
//		.body("size()", is(2));
//		 
//		}

}
