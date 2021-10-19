package com.carrental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.carrental.repositories.UserRepository;
import com.carrental.security.RentalSimpleUrlAuthenticationSucessHandler;
import com.carrental.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("com.carrental.service")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new RentalSimpleUrlAuthenticationSucessHandler();
	}
	
	/**
	 * configuring URL pages and required parameters to access them. Setting up
	 * authentication handler on successful authentication.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/userHome", "/userNewOrder", "/userView")
				.access("hasRole('ROLE_USER')");

		http.authorizeRequests().antMatchers("/adminHome", "/adminCars",
				 "/adminOrders", "/cars", "/cars/**")
				.access("hasRole('ROLE_ADMIN')");		

		http.authorizeRequests().antMatchers("/orders/{orderId}", "/orders")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/cars", "/cars/**").access("hasRole('ROLE_USER')");

		http.authorizeRequests().antMatchers("/", "/home", "/login", "/registration", "/logOut")
				.permitAll();

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin().loginProcessingUrl("/login").loginPage("/login")
				.successHandler(getAuthenticationSuccessHandler()).failureUrl("/login").usernameParameter("login")
				.passwordParameter("passw").and().logout().logoutUrl("/logOut").logoutSuccessUrl("/home");

	}
	/**
	 * Storing access tokens in memory
	 * @return and instance of implementation of PersistenTokenRopository
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	}

}