package application;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ConvolutionController {

	@FXML
	private ChoiceBox<String> imagePicker;

	@FXML
	private ImageView origImage;

	@FXML
	private ComboBox<Filter> effectChoice;

	@FXML
	private ImageView filteredImage;

	@FXML
	protected void initialize() {
		String[] imageNames = { "Apples", "Flowers", "Flowers2", "Blumenwiese",
				"Leaves" };
		imagePicker.getItems().addAll(imageNames);
		imagePicker
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(ObservableValue<? extends String> ov, String oldName,
								String newName) -> {
							if (newName != null) {
								Image newImage = new Image(getClass()
										.getResourceAsStream(newName + ".jpg"));
								origImage.setFitWidth(newImage.getWidth());
								origImage.setFitHeight(newImage.getHeight());
								origImage.setImage(newImage);
								filteredImage.setImage(null);
							}
						});

		imagePicker.setValue("Blumenwiese");
		effectChoice.getItems().addAll(Filter.IDENTITY, Filter.BLUR, Filter.STRONG_BLUR,
				Filter.MOTION_BLUR, Filter.EDGE_DETECT,
				Filter.HORIZONTAL_EDGES, Filter.VERTICAL_EDGES, Filter.SHARPEN,
				Filter.SUBTLE_SHARPEN, Filter.EMBOSS);
		effectChoice
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(ObservableValue<? extends Filter> ov, Filter oldF,
								Filter newF) -> {
							if (newF != null) {
								filteredImage.setImage(newF.apply(origImage
										.getImage()));
							}
						});
		effectChoice.setValue(Filter.BLUR);
	}
}
