package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReiseBuchungController extends Application {

	// left panel
	@FXML
	TextField kundenauswahlfeld = new TextField();
	@FXML
	Button flugEntfernen  = new Button();

	@FXML
	Label abflughafen = new Label();
	@FXML
	Label ankunftflughafen = new Label();
	@FXML
	Label abflugszeit = new Label();
	@FXML
	Label ankunftszeit = new Label();
	@FXML
	Label flugdauer = new Label();
	@FXML
	Label gesamtpreis = new Label();
	// right panel

	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = (HBox) FXMLLoader.load(getClass().getResource(
					"ReiseBuchung.fxml"));
			Scene scene = new Scene(root); // ,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Reisen");
			
			Button schwerVonKape;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
