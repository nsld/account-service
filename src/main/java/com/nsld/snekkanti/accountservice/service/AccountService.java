package com.nsld.snekkanti.accountservice.service;

import com.nsld.snekkanti.accountservice.common.Constants;
import com.nsld.snekkanti.accountservice.entity.Account;
import com.nsld.snekkanti.accountservice.repository.AccountRepository;
import com.nsld.snekkanti.accountservice.request.AccountRequest;
import com.nsld.snekkanti.accountservice.response.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }


    public AccountResponse addAccount(AccountRequest request){

        if(request  == null || !StringUtils.hasText(request.getFirstName())
                            || !StringUtils.hasText(request.getSecondName())
                            || StringUtils.isEmpty(request.getAccountNumber())){
            return AccountResponse.builder().message(Constants.INVALID_ACCOUNT_ADD_REQUEST)
                    .status(HttpStatus.BAD_REQUEST).build();
        }
        Account account = Account.builder().firstName(request.getFirstName())
                                           .secondName(request.getSecondName())
                                           .accountNumber(request.getAccountNumber()).build();
        Account savedAccount = accountRepository.save(account);
        AccountResponse response = new AccountResponse();
        if(savedAccount != null){
            response.setMessage(Constants.ACCOUNT_ADDED_SUCCESSFULLY);
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage(Constants.INTERNAL_SERVER_ERROR);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public AccountResponse deleteAccount(int id) throws Exception{
        accountRepository.deleteById(id);
        return AccountResponse.builder().message(Constants.ACCOUNT_DELETED_SUCCESSFULLY)
                .status(HttpStatus.OK).build();
    }
}
