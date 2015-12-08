package de.hawhh.akteure;

import de.hawhh.reisebuchung.Ort;

public class ReiseBuero {
	
	private String name;
	private Ort ort;

	public ReiseBuero(String name, Ort ort){
		this.name = name;
		this.ort = ort;
	}

	@Override
	public String toString() {
		return "ReiseBuero [name=" + name + ", ort=" + ort + "]";
	}

}
