<%-- 
    Document   : subtitles
    Created on : 12 sept. 2019, 05:29:26
    Author     : MAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>- TRANSLATOR -</h1>
        <p>File name</p> 
        <p><c:out value="${ sessionScope.fileSrt.getFileName() }" /></p>
        <p>File description</p> 
        <p><c:out value="${ sessionScope.fileSrt.getFileDescription() }" /></p>


        <form method="post">    
            <input type="submit" style="position:fixed; top: 10px; right: 10px;" />
            <table>
                <c:forEach items="${ sessionScope.fileSrt.getNumbers() }" var="lineNumber" varStatus="status">
                    <tr>
                        <td style="text-align:right;"><c:out value="${ lineNumber }" /> : <c:out value="${ sessionScope.fileSrt.getDatesStart().get(status.index) }" /> 
                            - <c:out value="${ sessionScope.fileSrt.getDatesEnd().get(status.index) }" /> -> </td>
                        <td><c:out value="${ sessionScope.fileSrt.getSubs().get(status.index) }" /> </td>
                        <c:choose>
                            <c:when test="${not empty translatedSubs }">
                                <td><input type="text" name="${ status.index }" size="50" value="${ translatedSubs.getTranlateSubtitles().get(status.index) }" /></td>
                            </c:when>
                            
                            <c:otherwise>
                               <td><input type="text" name="${ status.index }" size="35" /></td>
                            </c:otherwise>
                        </c:choose>
                        </tr>
                </c:forEach>
            </table>
        </form>




    </body>
</html>
