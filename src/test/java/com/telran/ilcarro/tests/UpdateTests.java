package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.dto.UserBaseDto;
import com.telran.ilcarro.helpers.AuthHelper;
import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Base64;

import static com.jayway.restassured.RestAssured.given;

public class UpdateTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com";
        UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD);
    }

    @Test
    public void updatePasswordPositiveTest() {
        UserBaseDto userBaseDto = UserBaseDto.builder()
                .first_name("Test")
                .photo("gfhkggh")
                .second_name("Testovich")
                .build();

        given().contentType("application/json")
                .header("Authorization", AuthHelper.basicAuth(UserHelper.EMAIL, UserHelper.PASSWORD))
                .header("X-New-Password", "Basic " + Base64.getEncoder().encodeToString("test1009@test.com:testTEST123".getBytes()))
                .body(userBaseDto)
                .put("user")
                .then()
                .assertThat().statusCode(200);
    }
}
