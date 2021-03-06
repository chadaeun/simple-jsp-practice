<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${ pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Simple Board</title>
    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body>
    <nav class="nav-bar navbar-expand bg-light py-3">
      <div class="container d-flex justify-content-between">
        <a href="${root}/article?action=list" class="navbar-brand fw-bold">Simple Board</a>
        <ul class="navbar-nav">
        <c:if test="${ ! empty user }">
          <li class="nav-item">
            <span class="nav-link fw-bold text-body"> Hello, ${ user.id }! </span>
          </li>
          <li class="nav-item">
            <a href="${root }/article?action=myarticles" class="nav-link">My Articles</a>
          </li>
          <li class="nav-item"><a href="${root }/user?action=signout" class="nav-link">Sign Out</a></li>
        
        </c:if>
        <c:if test="${ empty user }">
          <li class="nav-item">
            <a href="#" class="nav-link" data-bs-toggle="modal" data-bs-target="#signInModal"
              >Sign In</a
            >
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link" data-bs-toggle="modal" data-bs-target="#signUpModal"
              >Sign Up</a
            >
          </li>
        </c:if>
        </ul>
      </div>
    </nav>
    <main class="container p-5">
   	<c:if test="${ ! empty msg }">
   		<div class="alert ${ msgClass }">${ msg }</div>
   	</c:if>
   	<section>