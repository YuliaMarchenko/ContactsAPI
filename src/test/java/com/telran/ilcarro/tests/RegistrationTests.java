package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.dto.AuthRequestDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/";
    }

    @Test
    public void registrationPositiveTest() {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .first_name("Test")
                .second_name("Testovich")
                .build();

        given().contentType("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("test1006@test.com:testTEST121".getBytes()))
                .body(requestDto)
                .post("registration")
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Test
    public void registrationSecondTimeNegativeTest() {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .first_name("Test")
                .second_name("Testovich")
                .build();

        given().contentType("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("test1006@test.com:testTEST121".getBytes()))
                .body(requestDto)
                .post("registration")
                .then()
                .assertThat().statusCode(409)
                .extract().body().asString();
    }
}