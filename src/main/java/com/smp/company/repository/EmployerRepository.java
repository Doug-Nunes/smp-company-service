package com.smp.company.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smp.company.model.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

	boolean existsByCpf(String cpf);

	@Query("select ac.saldo from Employer er "
			+ "inner join Account ac on er.conta.id = ac.id  "
			+ "where er.cpf = :cpf and "
			+ "er.deleteDate is null")
	BigDecimal findSaldoCorrenteByCpf(String cpf);

}
