package main;

import java.util.Scanner;

import user.User;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Main{
	
	
	public static void main(String[] args) {
		
		Backend backend = Backend.getInstance();
		Frontend frontend = Frontend.getInstance();
		frontend.start();
		frontend.addObserver(backend);
		
		System.out.println("Done");
		
		
//		test_input();
		
	}
	
	
	public static void test_input() {
		Scanner scanner = new Scanner(System.in);
		
	 	System.out.println("Enter username: ");
		String username = scanner.nextLine();
		
		System.out.println("Enter password: ");
		String password = scanner.nextLine();
		
//		if (User.login(username, password)) {
//			User main_user = new User();
//		}
	}
	
}
