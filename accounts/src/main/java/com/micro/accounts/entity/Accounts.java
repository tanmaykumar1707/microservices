package com.micro.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Accounts extends BaseEntity {
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Id
	private Long accountNumber;
	
	private String accountType;
	
	private String branchAddress;

}
