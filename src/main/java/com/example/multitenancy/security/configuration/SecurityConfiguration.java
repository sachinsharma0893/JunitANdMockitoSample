package com.example.multitenancy.security.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.multitenancy.configurations.TenantContext;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("sysadmin").password("admin").roles("LEAD", "ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterAfter(subDomainFilter(), CsrfFilter.class).csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

	private Filter subDomainFilter() {
		return new OncePerRequestFilter() {

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				System.out.println("*************Spring security filter ************" + request.getRequestURL());

				final String[] serverParts = request.getServerName().split("\\.");
				String subdomain = "";
				if (serverParts.length > 2) {
					final int length = serverParts.length - 2;
					for (int i = 0; i < length; i++) {
						subdomain += serverParts[i];
						if (i < length - 1)
							subdomain += ".";
					}
				}
				TenantContext.setCurrentTenant(subdomain);
				filterChain.doFilter(request, response);
			}
		};

	}

}