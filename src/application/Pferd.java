package application;

public class Pferd implements Runnable {
	private Thread t;
	private String threadName;
	   
	private Integer ballruecklauf = 2000; // MilliSekunden
	private Integer ballwurfzeit = 3; // maximale Zeit bis Ball geworfen wird
	private int[] wahrscheinlichkeit = { 10, 40, 100 }; // Punkte 1,2,3 in Prozent
	private int[] punkte = { 3,2,1 };
	private Boolean laufen = false;
	private Strecke strecke;
	
	public Pferd(Strecke strecke) {
		this.strecke = strecke;
		this.threadName = "Strecke"+this.strecke;
	}
	
	public void start() {
		this.laufen = true;
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}		
	}
	
	public void run() {

		int ballwurfzeit;
		ballwurfzeit = (int)(Math.random() * this.ballwurfzeit) + 1;
		ballwurfzeit *= 1000;
		try {
			Thread.sleep(ballwurfzeit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.laufen) {
			int zufallszahl; 
		    zufallszahl = (int)(Math.random() * 100) + 1; 
		    for(int i = 0; i < this.wahrscheinlichkeit.length; i++) {
		    	if(zufallszahl <= this.wahrscheinlichkeit[i]) {
		    		this.strecke.report( this.punkte[i] );
		    	}
		    }
			try {
				Thread.sleep(this.ballruecklauf);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.run();
		}
		
	
	}
	public void stop() {
		this.laufen = false;
	}
	
	
}
