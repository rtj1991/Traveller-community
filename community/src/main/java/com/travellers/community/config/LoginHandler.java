package com.travellers.community.config;

import com.travellers.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Transactional
public class LoginHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            com.travellers.community.model.User userSession = userRepository.findByUsername(user.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user", userSession);
            System.out.println("session---> "+session.getAttribute("user"));
//            redirectStrategy.sendRedirect(request, response, "/dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
