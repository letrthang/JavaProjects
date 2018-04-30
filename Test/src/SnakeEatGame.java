import java.util.*;

public class SnakeEatGame {
	static List<SnakeEatGame.Body> snake = new ArrayList<SnakeEatGame.Body>();
	static SnakeEatGame snakeEatGame = new SnakeEatGame();
	// currDirection: L = Left, R = Right, F = Forward, B = Backward.
	static String currDirection = "F";

	public static void main(String[] args) {
		// note:
		// this is just an example on how our system
		// takes input and output and automate the process
		// of marking this submission

		Body initHead = snakeEatGame.new Body(0, 1);
		Body initTail = snakeEatGame.new Body(0, 0);
		snake.add(initTail);
		snake.add(initHead);

		try {
			Scanner scan = new Scanner(System.in);
			int numInputLines = Integer.parseInt(scan.nextLine());
			int numSteps = 0;
			String snakeSteps = "";

			String[] input;
			String output = "";

			// check if there is any more line
			while (scan.hasNextLine()) {

				// here's how you read the next line
				String string = scan.nextLine();

				// validate user input
				if (string.matches("[0-9LRFElrfe\\s+]*") == false) {
					numInputLines--;
					if (numInputLines > 0) {
						continue;
					} else {
						break;
					}
				}

				input = string.trim().split("\\s+");
				// then validate format
				if (input.length != 2)
					break;

				numSteps = Integer.parseInt(input[0]);
				snakeSteps = input[1].toUpperCase().trim();

				for (int i = 0; i < numSteps; i++) {
					if (snakeSteps.substring(i, i + 1).equals("L")) {
						move("L");
						// check snake status
						if (checkSnakeDie()) {
							output = String.valueOf(i + 1);
							break;
						} else {
							output = "YES";
						}
					} else if (snakeSteps.substring(i, i + 1).equals("R")) {
						move("R");
						// check snake status
						if (checkSnakeDie()) {
							output = String.valueOf(i + 1);
							break;
						} else {
							output = "YES";
						}
					} else if (snakeSteps.substring(i, i + 1).equals("F")) {
						move("F");
						// check snake status
						if (checkSnakeDie()) {
							output = String.valueOf(i + 1);
							break;
						} else {
							output = "YES";
						}
					} else if (snakeSteps.substring(i, i + 1).equals("E")) {
						move("E");
						// check snake status
						if (checkSnakeDie() == true) {
							output = String.valueOf(i + 1);
							break;
						} else {
							// not die
							output = "YES";
						}
					}

				}
				// here's how you output the result
				System.out.println(output);

				numInputLines--;
				if (numInputLines == 0) {
					// exit program
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void move(String direction) {

		Body currentHead = null;
		Body newHead = null;

		if (direction.equals("L")) {
			// last head position
			currentHead = snake.get(snake.size() - 1);

			// new head position
			if (currDirection.equals("F")) {
				newHead = snakeEatGame.new Body(currentHead.x - 1, currentHead.y);
				// update current direction
				currDirection = "L";
			} else if (currDirection.equals("L")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y - 1);
				// update current direction
				currDirection = "B";
			} else if (currDirection.equals("R")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y + 1);
				// update current direction
				currDirection = "F";
			} else if (currDirection.equals("B")) {
				newHead = snakeEatGame.new Body(currentHead.x + 1, currentHead.y);
				// update current direction
				currDirection = "R";
			}

			// update new head position
			snake.add(newHead);
			// remove old tail position
			snake.remove(0);

		} else if (direction.equals("R")) {
			// last head position
			currentHead = snake.get(snake.size() - 1);

			// new head position
			if (currDirection.equals("F")) {
				newHead = snakeEatGame.new Body(currentHead.x + 1, currentHead.y);
				// update current direction
				currDirection = "R";
			} else if (currDirection.equals("L")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y + 1);
				// update current direction
				currDirection = "F";
			} else if (currDirection.equals("R")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y - 1);
				// update current direction
				currDirection = "B";
			} else if (currDirection.equals("B")) {
				newHead = snakeEatGame.new Body(currentHead.x - 1, currentHead.y);
				// update current direction
				currDirection = "L";
			}
			// update new head position
			snake.add(newHead);
			// remove old tail position
			snake.remove(0);

		} else if (direction.equals("F")) {
			// last head position
			currentHead = snake.get(snake.size() - 1);

			// new head position
			if (currDirection.equals("F")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y + 1);
			} else if (currDirection.equals("L")) {
				newHead = snakeEatGame.new Body(currentHead.x - 1, currentHead.y);
			} else if (currDirection.equals("R")) {
				newHead = snakeEatGame.new Body(currentHead.x + 1, currentHead.y);
			} else if (currDirection.equals("B")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y - 1);
			}
			// update new head position
			snake.add(newHead);
			// remove old tail position
			snake.remove(0);

		} else if (direction.equals("E")) {
			// last head position
			currentHead = snake.get(snake.size() - 1);

			// new head position
			if (currDirection.equals("L")) {
				newHead = snakeEatGame.new Body(currentHead.x - 1, currentHead.y);
			} else if (currDirection.equals("R")) {
				newHead = snakeEatGame.new Body(currentHead.x + 1, currentHead.y);
			} else if (currDirection.equals("F")) {
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y + 1);
			} else if (currDirection.equals("B")) {
				// snake moves that starting with E
				newHead = snakeEatGame.new Body(currentHead.x, currentHead.y - 1);
			}
			// update new head position. now snake longer
			snake.add(newHead);

		}

	}

	public static boolean checkSnakeDie() {
		boolean ret = false;

		Body currentHead = snake.get(snake.size() - 1);

		for (int i = 0; i < snake.size() - 1; i++) {
			if (snake.get(i).x == currentHead.x && snake.get(i).y == currentHead.y) {
				ret = true;
			}
		}
		return ret;
	}

	public class Body {
		public int x;
		public int y;

		Body(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}