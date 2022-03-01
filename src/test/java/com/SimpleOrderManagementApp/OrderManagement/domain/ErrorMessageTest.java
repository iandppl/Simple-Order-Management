package com.SimpleOrderManagementApp.OrderManagement.domain;

import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class ErrorMessageTest {

    @Test
    void testConstructor(){
        ErrorMessage testErrorMessage = new ErrorMessage(new Date(),"test");
        Assertions.assertNotNull(testErrorMessage.getTimestamp());
        Assertions.assertNotNull(testErrorMessage.getMessage());
    }

    @Test
    void setterConstructor(){
        ErrorMessage testErrorMessage = new ErrorMessage();
        testErrorMessage.setMessage("test");
        testErrorMessage.setTimestamp(new Date());
        Assertions.assertNotNull(testErrorMessage.getTimestamp());
        Assertions.assertNotNull(testErrorMessage.getMessage());
    }
}
