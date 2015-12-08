package de.hawhh.reisebuchung.hotel;

public enum ZimmerTyp {
	EINZEL("Einzelzimmer"), DOPPEL("Doppelzimmer"), APPARTMENT("Appartment"), EINZELLUXUS("Luxus-Einzelzimmer"), DOPPELLUXUS("Luxus-Doppelzimmer"), APPARTMENTLUXUS("Luxus-Appartment");

	private String name;
	private ZimmerTyp(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
