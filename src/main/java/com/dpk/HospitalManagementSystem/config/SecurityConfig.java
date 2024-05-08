package com.dpk.HospitalManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	
	// @Autowired
	// public UserDetailsService geDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		// daoAuthenticationProvider.setUserDetailsService(geDetailsService);
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
		.requestMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
		.requestMatchers("/**").permitAll()
		.and()
		.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/uLogin")
			.successHandler(successHandler)
			.permitAll()
			.and()
		.exceptionHandling()
            .defaultAuthenticationEntryPointFor(adminEntryPoint(), new AntPathRequestMatcher("/admin/**")) // Redirect to /signin(admin) if authentication fails for URLs under /admin/**
            .defaultAuthenticationEntryPointFor(doctorEntryPoint(), new AntPathRequestMatcher("/doctor/**")) // Redirect to /signin(doctor) if authentication fails for URLs under /doctor/**
            .defaultAuthenticationEntryPointFor(userEntryPoint(), new AntPathRequestMatcher("/user/**")) // Redirect to /signin(user) if authentication fails for URLs under /user/**
            .and()
			.logout()
            .logoutSuccessUrl("/userLogout")
            .permitAll();

		
				
		
		return http.build();
	}
	
	@Bean
	public AuthenticationEntryPoint adminEntryPoint() {
	    return new LoginUrlAuthenticationEntryPoint("/signin?admin");
	}

	@Bean
	public AuthenticationEntryPoint doctorEntryPoint() {
	    return new LoginUrlAuthenticationEntryPoint("/signin?doctor");
	}

	@Bean
	public AuthenticationEntryPoint userEntryPoint() {
	    return new LoginUrlAuthenticationEntryPoint("/signin?user");
	}

}
