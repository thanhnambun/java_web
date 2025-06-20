package ra.edu.project.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        // Kiểm tra session có tồn tại và có role là ADMIN không
        if (session != null && "ADMIN".equals(session.getAttribute("role"))) {
            return true; // Cho phép đi tiếp
        }

        // Nếu không phải admin -> redirect về trang chủ hoặc báo lỗi
        response.sendRedirect(request.getContextPath() + "/access-denied");
        return false;
    }
}
