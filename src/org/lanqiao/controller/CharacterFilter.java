package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//1、初使化过滤器(init);2、拦截请求(doFilter)3放释资源(destroy)
public class CharacterFilter implements Filter{
	private FilterConfig config = null;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//获取初使化参数
		String charset = config.getInitParameter("charset");
		//1、设置服务器处理的字符编码;
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
//		System.out.println("被我拦截了........");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
