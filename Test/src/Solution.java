import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		/**
		 * Given a string, determine if a permutation of the string could form a
		 * palindrome. For example, "code" -> false, "aabbbc" "aabb" -> true, "baa" ->
		 * true, "carerac" -> true.
		 */

		System.out.println(isPermutePalindrome("aabbbc"));

	}

	public static boolean isPermutePalindrome(String s) {
		Map<Character, Integer> charCounter = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {

			Integer repeatCount = charCounter.get(s.charAt(i));

			if (repeatCount != null) {
				repeatCount++;
			} else {
				repeatCount = 1;
			}

			charCounter.put(s.charAt(i), repeatCount);
		}
		// process the hash
		boolean isPalindrome = true;
		int oddOccurCounter = 0;
		for (Character charact : charCounter.keySet()) {
			if (charCounter.get(charact) % 2 == 1) {
				oddOccurCounter++;

				if (oddOccurCounter > 1) {
					isPalindrome = false;
					break;
				}
			}
		}

		return isPalindrome;
	}

}
