<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="lang"
       value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title><fmt:message key="home.title"/></title>
</head>
<body>

<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <security:authorize access="hasAnyRole('ADMIN','MODERATOR','TRUSTED','OUTER_USER')">
            <div class="btn-toolbar mb-2 mb-md-0">
                <button class="btn btn-outline-secondary" onclick="location.href='/event/addEventForm'">
                    <span data-feather="plus" style="margin-bottom: 1px;"></span>
                    <fmt:message key="home.addEventBtn"/>
                </button>
            </div>
        </security:authorize>
        <security:authorize access="hasAnyRole('ADMIN','MODERATOR','TRUSTED')">
            <div class="btn-toolbar mb-2 mb-md-0">
                <button class="btn btn-outline-secondary" onclick="location.href='/event/list?archived=true'">
                    <span data-feather="archive" style="margin-bottom: 1px;"></span>
                    <fmt:message key="home.archiveBtn"/>
                </button>
            </div>
        </security:authorize>
    </div>
    <h2><fmt:message key="home.header"/></h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="home.colEventName"/></th>
                <th scope="col"><fmt:message key="home.colEventDate"/></th>
                <th scope="col"><fmt:message key="home.colEventPlace"/></th>
                <th scope="col"><fmt:message key="home.colEventType"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="event" items="${events}">
                <security:authorize access="hasRole('OUTER_USER')">
                    <c:if test="${event.published}">
                        <c:url var="eventDetails" value="/event/eventDetails">
                            <c:param name="eventId" value="${event.id}"/>
                        </c:url>
                        <tr>
                            <td>${event.name}</td>
                            <td>${event.date}</td>
                            <td>${event.place}</td>
                            <td>***</td>
                            <td>
                                <button class="btn btn-sm btn-outline-secondary"
                                        onclick="location.href='${eventDetails}'">
                                    <fmt:message key="home.eventDetailsBtn"/>
                                </button>
                            </td>
                        </tr>

                    </c:if>
                </security:authorize>
                <security:authorize access="hasAnyRole('ADMIN','MODERATOR','TRUSTED','USER')">
                    <c:url var="eventDetails" value="/event/eventDetails">
                        <c:param name="eventId" value="${event.id}"/>
                    </c:url>
                    <c:url var="deleteEvent" value="/event/deleteEvent">
                        <c:param name="eventId" value="${event.id}"/>
                    </c:url>
                    <tr>
                        <td>${event.name}</td>
                        <td>${event.date}</td>
                        <td>${event.place}</td>
                        <td>${event.videoType}</td>
                        <td>
                            <div class="btn-group mr-2">
                                <button class="btn btn-sm btn-outline-secondary"
                                        onclick="location.href='${eventDetails}'">
                                    <fmt:message key="home.eventDetailsBtn"/>
                                </button>
                                <button class="btn btn-sm btn-outline-secondary" data-toggle="modal"
                                        data-target="#joinModal" onclick="openModal(${event.id})"><fmt:message
                                        key="home.eventJoinBtn"/>
                                </button>
                            </div>
                            <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
                                <button class="btn btn-sm btn-outline-secondary"
                                        onclick="location.href='${deleteEvent}'">
                                    <fmt:message key="home.eventDeleteBtn"/>
                                </button>
                            </security:authorize>
                        </td>
                    </tr>
                </security:authorize>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<div class="modal fade" id="joinModal" tabindex="-1" role="dialog" aria-labelledby="joinModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="joinModalLabel"><fmt:message key="home.modalHeader"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="home.modalName"/></th>
                            <th scope="col"><fmt:message key="home.modalRole"/></th>
                        </tr>
                        </thead>
                        <tbody id="modalTbody">

                        </tbody>

                        <form:form action="/participation/addParticipation" modelAttribute="participation" method="POST"
                                   acceptCharset="utf8">
                            <input type="hidden" id="currEventId">
                            <tr>
                                <td>
                                    <span><fmt:message key="home.modalJoinAs"/></span>
                                </td>
                                <td>
                                    <form:select path="roleId" class="form-control" id="modalRoleId">
                                        <form:option value="0"><fmt:message key="event.selectRole"/></form:option>
                                        <c:forEach items="${roles}" var="role">
                                            <form:option value="${role.id}" label="${role.name}"/>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td>
                                    <button class="btn btn-outline-secondary" data-dismiss="modal" type="button"
                                            onclick="joinEvent();"><fmt:message key="home.modalJoin"/></button>
                                </td>
                            </tr>
                        </form:form>
                        <script>
                            function joinEvent() {
                                var xmlHttp = new XMLHttpRequest();
                                xmlHttp.open("GET", "${pageContext.request.contextPath}/api/makeParticipation?eventId=" + document.getElementById('currEventId').value + "&roleId=" + document.getElementById('modalRoleId').value, false); // false for synchronous request
                                xmlHttp.send(null);
                                return xmlHttp.responseText;

                            }

                            function reqParticipation(id) {
                                return new Promise(function (resolve, reject) {
                                    document.getElementById('currEventId').value = id.toString();
                                    var xmlhttp = new XMLHttpRequest();
                                    xmlhttp.open("GET", "${pageContext.request.contextPath}/api/getEventParticipants?eventId=" + id, true);
                                    xmlhttp.setRequestHeader("Content-Type", "application/json");
                                    xmlhttp.responseType = "json";
                                    xmlhttp.onreadystatechange = function () {
                                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                                            xmlhttp.response.forEach(function (par) {
                                                document.getElementById('modalTbody').innerHTML += '<td>' + par.user + '</td><td>' + par.roleName + '</td>';
                                            });
                                            console.log(xmlhttp.response);
                                            resolve();
                                        }
                                    };
                                    xmlhttp.send();
                                });
                            }

                            function openModal(id) {
                                document.getElementById('modalTbody').innerHTML = "";
                                reqParticipation(id);
                            }

                        </script>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
