package de.hawhh.reisebuchung.flug;

import java.time.LocalDateTime;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;
import de.hawhh.reisebuchung.ReiseBaustein;

public abstract class Flug extends ReiseBaustein {

	@Override
	public abstract LocalDateTime getBeginn();

	@Override
	public abstract LocalDateTime getEnde();

	@Override
	public abstract GeldBetrag getPreis();

	@Override
	public abstract Ort getEndOrt();

	@Override
	public abstract Ort getStartOrt();

}
