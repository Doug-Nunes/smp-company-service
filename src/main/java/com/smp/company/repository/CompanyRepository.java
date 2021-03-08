package com.smp.company.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smp.company.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("select ac.saldo from Company cy "
			+ "inner join Account ac on cy.conta.id = ac.id  "
			+ "where cy.cnpj = :cnpj and "
			+ "cy.deleteDate is null")
	BigDecimal findSaldoCorrenteByCnpj(String cnpj);

	boolean existsByNomeFantasiaIgnoreCaseOrCnpj(String nomeFantasia, String cnpj);

}
