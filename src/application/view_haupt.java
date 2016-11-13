package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class view_haupt {

	private Scene scene; 
	private Rennbahn rennbahn;
	private GridPane wurzel = new GridPane();
	private Button bttn_Start;
	private ProgressBar[] progr_Pferd = new ProgressBar[12];
	private ImageView[] img_Pferd = new ImageView[12];
	
	public view_haupt() {
		this.wurzel.setAlignment(Pos.CENTER);
		this.scene = new Scene(this.wurzel,700,700);
	}
	
	public void setRennbahn(Rennbahn rennbahn) {
		this.rennbahn = rennbahn;
	}
	
	public Scene erstelleScene() {	
		
		VBox verbo_aeussereBox = new VBox();
		
		ImageView imgv_Titelbild = new ImageView(getClass().getResource("KentuckyDerby.png").toExternalForm());
		
		HBox horbo_obereBox = new HBox();
		
		VBox verbo_linkeBox = new VBox();
		
		for(int i = 1; i <= img_Pferd.length; i++) {
			String Dateiname = "reiter_" + i + ".jpg";
			img_Pferd[i-1] = new ImageView(getClass().getResource(Dateiname).toExternalForm());
			img_Pferd[i-1].setFitHeight(32);
			img_Pferd[i-1].setFitWidth(32);
			verbo_linkeBox.getChildren().add(img_Pferd[i-1]);
		};

		VBox verbo_rechteBox = new VBox();
		
		for(int i = 1; i <= progr_Pferd.length; i++) {
			progr_Pferd[i-1] = new ProgressBar();
			progr_Pferd[i-1].setMaxWidth(600);
			progr_Pferd[i-1].setMinWidth(600);
			verbo_rechteBox.getChildren().add(progr_Pferd[i-1]);
		};
		
		horbo_obereBox.getChildren().add(verbo_linkeBox);
		horbo_obereBox.getChildren().add(verbo_rechteBox);
		
		HBox horbo_untereBox = new HBox();
		
		
		
		verbo_aeussereBox.getChildren().add(imgv_Titelbild);
		verbo_aeussereBox.getChildren().add(horbo_obereBox);
		
		this.wurzel.getChildren().add(verbo_aeussereBox);
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
		return this.scene;
	}
		
}
