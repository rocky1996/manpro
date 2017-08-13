package com.cal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;

import com.cal.entity.User;
import com.cal.service.UserService;
import com.cal.service.impl.UserServiceImpl;
import com.cal.util.MD5Util;

public class AutoLoginFilter implements Filter {
	@Autowired
	private UserService us = new UserServiceImpl();

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("u");
		
		if(u==null){
			//获取名为loginInfor的cookie
			Cookie loginInforCookie = null;//因为cookie只能遍历
			Cookie cs[] = request.getCookies();
			for(int i=0;cs!=null && i<cs.length;i++){
				if("loginInfor".equals(cs[i].getName())){
					loginInforCookie = cs[i];
				}
				break;
			}
			
			if(loginInforCookie != null){
				//有，取出cookie的值:用户名和加密的密码
				String AccountPassword = loginInforCookie.getValue();
				
				//拆开用户名和加密的密码
				String account = AccountPassword.split("\\_")[0];
				account = new String(new BASE64Decoder().decodeBuffer(account));
				String cookiePassword = AccountPassword.split("\\_")[1];//加密后的密码
				
				//再次验证用户名和密码是否正确(根据用户名查出密码来，加密后再与cookie中的那个密码进行比对)
				User user = us.findaccount(account);
				if(user != null){
					//根据用户名查出密码来，加密后再与cookie中的那个密码进行比对
					if(cookiePassword.equals(MD5Util.md5(user.getPassword()))){
						//正确:得到用户对象，放在HttpSession中(自动登录)
						session.setAttribute("u", user);
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
