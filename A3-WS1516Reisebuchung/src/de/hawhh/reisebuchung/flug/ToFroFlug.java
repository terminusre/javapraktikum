package de.hawhh.reisebuchung.flug;

import java.time.Duration;
import java.time.LocalDateTime;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;

public class ToFroFlug extends Flug {
	DirektFlug hinflug;
	DirektFlug rueckflug;

	public ToFroFlug(DirektFlug hinflug, DirektFlug rueckflug) {
		if (Duration.between(hinflug.getEnde(), rueckflug.getBeginn())
				.getSeconds() >= 2 * 60 * 60) {
			this.hinflug = hinflug;
			this.rueckflug = rueckflug;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return hinflug + "\n" + rueckflug + "\n";
	}

	@Override
	public LocalDateTime getBeginn() {
		return hinflug.getBeginn();
	}

	@Override
	public LocalDateTime getEnde() {
		return rueckflug.getEnde();
	}

	@Override
	public GeldBetrag getPreis() {
		return hinflug.getPreis().add(rueckflug.getPreis());
	}

	@Override
	public Ort getEndOrt() {
		return rueckflug.getEndOrt();
	}

	@Override
	public Ort getStartOrt() {
		return hinflug.getStartOrt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hinflug == null) ? 0 : hinflug.hashCode());
		result = prime * result
				+ ((rueckflug == null) ? 0 : rueckflug.hashCode());
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
		ToFroFlug other = (ToFroFlug) obj;
		if (hinflug == null) {
			if (other.hinflug != null)
				return false;
		} else if (!hinflug.equals(other.hinflug))
			return false;
		if (rueckflug == null) {
			if (other.rueckflug != null)
				return false;
		} else if (!rueckflug.equals(other.rueckflug))
			return false;
		return true;
	}

}
