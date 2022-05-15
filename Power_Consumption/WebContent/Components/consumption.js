$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	// Form validation-------------------
	var status = validateConsumptionForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
 	} 
	// If valid------------------------
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "ConsumptionAPI", 
		 type : type, 
		 data : $("#formConsumption").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onConsumptionSaveComplete(response.responseText, status); 
	 	} 
	 }); 
});

function onConsumptionSaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
	 	{ 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divConsumptionGrid").html(resultSet.data); 
	 	} 
	 	else if (resultSet.status.trim() == "error") 
	 	{ 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
	 	} 
	 } 
	 else if (status == "error") 
	 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } 
	 else
	{ 
	 	$("#alertError").text("Unknown error while saving.."); 
	 	$("#alertError").show(); 
	 }
	$("#hididSave").val(""); 
	$("#formConsumption")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	$("#hididSave").val($(this).data("id")); 
	$("#cName").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#cAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#accNo").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#cdate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#unitNo").val($(this).closest("tr").find('td:eq(4)').text());
	$("#priceUnit").val($(this).closest("tr").find('td:eq(5)').text());
	$("#totalAmount").val($(this).closest("tr").find('td:eq(6)').text());
});

$(document).on("click", ".btnRemove", function(event)
{ 
	$.ajax( 
	{ 
		 url : "ConsumptionAPI", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onConsumptionDeleteComplete(response.responseText, status); 
		 } 
	}); 	
});
		
function onConsumptionDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
	 	var resultSet = JSON.parse(response); 
	 	if (resultSet.status.trim() == "success") 
	 	{ 
	 		$("#alertSuccess").text("Successfully deleted."); 
	 		$("#alertSuccess").show(); 
	 		$("#divConsumptionGrid").html(resultSet.data); 
	 	} 
		else if (resultSet.status.trim() == "error") 
	 	{ 
	 		$("#alertError").text(resultSet.data); 
	 		$("#alertError").show(); 
	 	} 
	 } 
	 else if (status == "error") 
	 { 
	 	$("#alertError").text("Error while deleting."); 
	 	$("#alertError").show(); 
	 } 
	 else
	 { 
	 	$("#alertError").text("Unknown error while deleting.."); 
	 	$("#alertError").show(); 
 	} 
}


// CLIENT-MODEL================================================================
function validateConsumptionForm()
{
	// CODE
	if ($("#cName").val().trim() == "")
	{
	return "Insert Consumption name.";
	}
	// NAME
	if ($("#cAddress").val().trim() == "")
	{
	return "Insert Consumption address.";
	}
	//Account
	if ($("#accNo").val().trim() == "")
	{
	return "Insert Consumption AccNo.";
	}
	//date
	if ($("#cdate").val().trim() == "")
	{
	return "Insert Consumption date.";
	}
	//UNIT
	if ($("#unitNo").val().trim() == "")
	{
	return "Insert Consumption UnitNo.";
	}
	//pticeunit
	if ($("#priceUnit").val().trim() == "")
	{
	return "Insert Consumption PriceUnit.";
	}

// PRICE-------------------------------
if ($("#totalAmount").val().trim() == ""){
	return "Insert Consumption TotalAmount.";
}
		// is numerical value
		var tmpPrice = $("#totalAmount").val().trim();
		if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for Consumption TotalAmount.";
	}
		
// convert to decimal price
$("#totalAmount").val(parseFloat(tmpPrice).toFixed(2));

	return true;
}