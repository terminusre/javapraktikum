package de.hawhh.reisebuchung;

import java.time.Duration;
import java.time.LocalDateTime;

import de.hawhh.kosten.GeldBetrag;

public abstract class ReiseBaustein implements Reise {

	@Override
	public Duration getDauer() {
		return Duration.between(getBeginn(), getEnde());
	}

}
