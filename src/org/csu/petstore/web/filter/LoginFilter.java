package org.csu.petstore.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.强制转换
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //2.获取请求资源路径
        String requestURI = request.getRequestURI();

        //3.判断是否包含登录相关资源路径,同时排除css,js，图片等
        if (requestURI.equals("/MyPetStore_war_exploded/") || requestURI.contains("/verifyCodeImage") || requestURI.contains("/main") || requestURI.contains("/signIn") || requestURI.contains("/signUp") || requestURI.contains("/static")) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            //4.判断是否登录
            Object user = request.getSession().getAttribute("account");
            if (user != null) {
                //已登录，放行
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                //未登录，跳转登陆页面
                request.setAttribute("msg","Please sign in first");
                request.getRequestDispatcher("/WEB-INF/jsp/account/signIn.jsp").forward(request,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
