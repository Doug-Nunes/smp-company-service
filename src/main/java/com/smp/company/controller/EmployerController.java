package com.smp.company.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smp.company.model.Employer;
import com.smp.company.service.EmployerService;

@RestController
@RequestMapping("/employees")
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	
	@PostMapping
	public ResponseEntity<Employer> save(@RequestBody final Employer employer) throws Exception{
		Employer saved = this.employerService.save(employer);		
		return ResponseEntity.ok(saved);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employer> findById(@PathVariable Long id){
		Optional<Employer> employer = this.employerService.findById(id);
		return employer.map(er -> ResponseEntity.ok(er)).orElse(ResponseEntity.notFound().build());
	}
		
	@GetMapping("/getSaldoCorrente/{cpf}")
	public ResponseEntity<BigDecimal> getSaldoCorrente(@PathVariable String cpf){
		BigDecimal saldoCorrente = this.employerService.findSaldoCorrenteByCpf(cpf);		
		return ResponseEntity.ok(saldoCorrente);
	}
	
	
	@PostMapping("/pagamento")	
	public ResponseEntity<List<Employer>> pagamento(@RequestParam List<Long> ids){
		List<Employer> list = this.employerService.pagamento(ids);
		return ResponseEntity.ok(list);
	}
}
