package de.hawhh.kosten;

public abstract class AbstractGeldBetrag implements GeldBetrag {
	protected int geldbetrag;
	
	@Override
	public abstract double inBasis();

	@Override
	public abstract GeldBetrag ausBasis(double basis);

	@Override
	public GeldBetrag add(GeldBetrag p2) {
		return ausBasis(inBasis() + p2.inBasis());
	}

	@Override
	public GeldBetrag sub(GeldBetrag p2) {
		return ausBasis(inBasis() - p2.inBasis());
	}

	@Override
	public GeldBetrag div(double d) {
		return ausBasis(inBasis() / d);
	}

	@Override
	public GeldBetrag mult(double d) {
		return ausBasis(inBasis() * d);
	}

	@Override
	public abstract int getMajor();

	@Override
	public abstract int getMinor();

	@Override
	public abstract String getMinorSymbol();

	@Override
	public abstract String getMajorSymbol();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) this.inBasis();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (GeldBetrag.class != obj.getClass().getSuperclass())
			return false;
		double otherGeldbetrag = ((GeldBetrag) obj).inBasis();
		if (otherGeldbetrag != this.inBasis())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getMajor() + "," + getMinor() + getMajorSymbol();
	}
}
