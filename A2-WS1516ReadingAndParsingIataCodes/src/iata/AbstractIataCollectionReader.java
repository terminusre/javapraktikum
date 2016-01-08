package iata;

import iata.airline.IataAirlineCollectionReader;
import iata.airport.IataAirportCollectionReader;

import java.net.URL;
import java.util.Collection;

public abstract class AbstractIataCollectionReader {

	public static AbstractIataCollectionReader getInstance(String category) {
		switch (category) {
		case "airport":
			return new IataAirportCollectionReader();
		case "airline":
			return new IataAirlineCollectionReader();
		default:
			throw new IllegalArgumentException("unkown category " + category);
		}
	}

	public abstract Collection<? extends Iata> readLocalCollection();

	protected abstract Collection<? extends Iata> readSingleCollection(URL url);

	public static IataAirportCollectionReader getAirportInstance() {
		return new IataAirportCollectionReader();
	}

	public static IataAirlineCollectionReader getAirlineInstance() {
		return new IataAirlineCollectionReader();
	}

}
