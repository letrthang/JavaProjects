
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

		// 1. draw canvas (width_X, height_Y)
		drawCanvas(25, 10);
		// 2. draw vertical line
		drawLine(2, 3, 2, 8);
		// 3. draw horizon line
		drawLine(2, 8, 8, 8);
		// 4. draw rectangle
		drawRectangle(10, 2, 20, 6);
		// 5. fill in bucket
		bucketFill(8, 3, 'c');
		bucketFill(12, 3, '@');
		
		draw2Screen();
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

		// fill in all pixels of screen

		for (int n = 0; n < height; n++) {
			for (int m = 0; m < width; m++) {
				if (n == 0 || n == height - 1) {
					screen[n][m].setText('-');
				} else if (m == 0 || m == width - 1) {
					screen[n][m].setText('|');
				}
			}
		}
	}

	/**
	 * Draw line. coordinate (x,y) <=> array[y][x]
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	static void drawLine(int x1, int y1, int x2, int y2) {

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

	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	static void drawRectangle(int x1, int y1, int x2, int y2) {
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
	 * @param x
	 * @param y
	 * @param color
	 */
	static void bucketFill(int x, int y, char color) {
		// recursive to fill bucket.
		// Note: coordinate (x,y) <=> array[y][x]
		
		if (screen[y][x].getText() == ' ') {

			screen[y][x].setText(color);

			bucketFill(x + 1, y, color);
			bucketFill(x - 1, y, color);
			bucketFill(x, y + 1, color);
			bucketFill(x, y - 1, color);
		}
	}

	/**
	 * actual draw to console
	 */
	static void draw2Screen() {

		System.out.println("Draw canvas: " + "width (X):" + screen[0].length + ", height (Y): " + screen.length);

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
