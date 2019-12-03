package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.App;

public class Frontend extends Thread{
	
	private static Frontend instance;
	public static Frontend getInstance() {
		if (instance == null) {
			instance = new Frontend(new String[0]);
		}
		return instance;
	}
	private Frontend(String[] args) {this.args = args;}
	
	private App app;
	
	private String[] args;
	
	private Backend observer;
	
	@Override
	public void run() 
    { 
        try
        { 
            // Displaying the thread that is running 
            System.out.println ("Frontend is starting at thread: " + 
                  Thread.currentThread().getId());
//            app = App.getInstance(this.args);
            
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    }
	
	public void addObserver(Backend be) {
		this.observer = be;
	}

}
