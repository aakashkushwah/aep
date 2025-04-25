package com.archexc.acctmgmtservice.repository;

import com.archexc.acctmgmtservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByCustomerId, findByAccountType, etc.
}
