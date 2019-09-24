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
                        <!--                    <form method="post">
                                            <input type="submit" name="bouton" action="${ entry.value.getId() }" value="${ entry.value.getId() }">
                                            </form>-->
                    </div>
                </c:forEach>
            </c:if>


        </div>