package com.smp.company.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Audited
@Entity
@Data
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "COMPANIES")
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 581196612004888221L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate;    

    @Column(name = "DATA_ABERTURA")
    private LocalDate dataAbertura;
    
    @NotNull
    @Column(name = "NOME_FANTASIA", unique = true)
    private String nomeFantasia; 
    
    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;    
    
    @NotNull
    @Size(max = 14)
    @Column(name = "CNPJ", unique = true)
    private String cnpj;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTA_ID", referencedColumnName = "ID")
    private Account conta;
 
    @JsonIgnore
    @OneToMany( mappedBy = "company")
    private List<Employer> employees;
    
    @PrePersist
    void prePersist() {
    	this.createDate = LocalDateTime.now();
    }
    
    @PreUpdate
    void preUpdate() {
    	this.updateDate = LocalDateTime.now();
    }
}
