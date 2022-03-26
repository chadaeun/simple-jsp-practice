<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
          function cancel() {
            if (confirm("Are you sure to cancel?")) {
              location.href = "${root}/article?action=list";
            }
          }
        </script>

        <h1 class="mb-5">Modify</h1>
        <form action="${root}/article" method="post">
          <input type="hidden" name="action" value="modify" />
          <input type="hidden" name="no" value="${ article.no }" />
          <div class="row mb-3">
            <div class="col-md-1">
              <label class="form-label fw-bold" for="subject">Subject</label>
            </div>
            <div class="col">
              <input class="form-control" type="text" name="subject" id="subject" value="${ article.subject }" />
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-1">
              <label class="form-label fw-bold" for="content">Content</label>
            </div>
            <div class="col">
              <textarea
                class="form-control"
                name="content"
                id="content"
                cols="30"
                rows="10"
              >${ article.content }</textarea>
            </div>
          </div>
          <div class="d-flex justify-content-end">
            <input
              id="btn-cancel"
              class="btn btn-secondary mx-2"
              type="button"
              onclick="cancel();"
              value="Cancel"
            />
            <input class="btn btn-primary mx-2" type="submit" value="Modify" />
          </div>
        </form>
<%@ include file="/include/footer.jsp" %>