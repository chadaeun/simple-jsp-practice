<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<div class="row">
        <div class="col">
          <h1 class="mb-5">Articles</h1>
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
          <tr>
            <td>2</td>
            <td>여기 한국인 없나요?</td>
            <td>ChaDaEun</td>
          </tr>
          <tr>
            <td>1</td>
            <td>Hello and Welcome!</td>
            <td>Admin</td>
          </tr>
        </tbody>
        <tbody></tbody>
      </table>
<%@ include file="/include/footer.jsp" %>