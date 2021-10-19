<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>New Request</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/relatTable.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fixedTable.js"></script>
<style type="text/css">
body {
	background-image: url("images/car_3.jpg");
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
				<li class="active"><a href="userNewOrder">Place A New Request</a></li>
				<li><a href="userView">My Rentals</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logOut"><span
						class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
			</ul>
		</div>
	</nav>

	<div align="center">
		<form class="inputs">
			<div class="input-group">
				<div class="input-group-addon">
					<span class="glyphicon glyphicon-search"></span>
				</div>
				<input type="text" class="form-control" id="search"
					onkeyup="tableSearch()" placeholder="Search for car... ">
			</div>
		</form>

		<form action="orders" method="post">
			<table class="table-bordered" id="layOutTable">
				<tr>
					<th>S.No.</th>
					<th>Model</th>
					<th>Make</th>
					<th>Class</th>
					<th>Rent per day($)</th>
					<th>Choose Car</th>
				</tr>
				<c:forEach var="car" items="${CARS_LIST}">
					<tr>
						<td></td>
						<td>${car.model}</td>
						<td>${car.make}</td>
						<td>${car.carClass}</td>
						<td>${car.cost}</td>
						<td><input type="radio" name="carId" id="carId"
							value="${car.id}" /></td>
					</tr>
				</c:forEach>
			</table>
			<div class="form-group">

				<label for="emailInput">From</label>
				<div class="input-group">
					<input type="date" class="form-control" name="startDate"
						id="startDate"> <label for="emailInput">To</label> <input
						type="date" class="form-control" name="endDate" id="endDate">
				</div>
				<p><b>Note: The start date and end date should be at least 1 day apart</b></p>
			</div>
			<div class="form-group">
				<br> <label>Location </label>
				<select name="item">
    				<option value="Toronto">Toronto</option>
    				<option value="Quebec">Quebec</option>
  				</select>
  			</div>
			
			<div class="form-group" class="center">
				<button type="submit" name="command" value="Add"
					class="btn btn-primary">Create Request</button>				
			</div>

		</form>
	</div>
	<script>
		$(document).ready(
				function() {
					let today = new Date().toISOString().substr(0, 10);
					document.getElementById("startDate").value = today;
					document.querySelector("#endDate").min = document
							.querySelector("#startDate").value;
					document.querySelector("#endDate").value = document
							.querySelector("#startDate").value;
				});
	</script>
	<script src="js/dateCheck.js"></script>
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