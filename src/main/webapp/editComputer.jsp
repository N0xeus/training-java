<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="resources/css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
	    <header class="navbar navbar-inverse navbar-fixed-top">
	        <div class="container">
	            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
	        </div>
	    </header>
	    <section id="main">
	        <div class="container">
	            <div class="row">
	                <div class="col-xs-8 col-xs-offset-2 box">
	                    <div class="label label-default pull-right">
	                        id: ${computer.id}
	                    </div>
	                    <h1>Edit Computer</h1>
	
	                    <form action="editComputer" method="POST">
	                        <input type="hidden" value="${computer.id}" id="id"/>
	                        <fieldset>
	                            <div class="form-group">
	                                <label for="computerName">Computer name</label>
	                                <input type="text" class="form-control" id="computerName" placeholder="Computer name" value="${computer.name}">
	                            </div>
	                            <div class="form-group">
	                                <label for="introduced">Introduced date</label>
	                                <input type="date" class="form-control" id="introduced" placeholder="Introduced date" value="${computer.introduced}">
	                            </div>
	                            <div class="form-group">
	                                <label for="discontinued">Discontinued date</label>
	                                <input type="date" class="form-control" id="discontinued" placeholder="Discontinued date" value="${computer.name}">
	                            </div>
	                            <div class="form-group">
	                                <label for="companyId">Company</label>
	                                <select class="form-control" id="companyId" >
                                        <c:forEach items="${companies}" var="com">
                                            <option value="${com.id}">${com.name}
                                        </c:forEach>
	                                </select>
	                            </div>            
	                        </fieldset>
	                        <div class="actions pull-right">
	                            <input type="submit" value="Edit" class="btn btn-primary">
	                            or
	                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </section>
	</body>
</html>