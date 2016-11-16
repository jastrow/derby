package application;

interface TierFabrikInterface {
	static Pferd getPferd(Strecke strecke) {
		return new Pferd(strecke);
	}
	// More Animal-Getters possible
}
