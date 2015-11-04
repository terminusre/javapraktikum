package iata.airport;

import iata.AbstractIataCollectionReader;
import iata.Iata;

import java.net.URL;
import java.util.Collection;

public class IataAirportCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {
		IataAirport a = null;
		return null;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {
		return null;
	}
}
