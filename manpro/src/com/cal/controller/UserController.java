package com.cal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.mail.Multipart;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.cal.entity.User;
import com.cal.formbean.LoginFormBean;
import com.cal.formbean.UserFormBean;
import com.cal.service.UserService;
import com.cal.service.impl.UserServiceImpl;
import com.cal.util.MD5Util;
import com.cal.util.WebUtil;
import com.sun.mail.util.BASE64EncoderStream;

@Controller
@RequestMapping("/user")

public class UserController {
	@Autowired
	private UserService us = new UserServiceImpl();
	
	@RequestMapping("/skip1")
	public String skip1(){
		return "manager/register";
	}
	
	@RequestMapping("/skip2")
	public String skip2(){
		return "manager/login";
	}
	
	@RequestMapping("/skip3")
	public String skip3(){
		return "manager/welcome";
	}
	
	//用户注册
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		User u = new User();
		UserFormBean formbean = new UserFormBean();
		formbean = WebUtil.file(UserFormBean.class, request);
		
		if(!formbean.vaildate()){
			model.addAttribute("formbean", formbean);
			return "manager/register";
		}
		
		try{
			BeanUtils.copyProperties(u, formbean);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		us.register(u);
		model.addAttribute("uu", "<script type='text/javascript'>alert('注册成功!!!')</script>");
		return "manager/login";
	}
	
	//用户登录
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
//		System.out.println("*********");
//		System.out.println(account);
//		System.out.println(password);
//		System.out.println("*********");
		User u = us.login(account, password);
		if(u==null){
			model.addAttribute("uu", "<script type='text/javascript'>alert('用户名或者密码错误!!!')</script>");
			return "manager/login";
		}else{		
			request.getSession().setAttribute("u", u);
			String autologin = request.getParameter("autologin");
			if(autologin != null){
				//把用户名放到一个指定的cookie里面
				Cookie c = new Cookie("loginInfor",new BASE64Encoder().encode(account.getBytes())+"_"+MD5Util.md5(password));
				c.setMaxAge(Integer.MAX_VALUE);
				c.setPath(request.getContextPath());
				response.addCookie(c);
			}
			return "manager/welcome";
		}
 	}
	
	//用户注销
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		request.getSession().invalidate();
		return "manager/welcome";
	}
}
