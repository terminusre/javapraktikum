package de.hawhh.reisebuchung.mietwagen;

public class Mietwagen {
	private MietwagenAnbieter anbieter;
	private WagenTyp typ;

	public Mietwagen(MietwagenAnbieter anbieter, WagenTyp typ) {
		this.anbieter = anbieter;
		this.typ = typ;
	}

	public WagenTyp getWagenTyp() {
		return typ;
	}

	public MietwagenAnbieter getMietwagenAnbieter() {
		return anbieter;
	}

	@Override
	public String toString() {
		return "Mietwagen: Anbieter = " + anbieter + ", Typ = " + typ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anbieter == null) ? 0 : anbieter.hashCode());
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
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
		Mietwagen other = (Mietwagen) obj;
		if (anbieter == null) {
			if (other.anbieter != null)
				return false;
		} else if (!anbieter.equals(other.anbieter))
			return false;
		if (typ == null) {
			if (other.typ != null)
				return false;
		} else if (!typ.equals(other.typ))
			return false;
		return true;
	}

}
