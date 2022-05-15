package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consumption {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/test", "root", "y1o2h3a4n5@#");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertConsumption(String cName, String cAddress, String accNo, String cdate, String unitNo, String priceUnit, String totalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into POWER(`ID`, `Cname`, `Caddress`, `AccNo`, `Cdate`, `UnitNo`, `PriceUnit`, `TotalAmount`)" + " values ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cName);
			preparedStmt.setString(3, cAddress);
			preparedStmt.setString(4, accNo);
			preparedStmt.setString(5, cdate);
			preparedStmt.setString(6, unitNo);
			preparedStmt.setString(7, priceUnit);
			preparedStmt.setString(8, totalAmount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newConsumption = readConsumption();
			output = "Inserted successfully";
			output = "{\"status\":\"success\", \"data\": \"" +newConsumption + "\"}";
		} catch (Exception e) {
			output = "Error while inserting the consumpation.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Consumption.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readConsumption() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table\"><tr><th>Consumer_Name</th><th>Consumer_Address</th><th>Account_No</th><th>Date</th><th>No_Units</th><th>Price_Unit</th><th>Total_Amount</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from POWER";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String cName = rs.getString("cName");
				String cAddress = rs.getString("cAddress");
				String accNo = rs.getString("accNo");
				String cdate = rs.getString("cdate");
				String unitNo = rs.getString("unitNo");
				String priceUnit = rs.getString("priceUnit");
				String totalAmount = rs.getString("totalAmount");

				// Add into the html table
				output += "<tr><td><input id='hidCidUpdate' name='hidCidUpdate' type='hidden' value='"+id+"'>"+cName+"</td>"; 
				output += "<td>" + cAddress + "</td>";
				output += "<td>" + accNo + "</td>";
				output += "<td>" + cdate + "</td>";
				output += "<td>" + unitNo + "</td>";
				output += "<td>" + priceUnit + "</td>";
				output += "<td>" + totalAmount + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>"; 
				 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the consumpation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateConsumption(String id, String cName, String cAddress, String accNo, String cdate, String unitNo, String priceUnit, String totalAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE POWER SET Cname=?,Caddress=?,AccNo=?,Cdate=?,UnitNo=?,PriceUnit=?,TotalAmount=? WHERE ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cName);
			preparedStmt.setString(2, cAddress);
			preparedStmt.setString(3, accNo);
			preparedStmt.setString(4, cdate);
			preparedStmt.setString(5, unitNo);
			preparedStmt.setString(6, priceUnit);
			preparedStmt.setString(7, totalAmount);
			preparedStmt.setInt(8, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newConsumption = readConsumption();
			output = "{\"status\":\"success\", \"data\": \"" +newConsumption + "\"}";
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the consumpation.";
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Consumption.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	

	public String deleteConsumption(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from POWER where ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newConsumption = readConsumption();
			output = "Deleted successfully";
			output = "{\"status\":\"success\", \"data\": \"" +newConsumption + "\"}";
		} catch (Exception e) {
			output = "Error while deleting the consumpation.";
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Consumption.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

}