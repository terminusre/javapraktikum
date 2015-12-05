package de.hawhh.reisebuchung.hotel;

import java.time.LocalDateTime;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;
import de.hawhh.reisebuchung.ReiseBaustein;

public class HotelBaustein extends ReiseBaustein {
	
	private Hotel hotel;
	private LocalDateTime ankunft;
	private LocalDateTime abreise;
	private ZimmerTyp zimmer;
	private Verpflegung verpfl;

	public HotelBaustein(Hotel hotel, LocalDateTime ankunft, LocalDateTime abreise, ZimmerTyp zimmer, Verpflegung verpfl){
		this.hotel = hotel;
		this.ankunft = ankunft;
		this.abreise = abreise;
		this.zimmer = zimmer;
		this.verpfl = verpfl;	
	}

	@Override
	public LocalDateTime getBeginn() {
		return ankunft;
	}

	@Override
	public LocalDateTime getEnde() {
		return abreise;
	}

	@Override
	public GeldBetrag getPreis() {
		return hotel.getPreis(zimmer,verpfl,getDauer());
	}

	@Override
	public Ort getEndOrt() {
		return hotel.getOrt();
	}

	@Override
	public Ort getStartOrt() {
		return hotel.getOrt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreise == null) ? 0 : abreise.hashCode());
		result = prime * result + ((ankunft == null) ? 0 : ankunft.hashCode());
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + ((verpfl == null) ? 0 : verpfl.hashCode());
		result = prime * result + ((zimmer == null) ? 0 : zimmer.hashCode());
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
		HotelBaustein other = (HotelBaustein) obj;
		if (abreise == null) {
			if (other.abreise != null)
				return false;
		} else if (!abreise.equals(other.abreise))
			return false;
		if (ankunft == null) {
			if (other.ankunft != null)
				return false;
		} else if (!ankunft.equals(other.ankunft))
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (verpfl != other.verpfl)
			return false;
		if (zimmer != other.zimmer)
			return false;
		return true;
	}

}
