package com.example.graduation_project.configuration;

import com.example.graduation_project.entities.admin.RoleEntity;
import com.example.graduation_project.services.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    RoleService roleService;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        List<RoleEntity> allRole= roleService.getListRole();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            boolean check = false;
            for (RoleEntity role : allRole) {
                if (authority.getAuthority().equals(role.getCode())) {
                    check=true;
                    try {
                        redirectStrategy.sendRedirect(request, response, role.getUrl());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            //nếu quyền có vai trò user, chuyển đến trang "/" nếu login thành công
            if(check) {
                System.out.println("Thanh cong");
            }
            else {
                throw new IllegalStateException();
            }
        });
    }

}
