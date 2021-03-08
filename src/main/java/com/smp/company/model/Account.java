package com.smp.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
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
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -484715456889312366L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate; 
    
    @Size(min = 4, max = 20)
    @Column(name="AGENCIA")
    private String agencia;
    
    @Column(name="SALDO")
    private BigDecimal saldo;
    
    @PrePersist
    void prePersist() {
    	this.createDate = LocalDateTime.now();
    }
    
    @PreUpdate
    void preUpdate() {
    	this.updateDate = LocalDateTime.now();
    }

}
