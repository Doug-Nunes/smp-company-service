package com.smp.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smp.company.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
}
