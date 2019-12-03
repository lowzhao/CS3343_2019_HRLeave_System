package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application implements EventHandler<ActionEvent> {
	
	private static App instance;
	
	public static App getInstance(String[] args) {
		if (instance == null) {
			instance = new App(args);
		}
		return instance;
	}

	private Button button;
	
	private App(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Leave System.");
		
		// Stage -> Scene -> Layout -> Buttons, blahblah.....
		
		// creating layout
		
		
		button = new Button();
		button.setText("Click me.");
		button.setOnAction(this); // parameter is the class instance, can be other class instance.
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 1000, 1000);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		// Upon event this will be fired.
		
		
		
	}
}
