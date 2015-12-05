package de.hawhh.reisebuchung;

import de.hawhh.reisebuchung.flug.Flug;
import de.hawhh.reisebuchung.hotel.HotelBaustein;
import de.hawhh.reisebuchung.mietwagen.MietwagenBaustein;

public class EinfachReise extends AbstractReise {

	public EinfachReise(Flug hinflug, Flug rueckflug, HotelBaustein hotel) {
		reiseBausteine.add(hinflug);
		reiseBausteine.add(rueckflug);
		reiseBausteine.add(hotel);
	}

	public EinfachReise(Flug hinflug, Flug rueckflug, HotelBaustein hotel,
			MietwagenBaustein wagen) {
		this(hinflug, rueckflug, hotel);
		reiseBausteine.add(wagen);

	}

}
