package backend;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exception.NoResultException;

public class DBConnect {
	
	private Connection con;
	
	public DBConnect () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
			Statement st = con.createStatement();
			st.executeQuery("USE cs3343db;");
		} catch (Exception e){
			System.out.println("Error: "+ e.toString());
		}
	}
	
	public void getData() {
		try {
			String query = "SELECT * from Employee;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("Records from Database.");
			while (rs.next()) {
				String name = rs.getString("name");
				String age = rs.getString("age");
				System.out.println(name + ' ' + age);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private ArrayList<String[]> executeAndFetch(String sql,String[] responseOrders) throws SQLException {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			ArrayList<String[]> response = new ArrayList<String[]>();
			while (rs.next()) {
				String[] singleRowResponse = new String[responseOrders.length];
				for (int i = 0; i < responseOrders.length; i++) {
					singleRowResponse[i] = rs.getString(responseOrders[i]);
				}
				response.add(singleRowResponse);
			}
			return response;
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}
	}
	
	public String[] getUserAuthInfo(String username) throws SQLException, NoResultException {
		try {
			return this.executeAndFetch(
					String.format("SELECT password, salt FROM User WHERE username=%s;", username),
					new String[] {"password","salt"}
			).get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
			throw new NoResultException("User don't exist.");
		}
	}
	
	public void insertUser(String name, Date dateJoined, String username, BigInteger salt, BigInteger password,boolean isManager) throws SQLException {
		Statement st = con.createStatement();
		st.executeUpdate(String.format("INSERT INTO Employee (name, dateJoined, username, salt, password,isManager) VALUES (%s,%s,%s,%d,%d, %b)",name ,Date2Str(dateJoined), username,salt, password,isManager));
	}
	
	public static String Date2Str(Date d) {
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		return sdformat.format(d);
	}
}
