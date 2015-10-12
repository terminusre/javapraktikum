package application;

import javafx.scene.image.Image;

public abstract class Filter {

	public static final Filter IDENTITY = new Filter() {
		@Override
		public String toString() {
			return "identity";
		}

		@Override
		double[][] generateFilter() {
			// TODO Auto-generated method stub
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

	abstract double[][] generateFilter();

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

	public Filter() {
		// TODO
	}

	public Filter(double bias) {
		// TODO
	}

	private double calculateFactor() {
		// TODO
		return 0.0;
	}
}
