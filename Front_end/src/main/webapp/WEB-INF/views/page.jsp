<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Shopping - ${title}</title>

    <script>
        window.menu = '${title}';
        window.contextRoot = '${contextRoot}'
    </script>

    <!-- Bootstrap Core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap DataTables -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

    <!-- User Defined CSS -->
    <link href="${css}/custom.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<body>
    <div class="wrapper">
        <!-- Navigation -->
        <%@include file="./shared/navbar.jsp"%>

        <!-- Page Content -->
        <div class="content">
            <!-- Load only when user clicks Home -->
            <c:if test="${userClickHome == true}">
                <%@include file="home.jsp"%>
            </c:if>

            <!-- Load only when user clicks About -->
            <c:if test="${userClickAbout == true}">
                <%@include file="about.jsp"%>
            </c:if>

            <!-- Load only when user clicks Contact -->
            <c:if test="${userClickContact == true}">
                <%@include file="contact.jsp"%>
            </c:if>

            <!-- Load only when user clicks View Products -->
            <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true }">
                <%@include file="listProducts.jsp"%>
            </c:if>

            <!-- Load only when user clicks Show Product -->
            <c:if test="${userClickSingleProduct == true}">
                <%@include file="singleProduct.jsp"%>
            </c:if>

            <!-- Load only when user clicks Manage Products -->
            <c:if test="${userClickManageProducts == true}">
                <%@include file="manageProducts.jsp"%>
            </c:if>
        </div>

        <!-- Footer comes here -->
        <%@include file="./shared/footer.jsp"%>


        <%--  Javascript --%>
        <!-- jQuery -->
        <script src="${js}/jquery.min.js"></script>

        <!-- jQuery Validator -->
        <script src="${js}/jquery.validate.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="${js}/bootstrap.min.js"></script>

        <!-- DataTable Plugin -->
        <script src="${js}/jquery.dataTables.js"></script>

        <!-- DataTable Bootstrap Script -->
        <script src="${js}/dataTables.bootstrap.js"></script>

        <!-- Bootbox -->
        <script src="${js}/bootbox.min.js"></script>

        <!-- User defined javascript -->
        <script src="${js}/custom.js"></script>
    </div>
</body>

</html>