package com.nsld.snekkanti.accountservice.repository;

import com.nsld.snekkanti.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAll();
    Account save(Account account);
    void deleteById(Integer integer);
    Boolean existsByAccountNumber(Integer accountNumber);
}
