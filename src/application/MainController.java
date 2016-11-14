package application;

public class MainController {

	//private Stage primaryStage;
	//private Scene scene;
	private GameScene gameScene = new GameScene();
	private Rennbahn rennbahn = new Rennbahn();
	
	//public void launcher() {
	public MainController() {

		this.gameScene.setRennbahn(this.rennbahn);
		this.rennbahn.setView(this.gameScene);
		
		MainView mainView = new MainView(this.gameScene);
		//mainView.start();
		
		// in View verschieben 
		//this.rennbahn.startGame();

		
		// TESTING START

		// TESTING END
		
		
		System.out.println("launcher method");
		// launch(); // JavaFX
	}
	/*
	public void setScene(Scene s) {
		this.scene = s;
	}
	public Scene getScene() {
		if(! (this.scene instanceof Scene) ) {
			// durch view Objekt ersetzen
			this.scene = this.viewHaupt.erstelleScene();
		}
		return this.scene;
	}
	*/
	/*	
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
	*/
	
	/*
	public void calling(Object obj) {
		System.out.println( obj.getClass().toString() );
	}
	*/
	
}
