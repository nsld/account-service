package com.nsld.snekkanti.accountservice.service;

import com.nsld.snekkanti.accountservice.common.Constants;
import com.nsld.snekkanti.accountservice.entity.Account;
import com.nsld.snekkanti.accountservice.repository.AccountRepository;
import com.nsld.snekkanti.accountservice.request.AccountRequest;
import com.nsld.snekkanti.accountservice.response.AccountResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = NONE)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setupAccountsData(){
        Account account1 = Account.builder().firstName("firstName1")
                .secondName("secondName1")
                .accountNumber(123).build();
        Account account2 = Account.builder().firstName("firstName2")
                .secondName("secondName2")
                .accountNumber(124).build();
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.flush();
    }

    @After
    public void deleteAccountData(){
        accountRepository.deleteAll();
    }

    @Test
    public void testGetAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    public void testAddAccount_Bad_Request(){
        AccountResponse accountResponse = accountService.addAccount(null);
        assertEquals(HttpStatus.BAD_REQUEST, accountResponse.getStatus());
        assertEquals(Constants.INVALID_ACCOUNT_ADD_REQUEST, accountResponse.getMessage());
    }

    @Test
    public void testAddAccount_Bad_Request_Params(){
        AccountRequest accountRequest = AccountRequest.builder().build();
        AccountResponse accountResponse = accountService.addAccount(accountRequest);
        assertEquals(HttpStatus.BAD_REQUEST, accountResponse.getStatus());
        assertEquals(Constants.INVALID_ACCOUNT_ADD_REQUEST, accountResponse.getMessage());
    }

    @Test
    public void testAddAccount_Bad_Request_Duplicate_Account_Number(){
        AccountRequest accountRequest = AccountRequest.builder().firstName("Suresh").secondName("Nekkanti").accountNumber(123).build();
        AccountResponse accountResponse = accountService.addAccount(accountRequest);
        assertEquals(HttpStatus.BAD_REQUEST, accountResponse.getStatus());
        assertEquals(Constants.DUPLICATE_ACCOUNT_ADD_REQUEST, accountResponse.getMessage());
    }

    @Test
    public void testAddAccount(){
        AccountRequest accountRequest = AccountRequest.builder().firstName("Suresh").secondName("Nekkanti").accountNumber(456).build();
        AccountResponse accountResponse = accountService.addAccount(accountRequest);
        assertEquals(HttpStatus.OK, accountResponse.getStatus());
        assertEquals(Constants.ACCOUNT_ADDED_SUCCESSFULLY, accountResponse.getMessage());
    }


    @Test(expected = Exception.class)
    public void testDeleteAccount_No_Entity_found() throws Exception{
        AccountResponse accountResponse = accountService.deleteAccount(1);
    }

    @Test
    public void testDeleteAccount() throws Exception{
        List<Account> accounts = accountService.getAllAccounts();
        int accountId = accounts.get(0).getId();
        AccountResponse accountResponse = accountService.deleteAccount(accountId);
        assertEquals(HttpStatus.OK, accountResponse.getStatus());
        assertEquals(Constants.ACCOUNT_DELETED_SUCCESSFULLY, accountResponse.getMessage());
    }

}
