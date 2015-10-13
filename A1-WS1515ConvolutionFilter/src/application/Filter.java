package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class Filter {

	public Filter() {
		try {
			Image image = new Image(getClass().getResourceAsStream(
					"Blumenwiese.jpg"));
			apply(image);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Image apply(Image origImage) {

		WritableImage filteredImage = null;
		try {

			PixelReader reader = origImage.getPixelReader();

			int imgHeight = (int) origImage.getHeight();
			int imgWidth = (int) origImage.getWidth();

			filteredImage = new WritableImage(imgWidth, imgHeight);
			PixelWriter writer = filteredImage.getPixelWriter();

			double[][] filter = generateFilter();

			Color col;
			for (int y = 0; y < imgHeight; y++) {
				for (int x = 0; x < imgWidth; x++) {
					col = reader.getColor(x, y); // (readX, readY)
					writer.setColor(x, y,
							new Color(col.getRed(), 0.0, 0.0, 1.0));// col.getGreen(),
																	// col.getBlue()));
																	// // (posX,
																	// poxY,
																	// color);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return filteredImage;
	}

	public Filter(double bias) {
		// TODO
	}

	private double calculateFactor() {
		// TODO
		return 0.0;
	}

	abstract double[][] generateFilter();

	public static final Filter IDENTITY = new Filter() {
		@Override
		public String toString() {
			return "identity";
		}

		@Override
		double[][] generateFilter() {
			return new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 },
					{ 0.0, 0.0, 0.0 } };
		}

	};

	public static final Filter BLUR = new Filter() {
		@Override
		public String toString() {
			return "blur";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public static final Filter STRONG_BLUR = new Filter() {
		public String toString() {
			return "strong blur";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public static final Filter EDGE_DETECT = new Filter() {

		public String toString() {
			return "edge detect";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		};

	};

	public static final Filter MOTION_BLUR = new Filter() {
		public String toString() {
			return "motion blur";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public static final Filter HORIZONTAL_EDGES = new Filter() {
		public String toString() {
			return "horizontal edges";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};
	public static final Filter VERTICAL_EDGES = new Filter() {
		public String toString() {
			return "vertical edges";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public static final Filter SHARPEN = new Filter() {
		public String toString() {
			return "sharpen";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public static final Filter SUBTLE_SHARPEN = new Filter() {
		public String toString() {
			return "subtle sharpen";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}

	};

	public static final Filter EMBOSS = new Filter(0.5) {
		public String toString() {
			return "emboss";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
