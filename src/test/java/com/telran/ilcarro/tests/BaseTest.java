package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilcarro.helpers.UserHelper;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    UserHelper user = new UserHelper();

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/";
    }
}
