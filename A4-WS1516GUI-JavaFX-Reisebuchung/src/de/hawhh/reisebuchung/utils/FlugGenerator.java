package de.hawhh.reisebuchung.utils;

import iata.AbstractIataCollectionReader;
import iata.airline.IataAirline;
import iata.airline.IataAirlineCollectionReader;
import iata.airport.IataAirport;
import iata.airport.IataAirportCollectionReader;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import de.hawhh.kosten.Euro;
import de.hawhh.reisebuchung.flug.DirektFlug;
import de.hawhh.reisebuchung.flug.FlugNummer;

public class FlugGenerator {

	private static final int NUM_AIRPORTS = 20;
	private static final int NUM_AIRLINES = 5;

	private List<IataAirport> airportList = new ArrayList<IataAirport>();
	private List<IataAirline> airlineList = new ArrayList<IataAirline>();

	private static final Random reproduzierbar = new Random(9999);
	private static final LocalTime LAST_FLIGHT_OF_THE_DAY = LocalTime
			.of(19, 10);
	private static final int CHOSEN_AIRLINES = NUM_AIRLINES;
	private static final int TAKEOFF_DELAY_GENERAL_IN_MINUTES = 30;
	private static final int TAKEOFF_DELAY_PER_AIRLINE_IN_HOURS = 3;
	private static final int MIN_FLIGHT_TIME_IN_HOURS = 1;
	private static final int MAX_FLIGHT_TIME_IN_HOURS = 10;

	private static final int MIN_EURO = 200;
	private static final int MAX_EURO = 3000;

	/*
	 * Konfiguriert den FlugGenerator für 20 Airports und 5 Airlines
	 */
	public FlugGenerator() {
		this(NUM_AIRPORTS, NUM_AIRLINES);
	}

	/*
	 * Konfiguriert den FlugGenerator für numAirport Airports und numAirport
	 * Airlines IataAirport und Airline Codes werden hier einmal mit den Readern
	 * aus Aufgabe 2 gelesen und auf die gewünschte Größe reduziert. Die Auswahl
	 * erfolgt zufällig aber wiederholbar (Random mit Startwert).
	 * Die Listen werden gespeichert, um die nachfolgenden Operationen effizienter zu machen 
	 */
	public FlugGenerator(int numAirport, int numAirlines) {
		IataAirportCollectionReader airportReader = AbstractIataCollectionReader
				.getAirportInstance();
		List<IataAirport> airports = new ArrayList<IataAirport>(
				airportReader.readLocalCollection());
		/* waehle zufaellig numAirports Flughaefen aus */
		int totalAirports = airports.size();
		for (int i = 0; i < numAirport; i++) {
			airportList
					.add(airports.get(reproduzierbar.nextInt(totalAirports)));
		}
		Collections.sort(airportList,
				(a1, a2) -> a1.getCode().compareTo(a2.getCode()));

		IataAirlineCollectionReader airlineReader = AbstractIataCollectionReader
				.getAirlineInstance();
		List<IataAirline> airlines = new ArrayList<IataAirline>(
				airlineReader.readLocalCollection());
		/* waehle zufaellig numAirlines Fluggesellschaften aus */
		int totalAirlines = airlines.size();
		for (int i = 0; i < numAirlines; i++) {
			airlineList
					.add(airlines.get(reproduzierbar.nextInt(totalAirlines)));
		}
		Collections.sort(airlineList,
				(a1, a2) -> a1.getCode().compareTo(a2.getCode()));

	}

	/*
	 * Liefert eine Liste mit zufällig gewählten IataAirport Objekten zurück
	 */
	public List<IataAirport> getAirports() {
		return airportList;
	}

	/**
	 * Erzeugt ab dem Startdatum fuer die Strecke zwischen den Flughaefen von,
	 * nach Direktfluege fuer die Anzahl Tage
	 * 
	 * @param start
	 *            LocalDateTime des ersten Fluges
	 * @param plusDays
	 *            Abweichung vom Abflugdatum in Tagen. Fuer alle Tage des sich
	 *            ergebenden Intervalls werden Fluege berechnet. Die Flugnummern
	 *            sollen ueber die Tage erhalten bleiben, dazu wird auf den
	 *            Fluegen die Methode delayDays(days) aufgerufen.
	 * @param von
	 *            Ab-Flughafen
	 * @param nach
	 *            An-Flughafen
	 * @return eine Liste von Direktfluegen fuer den gewünschten Zeitraum
	 *         zwischen den Ab-/An- Flughaefen (in einer Richtung)
	 */
	public List<DirektFlug> generateDirektFlugListe(LocalDateTime start,
			int plusDays, IataAirport von, IataAirport nach) {

		List<DirektFlug> direktFluege = generateDirektFlugListeFurTag(start,
				von, nach);
		List<DirektFlug> fluegeCopy = new ArrayList<DirektFlug>(direktFluege);
		/*
		 * Nimm die Originalflugliste und verschiebe jeden Flug um eine gewisse
		 * Anzahl von Tagen
		 * 
		 * delayDays(int day) muss fuer alle Fluege implementiert werden.
		 * 
		 * delayDays muss Kopien der Originalfluege erzeugen.
		 */

		fluegeCopy.forEach(flug -> {
			for (int i = 0; i <= Math.max(0, plusDays); i++) {
				direktFluege.add(flug.delayDays(i));
			}
		});
		return direktFluege;
	}

