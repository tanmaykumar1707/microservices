package com.micro.accounts.audit;

import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.of("ACOUNTS_MS");
	}

}
