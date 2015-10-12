package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class Filter {

	private Image _image;
	PixelReader _reader;
	private WritableImage _filteredImage;
	PixelWriter _writer;

	public Filter() {
		try {
			Image _image = new Image(getClass().getResourceAsStream(
					"Blumenwiese.jpg"));
			_reader = _image.getPixelReader();
			//Color col = _reader.getColor(1, 1);// (readX, readY);

			_filteredImage = new WritableImage((int)_image.getWidth(), (int) _image.getHeight());
			_writer = _filteredImage.getPixelWriter();
			//_writer.setColor(1, 1, col);// (posX,poxY,color);

			/*
			 * System.out.println("data " + (int) _image.getWidth() + " / " +
			 * (int) _image.getHeight());
			 */

		} catch (Exception e) {
			System.out.println("Satz mit X, das war wohl nix!");
		}
	}

	abstract double[][] generateFilter();

	private double[][] init() {
		double[][] filter;
		int lines = (int) _image.getHeight();
		int linewidth = (int) _image.getWidth();

		filter = new double[lines][];

		for (int i = 0; i < lines; i++) {
			filter[i] = new double[linewidth];
		}

		return filter;
	}

	public static final Filter IDENTITY = new Filter() {
		@Override
		public String toString() {
			return "identity";
		}

		@Override
		double[][] generateFilter() {
			//double[][] filter = init();
			return null;
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

	public Image apply(Image origImage) {
		return null;
	}

	/*
	 * public Filter() { // TODO }
	 */

	public Filter(double bias) {
		// TODO
	}

	private double calculateFactor() {
		// TODO
		return 0.0;
	}
}
