package de.hawhh.kosten;

public abstract class AbstractGeldBetrag implements GeldBetrag {

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
	public String toString() {
		return getMajor() + "," + getMinorSymbol() + getMajorSymbol();
	}
}
