<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="org.lanqiao.service.OrderDetailService" %>
<%@ page import="org.lanqiao.service.impl.OrderDetailServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.lanqiao.entity.OrderDetail" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="utf-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>
<body class="main">
<%@include file="header.jsp" %>
<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%">
      <%@include file="myacount.jsp" %>
      </td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/dispacher.do?type=account">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单查询</div>
        <table cellspacing="0" class="infocontent">
        <tr>
          <td style="padding:20px"><p>欢迎${user.uloginid }光临ShopBook商城！</p>
            <p>您已经成交了 ${fn:length(orderlist) }  笔交易，有<font style="color:#FF0000">1</font>项交易正在处理中...</p>
            <table width="100%" border="0" cellspacing="0" class="tableopen">
              
              <tr>
               <td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
                <td bgcolor="#A3E2E6" class="tableopentd01">订单内容</td>
                <td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
                <td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
                <td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
              </tr>
              <c:set var="i" value="0"></c:set>
           <c:forEach items="${orderlist }" var="item" begin="${i }">
              <tr>
                <td class="tableopentd02">${item.orderid }</td>
               <td class="tableopentd02">
                <c:forEach items="${orderdetail[i] }" var="items">
               	 <tr>
               	 	<a href="info.html">${items.gtitle }</a>
                </tr>
                </c:forEach>
                </td>
                <td class="tableopentd02">${item.userid}</td>
                <td class="tableopentd02">${item.orderdate }</td>
                <td class="tableopentd03">已完成</td>
              </tr>
              <c:set var="i" value="${i+1}"></c:set>
            </c:forEach>
			   
            </table></td>
        </tr>
      </table>
	  
	  
	  </td>
    </tr>
  </table>
</div>



<div id="divfoot">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td rowspan="2" style="width:10%"><img src="images/bottomlogo.gif" width="195" height="50" style="margin-left:175px"/></td>
      <td style="padding-top:5px; padding-left:50px"><a href="#"><font color="#747556"><b>CONTACT US</b></font></a></td>
    </tr>
    <tr>
      <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font></td>
    </tr>
  </table>
</div>


</body>
</html>
