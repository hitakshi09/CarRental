<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>Manage Rental Orders</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/sitestyle.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<style type="text/css">
body {
	background-image: url("images/car_1.jpg");
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="adminHome"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="adminOrders">Manage Rental Orders</a></li>
				<li><a href="adminCars">Manage Cars</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logOut"><span
						class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
			</ul>
		</div>
	</nav>
	<div class="container" align="center">		
		<table class="table table-striped" id="layOutTable">
			<thead class="thead-dark">
				<tr>
					<th>S.No.</th>
					<th scope="col">Order Id</th>
					<th scope="col">User Id</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Total rent($)</th>
					<th>Model</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${listOrders}">
					<c:url var="statusReq" value="/orders/change/${order.id}">
						<c:param name="command" value="ChangeStatus" />
						<c:param name="setStatus" value="Car Rented" />
						<c:param name="model" value="${order.model}" />
					</c:url>					
					<c:url var="statusComp" value="/orders/change/${order.id}">
						<c:param name="command" value="ChangeStatus" />
						<c:param name="setStatus" value="Car Returned/Available" />
						<c:param name="model" value="${order.model}" />
					</c:url>
					<tr>
						<td></td>
						<td scope="row">${order.id}</td>
						<td>${order.userId}</td>
						<td>${order.start_date}</td>
						<td>${order.end_date}</td>
						<td>${order.bill}</td>
						<td>${order.model}</td>
						<td>${order.status}</td>
						<td>
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown">
									Set Status <span class="caret"></span>
								</button>
								<ul class="dropdown-menu"
									onclick="if(!(confirm('Are you sure you want to change the status?'))) return false">
									<li><a type="submit" href="${statusReq}">Car Rented</a></li>
									<li><a type="submit" href="${statusComp}">Car Returned/Available</a></li>
								</ul>
							</div>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="js/tableSearch.js"></script>
	<script>
		var addSerialNumber = function () {
	    	var i = 0
	    	$('table tr').each(function(i) {
	        	$(this).find('td:nth-child(1)').html(i+0);
	   		 });
		};

		addSerialNumber();
	</script>
</body>
</html>