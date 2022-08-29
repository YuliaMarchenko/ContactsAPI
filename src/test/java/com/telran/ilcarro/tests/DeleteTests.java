package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

public class DeleteTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/user";
    }

    @Test
    public void deletePositiveTest() {
        given().contentType("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("test1006@test.com:testTEST121".getBytes()))
                .delete()
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
    }
}
