
/**
 * @author Thang Le
 * 
 *         key idea is to create a 2D array of Pixels for output screen. Fill in
 *         data to that Pixels. Last is to draw all the Pixels to console. Note:
 *         coordinate (x,y) <=> array[y][x]
 *
 */
public class Main {
	static Pixel[][] screen;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test code
		// we just do a simple test code here and see results in console.
		// It is not simple to write unit test here because outputs to draw data to
		// console.

		// 1. draw canvas (width_X, height_Y)
		drawCanvas(25, 10);
		// 2. draw vertical line
		drawLine(2, 3, 2, 8);
		// 3. draw horizon line
		drawLine(2, 8, 8, 8);
		// 4. draw rectangle
		drawRectangle(10, 2, 20, 6);
		// 5. fill in bucket with color 'c'
		bucketFillConsole(8, 2, 'c');
		// 6. fill in bucket with color '@'
		bucketFillConsole(12, 3, '@');

	}

	/**
	 * draw canvas
	 * 
	 * @param width  (X)
	 * @param height (Y)
	 */
	static void drawCanvas(int width, int height) {
		screen = new Pixel[height][width];

		// init all pixels of screen
		for (int n = 0; n < height; n++) {
			for (int m = 0; m < width; m++) {
				screen[n][m] = new Pixel();
				screen[n][m].setX(m);
				screen[n][m].setY(n);
				screen[n][m].setText(' ');
			}
		}

		// fill in data for all pixels of screen

		for (int n = 0; n < height; n++) {
			for (int m = 0; m < width; m++) {
				if (n == 0 || n == height - 1) {
					screen[n][m].setText('-');
				} else if (m == 0 || m == width - 1) {
					screen[n][m].setText('|');
				}
			}
		}

		System.out.println("Draw canvas: " + "width (X):" + screen[0].length + ", height (Y): " + screen.length);

		// display Pixels to console
		draw2Screen();

	}

	/**
	 * Draw line. Note: coordinate (x,y) <=> array[y][x]
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	static void drawLine(int x1, int y1, int x2, int y2) {
		// 1. do basic input validation
		if (x1 > screen[0].length || x2 > screen[0].length || y1 > screen.length || y2 > screen.length) {
			System.out.println("drawLine: points are out of canvas");
			return;
		}

		if (x1 == x2) {
			for (int i = y1; i <= y2; i++) {
				screen[i][x1].setText('x');
			}
		} else if (y1 == y2) {
			for (int i = x1; i <= x2; i++) {
				screen[y1][i].setText('x');
			}
		} else {

		}

		// display Pixels to console
		draw2Screen();
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	static void drawRectangle(int x1, int y1, int x2, int y2) {
		// we ignore inputs validation here

		System.out.println("Start Draw Rectangle");
		// we reuse "drawLine" function which causes rectangle repeat drawing multiple
		// times, but it is fine :))

		// top edge (x1,y1) to (x2,y1)
		drawLine(x1, y1, x2, y1);
		// bottom edge (x1,y2) to (x2,y2)
		drawLine(x1, y2, x2, y2);
		// left edge (x1,y1) to (x1,y2)
		drawLine(x1, y1, x1, y2);
		// right edge (x2,y1) to (x2,y2)
		drawLine(x2, y1, x2, y2);

	}

	/**
	 * actual fill bucket to console
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	static void bucketFillConsole(int x, int y, char color) {
		System.out.println("Fill Bucket with " + color);

		bucketFill(x, y, color);
		// display Pixels to console
		draw2Screen();
	}

	/**
	 * only fill color of related Pixels
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	static void bucketFill(int x, int y, char color) {
		// using recursive algorithm to fill bucket.
		// Note: coordinate (x,y) <=> array[y][x]

		if (x > screen[0].length || y > screen.length) {
			System.out.println("Fill bucket: point is out of canvas");
			return;
		}

		if (screen[y][x].getText() == ' ') {

			screen[y][x].setText(color);

			bucketFill(x + 1, y, color);
			bucketFill(x - 1, y, color);
			bucketFill(x, y + 1, color);
			bucketFill(x, y - 1, color);
		}

	}

	/**
	 * actual draw Pixels data to console
	 */
	static void draw2Screen() {

		for (int n = 0; n < screen.length; n++) {

			for (int m = 0; m < screen[0].length; m++) {

				if (screen[n][m].getText() != 0) {
					System.out.print(screen[n][m].getText());
				} else {
					System.out.print(" ");
				}
			}

			// draw new line
			System.out.print("\n");
		}
	}
}
