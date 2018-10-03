package com.nsld.snekkanti.accountservice.controller;

import com.nsld.snekkanti.accountservice.common.Constants;
import com.nsld.snekkanti.accountservice.entity.Account;
import com.nsld.snekkanti.accountservice.request.AccountRequest;
import com.nsld.snekkanti.accountservice.response.AccountResponse;
import com.nsld.snekkanti.accountservice.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts-project/rest/account/json")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation("Get All Accounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.GET_ACCOUNTS),
            @ApiResponse(code = 404, message = Constants.ACCOUNTS_NOT_FOUND)})
    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        if(accounts == null || accounts.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }

    }

    @ApiOperation("Create Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.ACCOUNT_ADDED_SUCCESSFULLY),
            @ApiResponse(code = 400, message = Constants.INVALID_ACCOUNT_ADD_REQUEST),
            @ApiResponse(code = 500, message = Constants.INTERNAL_SERVER_ERROR)})
    @GetMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResponse> addAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.addAccount(accountRequest);
        return new ResponseEntity<>(accountResponse, accountResponse.getStatus());
    }

    @ApiOperation("Delete Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.ACCOUNT_DELETED_SUCCESSFULLY),
            @ApiResponse(code = 404, message = Constants.ACCOUNT_NOT_FOUND)})
    @GetMapping
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable int id) {
        try {
            AccountResponse accountResponse = accountService.deleteAccount(id);
            return new ResponseEntity<>(accountResponse, accountResponse.getStatus());
        }catch(Exception e){
            AccountResponse accountResponse = AccountResponse.builder().message(Constants.ACCOUNT_NOT_FOUND)
                    .status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<>(accountResponse, accountResponse.getStatus());
        }
    }
}
