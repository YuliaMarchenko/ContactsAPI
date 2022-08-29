package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/";
    }

    @Test
    public void registrationPositiveTest() {
       UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void registrationSecondTimeNegativeTest() {
        UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(409);
    }

    @AfterSuite
    public void tearDown(){
        UserHelper.deleteUser(UserHelper.EMAIL, UserHelper.PASSWORD);
    }
}