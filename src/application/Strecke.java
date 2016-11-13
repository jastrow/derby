package application;

public class Strecke {
	private Pferd pferd;
	private Integer laenge = 27;
	private Integer position = 0;
	private Integer streckennummer;
	private Rennbahn rennbahn;
	
	public Strecke(int num, Rennbahn rennbahn) {
		this.streckennummer = num;
		this.rennbahn = rennbahn;
		this.pferd = new Pferd(this);
	}
	
	public void start() {
		this.position = 0;
		this.pferd.start();
	}
	public void stop() {
		this.pferd.stop();
	}

	public void report(int laufweite) {
		if(this.position >= this.laenge) {
			this.position = this.laenge;
			this.stop();
			this.rennbahn.winner(this.streckennummer);
		} else {
			this.position += laufweite;
			this.rennbahn.update();
		}
	}
	
	public Integer getPosition() {
		return this.position;
	}
	
}
