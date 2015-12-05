package de.hawhh.reisebuchung;

public class Ort {

	private String name;

	public Ort() {
		this.name = "$UNKNOWN$";
	}
	
	public Ort(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ort [" + (name != null ?  name : "") + "]";
	}

}
