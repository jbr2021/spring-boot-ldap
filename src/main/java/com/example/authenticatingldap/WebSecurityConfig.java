package com.example.authenticatingldap;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .anyRequest()
      .fullyAuthenticated()
      .and()
      .formLogin();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .ldapAuthentication()
      .userSearchFilter("sAMAccountName={0}") //(sAMAccountName={0})
	  .userDnPatterns("sAMAccountName={0}")
      .userSearchBase("CN=users,DC=i12bretro,DC=local")//CN=users,
      .groupSearchBase("cn=users,dc=i12bretro,dc=local")
      //.groupSearchFilter("member={0}")
      .contextSource()
      .url("ldap://i12bretro.local")
      .port(389)
      .managerDn("CN=hackathon,CN=users,DC=i12bretro,DC=local")//ldapuser
      .managerPassword("hackathon@201001");//ldap@123

    // .ldapAuthentication()
    // 	.userDnPatterns("uid={0},ou=people")
    // 	.groupSearchBase("ou=groups")
    // 	.contextSource()
    // 		.url("ldap://localhost:8389/dc=springframework,dc=org")
    // 		.and()
    // 	.passwordCompare()
    // 		.passwordEncoder(new BCryptPasswordEncoder())
    // 		.passwordAttribute("userPassword");
  }
}
