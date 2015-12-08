package de.hawhh.reisebuchung;

import java.time.Duration;
import java.time.LocalDateTime;

import de.hawhh.akteure.Kunde;
import de.hawhh.akteure.ReiseBuero;
import de.hawhh.kosten.GeldBetrag;

public class ReiseBuchung implements Buchung {

	private Kunde kunde = null;
	private ReiseBuero reiseBuero = null;
	private Reise reise = null;

	public ReiseBuchung(Kunde kunde, ReiseBuero reiseBuero, Reise reise) {
		this.kunde = kunde;
		this.reiseBuero = reiseBuero;
		this.reise = reise;
	}

	@Override
	public LocalDateTime getBeginn() {
		return reise.getBeginn();
	}

	@Override
	public LocalDateTime getEnde() {
		return reise.getEnde();
	}

	@Override
	public GeldBetrag getPreis() {
		return reise.getPreis();
	}

	@Override
	public Duration getDauer() {
		return reise.getDauer();
	}

	@Override
	public Ort getEndOrt() {
		return reise.getEndOrt();
	}

	@Override
	public Ort getStartOrt() {
		return reise.getStartOrt();
	}
	
	public Kunde getKunde(){
		return kunde;
	}
	
	
	public ReiseBuero getReiseBuero(){
		return reiseBuero;
	}
	
	public Reise getReise(){
		return reise;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((reise == null) ? 0 : reise.hashCode());
		result = prime * result
				+ ((reiseBuero == null) ? 0 : reiseBuero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReiseBuchung other = (ReiseBuchung) obj;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		if (reise == null) {
			if (other.reise != null)
				return false;
		} else if (!reise.equals(other.reise))
			return false;
		if (reiseBuero == null) {
			if (other.reiseBuero != null)
				return false;
		} else if (!reiseBuero.equals(other.reiseBuero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReiseBuchung: Kunde =" + kunde + ", Reisebuero = " + reiseBuero
				+ ", Reise = " + reise;
	}
	
	

}
