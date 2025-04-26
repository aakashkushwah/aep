package com.archexc.acctmgmtservice.repository;

import com.archexc.acctmgmtservice.entity.ReceiverAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverAccountRepository extends JpaRepository<ReceiverAccount, Long> {
}
