package de.hawhh.reisebuchung.mietwagen;

import java.time.LocalDateTime;

public class Uebergabe {
	private Station station;
	private LocalDateTime zeitpunkt;

	public Uebergabe(Station station, LocalDateTime zeitpunkt) {
		this.station = station;
		this.zeitpunkt = zeitpunkt;
	}

	public Station getStation() {
		return station;
	}

	public LocalDateTime getZeitpunkt() {
		return zeitpunkt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result
				+ ((zeitpunkt == null) ? 0 : zeitpunkt.hashCode());
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
		Uebergabe other = (Uebergabe) obj;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		if (zeitpunkt == null) {
			if (other.zeitpunkt != null)
				return false;
		} else if (!zeitpunkt.equals(other.zeitpunkt))
			return false;
		return true;
	}
}
