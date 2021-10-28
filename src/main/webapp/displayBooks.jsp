<%@page import="java.io.FileOutputStream"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>

<% 
HttpSession session1 = request.getSession(false); 
String name;
if(session!=null){  
    name=(String)session.getAttribute("name");  
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
	<style type="text/css">
	
	
	input.right {
        float: right;
      }
	
	.button {
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 5px;

}
.button1 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}

.button1:hover {
  background-color: #008CBA;
  color: white;
}
	
label {
	padding-left: 50px;
    width:80px;
    clear:left;
    padding-right:10px;
}

input, label {
    float:left;
}



td, th {
  border: 1px solid black;
  text-align: center;
  padding: 8px;
}

td {
	height: 170px;
	width: 200px;
}
.td {
	height: 20px;
	width: 20px;
}
img {
	width: inherit;
	height: inherit;
}

.img {
	width: 25%;
	height: 25%;
}
table {
	margin-left: 50px;
}
.inputButton {
	padding : 5px;
	margin-left: 50px;
}

.container {
	display: flex;
	flex-direction: column;
	justify-content : space-evenly;
	
}
.divs {
	padding-top: 15px;
	padding-bottom: 15px;
}


	</style>
</head>
<body>
	<div class="container">
	
	<div class="divs">
		<form align="right" name="form1" method="post" action="logout">
	  
		  <label style="float: left; right:130px; top: 20px;">
			  <h3>
			  	 Hi ${name} 
			  </h3>
		  </label>
  		  <input class="inputButton button button1 right"  name="submit2" type="submit" id="submit2" value="log out">
	   
		</form>
	</div>
	
	<div class="divs" style="text-align: center;">
		<h1><strong>Books Listing</strong></h1>
	</div>
	
	<div class="divs">
		<form action="addBook" method="post" enctype="multipart/form-data">
			<input class="inputButton button button1 right" type="submit" value="Add Book">	
		</form>	
	</div>
	
	
	<div class="divs" style="width: 150%; margin-left: 30px;">
		<table>
			  <tr>
			    <th>Book Code</th>
			    <th>Book Name</th>
			    <th>Author</th>
			    <th>Date Added</th>
			    <th>Action</th>
			  </tr>
			 
			 
			  <c:forEach var="book" items="${listBooks}" varStatus="theCount">
		               <tr>
		               	
		               	
		               	<td><c:out value="${book.getBookCode()}"/></td>
		                   <td><c:out value="${book.getBookName()}" /></td>
		                   <td><c:out value="${book.getAuthorName()}" /></td>
		                   <td><c:out value="${book.getFormattedDate()}" /></td>
		   
		                   <td>
		                    <a href="edit?id=<c:out value='${book.getBookCode()}' />"><img class="img" src="https://icons-for-free.com/iconfiles/png/512/edit+document+edit+file+edited+editing+icon-1320191040211803620.png"></a>
		                    &nbsp;&nbsp;&nbsp;&nbsp;
		                    <a href="delete?id=<c:out value='${book.getBookCode()}' />"><img class="img" src="https://cdn4.iconfinder.com/data/icons/web-design-30/70/delete__trash__design__garbage-512.png"></a>                     
		                   </td>
		               </tr>
		           </c:forEach>
		           
		</table>
	</div>
	
	</div>
		
	
	   
</body>
</html>