	/**
	 * Hilfsmethode, die fuer die n-stellige ueberladene Methode Default Werte
	 * einsetzt
	 * 
	 * @param start
	 * @param von
	 * @param nach
	 * @return
	 */
	private List<DirektFlug> generateDirektFlugListeFurTag(LocalDateTime start,
			IataAirport von, IataAirport nach) {
		return generateDirektFlugListeFurTag(start, von, nach,
				LAST_FLIGHT_OF_THE_DAY, CHOSEN_AIRLINES,
				TAKEOFF_DELAY_GENERAL_IN_MINUTES,
				TAKEOFF_DELAY_PER_AIRLINE_IN_HOURS, MIN_FLIGHT_TIME_IN_HOURS,
				MAX_FLIGHT_TIME_IN_HOURS, MIN_EURO, MAX_EURO);
	}

	/**
	 * Generiert fuer einen Tag fuer eine gewaehlte Anzahl von Airlines
	 * Direktfluge fuer die Strecke von--nach. Die Fluege pro Airline starten in
	 * festen Intervallen (takeOffDelayPerAirlineInHours). Verschiedene Airlines
	 * starten nacheinander in festen Intervallen
	 * (takeOffDelayGeneralInMinutes). Aus dem Intervall minimale und maximale
	 * Flugzeit wird die aktuelle Flugzeit zufällig ausgewählt. Ebenso wird der
	 * Preis im Intervall minEuro, maxEuro zufällig ausgewählt.
	 * 
	 * THE MAGIC METHOD - DER NORMALE WEG SIND DATENBANKEN MIT FLUGVERBINDUNGSDATEN.
	 * 
	 * @param start
	 * @param von
	 * @param nach
	 * @param lastFlightOfTheDay
	 * @param chosenAirlines
	 * @param takeOffDelayGeneralInMinutes
	 * @param takeOffDelayPerAirlineInHours
	 * @param minFlightTimeInHours
	 * @param maxFlightTimeInHours
	 * @param maxEuro
	 * @param minEuro
	 * @return
	 */
	private List<DirektFlug> generateDirektFlugListeFurTag(LocalDateTime start,
			IataAirport von, IataAirport nach, LocalTime lastFlightOfTheDay,
			int chosenAirlines, int takeOffDelayGeneralInMinutes,
			int takeOffDelayPerAirlineInHours, int minFlightTimeInHours,
			int maxFlightTimeInHours, int minEuro, int maxEuro) {

		List<DirektFlug> direktFluege = new ArrayList<DirektFlug>();

		int flightTimeInterval = maxFlightTimeInHours - minFlightTimeInHours;
		int preisInterval = maxEuro - minEuro;
		LocalDateTime lastDepart = LocalDateTime.of(start.getYear(),
				start.getMonth(), start.getDayOfMonth(),
				lastFlightOfTheDay.getHour(), lastFlightOfTheDay.getMinute());
		HashSet<IataAirline> selAirlines = new HashSet<IataAirline>();
		while (selAirlines.size() < chosenAirlines) {
			selAirlines.add(airlineList.get(reproduzierbar
					.nextInt(NUM_AIRLINES)));
		}
		LocalDateTime departPerAirline = start;
		for (IataAirline airline : selAirlines) {
			LocalDateTime depart = departPerAirline;

			while (depart.isBefore(lastDepart)) {
				/*
				 * einfacher Flug
				 */
				DirektFlug d1 = new DirektFlug(new FlugNummer(airline,
						FlugNummernGenerator.naechsteNummer()), airline, von,
						nach, depart, depart.plus(
								reproduzierbar.nextInt(flightTimeInterval),
								ChronoUnit.HOURS).plus(
								minFlightTimeInHours + 1, ChronoUnit.HOURS),
						new Euro(reproduzierbar.nextInt(preisInterval)
								+ minEuro + 1, 0));
				direktFluege.add(d1);
				/*
				 * Abflugzeit fuer Airline erhöhen
				 */
				depart = depart.plus(takeOffDelayPerAirlineInHours,
						ChronoUnit.HOURS);
			}
			/*
			 * Abflugzeit fuer die naechste Airline ermitteln
			 */
			departPerAirline = departPerAirline.plus(
					takeOffDelayGeneralInMinutes, ChronoUnit.MINUTES);

		}
		return direktFluege;
	}

	private static class FlugNummernGenerator {
		private static final int maxLength = 5;
		private static int last = 0;

		static String naechsteNummer() {
			return String.format("%0" + (maxLength - ("" + (++last)).length())
					+ "d", last);
		}
	}

}
