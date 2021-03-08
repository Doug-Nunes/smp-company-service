package com.smp.company.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smp.company.model.Company;
import com.smp.company.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	public Company save(Company company) {
		boolean exists = this.companyRepository.existsByNomeFantasiaIgnoreCaseOrCnpj(company.getNomeFantasia(), company.getCnpj());
		if(!exists) {
			return this.companyRepository.save(company);
		}else {
			throw new DataIntegrityViolationException("Razão Social or Cnpj alread exists! Make sure the data is correct");
		}
	}

	public BigDecimal findSaldoCorrenteByCnpj(String cnpj) {
		return this.companyRepository.findSaldoCorrenteByCnpj(cnpj);
	}

	public Optional<Company> findById(Long id) {
		return this.companyRepository.findById(id);
	}	

}
