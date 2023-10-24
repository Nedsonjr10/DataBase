package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Connection con = null;
		PreparedStatement st = null; 
		try {
			con = DB.getConnection(); // connect to database
			
			st = con.prepareStatement(

					"INSERT INTO seller "
					+ "(Name , Email , BirthDate , BaseSalary , DepartmentID)"
					+ "VALUES"
					+"(? , ? , ? , ? , ?)" , 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Nedson Nogueira");
			st.setString(2, "nedson.junior91@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("16/06/2004").getTime()));
			st.setDouble(4, 4000.00);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No row Affected");
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(); //always close connection last
		}
	
	
	}
}