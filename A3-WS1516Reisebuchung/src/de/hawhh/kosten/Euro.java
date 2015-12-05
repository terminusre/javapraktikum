package de.hawhh.kosten;

public class Euro extends AbstractGeldBetrag {
	private int geldbetrag;

	public Euro(int geldbetrag) {
		this.geldbetrag = geldbetrag;
	}

	@Override
	public double inBasis() {
		return (double) geldbetrag;
	}

	@Override
	public GeldBetrag ausBasis(double basis) {
		return new Euro(geldbetrag);
	}

	@Override
	public int getMajor() {
		return geldbetrag / 100;
	}

	@Override
	public int getMinor() {
		return geldbetrag % 100;
	}

	@Override
	public String getMinorSymbol() {
		return "¢";
	}

	@Override
	public String getMajorSymbol() {
		return "€";
	}

}
