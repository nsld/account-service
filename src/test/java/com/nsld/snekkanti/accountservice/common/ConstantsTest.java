package com.nsld.snekkanti.accountservice.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstantsTest {

    @Test
    public void testConstants(){
        Constants constants = new Constants();
        assertTrue(constants != null);
    }
}
