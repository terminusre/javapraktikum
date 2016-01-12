package iata.airport;

import iata.Iata;

public class IataAirport implements Iata {
	String code = null;
	String country = null;
	String name = null;
	String location = null;

	public IataAirport(String code, String name, String country, String location) {
		this.code = code;
		this.name = name;
		this.country = country;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return code;
		// return code + ":" + name + ":" + country + ":" + location;
	}

}
