package de.hawhh.reisebuchung.flug;

import iata.airline.IataAirline;

public class FlugNummer {
	private String flugnummer;

	public FlugNummer(IataAirline airline, String naechsteNummer) {
		flugnummer = airline.getName() + naechsteNummer;
	}

	public String getFlugNummer() {
		return flugnummer;
	}

	public int toInt() {
		return flugnummer.hashCode();
	}
}
