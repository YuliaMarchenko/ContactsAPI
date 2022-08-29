package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.dto.AuthRequestDto;
import com.telran.ilcarro.dto.UserBaseDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

public class UpdateTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/user";
    }

    @Test
    public void updatePasswordPositiveTest() {
        UserBaseDto userBaseDto = UserBaseDto.builder()
                .first_name("Test")
                .photo("gfhkggh")
                .second_name("Testovich")
                .build();

        given().contentType("application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("test1006@test.com:testTEST121".getBytes()))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("test1006@test.com:testTEST123".getBytes()))
                .body(userBaseDto)
                .put()
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();
    }
}
