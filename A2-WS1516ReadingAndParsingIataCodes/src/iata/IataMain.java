package iata;

import java.io.IOException;

public class IataMain {

	public static void main(String[] args) {

		AbstractIataCollectionReader readerAirport = AbstractIataCollectionReader
				.getInstance("airport");
		AbstractIataCollectionReader readerAirline = AbstractIataCollectionReader
				.getInstance("airline");

		try {
			IataCollectionUtility.toFile(readerAirport.readLocalCollection(),
					"iata_airport_list");
		} catch (IOException e) {
			System.err.println("IataAirportCollectionReader needs work");
			e.printStackTrace();
		}

		try {
			IataCollectionUtility.toFile(readerAirport
					.readSingleCollection(readerAirport.getClass().getResource(
							"S.htm")), "iata_airport_list_S");
		} catch (IOException e) {
			System.err.println("IataAirportCollectionReader needs work");
			e.printStackTrace();
		}

		try {
			IataCollectionUtility.toFile(readerAirline.readLocalCollection(),
					"iata_airline_list");
		} catch (IOException e) {
			System.err.println("IataAirlineCollectionReader needs work");
			e.printStackTrace();
		}

	}

}
