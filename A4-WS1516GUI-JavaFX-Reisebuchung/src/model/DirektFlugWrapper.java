package model;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.flug.DirektFlug;

public class DirektFlugWrapper {
	
	private DirektFlug direktFlug;
	private SimpleStringProperty vonProperty;
	private SimpleStringProperty nachProperty;
	private SimpleStringProperty abProperty;
	private SimpleStringProperty anProperty;
	private SimpleStringProperty flugNrProperty;
	private SimpleObjectProperty<GeldBetrag> preisProperty;
	
	public static List<DirektFlugWrapper> wrap(List<DirektFlug> dfl) {
		return dfl.stream().map(df -> new DirektFlugWrapper(df)).collect(Collectors.toList());
	}

	public DirektFlugWrapper(DirektFlug direktFlug) {
		this.direktFlug = direktFlug;
		this.flugNrProperty = new SimpleStringProperty(direktFlug.getFlugNummer().toString());
		this.vonProperty = new SimpleStringProperty(direktFlug.getStartOrt().toString());
		this.nachProperty = new SimpleStringProperty(direktFlug.getEndOrt().toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.abProperty = new SimpleStringProperty(direktFlug.getBeginn().format(formatter));
		this.anProperty = new SimpleStringProperty(direktFlug.getEnde().format(formatter));	
		this.preisProperty = new SimpleObjectProperty<GeldBetrag>(direktFlug.getPreis());
	}

	public String getFlugNr() {
		return flugNrProperty.get();
	}

	public void setFlugNr(String flugNr) {
		this.flugNrProperty.set(flugNr);
	}

	public String getVon() {
		return vonProperty.get();
	}

	public void setVon(String von) {
		this.vonProperty.set(von);
	}

	public String getNach() {
		return nachProperty.get();
	}

	public void setNach(String nach){
		this.nachProperty.set(nach);
	}

	public String getAb() {
		return abProperty.get();
	}

	public void setAb(String ab) {
		this.abProperty.set(ab);
	}

	public String getAn() {
		return anProperty.get();
	}

	public void setAn(String an) {
		this.anProperty.set(an);
	}

	public DirektFlug getDirektFlug() {
		return direktFlug;
	}
	
	public GeldBetrag getPreis() {
		return preisProperty.get();
	}
	
	public void setPreis(GeldBetrag preis) {
		this.preisProperty.set(preis);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direktFlug == null) ? 0 : direktFlug.hashCode());
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
		DirektFlugWrapper other = (DirektFlugWrapper) obj;
		if (direktFlug == null) {
			if (other.direktFlug != null)
				return false;
		} else if (!direktFlug.equals(other.direktFlug))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return direktFlug.toString();
	}
}
