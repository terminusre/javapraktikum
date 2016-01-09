package gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import de.hawhh.reisebuchung.flug.DirektFlug;
import de.hawhh.reisebuchung.utils.FlugGenerator;
import iata.airport.IataAirport;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.DirektFlugWrapper;

public class ReiseBuchungController extends Application {

	FlugGenerator flugGenerator = new FlugGenerator();

	// left panel
	@FXML
	TextField kundenauswahlfeld = new TextField();
	@FXML
	Label left_panel_abflughafen = new Label();
	@FXML
	Label left_panel_ankunftflughafen = new Label();
	@FXML
	Label left_panel_abflugszeit = new Label();
	@FXML
	Label left_panel_ankunftszeit = new Label();
	@FXML
	Label left_panel_flugdauer = new Label();
	@FXML
	Label left_panel_preis = new Label();
	@FXML
	Button flugEntfernenButton = new Button();
	@FXML
	ListView<DirektFlug> left_panel_listeGebuchterFluege = new ListView<DirektFlug>();

	// right panel
	@FXML
	Button uebernehmenButton = new Button();
	@FXML
	ChoiceBox<IataAirport> right_panel_abflughafenDropdownMenue = new ChoiceBox<IataAirport>();
	@FXML
	ChoiceBox<IataAirport> right_panel_ankunftflughafenDropdownMenue = new ChoiceBox<IataAirport>();
	@FXML
	DatePicker right_panel_kalenderBox = new DatePicker();
	@FXML
	TableView<DirektFlugWrapper> right_panel_listeVerfuegbarerFluege = new TableView<DirektFlugWrapper>();
	@FXML
	TableColumn<DirektFlugWrapper, String> flugnummernColumn = new TableColumn<DirektFlugWrapper, String>();
	@FXML
	TableColumn<DirektFlugWrapper, String> abflughafenColumn = new TableColumn<DirektFlugWrapper, String>();
	@FXML
	TableColumn<DirektFlugWrapper, String> ankunftflughafenColumn = new TableColumn<DirektFlugWrapper, String>();
	@FXML
	TableColumn<DirektFlugWrapper, String> abflugszeitColumn = new TableColumn<DirektFlugWrapper, String>();
	@FXML
	TableColumn<DirektFlugWrapper, String> ankunftszeitColumn = new TableColumn<DirektFlugWrapper, String>();
	@FXML
	TableColumn<DirektFlugWrapper, String> preisColumn = new TableColumn<DirektFlugWrapper, String>();

	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = (HBox) FXMLLoader.load(getClass().getResource("ReiseBuchung.fxml"));
			Scene scene = new Scene(root); // ,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Reisen");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void initialize() {
		// flugGenerator.generateDirektFlugListe(start, plusDays, von, nach)
		// right_panel_listeVerfuegbarerFluege
		// airportCollectionReader.readLocalCollection()

		// choice boxen initialisieren
		List<IataAirport> airports = flugGenerator.getAirports();
		right_panel_abflughafenDropdownMenue.getItems().addAll(airports);
		right_panel_ankunftflughafenDropdownMenue.getItems().addAll(airports);

		// Initialisierung der Kalenderbox
		final String pattern = "yyyy-MM-dd";

		right_panel_kalenderBox.setValue(LocalDate.now());
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

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
		
		
		
		flugnummernColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("Flg#"));
		abflughafenColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("von"));
		ankunftflughafenColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("nach"));
		abflugszeitColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("ab"));
		ankunftszeitColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("an"));
		preisColumn.setCellValueFactory(new PropertyValueFactory<DirektFlugWrapper, String>("Preis"));

		// flugGenerator.generateDirektFlugListe(start, plusDays, von, nach)
		// right_panel_listeVerfuegbarerFluege.getItems().addAll( );

		uebernehmenButton.setOnAction((ActionEvent e) -> {
			// TODO
		});
		flugEntfernenButton.setOnAction((ActionEvent e) -> {
			// TODO
		});
	}

	public static void main(String[] args) {

		launch(args);
	}

}
