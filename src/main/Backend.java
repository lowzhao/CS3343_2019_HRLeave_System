package main;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;

import backend.Authentication;
import backend.DBConnect;
import backend.LoginAuthentication;
import util.HashingCalculator;

public class Backend{
	
	private static Backend instance;
	public static Backend getInstance() {
		if (instance == null) {
			instance = new Backend();
		}
		return instance;
	}

	private DBConnect con;
	
	private Backend() {
		// Displaying the thread that is running 
        System.out.println ("Backend started");

		// Connect DB
		this.con = new DBConnect();
		
		// Open for request;
		try {
			this.insert_user("","Ella","123","name",10,new Date(),false);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public boolean authenticate_login_user(String username, String password) {
		Authentication auth = new LoginAuthentication(username, password, this.con);
		try {
			return auth.authenticate();
		}catch(SQLException e){
			System.out.println(e);
		}
		return false;
	}
	
	public boolean insert_user(String session,String username, String password, String name, int age, Date dateJoined, boolean isManager) throws Exception {
		
		if (true) {
			byte[] salt =  HashingCalculator.genSalt();
			BigInteger bigIntSalt = new BigInteger(salt);
			BigInteger hash =new BigInteger( HashingCalculator.genHash(password, salt));
			
			this.con.insertUser(name, dateJoined, username, bigIntSalt,hash,isManager);
			
			return true;
		}else {
			return false;
		}
	}

	public void call() {
		System.out.println("Called from frontend");
	}
	
}
