package com.micro.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.micro.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

@Repository
public interface AccountSRepository extends JpaRepository<Accounts, Long> {
	
	public Optional<Accounts> findByCustomerId(Long id);
	
	@Transactional @Modifying
	void deleteByCustomerId(Long customerId);

}
