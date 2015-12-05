package de.hawhh.reisebuchung.mietwagen;

import de.hawhh.reisebuchung.Ort;

public class Station {
	private Ort station;

	public Station(Ort station) {
		this.station = station;
	}

	public Ort getOrt() {
		return station;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		return true;
	}

}
