<%-- 
    Document   : main
    Created on : 10 sept. 2019, 02:43:04
    Author     : MAMA
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>JSP Page</title>

        <link href="css/styleMain.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1><a href="https://openclassrooms.com/fr/courses/2434016-developpez-des-sites-web-avec-java-ee">Activité finale : Développez des sites web avec Java EE</a></h1>
        <button type="button" class="btn btn-primary">Primary</button>
        <div>
            <h2>Upload new .srt file :</h2>

            <form method="POST" enctype="multipart/form-data">
                <p>Description du fichier : </p>
                <input type="text" name="fileDescription" placeholder="description du fichier">
                <p>Select a file : </p> <input type="file" name="fichier_srt"><br><br>
                <input type="submit">
            </form>
        </div>


        <div>
            <h2>Files ready to translate : </h2>

            <c:if test= "${ not empty listeFiles }" >

                <c:forEach items="${ listeFiles }" var="file" varStatus="status">
                    <p class="newSubsFile" fileId="${ file.getId() }">N^<c:out value="${ file.getId() }" /> : <c:out value="${ file.getFileName() }" /> : <c:out value="${ file.getFileDescription() }" /></p>
                    
                    <form method="post">
                    <input type="submit" name="boutonFile" value="${ file.getId() }">
                    </form>
                </c:forEach>

            </c:if>


        </div>

        <!-- Fichier .srt déjà commencé et à terminer -->

        <div>
            <h2>Subtitles continue to translate : </h2>

            <c:if test= "${ not empty listeSubs }" >
                <c:forEach var="entry" items="${listeSubs}">
                    <div class="subOn" id="${entry.value.getId()}">
                    FileName: <c:out value="${entry.key.getFileName()}"/> : FileDescription: <c:out value="${entry.key.getFileDescription()}"/>
                    <br>
                    Subs Translation[id = <c:out value="${entry.value.getId()}"/>] :  <c:out value="${entry.value.getSubtitlesDescription()}"/>
                    <br>
                    <br>
                    <form method="post">
                    <input type="submit" name="bouton" action="${ entry.value.getId() }" value="${ entry.value.getId() }">
                    </form>
                    </div>
                </c:forEach>
            </c:if>


        </div>

        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
