package application;

public abstract class Tier implements Runnable {
	Thread t;
	Boolean laufen = false;
	Strecke strecke;
	
	void start() {
		this.laufen = true;
		this.t = new Thread (this);
		this.t.start();
	}
	
	public void stop() {
		this.laufen = false;
	}
	void reportStrecke(Integer punkte) {
		this.strecke.report( punkte );
	}

	@Override
	public abstract void run();

	
}
