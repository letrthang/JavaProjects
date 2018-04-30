import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Main2 {
	public static void main(String[] args) {
		// note:
		// this is just an example on how our system
		// takes input and output and automate the process
		// of marking this submission

		try {
			Scanner scan = new Scanner(System.in);
			// 1st operand
			long sum = 0;
			// number of test cases
			int numTC = 0;
			// calculated result
			String result = "";
			numTC = Integer.parseInt(scan.nextLine());

			// check if there is any more line
			while (scan.hasNextLine()) {
				// here's how you read the next line
				String string = scan.nextLine().trim();

				String[] arrStr = string.split("");
				System.out.println("User input:" + Arrays.asList(arrStr));

				// validate input, only allow 0-9, length = 11 digits
				if (validateInput(string) == false) {
					System.out.println("N");

					numTC--;
					if (numTC > 0) {
						continue;
					} else {
						break;
					}
				}

				// a+3b+7c+9d+e+3f+7g+9h+i+3j+k

				sum = ValueAt(string, 0) + (3 * ValueAt(string, 1)) + (7 * ValueAt(string, 2))
						+ (9 * ValueAt(string, 3)) + ValueAt(string, 4) + (3 * ValueAt(string, 5))
						+ (7 * ValueAt(string, 6)) + (9 * ValueAt(string, 7)) + ValueAt(string, 8)
						+ (3 * ValueAt(string, 9)) + ValueAt(string, 10);

				if (sum % 10 == 0) {
					result = "Y";
				} else {
					result = "N";
				}

				// here's how you output the result
				System.out.println(result);

				numTC--;
				if (numTC == 0) {
					break;
				}
			}

		} catch (Exception e) {
			// System.out.println("N");
		}
	}

	public static boolean validateInput(String input) {

		String pattern = "[0-9]{11}";
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern, 2);
		// Now create matcher object.
		Matcher m = r.matcher(input);

		return m.matches();
	}

	public static int ValueAt(String str, int index) {

		return Integer.parseInt(str.substring(index, index + 1));
	}
}