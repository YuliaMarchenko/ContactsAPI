package com.telran.ilcarro.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateTests extends BaseTest{
    @BeforeMethod
    public void ensurePrecondition() {
        user.createUser();
    }

    @Test
    public void updatePasswordPositiveTest() {
        user.updateUser()
                .then()
                .assertThat().statusCode(200);
    }

    @AfterMethod
    public void tearDown(){
        user.deleteUser();
    }
}
