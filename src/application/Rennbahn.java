package application;

import java.util.ArrayList;
import java.util.List;

public class Rennbahn {
	private Integer anzahlStrecken = Configuration.getAnzahlBahnen();
	private List<Strecke> strecken = new ArrayList<Strecke>();
	private List<Integer> reihenfolge = new ArrayList<Integer>();
	private Integer letzterZieleinlauf = 0;
	private GameScene gameScene;
	private Boolean run = false;
	
	public Rennbahn() {
		this.buildStrecken();
	}
	
	/*
	 * Schnittstelle zum View Scene.
	 */
	public void setView(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	
	
	/*
	 * Erstellt die Strecken (construct)
	 */
	public void buildStrecken() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.add(
				new Strecke(i, this)
			);
		}
	}
	/*
	 * Setzt die Rennbahn und die Strecken wieder zurück auf den Anfang.
	 */
	public void resetGame() {
		this.stopGame();
		this.letzterZieleinlauf = 0;
		this.reihenfolge.clear();
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.get(i).reset();
		}
	}

	/*
	 * Startet auf allen Strecken die Pferde.
	 */
	public void startGame() {
		if(!this.run) {
			this.resetGame();
			this.run = true;
			for(int i = 0; i < this.anzahlStrecken; i++) {
				this.strecken.get(i).start();
			}
		}
	}
	/*
	 * Stoppt auf allen Strecken die Pferde.
	 */
	public void stopGame() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.get(i).stop();
		}
		this.run = false;
	}
	/**
	 * Eine Strecke meldet, dass sie einen Ziellauf hat.
	 */
	public void winner(int strecke) {
		
		// Wenn die Strecke schon enthalten ist, dann verwerfen (bug)
		if(!this.reihenfolge.contains(strecke)) {
			this.letzterZieleinlauf++;
			// System.out.println("Zieleinlauf "+this.letzterZieleinlauf+" Bahn "+strecke);
			gameScene.zeigeSieger(strecke, this.letzterZieleinlauf);
			this.reihenfolge.add(strecke);
			
			if(this.letzterZieleinlauf >= this.anzahlStrecken) {
				for(int j = 0; j < this.anzahlStrecken; j++) {
					gameScene.zeigeSchlussbild();
					// System.out.println(j+"->"+this.reihenfolge.get(j));
				}
				this.run = false;
			}
		}
	}
	/*
	 * Eine Strecke meldet, dass sich etwas geändert hat.
	 */
	public void update(Integer streckennummer, Integer position) {
		// System.out.println("strecke "+streckennummer+" auf position "+position);
		double hilf = (double) position / Configuration.getStreckenlaenge();
		//System.out.println("Rennbahn Pferd: " + streckennummer + " Fortschritt: " + position);
		gameScene.bewegePferd(streckennummer, hilf);
	}
}
