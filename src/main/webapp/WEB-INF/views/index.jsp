
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Das neueste kompilierte und minimierte CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optionales Theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Das neueste kompilierte und minimierte JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>URL Shortener</title>
</head>
<body>
    <div align="center">
            <h2>URL Shortener service</h2>
            <form:form action="" method="post" modelAttribute="urldto">
                <form:label path="urldto">URL: </form:label>
                <form:input path="urldto"/>
                <form:button>Shortener</form:button><br/>

                </form:form>
        </div align="center">

        <div align="center">
                <form action="/url">
                Url-Shortener : <input type="text" name="urlShort"/>
                <input type="submit" value="retrieve">
                <table class="table table-bordered" >
                                          <thead>
                                            <tr>
                                              <td>ID</td>
                                              <td>URL</td>
                                              <td>Shortener</td>
                                              <td>Date</td>
                                              <td>CallNumber</td>
                                            </tr>
                                          </thead>

                                            <tr>
                                                <td>"${urlObj.id}"</td>
                                                <td>"${urlObj.url}"</td>
                                                <td>"${urlObj.urlShort}"</td>
                                                <td>"${urlObj.urlDate}"</td>
                                                <td>"${urlObj.urlCallNumber}"</td>
                                            </tr>
                    </table>
                </form>
        </div>

        <div align="center">
                        <form action="/del">
                        ID : <input type="text" name="deleteID"/>
                        <input type="submit" value="remove">
                        <table class="table table-bordered" >
                                                  <thead>
                                                    <tr>
                                                      <td>ID</td>
                                                      <td>URL</td>
                                                      <td>Shortener</td>
                                                      <td>Date</td>
                                                      <td>CallNumber</td>
                                                    </tr>
                                                  </thead>
                                                    <tr>
                                                        <td>"${deleteUrl.id}"</td>
                                                        <td>"${deleteUrl.url}"</td>
                                                        <td>"${deleteUrl.urlShort}"</td>
                                                        <td>"${deleteUrl.urlDate}"</td>
                                                        <td>"${deleteUrl.urlCallNumber}"</td>
                                                    </tr>
                            </table>
                        </form>
                </div>

                <div align="center">
                     <form action="/urlObjs">
                     URLs: <input type="submit" value="URLs"></br>
                     <table class="table table-bordered"  >
                              <thead>
                                <tr>
                                  <td>ID</td>
                                  <td>URL</td>
                                  <td>Shortener</td>
                                  <td>Date</td>
                                  <td>CallNumber</td>
                                </tr>
                              </thead>
                              <tbody>

                                  <c:forEach var="url" items="${urlObjs}">
                                     <tr>
                                      <td><c:out value="${url.id}"/></td>
                                      <td><c:out value="${url.url}"/></td>
                                      <td><c:out value="${url.urlShort}"/></td>
                                      <td><c:out value="${url.urlDate}"/></td>
                                      <td><c:out value="${url.urlCallNumber}"/></td>
                                    </tr>
                                  </c:forEach>
                               </tbody>
                     </table>
                     </form>
                 </div>
</body>
</html>