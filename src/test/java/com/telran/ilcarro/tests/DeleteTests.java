package com.telran.ilcarro.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteTests extends BaseTest{
    @BeforeMethod
    public void ensurePrecondition() {
        user.createUser();
    }

    @Test
    public void deletePositiveTest() {
        user.deleteUser()
                .then()
                .assertThat().statusCode(200);
    }
}
