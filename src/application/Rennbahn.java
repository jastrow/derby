package application;

import java.util.ArrayList;
import java.util.List;

public class Rennbahn {
	private Integer anzahlStrecken = 12;
	private List<Strecke> strecken = new ArrayList<Strecke>();
	private List<Integer> reihenfolge = new ArrayList<Integer>();
	private Integer letzterZieleinlauf = 0;
	private view_haupt viewHaupt;
	
	public Rennbahn() {
		this.buildStrecken();
	}
	
	public void setView(view_haupt viewHaupt) {
		this.viewHaupt = viewHaupt;
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
		
		// Wenn die Strecke schon enthalten ist, dann verwerfen (bug)
		if(!this.reihenfolge.contains(strecke)) {
			this.letzterZieleinlauf++;
			
			System.out.println("Zieleinlauf "+this.letzterZieleinlauf+" Bahn "+strecke);
			this.reihenfolge.add(strecke);
			
			if(this.letzterZieleinlauf >= 12) {
				for(int j = 0; j < this.anzahlStrecken; j++) {
					System.out.println(j+"->"+this.reihenfolge.get(j));
				}
			}
		}
	}
	/*
	 * Eine Strecke meldet, dass sich etwas geändert hat.
	 */
	public void update() {
		for(int i = 0; i < this.anzahlStrecken; i++) {
			// viewHaupt.bewegePferd(i, this.strecken.get(i).getPosition());
			System.out.print("<"+i+"> "+this.strecken.get(i).getPosition()+ " ");
		}
		System.out.println("");
	}
}
