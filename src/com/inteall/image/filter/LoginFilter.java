package com.inteall.image.filter;


import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inteall.image.pojo.SysUser;


public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession(true);

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        System.out.println(path);

        // 从session里取用户信息
        SysUser sysUser =  (SysUser) session.getAttribute("SysUser");

        // 判断如果没有取到用户信息,就跳转到登陆页面
        if (sysUser == null || "".equals(sysUser)) {
        	
        	try {
        		if(path.indexOf("loginFind.do") > -1) {
		            chain.doFilter(servletRequest, servletResponse);
		            return;
		        }
		        if(path.indexOf("loginCheck.do") > -1) {
		            chain.doFilter(servletRequest, servletResponse);
		            return;
		        }
		        
    		        
				// 跳转到登陆页面
	            servletResponse.sendRedirect(servletRequest.getContextPath() + "/loginFind.do");
	            return;
    		} catch (SocketException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (UnknownHostException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        } else {
            
            chain.doFilter(servletRequest, servletResponse);
            
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}