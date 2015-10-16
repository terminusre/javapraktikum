package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class Filter {

	double _bias;

	public Filter(double bias) {
		_bias = bias;
	}

	public Filter() {
		this(0.0);
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
			double factor = calculateFactor();
			pf(filter);
			System.out.println("");

			Color col;
			// iterate pixel by pixel over whole image
			for (int y = 0; y < imgHeight; y++) {
				for (int x = 0; x < imgWidth; x++) {

					double[] rgbo = { 0.0, 0.0, 0.0, 1.0 };

					// iterate over filter matrix
					for (int yf = 0; yf < filter.length; yf++) {
						for (int xf = 0; xf < filter[0].length; xf++) {
							if (x + xf - (filter[0].length / 2) > -1
									&& x + xf - (filter[0].length / 2) < imgWidth
									&& y + yf - (filter.length / 2) > -1
									&& y + yf - (filter.length / 2) < imgHeight) {

								col = reader.getColor(x + xf
										- (filter[0].length / 2), y + yf
										- (filter.length / 2));
								rgbo[0] += col.getRed() * filter[yf][xf];
								rgbo[1] += col.getGreen() * filter[yf][xf];
								rgbo[2] += col.getBlue() * filter[yf][xf];
							}

						}
					}

					for (int i = 0; i < 3; i++) {
						rgbo[i] = (rgbo[i] * factor) + _bias;
						if (rgbo[i] < 0.0)
							rgbo[i] = 0.0;
						if (rgbo[i] > 1.0)
							rgbo[i] = 1.0;
					}
					

					writer.setColor(x, y, new Color(rgbo[0], rgbo[1], rgbo[2],
							rgbo[3]));
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return filteredImage;
	}

	private double calculateFactor() {

		double[][] filter = generateFilter();
		double factor = 0.0;

		for (int y = 0; y < filter.length; y++) {
			for (int x = 0; x < filter[0].length; x++) {
				factor += filter[y][x];
			}
		}

		if (factor == 0.0)
			factor = 1.0;

		return 1.0 / factor;
	}

	private static void pf(double[][] filter) {
		for (int y = 0; y < filter.length; y++) {
			for (int x = 0; x < filter[0].length; x++) {
				System.out.print(filter[y][x] + " ");
			}
			System.out.println("");
		}
	}
	
	abstract double[][] generateFilter();

	public static final Filter IDENTITY = new Filter() {
		@Override
		public String toString() {
			return "identity";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[3][3];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = 0.0;
				}
			}

			filter[1][1] = 1.0;

			return filter;
		}

	};

	public static final Filter BLUR = new Filter() {
		@Override
		public String toString() {
			return "blur";
		}

		@Override
		double[][] generateFilter() {
			return diamond(3, 3, 0.2);
		}

	};

	private static double[][] diamond(int xlen, int ylen, double val) {
		double[][] filter = new double[xlen][ylen];

		int delta = 0;
		for (int y = 0; y < filter.length; y++) {

			if (y <= filter.length / 2)
				delta = y;
			else
				delta = filter.length - 1 - y;

			for (int x = 0; x < filter[0].length; x++) {

				if (x == (filter.length / 2) - delta
						|| x == (filter.length / 2) + delta)
					filter[y][x] = val;
				else if (x > (filter.length / 2) + delta
						|| x < (filter.length / 2) - delta)
					filter[y][x] = 0.0;
				else
					filter[y][x] = val;
			}
		}

		return filter;
	}

	public static final Filter STRONG_BLUR = new Filter() {
		public String toString() {
			return "strong blur";
		}

		@Override
		double[][] generateFilter() {
			return diamond(7, 7, 1.0);
		}

	};

	public static final Filter EDGE_DETECT = new Filter() {

		public String toString() {
			return "edge detect";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[3][3];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = -1.0;
				}
			}

			filter[1][1] = 8.0;

			return filter;
		};

	};

	public static final Filter MOTION_BLUR = new Filter() {
		public String toString() {
			return "motion blur";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[9][9];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = 0.0;
					if (x == y)
						filter[y][x] = 1.0;
				}
			}

			return filter;
		}

	};

	public static final Filter HORIZONTAL_EDGES = new Filter() {
		public String toString() {
			return "horizontal edges";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[5][5];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = 0.0;
				}
			}

			filter[2][0] = -1.0;
			filter[2][1] = -1.0;
			filter[2][2] = 2.0;

			return filter;
		}

	};
	public static final Filter VERTICAL_EDGES = new Filter() {
		public String toString() {
			return "vertical edges";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[5][5];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = 0.0;
					if (x == 2)
						filter[y][x] = -1.0;
				}
			}

			filter[2][2] = 4.0;

			return filter;
		}
	};

	public static final Filter SHARPEN = new Filter() {
		public String toString() {
			return "sharpen";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[3][3];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					filter[y][x] = -1.0;
				}
			}

			filter[1][1] = 9.0;

			return filter;
		}

	};

	public static final Filter SUBTLE_SHARPEN = new Filter() {
		public String toString() {
			return "subtle sharpen";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[5][5];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					if (x == 0 || x == filter[0].length - 1 || y == 0
							|| y == filter[0].length - 1)
						filter[y][x] = -1.0;
					else
						filter[y][x] = 2.0;
				}
			}

			filter[2][2] = 8.0;

			return filter;
		}

	};

	public static final Filter EMBOSS = new Filter(0.5) {
		public String toString() {
			return "emboss";
		}

		@Override
		double[][] generateFilter() {
			double[][] filter = new double[3][3];

			for (int y = 0; y < filter.length; y++) {
				for (int x = 0; x < filter[0].length; x++) {
					if (x + y <= 1)
						filter[y][x] = -1.0;
					else if (x + y == 2)
						filter[y][x] = 0.0;
					else
						filter[y][x] = 1.0;
				}
			}

			return filter;
		}
	};

}
