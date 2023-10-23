package com.qlBanGiay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.qlBanGiay.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth.requestMatchers("/*").permitAll().
//				requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()).
//				formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").
//				usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin",true));
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth.requestMatchers("/*").permitAll().requestMatchers("/cartItemApi/**").permitAll().
				requestMatchers("/detail/**").permitAll().requestMatchers("/list/**").permitAll().requestMatchers("/cart/**").permitAll().
				requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()).
				formLogin(login -> login.loginPage("/logon").loginProcessingUrl("/logon").
					usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin",true)).
					logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logout").deleteCookies("JSESSIONID")).
				formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login").
					usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/",true)).
					logout(logout -> logout.logoutUrl("/user-logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID"));
		return http.build();
	}

	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/static/**","/fe/**","/asset/**","/uploads/**");
	}
}
