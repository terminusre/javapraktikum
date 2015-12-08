package de.hawhh.akteure;

import de.hawhh.reisebuchung.Ort;

public class Kunde {

	private String name;
	private Ort ort;

	public Kunde(String name, Ort ort) {
		this.name = name;
		this.ort = ort;
	}

	@Override
	public String toString() {
		return "Kunde [" + (name != null ? "name=" + name + ", " : "")
				+ (ort != null ? "ort=" + ort : "") + "]";
	}
}
