<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>Manage Cars</title>
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
				<li><a href="adminOrders">Manage Rental Orders</a></li>
				<li class="active"><a href="adminCars">Manage Cars</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logOut"><span
						class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
			</ul>
		</div>
	</nav>
	<div class="container" align="left">		
		<table class="table table-bordered" id="layOutTable">

			<tr>
				<th>S.No.</th>
				<th>Car Id</th>
				<th>Model</th>
				<th>Make</th>
				<th>Class</th>
				<th>Rent per day($)</th>
				<th>Action</th>
			</tr>

			<c:forEach var="car" items="${CARS_LIST}">				
				<c:url var="deleteLink" value="/cars/delete/${car.id}">
					<c:param name="command" value="Delete" />
					<c:param name="carId" value="${car.id}" />
				</c:url>
				<tr>
					<td></td>
					<td>${car.id }</td>
					<td>${car.model}</td>
					<td>${car.make}</td>
					<td>${car.carClass}</td>
					<td>${car.cost}</td>
					<td>
						<div class="btn-group">
							<a href="${deleteLink}" class="btn btn-danger"
								onclick="if(!(confirm('Are you sure you want to delete this car'))) return false">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
								Delete
							</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<h1>Add a new car</h1>
		<form class="form-inline" action="/cars" method="post">
			<div class="form-group">
				<label for="carName">Model</label><br> <input type="text"
					class="form-control" pattern="^\w+$" maxLength="20" name="carName"
					id="carName" title="Only letters,numbers and signs ">
			</div>
			<div class="form-group">
				<label for="make">Make</label><br> <input type="text"
					class="form-control" pattern="^[a-zA-Z]+$" maxLength="20"
					name="make" id="make" title="Only letters ">
			</div>

			<div class="form-group">
				<label for="make">Rent per day($) </label><br> <input type="text"
					class="form-control" pattern="^[0-9]+$" maxLength="6"
					name="carCost" id="make" title="Only numbers ">
			</div>
			<div class="form-group">
				<label for="carClass">Class</label><br> <select
					class="form-control" id="carClass" name="carClass">
					<option>Crossover SUV</option>
					<option>Sedan</option>
					<option>Hatchback</option>
				</select>
			</div>
			<br>
			<div class="input-group">
				<button type="submit" class="btn btn-primary" name="command"
					value="Add">
					<span class="glyphicon glyphicon-save">Add</span>
				</button>
			</div>
		</form>
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
<body>
</body>
</html>