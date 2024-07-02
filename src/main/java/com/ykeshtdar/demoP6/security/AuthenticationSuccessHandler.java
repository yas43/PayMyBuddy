package com.ykeshtdar.demoP6.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;

import java.io.*;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        boolean isUser =authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));

        if (isUser){
            setDefaultTargetUrl("/user/welcome");
        }
        else {
            setDefaultTargetUrl("/user/welcome");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
