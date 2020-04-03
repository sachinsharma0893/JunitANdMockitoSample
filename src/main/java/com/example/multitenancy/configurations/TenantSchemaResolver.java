package com.example.multitenancy.configurations;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver{

	private static final String DEFAULT_TENANT = "mulititenancy";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String t = TenantContext.getCurrentTenant();
		if (t != null) {
			return t;
		} else {
			return DEFAULT_TENANT;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
