package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteTests {
    @BeforeMethod
    public void ensurePrecondition() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/";
        UserHelper.createUser(UserHelper.EMAIL, UserHelper.PASSWORD);
    }

    @Test
    public void deletePositiveTest() {
        UserHelper.deleteUser(UserHelper.EMAIL, UserHelper.PASSWORD)
                .then()
                .assertThat().statusCode(200);
    }
}
