package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainView extends Application {

	private GameScene gameScene;
	private Stage primaryStage;
	
	public MainView(GameScene s) {
		this.gameScene = s;
	}
	public void start() {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		try {
			this.primaryStage.setScene(this.gameScene.getScene());
			this.primaryStage.show();
			System.out.println(this.primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	

}
