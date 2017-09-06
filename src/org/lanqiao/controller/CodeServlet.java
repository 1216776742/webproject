package org.lanqiao.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "codeServlet", urlPatterns = { "/codeServlet.do" })
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //验证码：动态生成内存中的一张验证图片（图片中显示的是验证字符）
		//1、生成验证字符;
		String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		//创建一个随机器对象
		String codes="";
		StringBuilder builder =new StringBuilder();
		Random rand = new Random();
		for(int i=0;i<4;i++){
			int index = rand.nextInt(61);//[0,61];
			builder.append(chars.charAt(index));
		}
		codes = builder.toString();
		//2、生成一个内存中的图片，在图片中写入验证字符
		//A、创建一个内存中的图片;
		BufferedImage bufferImage = new BufferedImage(90, 30,BufferedImage.TYPE_INT_RGB);
		//B、绘制图片;
		//拿到一个画笔 -->绘制图片及图片内容;
		Graphics g = bufferImage.getGraphics();
		//填充颜色;
		g.setColor(Color.WHITE); //修改画笔颜色为白色;
		g.fillRect(0, 0, 90, 30); //填充白色背景;
		//画边框 ;
		g.setColor(Color.RED);
		g.drawRect(1, 1, 88, 28);
		//在图象上画验证字符
		g.setFont(new Font("宋体",Font.BOLD,20));
		//画验证字符;
		g.drawString(codes, 20, 20);//px
		//画干扰线;
		g.setColor(Color.GREEN);
		for(int i=0;i<10;i++){
			g.drawLine(rand.nextInt(90), rand.nextInt(30), rand.nextInt(90), rand.nextInt(30));
		}
		//将验证字符存到session
		request.getSession().setAttribute("code", codes); //*************************
		//3输出图片;
		//指定图出的为图片格式数据;
		response.setContentType("image/jpeg");
		ImageIO.write(bufferImage, "jpg", response.getOutputStream());
		response.getOutputStream().flush();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
