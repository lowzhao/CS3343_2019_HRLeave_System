package ui;

import events.EventPageLogin;
import events.EventSignUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

public class PaneHomePage extends VBox {

	private TextField usernameField;
	private TextField passwordField;
	private TextField preferredNameField;
	private TextField ageField;
	private RadioButton isManagerRadio;

	private EventPageLogin eventPageLogin = new EventPageLogin(this);

	public PaneHomePage() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

		// add username
		Label usernameLabel = new Label("Username:");
		this.usernameField = new TextField();
		this.getChildren().add(usernameLabel);
		this.getChildren().add(this.usernameField);

		// add password
		Label passwordLabel = new Label("Password:");
		this.passwordField = new TextField();
		this.getChildren().add(passwordLabel);
		this.getChildren().add(this.passwordField);

		// add preferred name
		Label preferredNameLabel = new Label("Preferred name:");
		this.preferredNameField = new TextField();
		this.getChildren().add(preferredNameLabel);
		this.getChildren().add(this.preferredNameField);

		// add age
		Label ageLabel = new Label("Age:");
		this.ageField = new TextField();
		ageField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
		this.getChildren().add(ageLabel);
		this.getChildren().add(this.ageField);

		// add isManager
		this.isManagerRadio = new RadioButton("Are you manager?");
		this.getChildren().add(this.isManagerRadio);

		// add buttons
		this.getChildren().add(actionButtons());
	}
	


	public HBox actionButtons() {
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);

		Button cancleButton = new Button("Cancle");
		cancleButton.setPrefSize(100, 20);
		cancleButton.setOnAction(this.eventPageLogin);
		buttons.getChildren().add(cancleButton);

		return buttons;
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return passwordField.getText();
	}

	public String getPreferredName() {
		return preferredNameField.getText();
	}
	
	public int getAge() {
		// BUG: I have a error of number format exception here  
		if (ageField.getText().isEmpty()) {
			return 0;
		}else {
			return Integer.parseInt(ageField.getText());
		}
	}
	
	public boolean getIsManager() {
		return isManagerRadio.isSelected();
	}
	
	
	public EventPageLogin getEventPageLogin() {
		return eventPageLogin;
	}
	
	
}
