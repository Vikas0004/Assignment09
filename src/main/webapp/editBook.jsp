<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>.
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
 	<title>Edit Book</title>
 	<style type="text/css">
 		
	h1,h2 {
		text-align: center;
	}
	h3 {
		padding-top: 10px;
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
  <h2>Edit Book</h2>

    <div align="center">
  <c:if test="${book != null}">
   <form action="update" method="post" >
        </c:if>
        
        <table border="1" cellpadding="5">
                    
            <tr>
                <th>Book Code: </th>
                <td>
                 <input type="text" name="bookCode" size = "50"
                 value='<c:out value="${book.getBookCode()}"></c:out>'
                 readonly/>
                </td>
            </tr>
            <tr>
                <th>Book Name: </th>
                <td>
                 <input type="text" name="bookName" size = "50" 
                 value='<c:out value="${book.getBookName()}"></c:out>'
                 />
                </td>
            </tr>
            <tr>
                <th>Select Author </th>
                <td>
               	<select name="author" id="select">
                <c:forEach var="author" items="${listAuthors}" varStatus="theCount">
					<c:choose>
						<c:when test="${author.getAuthorName() == book.getAuthorName()}">
							<option value = "${author.getAuthorName()}" selected> 
								<c:out value="${author.getAuthorName()}" />  
							</option>
						</c:when>
						<c:when test="${author.getAuthorName() != book.getAuthorName()}">
							<option value = "${author.getAuthorName()}"> 
								<c:out value="${author.getAuthorName()}" />  
							</option>  
						</c:when>
					</c:choose>
										
				</c:forEach>  
				</select>
                </td>
            </tr>
            <tr>
                <th>Date Added</th>
                <td>
                <input type="hidden" value="${book.getCreatedAt()}" name = "date">
                 <label>
                 	 <h3><c:out value="${book.getFormattedDate()}"></c:out></h3>
                 </label>
             
      
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Update Book" />
             </td>
            </tr>
        </table>
       </form>
    </div> 
</body>
</html>
