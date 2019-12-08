package main;

import java.util.Calendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.Presentation;

public class Frontend extends Thread {

	private static Frontend instance;

	public static Frontend getInstance() {
		if (instance == null) {
			instance = new Frontend(new String[0]);
		}
		return instance;
	}

	private Frontend(String[] args) {
		this.args = args;
		
	}

	private Presentation app;

	private String[] args;

	private Backend observer;
	
	private String username;
	private String password;
	private int eid;
	private String sessionId;
	private boolean authenticated = false;

	@Override
	public void run() 
    { 
		try {
			// Displaying the thread that is running
			System.out.println("Frontend is starting at thread: " + Thread.currentThread().getId());
			Presentation.start(this.args);

		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
    }

	public void addObserver(Backend be) {
		this.observer = be;
	}

	public boolean authenticate_login_user(String username, String password) {
		this.username = username;
		this.password = password;
		Backend.AuthState authState = this.observer.authenticate_login_user(username, password);

		this.eid = authState.eid;
		this.sessionId = authState.sessionId;
		this.authenticated = authState.authenticated;
		
		return authState.authenticated;
	}
	
	public boolean dummy_insertUser(String username, String password) {
		try {
			return this.observer.dummy_insert_user(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertUser(String username, String password, String name, int age, boolean isManager) {
		try {
			return this.observer.insert_user(username, password,name,age,isManager);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean loggedIn() {
		return this.authenticated;
	}

	public void logout() {
		this.authenticated = false;
	}

}
