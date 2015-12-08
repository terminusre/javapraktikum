package de.hawhh.kosten;

public interface GeldBetrag {

	public double inBasis();
	public GeldBetrag ausBasis(double basis);
	
	public GeldBetrag add(GeldBetrag p2);
	public GeldBetrag sub(GeldBetrag p2);
	public GeldBetrag div(double d);
	public GeldBetrag mult(double d);

	public int getMajor();
	public int getMinor();
	public String getMinorSymbol();
	public String getMajorSymbol();
}
