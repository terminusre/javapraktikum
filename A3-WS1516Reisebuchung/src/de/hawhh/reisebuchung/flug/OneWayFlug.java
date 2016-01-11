package de.hawhh.reisebuchung.flug;

public abstract class OneWayFlug extends Flug {
	@Override
	public abstract OneWayFlug delayDays(int numDays);
}
