package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class view_haupt {

	private Scene scene; 
	private Rennbahn rennbahn;
	private AnchorPane root = new AnchorPane();
	
	public view_haupt() {
		this.scene = new Scene(root,400,400);
	}
	
	public void setRennbahn(Rennbahn rennbahn) {
		this.rennbahn = rennbahn;
	}
	
	public Scene makeStandardScene() {
		Button btn = new Button();
		btn.setText("Start");		
		this.root.getChildren().add(btn);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
		return this.scene;
	}
	
	
	public Scene getScene() {
		return this.scene;
	}
	
}
