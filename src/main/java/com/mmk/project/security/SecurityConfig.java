package com.mmk.project.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
//	 @Autowired
//	 private DataSource dataSource;
//	 
//	 @Bean
//	 @ConfigurationProperties("spring.datasource")
//	 public DataSource ds() {
//		 return DataSourceBuilder.create().build();
//	 }
	
	@Autowired
	UserDetailsService userDetailService;
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception
	 {
        http//.csrf().disable()
        	.httpBasic()
        	.and()
        	.authorizeRequests()
            .anyRequest().authenticated()
            ;
	 }
	 
	 @Autowired
	 UserDetailsService userDetailsService;
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 auth.userDetailsService(userDetailsService);
	 }
	 
	
	 @SuppressWarnings("deprecation")
	@Bean
	 public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		 
	 }
	 
	 

//	 @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.jdbcAuthentication().dataSource(dataSource)
//          //  .authoritiesByUsernameQuery("select username, auth_id from account where username = ?");
//           .usersByUsernameQuery("select username, auth_id from account where username=?");
//    }
    
//    @Bean
//    @Lazy(true)
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
////	--Basic Http authentication : checked via postman save or application.prop save
//    @Override
//    protected void configure(HttpSecurity http) throws Exception 
//    {
//        http
//         .formLogin().disable()
//         .csrf().disable()
//         .authorizeRequests().anyRequest().authenticated()
//         .and()
//         .httpBasic();
//    }
  //  -- Allow some urls like /actuators/
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin().disable()
//            .csrf().disable()
//            .httpBasic()
//            .and()
//            .authorizeRequests()
//            .antMatchers("/actuator/**").permitAll()
//            .anyRequest().authenticated();
//    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("admin01")
//            .password("{noop}admin01@123#")
//            .roles("ADMIN")
// 
//    }
	  
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource);
//    }
//    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) 
//            throws Exception 
//    {
//        auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password("{noop}password")
//            .roles("USER");
//    }
}