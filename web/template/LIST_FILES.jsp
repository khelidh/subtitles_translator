<div>

    <h2>Fichiers sous-titres disponibles à traduire</h2>

    <c:if test= "${ not empty listeFiles }" >

        <c:forEach items="${ listeFiles }" var="file" varStatus="status">

            <div class="card">
                <div class="card-header">
                    <c:out value="${ file.getFileName() }" />
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p> <c:out value="${ file.getFileDescription() }" /> </p>
                        <footer class="blockquote-footer">DATE<cite title="Source Title">Source Title</cite></footer>
                    </blockquote>
                </div>
            </div>

        </c:forEach>

    </c:if>

</div>