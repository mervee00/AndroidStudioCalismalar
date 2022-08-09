package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExampleUnitTest {

    @BeforeClass
    public static void beforeCls(){
        System.out.println("Her test için - oncesi");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Her test için - sonrası");
    }

    @Before
    public void setUp() throws Exception{
        System.out.println("Method Öncesi");
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("Method Sonrası");
    }

    @Test
    public void testHelloWorld1() throws Exception{
        System.out.println("Test Hello World 1");
    }


}