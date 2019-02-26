
/**
 * @author Thang Le
 * 
 *         key idea is to create a 2D array of Pixels for output screen. Fill in
 *         data to that Pixels. Last is to draw all the Pixels to console.
 *
 */
public class Main {
	static Pixel[][] screen;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		drawCanvas(10, 30);
		drawLine(2, 3, 2, 8);
		drawLine(2, 8, 8, 8);
		draw2Screen();
	}

	/**
	 * draw canvas
	 * 
	 * @param x
	 * @param y
	 */
	static void drawCanvas(int x, int y) {
		screen = new Pixel[x][y];

		// init all pixels of screen
		for (int n = 0; n < x; n++) {
			for (int m = 0; m < y; m++) {
				screen[n][m] = new Pixel();
				screen[n][m].setX(m);
				screen[n][m].setY(n);
			}
		}

		// fill in all pixels of screen

		for (int n = 0; n < x; n++) {
			for (int m = 0; m < y; m++) {
				if (n == 0 || n == x - 1) {
					screen[n][m].setText('-');
				} else if (m == 0 || m == y - 1) {
					screen[n][m].setText('|');
				}
			}
		}
	}

	/**
	 * draw line. ordinate (x,y) is revert of array index array(x,y)
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	static void drawLine(int x1, int y1, int x2, int y2) {

		if (x1 == x2) {
			for (int i = y1; i < y2; i++) {
				screen[i][x1].setText('x');
			}
		} else if (y1 == y2) {
			for (int i = x1; i < x2; i++) {
				screen[y1][i].setText('x');
			}
		} else {

		}

	}

	/**
	 * actual draw to console
	 */
	static void draw2Screen() {

		System.out.println(screen.length + "#" + screen[0].length);

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
