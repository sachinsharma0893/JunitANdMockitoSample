package com.example.multitenancy.configurations;

public class TenantContext {

	private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

	public static String getCurrentTenant() {
		return currentTenant.get();
	}

	public static void setCurrentTenant(String tenant) {
		currentTenant.set(tenant);
		System.out.println("Current Tenant is ************"+ currentTenant.get());
	}

	public static void clear() {
		currentTenant.set(null);
	}
}
