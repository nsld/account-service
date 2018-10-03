package com.nsld.snekkanti.accountservice.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

    @Test
    public void testAccount_builder(){
        String firstName = "Suresh";
        String secondName = "Nekkanti";
        Integer accountNumber = 123;
        Account account1 = Account.builder().firstName(firstName)
                .secondName(secondName)
                .accountNumber(accountNumber).build();
        assertEquals(accountNumber, account1.getAccountNumber());
        assertEquals(firstName, account1.getFirstName());
        assertEquals(secondName, account1.getSecondName());
    }

    @Test
    public void testAccount_Data(){
        String firstName = "Suresh";
        String secondName = "Nekkanti";
        Integer accountNumber = 123;
        Account account1 = Account.builder().build();
        account1.setAccountNumber(accountNumber);
        account1.setFirstName(firstName);
        account1.setSecondName(secondName);
        assertEquals(accountNumber, account1.getAccountNumber());
        assertEquals(firstName, account1.getFirstName());
        assertEquals(secondName, account1.getSecondName());
    }
}
