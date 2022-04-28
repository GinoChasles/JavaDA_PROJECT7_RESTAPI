//package com.nnk.springboot.security;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * The type Spring security config.
// */
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//  @Override
//  public void configure(final HttpSecurity http) throws Exception {
//    http
//        .httpBasic();
//    http
//        .authorizeRequests()
////        .antMatchers("/user", "/transfer/saveTransaction", "/transfer", "/home", "/user/profile", "/bankAccount", "/user/contact").hasRole("USER")
//        .antMatchers("/*").permitAll()
//        .antMatchers("/admin").hasRole("ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .cors().and().csrf().disable()
//        .formLogin()
//        .loginPage("/login")
//        .permitAll()
//        .failureUrl("/")
//        .defaultSuccessUrl("/home", true)
//        .and()
//        .logout()
//        .logoutUrl("/logout")
//        .logoutSuccessUrl("/")
//        .clearAuthentication(true)
//        .invalidateHttpSession(true)
//        .deleteCookies("JSESSIONID")
//        .permitAll()
//        .and()
//        .rememberMe().key("UniqueKey").tokenValiditySeconds(3600);
//  }
//
////  @Bean
////  public UserDetailsService userDetailsService() {
////    return new CustomUserDetailsService();
////  }
//
//  /**
//   * Authentication provider dao authentication provider.
//   *
//   * @return the dao authentication provider
//   */
//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService());
//    authProvider.setPasswordEncoder(passwordEncoder());
//
//    return authProvider;
//  }
//
//  @Override
//  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authenticationProvider());
//  }
//
//  /**
//   * Password encoder password encoder.
//   *
//   * @return the password encoder
//   */
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//}
//
package com.nnk.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nnk.springboot.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userDetailsService());
    auth.setPasswordEncoder(passwordEncoder());
    return auth;
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
        .authorizeRequests()
        .antMatchers("/css/**", "/static/**", "/favicon.ico").permitAll()
        .antMatchers("/", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/user/**", "/admin/home").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .defaultSuccessUrl("/bidList/list")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/app-logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .permitAll();
  }
}