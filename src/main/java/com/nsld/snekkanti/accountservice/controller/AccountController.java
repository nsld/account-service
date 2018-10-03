package com.nsld.snekkanti.accountservice.controller;

import com.nsld.snekkanti.accountservice.request.AccountRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts-project/rest/account/json")
public class AccountController {

    @ApiOperation("Get All Accounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gets All Accounts"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAllAccounts() {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Create Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account has been successfully added"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addAccount(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("Delete Account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account has been deleted successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable int id) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
