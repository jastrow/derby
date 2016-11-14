package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private GameScene gameScene;
	private Rennbahn rennbahn;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		this.rennbahn = new Rennbahn();
		this.gameScene = new GameScene(this.rennbahn);
		this.rennbahn.setView(this.gameScene);

		try {
			this.primaryStage.setScene(this.gameScene.getScene());
			this.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	
	}
	public static void main(String[] args) {
		launch(args);
	}	

}
