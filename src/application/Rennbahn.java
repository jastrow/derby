package application;

import java.util.ArrayList;
import java.util.List;

public class Rennbahn {
	private Integer anzahlStrecken = 12;
	private List<Strecke> strecken = new ArrayList<Strecke>();
	private List<Integer> reihenfolge = new ArrayList<Integer>();
	private Integer letzterZieleinlauf = 0;
	
	public Rennbahn() {
		this.buildStrecken();
	}
	
	public Integer getAnzahlStrecken() {
		return this.anzahlStrecken;
	}
	public Integer setAnzahlStrecken(Integer num) {
		this.anzahlStrecken = num;
		return this.anzahlStrecken;
	}
	
	public void buildStrecken() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.add(
				new Strecke(i, this)
			);
			this.reihenfolge.add(0);
		}
	}
	public void resetStrecken() {
		
	}
	public void startGame() {
		this.letzterZieleinlauf = 0;
		this.reihenfolge.clear();
		// Todo löschen der position innerhalb der strecken
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.get(i).start();
		}
	}
	public void stopGame() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			this.strecken.get(i).stop();
		}
	}
	public void winner(int strecke) {
		//this.stopGame();
		this.letzterZieleinlauf++;
		System.out.println("[["+this.letzterZieleinlauf+"]]");
		this.reihenfolge.add(strecke);
		//this.strecken.get(strecke).stop();
	}
	/*
	 * Eine Strecke meldet, dass sich etwas geändert hat, 
	 * Rennbahn kann sich aktualisieren.
	 */
	public void update() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			System.out.print("<"+i+"> "+this.strecken.get(i).getPosition()+ " ");
		}
		System.out.println("");
		// Prüfen ob alle im Ziel -> Ausgabe der Zieleinläufe
		if(this.letzterZieleinlauf == this.anzahlStrecken) {
			for(int j = 0; j < this.anzahlStrecken; j++) {
				System.out.println(j+"->"+this.reihenfolge.get(j));
			}
		}
	}
}
