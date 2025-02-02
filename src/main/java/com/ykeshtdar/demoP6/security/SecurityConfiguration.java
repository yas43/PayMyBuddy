package com.ykeshtdar.demoP6.security;

import com.ykeshtdar.demoP6.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfiguration(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      return   httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        registry->{
                                   registry.requestMatchers("user/signUp","user/welcome","/registry/user","registry/{userId}/friend/{friendId}","registry/{senderId}/receiver/{receiverId}","registry/{senderId}/alltransaction/{receiverId}").permitAll();
//                                   registry.requestMatchers("/user/welcome").hasRole("USER");
                                   registry.anyRequest().authenticated();
        })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                            httpSecurityFormLoginConfigurer
                                    .loginPage("/user/login")
                                    .failureUrl("/user/login?error=true")
                                    .defaultSuccessUrl("/user/logIn",true)
                                    .loginProcessingUrl("/login")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .successHandler(new AuthenticationSuccessHandler())
                                    .permitAll();
                })
              .logout(httpSecurityLogoutConfigurer -> {
                  httpSecurityLogoutConfigurer
                          .logoutSuccessUrl("/user/login?logout=true")
                          .deleteCookies("JSESSIONID")
                          .invalidateHttpSession(true)
                          .permitAll();
              })
              .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
//    @Bean
//    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//                .userDetailsService(customUserDetailService)
//                .passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();

//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
