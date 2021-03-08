package com.smp.company.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smp.company.model.Company;
import com.smp.company.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping
    public ResponseEntity<Company> save(@RequestBody final Company company){
		Company saved = this.companyService.save(company);		
		return ResponseEntity.ok(saved);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable Long id){
		Optional<Company> company = this.companyService.findById(id);
		return company.map(cp -> ResponseEntity.ok(cp)).orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/getSaldoCorrente/{cnpj}")
	public ResponseEntity<BigDecimal> getSaldoCorrente(@PathVariable String cnpj){
		BigDecimal saldoCorrente = this.companyService.findSaldoCorrenteByCnpj(cnpj);		
		return ResponseEntity.ok(saldoCorrente);
	}
	
}
