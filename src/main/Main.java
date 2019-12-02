package main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	Button button;
	
	public static void main(String[] args) {
		System.out.println("Hello Galen.");
		launch(args); // setup app as java application;
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Leave System.");
		
		// Stage -> Scene -> Layout -> Buttons, blahblah.....
		
		// creating layout
		
		
		button = new Button();
		button.setText("Click me.");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 1000, 1000);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
}
