package com.ykeshtdar.demoP6.security;

import com.ykeshtdar.demoP6.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
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
                                   registry.requestMatchers("/user/welcome","/registry/user","registry/{userId}/friend/{friendId}","registry/{senderId}/receiver/{receiverId}").permitAll();
//                                   registry.requestMatchers("/user/welcome").hasRole("USER");
                                   registry.anyRequest().authenticated();
        })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                            httpSecurityFormLoginConfigurer
                                    .loginPage("/user/login")
                                    .loginProcessingUrl("/user/logIn")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .successHandler(new AuthenticationSuccessHandler())
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

}
