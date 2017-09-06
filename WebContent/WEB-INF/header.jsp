<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.lanqiao.service.impl.CategoryServiceImpl"%>
<%@page import="org.lanqiao.service.CategoryService"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 加载 购物车数据 -->
<%@include file="cookiedata.jsp" %>
<!-- Logo -->
<div id="divhead">
  <table class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/index.do"><img src="${pageContext.request.contextPath }/images/logo.gif" width="95" height="30" border="0" /></a></td>
      <td style="text-align:right"><c:if test="${cookie.uloginid==null && sessionScope.user==null }">欢迎光临eShop,请<a href="${pageContext.request.contextPath }/dispacher.do?type=login">登录</a>成为会员&nbsp;|&nbsp;</c:if><c:if test="${cookie.uloginid!=null || sessionScope.user!=null }">欢迎${sessionScope.user.uloginid },<a href="${pageContext.request.contextPath }/login.do?type=loginout">注销</a>退出登录&nbsp;|&nbsp;</c:if><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/dispacher.do?type=cart">购物车<font color='red'>(<c:if test="${cart==null }">0</c:if><c:if test="${cart!=null }">${fn:length(cart) }</c:if>)</font></a>　|　<a href="#">帮助中心</a>　|　<c:if test="${sessionScope.user!=null }"><a href="${pageContext.request.contextPath}/dispacher.do?type=account">我的帐户</a>　|　</c:if><a href="${pageContext.request.contextPath}/dispacher.do?type=reg">新用户注册</a></td>
    </tr>
  </table>
</div>
<!-- Logo end -->
<%
	CategoryService cs = new CategoryServiceImpl();
	request.setAttribute("cates", cs.categoryList());
%>
<!-- menu -->
<div id="divmenu">
	<c:forEach items="${cates }" var="cate">
    <a href="${pageContext.request.contextPath }/list.do?type=goods&cid=${cate.cid }">${cate.cname }</a>　　
    </c:forEach>
    　　　　<a href="${pageContext.request.contextPath }/productList.do?type=listgoods" style="color:#FFFF00">全部商品目录</a>
</div>
<!-- menu end -->
<!-- search -->
<div id="divsearch"><table style="width:100%; border:0; cellspacing:0">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textfield" class="inputtable"/>
<!--<input name="searchbutton" type="image" src="${pageContext.request.contextPath }/images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
<a href="search.html"><img src="${pageContext.request.contextPath }/images/serchbutton.gif" border="0" style="margin-bottom:-4px"/></a></td>
  </tr>
</table>
</div>
<!-- search end -->