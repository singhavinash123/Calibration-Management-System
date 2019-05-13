package com.spiraxcalibration.webSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spiraxcalibration.WebConfig.AppsPropertyFile;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	AppsPropertyFile  appsPropertyFile = new AppsPropertyFile();
	final String dBName =  appsPropertyFile.getQueryForKey("dbName");
	
	String adminRole = appsPropertyFile.getUserDetail("spring.role.admin");
	String userRole = appsPropertyFile.getUserDetail("spring.role.user");
	String managerRole = appsPropertyFile.getUserDetail("spring.role.manager");
	String engineerRole = appsPropertyFile.getUserDetail("spring.role.engineer");
	String manufacture_associatesRole = appsPropertyFile.getUserDetail("spring.role.manufacturing_associates");
	String approver1Role = appsPropertyFile.getUserDetail("spring.role.approver1");
	String approver2Role = appsPropertyFile.getUserDetail("spring.role.approver2");
	String procurement_engineerRole = appsPropertyFile.getUserDetail("spring.role.procurement_engineer");

	@Autowired
	DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


	protected void configure(HttpSecurity http) throws Exception {

		http.
		authorizeRequests()
		.antMatchers("/lovMaintenance").hasAuthority(adminRole)
		.antMatchers("/userMaintenance").hasAuthority(adminRole)
		.antMatchers("/addUser").hasAuthority(adminRole)
		.antMatchers("/saveRole").hasAuthority(adminRole)
		.antMatchers("/saveUser").hasAuthority(adminRole)
		.antMatchers("/searchcalibequipment/**").permitAll()
		.antMatchers("/deleteUser/**").hasAuthority(adminRole)
		.antMatchers("/addRole/**").hasAuthority(adminRole)
		.antMatchers("/searchUser").hasAuthority(adminRole)
		.antMatchers("/save_Approval1").hasAuthority(adminRole)
		.antMatchers("/save_Approval2").hasAuthority(adminRole)
		.antMatchers("/save_Approval1").hasAuthority(approver1Role)
		.antMatchers("/save_Approval2").hasAuthority(approver2Role)
		.antMatchers("/login").permitAll()
		.antMatchers("/403").permitAll()
		.antMatchers("/forgotpassword").permitAll()
		.antMatchers("/passwordform").permitAll()
		.antMatchers("/scannedProductDetails/**").permitAll()
		.antMatchers("/reset").permitAll()
		.antMatchers("/reset_password").permitAll()
		.anyRequest()
		.authenticated().and().formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/firstpage")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().exceptionHandling()
		.accessDeniedPage("/403");
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**", "/customcss/**" , "/bootstrap/**" , "/custom/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}