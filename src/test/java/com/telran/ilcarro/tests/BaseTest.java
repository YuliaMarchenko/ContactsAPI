package com.telran.ilcarro.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://rent-cars-app.herokuapp.com/";
    }
}
