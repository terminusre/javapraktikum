package de.hawhh.reisebuchung;

import java.time.Duration;

public abstract class ReiseBaustein implements Reise {

	@Override
	public Duration getDauer() {
		return Duration.between(getBeginn(), getEnde());
	}

}
