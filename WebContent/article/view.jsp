<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<script>
	function deleteConfirm() {
		if (confirm("Are you sure to delete?")) {
			location.href = "${root }/article?action=delete&no=${article.no}";
		}
	}
</script>

<div class="row">
	<div class="col">
		<h1 class="mb-5">View</h1>
	</div>
	<div class="col">

		<c:if test="${ article.userid.equals(user.id) }">
		<div class="d-flex justify-content-end">
			<a class="btn btn-primary mx-2" href="${root }/article?action=mvmodify&no=${article.no}"> Modify </a> 
			<a class="btn btn-secondary mx-2" onclick="deleteConfirm()"> Delete </a>
		</div>
		</c:if>
	</div>
</div>
<div class="row mb-3">
            <div class="col-md-1">
              <label class="form-label fw-bold" for="subject">Subject</label>
            </div>
            <div class="col">
            ${ article.subject }
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-1">
              <label class="form-label fw-bold" for="content">Content</label>
            </div>
            <div class="col">
              ${ article.content }
            </div>
          </div>
<%@ include file="/include/footer.jsp" %>