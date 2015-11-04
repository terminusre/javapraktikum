package iata.airport;

import iata.Iata;

public class IataAirport implements Iata {
	String code = null;
	String country = null;
	String name = null;

	public IataAirport(String code, String name, String country) {
		this.code = code;
		this.name = name;
		this.country = country;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return code + ":" + country + ":" + name;
	}

}
