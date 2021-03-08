package com.smp.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Audited
@Entity
@Data
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "EMPLOYEES")
public class Employer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5213509402325022023L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate; 
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="COMPANY_ID", referencedColumnName="id",nullable=false) 
    private Company company;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTA_ID", referencedColumnName = "ID")
    private Account conta;

    @Size(max = 20)
    @Column(name = "NOME_CURTO")
    private String nomeCurto;    
   
    @NotNull
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;
    
    @NotNull
    @Size(max = 11)
    @Column(name = "CPF")
    private String cpf;
    
    @Column(name="DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    
    @Column(name="SALARIO")
    private BigDecimal salario;
    
    @Column(name="DATA_ADMISSAO")
    private LocalDate dataAdmissao;   
    
    @PrePersist
    void prePersist() {
    	this.createDate = LocalDateTime.now();
    }
    
    @PreUpdate
    void preUpdate() {
    	this.updateDate = LocalDateTime.now();
    }
}
