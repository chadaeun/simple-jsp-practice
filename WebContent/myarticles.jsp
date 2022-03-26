<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<c:if test="${ articles eq null }">
	<script>
		location.href = "${root}/article?action=myarticles";
	</script>
</c:if>
<div class="row">
        <div class="col">
          <h1 class="mb-5">My Articles</h1>
        </div>
        <div class="col text-end">
          <a href="article?action=mvwrite" class="btn btn-primary btn-lg">Write</a>
        </div>
      </div>
      <table class="table table-hover text-center">
        <thead>
          <tr class="table-light">
            <th>No.</th>
            <th>Subject</th>
            <th>Author</th>
          </tr>
        </thead>
        <tbody>
        <c:if test="${ ! empty articles }">
        <c:forEach items="${articles }" var="article">
          <tr>
            <td>${ article.no }</td>
            <td>
        <a href="${ root }/article?action=view&no=${ article.no }">
            ${ article.subject }
        </a>
            <td>${ article.userid }</td>
          </tr>
        </c:forEach>
        </c:if>
        <c:if test="${ empty articles }">
			<tr>
            <td colspan=3>No Article: Hit the 'Write' button above and write the first article!</td>
          </tr>        
        </c:if>
        </tbody>
        <tbody></tbody>
      </table>
<%@ include file="/include/footer.jsp" %>