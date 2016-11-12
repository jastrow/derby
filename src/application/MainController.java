package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController extends Application {

	private Stage primaryStage;
	private Scene scene;
	
	public void launcher() {

		
		// Testing
		Observer observer = new Observer();
		observer.addSubscriber(this, "test");
		Object test = new Object();
		observer.trigger("test", test);
		
		
		System.out.println("launcher method");
		launch(); // JavaFX
	}
	public void setScene(Scene s) {
		this.scene = s;
	}
	public Scene getScene() {
		if(! (this.scene instanceof Scene) ) {
			this.scene = this.makeStandardScene();
		}
		return this.scene;
	}
	public Scene makeStandardScene() {
		Button btn = new Button();
		btn.setText("Start");
	 
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
		return scene;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			this.primaryStage.setScene(this.getScene());
			this.primaryStage.show();
			System.out.println(this.primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void calling(Object obj) {
		System.out.println( obj.getClass().toString() );
	}
	
}
