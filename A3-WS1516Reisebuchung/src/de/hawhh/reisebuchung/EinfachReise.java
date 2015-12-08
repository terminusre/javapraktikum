package de.hawhh.reisebuchung;

import de.hawhh.reisebuchung.flug.Flug;
import de.hawhh.reisebuchung.hotel.HotelBaustein;
import de.hawhh.reisebuchung.mietwagen.MietwagenBaustein;

public class EinfachReise extends AbstractReise {

	public EinfachReise(Flug hinflug, Flug rueckflug, HotelBaustein hotel) {
		add(hinflug);
		add(rueckflug);
		add(hotel);
	}

	public EinfachReise(Flug hinflug, Flug rueckflug, HotelBaustein hotel,
			MietwagenBaustein wagen) {
		this(hinflug, rueckflug, hotel);
		add(wagen); 

	}

}
