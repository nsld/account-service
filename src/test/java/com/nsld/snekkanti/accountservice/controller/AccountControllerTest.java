package com.nsld.snekkanti.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsld.snekkanti.accountservice.AccountServiceApplication;
import com.nsld.snekkanti.accountservice.common.Constants;
import com.nsld.snekkanti.accountservice.entity.Account;
import com.nsld.snekkanti.accountservice.request.AccountRequest;
import com.nsld.snekkanti.accountservice.response.AccountResponse;
import com.nsld.snekkanti.accountservice.service.AccountService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=AccountServiceApplication.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private String ACCOUNTS_SERVICE_URI="/accounts-project/rest/account/json";

    @Test
    public void whenGetAllAccounts_OK() throws Exception{
        // Mock getAccounts Service method
        Account account1 = Account.builder().id(1).firstName("firstName1")
                .secondName("secondName1")
                .accountNumber(123).build();
        Account account2 = Account.builder().id(2).firstName("firstName2")
                .secondName("secondName2")
                .accountNumber(124).build();
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        Mockito.when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void whenGetAllAccounts_NotFound() throws Exception{
        mockMvc.perform(get(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAddAccount_OK() throws Exception{
        AccountRequest accountRequest = AccountRequest.builder().firstName("Suresh").secondName("Nekkanti").accountNumber(456).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountRequestjson = objectMapper.writeValueAsString(accountRequest);

        // when
        AccountResponse accountResponse = AccountResponse.builder().message(Constants.ACCOUNT_ADDED_SUCCESSFULLY)
                .status(HttpStatus.OK).build();
        Mockito.when(accountService.addAccount(accountRequest)).thenReturn(accountResponse);
        mockMvc.perform(post(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON).content(accountRequestjson))
                .andExpect(status().isOk()).andExpect(jsonPath("$.message", Is.is(accountResponse.getMessage())));
    }

    @Test
    public void whenAddAccount_InternalServerError() throws Exception{
        AccountRequest accountRequest = AccountRequest.builder().firstName("Suresh").secondName("Nekkanti").accountNumber(456).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountRequestjson = objectMapper.writeValueAsString(accountRequest);

        // when
        AccountResponse accountResponse = AccountResponse.builder().message(Constants.INTERNAL_SERVER_ERROR)
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        Mockito.when(accountService.addAccount(accountRequest)).thenReturn(accountResponse);
        mockMvc.perform(post(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON).content(accountRequestjson))
                .andExpect(status().isInternalServerError()).andExpect(jsonPath("$.message", Is.is(accountResponse.getMessage())));
    }

    @Test
    public void whenDeleteAccount_OK() throws Exception{

        String DELETE_SERVICE_URI= ACCOUNTS_SERVICE_URI + "/1";

        // when
        AccountResponse accountResponse = AccountResponse.builder().message(Constants.ACCOUNT_DELETED_SUCCESSFULLY)
                .status(HttpStatus.OK).build();
        Mockito.when(accountService.deleteAccount(1)).thenReturn(accountResponse);
        mockMvc.perform(delete(DELETE_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.message", Is.is(accountResponse.getMessage())));
    }

    @Test
    public void whenDeleteAccount_Entity_Not_found() throws Exception{

        String DELETE_SERVICE_URI= ACCOUNTS_SERVICE_URI + "/1";

        // when
        mockMvc.perform(delete(DELETE_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message", Is.is(Constants.ACCOUNT_NOT_FOUND)));
    }

}
