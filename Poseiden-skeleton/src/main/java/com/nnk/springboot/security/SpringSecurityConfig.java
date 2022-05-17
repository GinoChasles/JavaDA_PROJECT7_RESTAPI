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

import com.nnk.springboot.service.CustomOAuth2User;
import com.nnk.springboot.service.CustomUserDetailsService;
import com.nnk.springboot.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private Oauth2Service oauth2Service;

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(userDetailsService());
    auth.setPasswordEncoder(passwordEncoder());
    return auth;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.authenticationProvider(authenticationProvider());
    auth.inMemoryAuthentication();
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.cors().and().csrf().disable();
    http
            .authorizeRequests()
            .antMatchers("/img/**").permitAll()
            .antMatchers("/register/**").permitAll()
            .antMatchers("/login/**").permitAll()
            .antMatchers("/validate").permitAll()
            .antMatchers("/css/**", "/static/**", "/favicon.ico").permitAll()
            .antMatchers("/", "/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/user/**", "/admin/home").hasAuthority("ADMIN")
            .antMatchers("/oauth2/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .loginPage("/app/login")
              .defaultSuccessUrl("/bidList/list")
            .and()
            .oauth2Login()
            .loginPage("/app/login")
              .userInfoEndpoint()
                .userService(oauth2Service)
            .and()
            .defaultSuccessUrl("/bidList/list")
            .permitAll()
    ;
//    http
//              .logout()
//              .logoutUrl("/app-logout")
//              .logoutSuccessUrl("/")
//              .deleteCookies("JSESSIONID")
//              .invalidateHttpSession(true)
//              .clearAuthentication(true)
//              .permitAll()
//
//    ;
//    http.authorizeRequests()
//            .antMatchers("/", "/login").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().permitAll()
//            .loginPage("/login")
//            .and()
//            .oauth2Login()
//            .loginPage("/login")
//            .userInfoEndpoint()
//            .userService(oauth2Service)
//            .and()
//            .logout().logoutSuccessUrl("/").permitAll();
  }
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
  }
}