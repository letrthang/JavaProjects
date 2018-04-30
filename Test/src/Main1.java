import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Main1 {
	public static void main(String[] args) {
		// note:
		// this is just an example on how our system
		// takes input and output and automate the process
		// of marking this submission

		try {
			Scanner scan = new Scanner(System.in);
			String sign = "";
			// 1st operand
			String op1 = "";
			// 2nd operand
			String op2 = "";
			// calculated result
			long result = 0;
			String[] input;

			// check if there is any more line
			while (scan.hasNextLine()) {
				// here's how you read the next line
				String string = scan.nextLine();
				input = string.trim().split("");
				// validate input, only allow digit and + - * / %
				if (validateInput(string) == false) {
					break;
				}

				input = string.trim().split("\\s+");
				// then validate format
				if (input.length != 3)
					break;

				sign = input[0];
				op1 = input[1];
				op2 = input[2];

				if (sign.equals("+")) {
					result = Integer.parseInt(op1) + Integer.parseInt(op2);
				} else if (sign.equals("-")) {
					result = Integer.parseInt(op1) - Integer.parseInt(op2);
				} else if (sign.equals("*")) {
					result = Integer.parseInt(op1) * Integer.parseInt(op2);
				} else if (sign.equals("/")) {
					result = Integer.parseInt(op1) / Integer.parseInt(op2);
				} else if (sign.equals("%")) {
					result = Integer.parseInt(op1) % Integer.parseInt(op2);
				} else {
					break;
				}

				// here's how you output the result
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean validateInput(String input) {

		String pattern = "[0-9%+-/\\*\\s]{1,}";
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern, 2);
		// Now create matcher object.
		Matcher m = r.matcher(input);

		return m.matches();
	}
}