import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Thang Le
 * 
 *         key idea is to create a 2D array of Pixels for output screen. Fill in
 *         data to that Pixels. Last is to draw all the Pixels to console. Note:
 *         coordinate (x,y) <=> array[y][x]
 *
 */
public class Main {
	static Pixel[][] screenData;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Reading data using readLine
		try {
			System.out.println("start draw...");

			for (;;) {
				String input = reader.readLine();

				String command = input.substring(0, 1);
				if (command.equals("C")) {

					// this command is only 2 arguments
					String[] arguments = input.split("\\s+");
					// 1. draw canvas (width_X, height_Y)
					drawCanvas(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));

				} else if (command.equals("L")) {

					// this command is only 4 arguments
					String[] arguments = input.split("\\s+");
					// 2. draw line
					drawLine(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]),
							Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));

				} else if (command.equals("R")) {

					// this command is only 4 arguments
					String[] arguments = input.split("\\s+");
					// 3. draw rectangle
					drawRectangle(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]),
							Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));

				} else if (command.equals("B")) {
					// this command is only 3 arguments
					String[] arguments = input.split("\\s+");
					// 4. fill in bucket with color
					bucketFillConsole(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]),
							arguments[3].charAt(0));

				} else if (command.equals("Q")) {
					System.out.println("quit program ...");
					break;
				} else {
					System.out.println("invalid input...");
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Test code
		// we just do a simple test code here and see results in console.
		// It is not simple to write unit test here because outputs to draw data to
		// console.

		// 1. draw canvas (width_X, height_Y)
		// drawCanvas(25, 10);
		// 2. draw vertical line
		// drawLine(2, 3, 2, 8);
		// 3. draw horizon line
		// drawLine(2, 8, 8, 8);
		// 4. draw rectangle
		// drawRectangle(10, 2, 20, 6);
		// 5. fill in bucket with color 'c'
		// bucketFillConsole(8, 2, 'c');
		// 6. fill in bucket with color '@'
		// bucketFillConsole(12, 3, '@');

	}

	/**
	 * draw canvas
	 * 
	 * @param width  (X)
	 * @param height (Y)
	 */
	static void drawCanvas(int width, int height) {
		screenData = new Pixel[height][width];

		// init all pixels of screen
		for (int n = 0; n < height; n++) {
			for (int m = 0; m < width; m++) {
				screenData[n][m] = new Pixel();
				screenData[n][m].setX(m);
				screenData[n][m].setY(n);
				screenData[n][m].setText(' ');
			}
		}

		// fill in data for all pixels of screen

		for (int n = 0; n < height; n++) {
			for (int m = 0; m < width; m++) {
				if (n == 0 || n == height - 1) {
					screenData[n][m].setText('-');
				} else if (m == 0 || m == width - 1) {
					screenData[n][m].setText('|');
				}
			}
		}

		System.out
				.println("Draw canvas: " + "width (X):" + screenData[0].length + ", height (Y): " + screenData.length);

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
		if (x1 > screenData[0].length || x2 > screenData[0].length || y1 > screenData.length
				|| y2 > screenData.length) {
			System.out.println("drawLine: points are out of canvas");
			return;
		}

		if (x1 == x2) {
			for (int i = y1; i <= y2; i++) {
				screenData[i][x1].setText('x');
			}
		} else if (y1 == y2) {
			for (int i = x1; i <= x2; i++) {
				screenData[y1][i].setText('x');
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

		if (x > screenData[0].length || y > screenData.length) {
			System.out.println("Fill bucket: point is out of canvas");
			return;
		}

		// fill color at coordinate [x,y] and its left, right, top and bottom.
		// until reaching border.
		if (screenData[y][x].getText() == ' ') {

			screenData[y][x].setText(color);

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

		for (int n = 0; n < screenData.length; n++) {

			for (int m = 0; m < screenData[0].length; m++) {

				if (screenData[n][m].getText() != 0) {
					System.out.print(screenData[n][m].getText());
				} else {
					System.out.print(" ");
				}
			}

			// draw new line
			System.out.print("\n");
		}
	}
}
