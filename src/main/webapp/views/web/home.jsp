<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
<h1>${model.fullName}</h1> <!-- JSP sử dụng quy ước JavaBeans để truy cập thuộc tính của đối tượng. Nếu đối tượng có một phương thức getter có tên "get<propertyName>", thì thuộc tính tương ứng có thể được truy cập bằng cú pháp "${object.propertyName}". -->
</body>
</html>