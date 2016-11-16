package application;

public class TierFabrik implements TierFabrikInterface {

	static Pferd getPferd(Strecke strecke) {
		return new Pferd(strecke);
	}
	
}
