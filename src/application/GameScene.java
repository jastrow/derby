package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class GameScene {

	private Scene scene; 
	private Rennbahn rennbahn;
	private GridPane wurzel = new GridPane();
	private Button bttn_Start;
	private ProgressBar[] progr_Pferd = new ProgressBar[12];
	private ImageView[] img_Pferd = new ImageView[12];
	
	public GameScene() {
		this.wurzel.setAlignment(Pos.CENTER);
		this.scene = new Scene(this.wurzel,700,700);
	}
	
	public void setRennbahn(Rennbahn rennbahn) {
		this.rennbahn = rennbahn;
		System.out.println("rennbahn set "+this.rennbahn);
	}
	
	public Scene getScene() {
		if(this.scene == null) {
			this.scene = this.erstelleScene();
		}
		return this.scene;
	}
	
	public Scene erstelleScene() {	
		
		VBox verbo_aeussereBox = new VBox();
		
		ImageView imgv_Titelbild = new ImageView(getClass().getResource("KentuckyDerby.png").toExternalForm());
		
		HBox horbo_obereBox = new HBox();
		
		VBox verbo_linkeBox = new VBox();
		
		for(int i = 1; i <= img_Pferd.length; i++) {
			String Dateiname = "reiter_" + i + ".jpg";
			img_Pferd[i-1] = new ImageView(getClass().getResource(Dateiname).toExternalForm());
			img_Pferd[i-1].setFitHeight(20);
			img_Pferd[i-1].setFitWidth(20);
			verbo_linkeBox.getChildren().add(img_Pferd[i-1]);
		};

		VBox verbo_rechteBox = new VBox();
		
		for(int i = 0; i < progr_Pferd.length; i++) {
			progr_Pferd[i] = new ProgressBar();
			progr_Pferd[i].setMaxWidth(600);
			progr_Pferd[i].setMinWidth(600);
			progr_Pferd[i].setProgress(0);
			verbo_rechteBox.getChildren().add(progr_Pferd[i]);
		};
		
		horbo_obereBox.getChildren().add(verbo_linkeBox);
		horbo_obereBox.getChildren().add(verbo_rechteBox);
		
		HBox horbo_untereBox = new HBox();
		
		bttn_Start = new Button("Start");
		System.out.println("setUserData "+this.rennbahn);
		bttn_Start.setUserData(this.rennbahn);
		bttn_Start.setOnAction(new EventHandler<ActionEvent>(){
		    
		    public void handle(ActionEvent arg0){
		    	Button obj = (Button) arg0.getSource();
		    	System.out.println(obj.getUserData());
		    	Rennbahn rennbahn = (Rennbahn) obj.getUserData();
		    	rennbahn.startGame();
			}

		
		});
		
		horbo_untereBox.getChildren().add(bttn_Start);
		
		verbo_aeussereBox.getChildren().add(imgv_Titelbild);
		verbo_aeussereBox.getChildren().add(horbo_obereBox);
		verbo_aeussereBox.getChildren().add(horbo_untereBox);
		
		this.wurzel.getChildren().add(verbo_aeussereBox);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
		return this.scene;
	}
		
	public void bewegePferd(int Pferd, double Fortschritt) {
		progr_Pferd[Pferd].setProgress(Fortschritt);
	}
	
}
