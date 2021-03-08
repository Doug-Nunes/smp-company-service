package com.smp.company.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smp.company.model.Company;
import com.smp.company.model.Employer;
import com.smp.company.repository.EmployerRepository;

@Service
public class EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AccountService accountService;
	
	public Employer save(Employer employer)  throws Exception {
		boolean exists = this.employerRepository.existsByCpf(employer.getCpf());
		if(!exists) {
			if(employer.getCompany() != null && employer.getCompany().getId() != null) {
				Optional<Company> optCompany = this.companyService.findById(employer.getCompany().getId()); 
				
				employer.setCompany(optCompany.get());
				
				return this.employerRepository.save(employer);	
			}else {
				throw new DataIntegrityViolationException("Company needs to be registered!");	
			}					
		}else {
			throw new DataIntegrityViolationException("CPF alread register! Make sure the data is correct");
		}
	}

	public Optional<Employer> findById(Long id) {
		return this.employerRepository.findById(id);
	}

	public BigDecimal findSaldoCorrenteByCpf(String cpf) {
		return this.employerRepository.findSaldoCorrenteByCpf(cpf);
	}

	public List<Employer> pagamento(List<Long> ids) {
		List<Employer> list = new ArrayList<>();
		ids.stream().forEach(id -> {			
			findById(id).ifPresent(er-> {
				BigDecimal empresaSaldo = this.companyService.findSaldoCorrenteByCnpj(er.getCompany().getCnpj());
				BigDecimal funcSaldo  = findSaldoCorrenteByCpf(er.getCpf());
				BigDecimal totalFolhaFunc = er.getSalario().subtract(er.getSalario().multiply(new BigDecimal("0.0038")));
				
				BigDecimal newEmpresaSaldo = empresaSaldo.subtract(totalFolhaFunc);
				BigDecimal newFuncSaldo = funcSaldo.add(totalFolhaFunc);
				
				er.getCompany().setConta(this.accountService.update(newEmpresaSaldo, er.getCompany().getConta().getId()));
				er.setConta(this.accountService.update(newFuncSaldo, er.getConta().getId()));
				
				list.add(er);
			});			

		});
		return list;
	}
}
