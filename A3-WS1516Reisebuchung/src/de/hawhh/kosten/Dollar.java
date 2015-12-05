package de.hawhh.kosten;

public class Dollar extends AbstractGeldBetrag {
	private int geldbetrag;
	private double dollar_to_euro = 0.92;

	public Dollar(int geldbetrag) {
		this.geldbetrag = geldbetrag;
	}

	@Override
	public double inBasis() {
		return geldbetrag * dollar_to_euro;
	}

	@Override
	public GeldBetrag ausBasis(double basis) {
		return new Dollar((int) (basis / dollar_to_euro));
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
		return "�";
	}

	@Override
	public String getMajorSymbol() {
		return "$";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + geldbetrag;
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

}
