package model;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import de.hawhh.kosten.Euro;
import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.RundReise;

public class RundReiseWrapper {

	private SimpleStringProperty vonProperty;
	private SimpleStringProperty nachProperty;
	private SimpleStringProperty abProperty;
	private SimpleStringProperty anProperty;
	private SimpleObjectProperty<GeldBetrag> preisProperty;
	private RundReise reise;
	private SimpleObjectProperty<Duration> dauerProperty;

	public RundReiseWrapper(RundReise reise) {
		this.reise = reise;
		this.vonProperty = new SimpleStringProperty(
				(reise.getStartOrt() == null) ? "" : reise.getStartOrt()
						.toString());
		this.nachProperty = new SimpleStringProperty(
				reise.getEndOrt() == null ? "" : reise.getEndOrt().toString());
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("dd-MM-yyyy HH:mm");
		this.abProperty = new SimpleStringProperty(
				reise.getBeginn() == null ? "" : reise.getBeginn().format(
						formatter));
		this.anProperty = new SimpleStringProperty(reise.getEnde() == null ? ""
				: reise.getEnde().format(formatter));
		this.dauerProperty = new SimpleObjectProperty<Duration>(
				reise.getDauer()==null? Duration.ofMinutes(0) : reise.getDauer());
		this.preisProperty = new SimpleObjectProperty<GeldBetrag>(
				reise.getPreis() == null ? new Euro(0) : reise.getPreis());
	}

	public void add(DirektFlugWrapper wrapper) {
		reise.add(wrapper.getDirektFlug());
		updateProperties();
	}

	public void remove(DirektFlugWrapper wrapper) {
		reise.remove(wrapper.getDirektFlug());
		updateProperties();
	}

	private void updateProperties() {
		setVon((reise.getStartOrt()== null)? "" : reise.getStartOrt()
				.toString());
		setNach(reise.getEndOrt()== null ? "": reise.getEndOrt()
				.toString());
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("dd-MM-yyyy HH:mm");
		setAb(reise.getBeginn()==null ?  "": reise.getBeginn().format(
				formatter));
		setAn(reise.getEnde()==null? "" : reise.getEnde().format(
				formatter));
		setDauer((reise.getDauer()==null) ? Duration.ofMinutes(0) : reise.getDauer());
		setPreis(reise.getPreis()== null? new Euro(0) : 
			reise.getPreis());
	}

	public void setDauer(Duration dauer) {
		System.out.println(dauer.toMinutes());
		this.dauerProperty.set(dauer);
	}

	public Duration getDauer() {
		return this.dauerProperty.get();
	}

	public SimpleStringProperty getVonProperty() {
		return vonProperty;
	}

	public SimpleStringProperty getNachProperty() {
		return nachProperty;
	}

	public SimpleStringProperty getAbProperty() {
		return abProperty;
	}

	public SimpleStringProperty getAnProperty() {
		return anProperty;
	}

	public SimpleObjectProperty<GeldBetrag> getPreisProperty() {
		return preisProperty;
	}

	public RundReise getReise() {
		return reise;
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

	public void setNach(String nach) {
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

	public GeldBetrag getPreis() {
		return preisProperty.get();
	}

	public void setPreis(GeldBetrag preis) {
		this.preisProperty.set(preis);
	}

	public SimpleObjectProperty<Duration> getDauerProperty() {
		return dauerProperty;
	}
}
