package org.lanqiao.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "testController", urlPatterns = { "/test.do" })
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8"); 
	    response.setCharacterEncoding("utf-8");
	       String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		System.out.println(userid+","+password+","+email);
		String savePath = "E:\\JavaEE\\form_test\\WebContent\\js";
		Part file = request.getPart("file");
		if(file!=null){
		String header = file.getHeader("content-disposition");
	        String fileName = getFileName(header);  
		file.write(savePath + File.separator +fileName );
		response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	 public String getFileName(String header) {  
	        /** 
	         * String[] tempArr1 = 
	         * header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1��������������������� 
	         * �������google������£� 
	         * tempArr1={form-data,name="file",filename="snmp4j--api.zip"} 
	         * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"} 
	         */  
	        String[] tempArr1 = header.split(";");  
	        /** 
	         * �������google������£�tempArr2={filename,"snmp4j--api.zip"} 
	         * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"} 
	         */  
	        String[] tempArr2 = tempArr1[2].split("=");  
	        // ��ȡ�ļ��������ݸ����������д��  
	        String fileName = tempArr2[1].substring(  
	                tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");  
	        return fileName;  
	    }  
}
