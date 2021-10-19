<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>My Rentals</title>
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/relatTable.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fixedTable.js"></script>
<style type="text/css">
body {
	background-image: url("images/car_2.jpg");
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="userHome"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="userNewOrder">Place a New Request</a></li>
				<li class="active"><a href="userView">My Rentals</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logOut"><span
						class="glyphicon glyphicon-log-out"></span> LogOut</a></li>

			</ul>
		</div>
	</nav>
	<div align="center">
			<table class="table-bordered" id="layOutTable">
				<tr>
					<th>S.No.</th>
					<th>Total rent($)</th>
					<th>Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Model</th>
				</tr>	
				<c:forEach var="order" items="${listOrders}">
					<tr>
						<td></td>
						<td>${order.bill}</td>
						<td>${order.status}</td>
						<td>${order.start_date}</td>
						<td>${order.end_date }</td>
						<td>${order.model}</td>
					</tr>
				</c:forEach>	
			</table>			
	</div>
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