package com.thienloc.springboot.lab_6.Interceptor;

import com.thienloc.springboot.lab_6.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
// HandlerInterceptor giống như cái "Trạm kiểm soát" hoặc bác bảo vệ tòa nhà.
  /*  Trước khi một yêu cầu (Request) từ người dùng được phép chạy
    vào hàm xử lý chính (Controller) của bro, nó phải đi qua ông "bảo vệ" này.*/

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        session.setAttribute("securityUri", uri);
        
        System.out.println("AuthInterceptor: Checking URI: " + uri);
        
        Account user = (Account) session.getAttribute("user");
        if(user == null) { // chưa đăng nhập
            System.out.println("AuthInterceptor: User not logged in, redirecting to login");
            response.sendRedirect("/auth/login");
            return false;
        }
        
        System.out.println("AuthInterceptor: User logged in: " + user.getUsername() + ", Admin: " + user.getAdmin());
        
        if(uri.startsWith("/admin") && !user.getAdmin()) { // không phải admin
            System.out.println("AuthInterceptor: User is not admin, access denied");
            response.sendRedirect("/auth/login");
            return false;
        }
        
        System.out.println("AuthInterceptor: Access granted");
        return true;
    }
}
