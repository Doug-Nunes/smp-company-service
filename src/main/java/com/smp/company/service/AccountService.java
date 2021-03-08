package com.smp.company.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smp.company.model.Account;
import com.smp.company.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account update(BigDecimal saldo, long id) {
		return this.accountRepository.findById(id).map(cc ->{
			cc.setSaldo(saldo);
			return this.accountRepository.save(cc);
		}).orElseThrow(() -> new DataIntegrityViolationException("Account not found!"));
	}
	
}
