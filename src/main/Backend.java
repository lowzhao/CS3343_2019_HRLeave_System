package main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import backend.Authentication;
import backend.DBConnect;
import backend.LoginAuthentication;
import backend.SessionAuthentication;
import exception.NoResultException;
import util.Converters;
import util.HashingCalculator;

public class Backend {

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
		System.out.println("Backend started");

		// Connect DB
		this.con = new DBConnect();

//		// Open for request;
//		try {
////			this.insert_user("","Ella","123","Ella Wong",21,new Date(),false);
////			System.out.println(this.authenticate_login_user("lowzhao","123"));
//			LoginAuthentication auth = new LoginAuthentication("lowzhao", "123", this.con);
//			if (auth.authenticate()) {
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}

	public AuthState authenticate_login_user(String username, String password) {
		AuthState authState = new AuthState();
		try {
			Authentication auth = new LoginAuthentication(username, password, this.con);
			authState.authenticated = auth.authenticate();
			if (authState.authenticated) {
				authState.eid = Integer.parseInt(this.con.getUserEid(username));
				authState.sessionId = SessionAuthentication.createSession(authState.eid, this.con);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (NoResultException e) {
			System.out.println(e);
		}
		return authState;
	}

	public boolean authenticate_session_user(String sessionId, int eid) {
		try {
			Authentication auth = new SessionAuthentication(sessionId, eid, this.con);
			return auth.authenticate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean dummy_insert_user(String username, String password) {
		return this.insert_user(username, password, "namamamam", 12, false);
	}

	public boolean insert_user(String username, String password, String name, int age, boolean isManager) {
		try {
			byte[] salt = HashingCalculator.genSalt();
			String saltStr = Converters.bytesToHex(salt);
			byte[] hash = HashingCalculator.genHash(password, salt);
			String hashStr = Converters.bytesToHex(hash);
			this.con.insertUser(name, Calendar.getInstance(), username, saltStr, hashStr, isManager);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;

	}

	public class AuthState {
		boolean authenticated;
		int eid;
		String sessionId;
	}

}
