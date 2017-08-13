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
			//��ȡ��ΪloginInfor��cookie
			Cookie loginInforCookie = null;//��Ϊcookieֻ�ܱ���
			Cookie cs[] = request.getCookies();
			for(int i=0;cs!=null && i<cs.length;i++){
				if("loginInfor".equals(cs[i].getName())){
					loginInforCookie = cs[i];
				}
				break;
			}
			
			if(loginInforCookie != null){
				//�У�ȡ��cookie��ֵ:�û����ͼ��ܵ�����
				String AccountPassword = loginInforCookie.getValue();
				
				//���û����ͼ��ܵ�����
				String account = AccountPassword.split("\\_")[0];
				account = new String(new BASE64Decoder().decodeBuffer(account));
				String cookiePassword = AccountPassword.split("\\_")[1];//���ܺ������
				
				//�ٴ���֤�û����������Ƿ���ȷ(�����û�����������������ܺ�����cookie�е��Ǹ�������бȶ�)
				User user = us.findaccount(account);
				if(user != null){
					//�����û�����������������ܺ�����cookie�е��Ǹ�������бȶ�
					if(cookiePassword.equals(MD5Util.md5(user.getPassword()))){
						//��ȷ:�õ��û����󣬷���HttpSession��(�Զ���¼)
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
