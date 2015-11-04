package iata.airport;

import iata.Iata;

public class IataAirport implements Iata {
	String code = null;
	String country = null;
	String name = null;

	public IataAirport(String code, String country, String name) {
		this.code = code;
		this.country = country;
		this.name = name;
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
	public String toString(){
		return "Code: " + code + ", Country: " + country + ", Name: " + name;
	}

}
