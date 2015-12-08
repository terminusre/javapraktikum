package de.hawhh.reisebuchung.flug;

import iata.airline.IataAirline;
import iata.airport.IataAirport;

import java.time.LocalDateTime;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;

public class DirektFlug extends OneWayFlug {
	private int flugnummer;
	private IataAirline airline;
	private IataAirport abflughafen;
	private IataAirport ankunftflughafen;
	private LocalDateTime abflugszeit;
	private LocalDateTime ankunftsflugszeit;
	private GeldBetrag preis;

	public DirektFlug(int flugnummer, IataAirline airline,
			IataAirport abflughafen, IataAirport ankunftflughafen,
			LocalDateTime abflugszeit, LocalDateTime ankunftsflugszeit,
			GeldBetrag preis) {
		this.flugnummer = flugnummer;
		this.airline = airline;
		this.abflughafen = abflughafen;
		this.ankunftflughafen = ankunftflughafen;
		this.abflugszeit = abflugszeit;
		this.ankunftsflugszeit = ankunftsflugszeit;
		this.preis = preis;
	}

	@Override
	public LocalDateTime getBeginn() {
		return abflugszeit;
	}

	@Override
	public LocalDateTime getEnde() {
		return ankunftsflugszeit;
	}

	@Override
	public GeldBetrag getPreis() {
		return preis;
	}

	@Override
	public Ort getEndOrt() {
		return new Ort(ankunftflughafen.getLocation());
	}

	@Override
	public Ort getStartOrt() {
		return new Ort(abflughafen.getLocation());
	}

	@Override
	public String toString() {
		return "Flug " + flugnummer + " der Airline " + airline
				+ " startet in " + abflughafen + " um " + abflugszeit
				+ " und landet in " + ankunftflughafen + " um "
				+ ankunftsflugszeit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abflugszeit == null) ? 0 : abflugszeit.hashCode());
		result = prime * result + flugnummer;
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
		DirektFlug other = (DirektFlug) obj;
		if (abflugszeit == null) {
			if (other.abflugszeit != null)
				return false;
		} else if (!abflugszeit.equals(other.abflugszeit))
			return false;
		if (flugnummer != other.flugnummer)
			return false;
		return true;
	}

}
