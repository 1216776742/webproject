<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots" />
<meta name="author" content="Fisher" />
<meta name="Copyright"
	content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="stylesheet" rev="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css"
	type="text/css" media="all" />
</head>

<body class="main">
	<%@include file="header.jsp"%>
	<%
		CategoryService goodscs = new CategoryServiceImpl();
		request.setAttribute("goodscates", goodscs.categoryList());
	%>
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%"><table width="100%" border="0" cellspacing="0"
						style="margin-top: 30px">
						<tr>
							<td class="listtitle">分类</td>
						</tr>
						<c:forEach items="${goodscates }" var="goodscate">
							<tr>
								<td class="listtd"><img
									src="${pageContext.request.contextPath }/images/miniicon.gif"
									width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
									<a href="${pageContext.request.contextPath }/list.do?type=goods&cid=${goodscate.cid }">${goodscate.cname }</a></td>
							</tr>
						</c:forEach>
					</table></td>
				<td>
					<div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>全部商品</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pageinfos.totalnumber }种商品
								<hr /> <img src="${pageContext.request.contextPath }/images/miniicon2.gif" />&nbsp;&nbsp;&nbsp;&nbsp;商品列表&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;JAVA&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;ASP.NET&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;网站设计&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;图形处理

								<div style="margin-top: 20px; margin-bottom: 5px">
									<img
										src="${pageContext.request.contextPath }/images/productlist.gif"
										width="631" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<c:set var="i" value="0"></c:set>
									<c:forEach items="${pageinfos.data }" var="goods" begin="${i }">
										<c:if test="${i==0 }">
										<tr>
										</c:if>
										<c:if test="${i%4==0 }">
										<tr>
										</c:if>
											<td>
											<div class="divbookpic">
														<a href="${pageContext.request.contextPath}/dispacher.do?type=goods&id=${goods.gid}">
														<img src="${pageContext.request.contextPath }/images/bookcover/${goods.gimg }.jpg" width="97" height="135" /></a>
												</div>
												<div class="divlisttitle">
												<a href="${pageContext.request.contextPath}/dispacher.do?type=goods&id=${goods.gid}"> 
												${goods.gtitle }
													<br /> 售价： ${goods.gsaleprice }
												</a></div>
											</td>
										<c:if test="${i+1%4==0 }">
											</tr>
										</c:if>
										<c:set var="i" value="${i+1 }"></c:set>
									</c:forEach>

								</table>
								<div class="pagination">
									<ul>

										<c:if test="${pageinfos.isfirstpage }">
											<c:if test="${pageinfos.totalpage}>1">
												<li class="disablepage"><< 上一页</li>
											</c:if>
										</c:if>
										<c:if test="${!pageinfos.isfirstpage }">
											<li class="nextpage"><a
												href="${pageContext.request.contextPath}/productList.do?pageindex=${pageinfos.pageindex - 1}">上一页
													>></a></li>
										</c:if>

										<c:set var="currentpage" value="${pageinfos.pageindex }"></c:set>
										<c:if test="${pageinfos.totalnumber>5 }">
											<c:set var="startindex" value="${currentpage-5 }"></c:set>
											<c:set var="endindex" value="${startindex+9 }"></c:set>
										</c:if>
										<c:if test="${startindex<=0 }">
											<c:set var="startindex" value="1"></c:set>
											<c:set var="endindex" value="${startindex+9 }"></c:set>
										</c:if>

										<c:if test="${endindex>=pageinfos.totalpage }">
											<c:set var="endindex" value="${pageinfos.totalpage }"></c:set>
											<c:set var="startindex" value="${endindex-9 }"></c:set>
										</c:if>
										<c:if test="${pageinfos.pageindex>6}">
											<li><a
												href="${pageContext.request.contextPath}/productList.do?pageindex=1">1</a></li>
										</c:if>
										<c:if test="${pageinfos.pageindex>6}">
											<li>...</li>
										</c:if>
										<c:forEach begin="${startindex }" end="${endindex }" var="i">
											<c:if test="${i==currentpage }">
												<li class="currentpage">${i }</li>
											</c:if>
											<c:if test="${!(i==currentpage) }">
												<c:if test="${pageinfos.totalpage>1}">
													<li><a
														href="${pageContext.request.contextPath}/productList.do?pageindex=${i}">${i }</a></li>
												</c:if>
											</c:if>
										</c:forEach>

										<c:if test="${pageinfos.pageindex<pageinfos.totalpage-4}">
											<li>...</li>
										</c:if>

										<c:if test="${pageinfos.pageindex<pageinfos.totalpage-4 }">
											<li><a
												href="${pageContext.request.contextPath}/productList.do?pageindex=${pageinfos.totalpage}">${pageinfos.totalpage }</a></li>
										</c:if>


										<c:if test="pageinfo.islastpage">
											<li class="disablepage">下一页>></li>
										</c:if>
										<c:if test="${!pageinfos.islastpage }">
											<li class="nextpage"><a
												href="${pageContext.request.contextPath}/productList.do?pageindex=${pageinfos.pageindex + 1}">下一页
													>></a></li>
										</c:if>

									</ul>
								</div>


							</td>
						</tr>
					</table>



				</td>
			</tr>
		</table>
	</div>
	<%@include file="footer.jsp"%>

</body>
</html>