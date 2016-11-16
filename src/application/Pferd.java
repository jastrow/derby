package application;

public class Pferd extends Tier implements Runnable {

	private Integer ballruecklauf = 500; // MilliSekunden
	private Integer ballwurfzeit = 3; // maximale Zeit bis Ball geworfen wird
	private int[] wahrscheinlichkeit = { 15, 50, 100 }; // Punkte 1,2,3 in Prozent
	private int[] punkte = { 3,2,1 };
	
	public Pferd(Strecke strecke) {
		this.strecke = strecke;
	}
	
	
	@Override
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
		    		this.reportStrecke( this.punkte[i] );
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

	
	
}
