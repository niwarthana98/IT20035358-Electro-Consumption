<%@page import="model.Consumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumption</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/consumption.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6"> 
				<h1>Consumption Management</h1>
				<form action="consumption.jsp" id="formConsumption" name="formConsumption" method="post">
 					Cname: 
 					<input id="cName" name="cName" type="text" class="form-control form-control-sm">
 					<br>
 					Caddress: 
 					<input id="cAddress" name="cAddress" type="text" class="form-control form-control-sm">
 					<br>
 					AccNo: 
 					<input id="accNo" name="accNo" type="text" class="form-control form-control-sm">
 					<br>
 					Cdate: 
 					<input id="cdate" name="cdate" type="text" class="form-control form-control-sm">
 					<br>
 					UnitNo: 
 					<input id="unitNo" name="unitNo" type="text" class="form-control form-control-sm">
 					<br>
 					PriceUnit: 
 					<input id="priceUnit" name="priceUnit" type="text" class="form-control form-control-sm">
 					<br>
 					TotalAmount: 
 					<input id="totalAmount" name="totalAmount" type="text" class="form-control form-control-sm">
 					<br>
 					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					<input type="hidden" id="hididSave" name="hididSave" value="">
 				</form>
 				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divConsumptionGrid">
				 	<%
				 		Consumption ConsumptionObj = new Consumption();
				 		out.print(ConsumptionObj.readConsumption()); 
				 	%>
				</div>
 			</div>
 		</div>
 	</div>
</body>
</html>