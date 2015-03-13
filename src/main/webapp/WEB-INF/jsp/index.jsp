<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<form method="POST" action="${url}">
		<table style="width: 100%">
			<tr>
				<td>Merchant Code :</td>
				<td><input type="text" id="Mcode" /></td>
			</tr>
			<tr>
				<td>Amount :</td>
				<td><input type="text" id="amount" /></td>
			</tr>

			<tr>
				<td>Return URL :</td>
				<td><input type="text" id="retUrl" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="button" value="Generate Invoice" id="invoice"></td>
				<td><input type="submit" value="pay"></td>
			</tr>
		</table>
		<input type="hidden" value="" name="merchantInvoice"> <br>
		<input id="restUrl" value="${rest}" type="hidden" /> <br> <input
			id="returnUrl" value="${returnUrl}" type="hidden" />
		<div id="div1"></div>
	</form>

	<script type="text/javascript">
	$(document).ready(function() {
	    var returnUrl = $("#returnUrl").val();
	    $("#retUrl").val(returnUrl);
	    
	    $("#invoice").click(function() {

		var mCode = $("#Mcode").val();
		var amount = $("#amount").val();
		var retUrl = $("#retUrl").val();
		var url = $("#restUrl").val();

		$.ajax({
		    url : url,
		    type : "GET",
		    contentType : "application/json; charset=utf-8",
		    data : {
			merchantCode : mCode,
			amount : amount,
			returnUrl : retUrl
		    },
		    success : function(result) {
			$("#div1").html(result);
			$('[name="merchantInvoice"]').val(result);
		    }
		});
	    });

	});
    </script>
</body>
</html>