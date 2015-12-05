package de.hawhh.reisebuchung;

import java.time.Duration;
import java.time.LocalDateTime;
import de.hawhh.kosten.GeldBetrag;

public interface Buchung {
	public LocalDateTime getBeginn();
	public LocalDateTime getEnde();
	public GeldBetrag getPreis();
	public Duration getDauer();
	public Ort getEndOrt();
	public Ort getStartOrt();
}
