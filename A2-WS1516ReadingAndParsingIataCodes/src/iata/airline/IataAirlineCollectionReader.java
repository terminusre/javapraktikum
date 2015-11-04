package iata.airline;

import java.net.URL;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iata.AbstractIataCollectionReader;
import iata.Iata;

public class IataAirlineCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {
		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();
		IataAirline a = null;
		return null;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {
		return null;
	}

}
