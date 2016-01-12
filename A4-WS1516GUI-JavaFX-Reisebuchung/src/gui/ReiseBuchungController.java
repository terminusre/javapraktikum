package gui;

import iata.airport.IataAirport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import model.DirektFlugWrapper;
import model.RundReiseWrapper;
import de.hawhh.reisebuchung.RundReise;
import de.hawhh.reisebuchung.flug.DirektFlug;
import de.hawhh.reisebuchung.utils.FlugGenerator;

public class ReiseBuchungController {
	private RundReiseWrapper rundReiseWrapper;
	RundReise rundreise = new RundReise();

	FlugGenerator flugGenerator = new FlugGenerator();

	// left panel
	@FXML
	TextField kundenauswahlfeld;
	@FXML
	Label left_panel_abflughafen;
	@FXML
	Label left_panel_ankunftflughafen;
	@FXML
	Label left_panel_abflugszeit;
	@FXML
	Label left_panel_ankunftszeit;
	@FXML
	Label left_panel_flugdauer;
	@FXML
	Label left_panel_preis;
	@FXML
	Button flugEntfernenButton;
	@FXML
	ListView<DirektFlugWrapper> left_panel_listeGebuchterFluege;

	// right panel
	@FXML
	Button uebernehmenButton;
	@FXML
	Button suchenButton;
	@FXML
	ChoiceBox<IataAirport> right_panel_abflughafenDropdownMenue;
	@FXML
	ChoiceBox<IataAirport> right_panel_ankunftflughafenDropdownMenue;
	@FXML
	DatePicker right_panel_kalenderBox;
	@FXML
	TableView<DirektFlugWrapper> right_panel_listeVerfuegbarerFluege;
	//
	@FXML
	TableColumn<DirektFlugWrapper, String> flugnummernColumn;
	@FXML
	TableColumn<DirektFlugWrapper, String> abflughafenColumn;
	@FXML
	TableColumn<DirektFlugWrapper, String> ankunftflughafenColumn;
	@FXML
	TableColumn<DirektFlugWrapper, String> abflugszeitColumn;
	@FXML
	TableColumn<DirektFlugWrapper, String> ankunftszeitColumn;
	@FXML
	TableColumn<DirektFlugWrapper, String> preisColumn;
	@FXML
	TextField right_panel_uhrzeitBox;
	@FXML
	TextField right_panel_tageBox;

	List<DirektFlug> verfuegbareFluege;

	@FXML
	protected void initialize() {

		// choice boxen initialisieren
		List<IataAirport> airports = flugGenerator.getAirports();
		right_panel_abflughafenDropdownMenue.getItems().addAll(airports);
		right_panel_ankunftflughafenDropdownMenue.getItems().addAll(airports);

		// Initialisierung der Kalenderbox
		final String pattern = "yyyy-MM-dd";

		right_panel_kalenderBox.setValue(LocalDate.now());
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter
					.ofPattern(pattern);

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else
					return null;
			}

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else
					return "";
			}
		};
		right_panel_kalenderBox.setConverter(converter);
		right_panel_kalenderBox.requestFocus();

		right_panel_uhrzeitBox.setTextFormatter(new TextFormatter<LocalTime>(
				new LocalTimeStringConverter(FormatStyle.SHORT)));

		right_panel_tageBox.setTextFormatter(new TextFormatter<Integer>(
				new IntegerStringConverter()));

		// left_panel_abflughafen.textProperty().bind(
		// rundReiseWrapper.getVonProperty());
		// left_panel_ankunftflughafen.textProperty().bind(
		// (rundReiseWrapper.getNachProperty()));
		// left_panel_ankunftszeit.textProperty().bind(
		// (rundReiseWrapper.getAnProperty()));
		// left_panel_abflugszeit.textProperty().bind(
		// (rundReiseWrapper.getAbProperty()));
		// left_panel_preis.textProperty().bind(
		// rundReiseWrapper.getPreisProperty().asString());
		// left_panel_preis.textProperty().bind(
		// rundReiseWrapper.getPreisProperty().asString());

		flugnummernColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"flugNr"));
		abflughafenColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"von"));
		ankunftflughafenColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"nach"));
		abflugszeitColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"ab"));
		ankunftszeitColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"an"));
		preisColumn
				.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>(
						"preis"));

		suchenButton
				.setOnAction((ActionEvent e) -> {
					// Ueberpruefe alle noetigen Werte auf Validitaet
					if (right_panel_abflughafenDropdownMenue.getValue() != null
							&& right_panel_ankunftflughafenDropdownMenue
									.getValue() != null
							&& right_panel_kalenderBox.getValue() != null
							&& right_panel_uhrzeitBox.getText() != null
							&& right_panel_tageBox.getText() != null) {
						ObservableList<DirektFlugWrapper> l = FXCollections
								.observableArrayList();
						verfuegbareFluege = flugGenerator
								.generateDirektFlugListe(LocalDateTime
										.parse(right_panel_kalenderBox
												.getValue()
												+ "T"
												+ right_panel_uhrzeitBox
														.getText() + ":00"),
										Integer.valueOf(right_panel_tageBox
												.getText()),
										right_panel_abflughafenDropdownMenue
												.getValue(),
										right_panel_ankunftflughafenDropdownMenue
												.getValue());

						right_panel_listeVerfuegbarerFluege.setEditable(true);

						for (DirektFlug f : verfuegbareFluege)
							l.add(new DirektFlugWrapper(f));
						right_panel_listeVerfuegbarerFluege.getItems()
								.addAll(l);
					}
				});

		uebernehmenButton.setOnAction((ActionEvent e) -> {
			DirektFlugWrapper f = right_panel_listeVerfuegbarerFluege
					.selectionModelProperty().get().getSelectedItem();
			left_panel_listeGebuchterFluege.getItems().add(f);
			right_panel_listeVerfuegbarerFluege.getItems().remove(f);
		});

		flugEntfernenButton.setOnAction((ActionEvent e) -> {
			DirektFlugWrapper f = left_panel_listeGebuchterFluege
					.selectionModelProperty().get().getSelectedItem();
			left_panel_listeGebuchterFluege.getItems().remove(f);
			right_panel_listeVerfuegbarerFluege.getItems().add(f);
		});
	}
}
