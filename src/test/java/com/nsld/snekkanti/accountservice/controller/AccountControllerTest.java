package com.nsld.snekkanti.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsld.snekkanti.accountservice.request.AccountRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String ACCOUNTS_SERVICE_URI="/accounts-project/rest/account/json";

    @Test
    public void whenGetAllAccounts_OK() throws Exception{
        mockMvc.perform(get(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetAllAccounts_NotFound() throws Exception{
        mockMvc.perform(get(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenAddAccount_OK() throws Exception{
        AccountRequest accountRequest = AccountRequest.builder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountRequestjson = objectMapper.writeValueAsString(accountRequest);

        // when
        mockMvc.perform(post(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON).content(accountRequestjson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenAddAccount_InternalServerError() throws Exception{
        AccountRequest accountRequest = AccountRequest.builder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        String accountRequestjson = objectMapper.writeValueAsString(accountRequest);

        // when
        mockMvc.perform(post(ACCOUNTS_SERVICE_URI)
                .contentType(APPLICATION_JSON).content(accountRequestjson))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void whenDeleteAccount_OK() throws Exception{

        String DELETE_SERVICE_URI= ACCOUNTS_SERVICE_URI + "/1";

        // when
        mockMvc.perform(delete(DELETE_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenDeleteAccount_InternalServerError() throws Exception{

        String DELETE_SERVICE_URI= ACCOUNTS_SERVICE_URI + "/1";

        // when
        mockMvc.perform(delete(DELETE_SERVICE_URI)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
