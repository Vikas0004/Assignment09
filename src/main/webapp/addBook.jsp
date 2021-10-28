<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>.
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
 	<title>Edit Product</title>
 	<style type="text/css">
 		
	h1,h2 {
		text-align: center;
	}
	
	 table {
	 
		border: 1px solid black;
		border-collapse:collapse;
		text-align: center;
		padding: 8px;
	}
	
	input {
		size: 50;
	}
	select{
		width: 370px;
		height: 20px;
	}
	 		
 	</style>
 
</head>
<body>
	
   &nbsp;
  <h1>Books Management System</h1>
   &nbsp;&nbsp;
  <h2>Add Book</h2>

    <div align="center">
  
   <form action="insert" method="post">
     
        <table border="1" cellpadding="5">           
            <tr>
                <th>Book Code: </th>
                <td>
                 <input type="text" name="bookCode" size = "50" />
                </td>
            </tr>
            <tr>
                <th>Book Name: </th>
                <td>
                 <input type="text" name="bookName" size = "50" />
                </td>
            </tr>
            <tr>
                <th>Select Author </th>
                <td>
               	<select name="author" id="select">
                <c:forEach var="author" items="${listAuthors}" varStatus="theCount">
					<option value = "${author.getAuthorName()}"> 
						<c:out value="${author.getAuthorName()}" />  
					</option>  
				</c:forEach>  
				</select>
                </td>
            </tr>
            <tr>
                <th>Date Added</th>
                <td>
                <c:set var="today" value="<%=new Date()%>"/>
                 <label>
                 	 <fmt:formatDate type="date" value="${today}" pattern="dd MMMM YYYY"/> 
                 </label>
             
                  
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Add Book" />
             </td>
            </tr>
        </table>
        </form>
    </div> 
</body>
</html>
