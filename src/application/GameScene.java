 package application;

    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.*;
    import javafx.scene.layout.*;

    /**
     * @author JackRyan
     *
     */
    public class GameScene {

        private Scene scene;
        private Rennbahn rennbahn;
        // Hauptpanel
        private GridPane wurzel = new GridPane();
        // Startbutton
        private Button bttnStart;
        // Array fuer die Fortschrittsleisten
        private ProgressBar[] progrPferd = new ProgressBar[Configuration.getAnzahlBahnen()];
        // Array fuer die Streckenbilder
        private ImageView[] imgPferd = new ImageView[Configuration.getAnzahlBahnen()];
        // Array fuer die Siegesbilder
        private ImageView[] imgSieg = new ImageView[Configuration.getAnzahlBahnen()];
        // Platzhalter fuer Schlussbild
        private ImageView imgSchluss;
        // Siegesbilder
        private Image schlussbild = new Image(getClass().getResource("theEnd.png").toExternalForm());
        // Siegesbilder
        private Image ersterPlatz = new Image(getClass().getResource("platz_1.png").toExternalForm());
        private Image zweiterPlatz = new Image(getClass().getResource("platz_2.png").toExternalForm());
        private Image dritterPlatz = new Image(getClass().getResource("platz_3.png").toExternalForm());
        private Image restlichePlaetze = new Image(getClass().getResource("platz_halter.png").toExternalForm());
        
        private int anzahlBahnen = Configuration.getAnzahlBahnen();

        //Box-Elemente
        VBox verboAeussereBox = new VBox();
        HBox horboObereBox = new HBox();
        VBox verboMittlereBox = new VBox();
        VBox verboRechteBox = new VBox();
        HBox horboUntereBox = new HBox();

        /**
         * Konstruktor
         */
        public GameScene(Rennbahn rennbahn) {
            this.rennbahn = rennbahn;
            this.wurzel.setAlignment(Pos.CENTER);
            this.scene = new Scene(this.wurzel,700,700);
            this.erstelleScene();
            //Szene Formatierungs CSS  zuweisen
            this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        }


        /**
         * Funktion zum erstellen der Szene mit ihren Elementen
         * @return komplette Szene mit allen Elementen
         */
        private void erstelleScene() {

        	this.anzeigenTitelBild();
        	this.anzeigenPferdeBilder();
        	this.anzeigenProgressBar();
        	this.anzeigenSiegerBilder();
            this.buttonHandling();

            //Platzhalter fuer Schlussbild
            imgSchluss = new ImageView();
            imgSchluss.getStyleClass().add("imgSchlussStyle");
            imgSchluss.setFitHeight(100);
            imgSchluss.setFitWidth(300);
            horboUntereBox.getChildren().add(imgSchluss);
            
            verboAeussereBox.getChildren().add(horboObereBox);
            verboAeussereBox.getChildren().add(horboUntereBox);
            this.wurzel.getChildren().add(verboAeussereBox);
        };
        

        /**
         * Hilfsfunktion zur Rueckgabe der Szene
         * @return komplette Szene mit allen Elementen
         */
        public Scene getScene() {
            return this.scene;
        };
        

        //Methode stellt Titelbild dar
        public void anzeigenTitelBild(){
            ImageView imgvTitelbild = new ImageView(getClass().getResource("KentuckyDerby.png").toExternalForm());
            verboAeussereBox.getChildren().add(imgvTitelbild);
        };
        

        //Methode zeigt je Bahn ein Pferdeicon an
        public void anzeigenPferdeBilder(){
            horboObereBox.getStyleClass().add("horboObereBox");
            VBox verboLinkeBox = new VBox();
            for(int i = 1; i <= imgPferd.length; i++) {
                String dateiname = "reiter_" + i + ".png";
                imgPferd[i-1] = new ImageView(getClass().getResource(dateiname).toExternalForm());
                imgPferd[i-1].getStyleClass().add("pferdStyle");
                verboLinkeBox.getChildren().add(imgPferd[i-1]);
                };
            horboObereBox.getChildren().add(verboLinkeBox);
        };
        

        //Methode definiert 12 Progressbars
        public void anzeigenProgressBar(){
            verboMittlereBox.getStyleClass().add("progressBarBox");
            for(int i = 0; i < progrPferd.length; i++) {
                progrPferd[i] = new ProgressBar();
                progrPferd[i].setProgress(0);
                progrPferd[i].getStyleClass().add("progressBar");
                verboMittlereBox.getChildren().add(progrPferd[i]);
                };
            horboObereBox.getChildren().add(verboMittlereBox);
        };
        

        //Methode zeigt Medaillen fuer Platz 1 – 3 an
        public void anzeigenSiegerBilder(){
            for(int i = 0; i < anzahlBahnen; i++) {
                imgSieg[i] = new ImageView();
                imgSieg[i].setFitHeight(30);
                imgSieg[i].setFitWidth(30);
                imgSieg[i].getStyleClass().add("imgSiegStyle");
                verboRechteBox.getChildren().add(imgSieg[i]);
                };
            horboObereBox.getChildren().add(verboRechteBox);
        };
        

        //Mothode setzt Listener auf Button und startet das Rennen
        public void buttonHandling(){
            horboUntereBox.getStyleClass().add("horboUntereBox");
            bttnStart = new Button("Start");
            //System.out.println("setUserData "+this.rennbahn);
            bttnStart.setUserData(this.rennbahn);
            bttnStart.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent arg0){
                   Button obj = (Button) arg0.getSource();
                   System.out.println(obj.getUserData());
                   //Rennbahn aus Userdaten entgegennehmen
                   Rennbahn rennbahn = (Rennbahn) obj.getUserData();
                   ruecksetzenSzene();
                   obj.setDisable(true);
                   rennbahn.startGame();
                }
            });
            horboUntereBox.getChildren().add(bttnStart);
        };
        

        //Methode zum Anzeigen des Schlussbildes
        public void zeigeSchlussbild() {
            imgSchluss.setImage(schlussbild);
            bttnStart.setDisable(false);
        };
        

        //Methode zum Zuruecksetzen auf Anfang
        public void ruecksetzenSzene() {

            for(int i = 0; i < anzahlBahnen; i++) {
                //zuruecksetzen der Siegbilder
                imgSieg[i].setImage(null);
            };

            for(int i = 0; i < progrPferd.length; i++) {
                //Fortschrittsbalken auf 0 setzen
                progrPferd[i].setProgress(0);
            };
            // Schlussbild entfernen
            imgSchluss.setImage(null);
        };
        

        /**
         * Methode zum Anzeigen des Siegers
         * @param pferd -> pferd im Ziel
         * @param platzierung -> platzierung des Pferdes
         */
        public void zeigeSieger(int pferd, Integer platzierung){

            switch (platzierung) {
            case 1: imgSieg[pferd].setImage(ersterPlatz);
                    break;
            case 2: imgSieg[pferd].setImage(zweiterPlatz);
                    break;
            case 3: imgSieg[pferd].setImage(dritterPlatz);
            	break;
            default: imgSieg[pferd].setImage(restlichePlaetze);
            }

        };


        /**Hilfsfunktion zum aktualisieren der Fortschrittsbalken
         *
         *
         * @param pferd -> betreffender Balken
         * @param fortschritt -> Fortschritt in %
         */
        public void bewegePferd(Integer pferd, double fortschritt) {
            // System.out.println("GameScene Pferd: " + pferd + " Fortschritt: " + fortschritt);
            progrPferd[pferd].setProgress(fortschritt);
        }

    }