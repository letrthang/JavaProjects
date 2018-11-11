import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.DatatypeConverter;

public class QuickTest {
	List<String> lst = new ArrayList<>();

	public QuickTest() {
		System.out.println("Default CTOR");
	}

	public QuickTest(Object o) {
		System.out.println("CTOR with Object Argument");
	}

	public QuickTest(double[] dArray) {
		System.out.println("CTOR with double array");
	}

	public QuickTest(char[] cArray) {
		System.out.println("CTOR with char array");
	}

	static int ironMan(int[] p) {
		int initPower = 0;
		int lastIndexNeg = -1;
		for (int i = p.length - 1; i > 0; i--) {
			if (p[i] < 0) {
				lastIndexNeg = i;
				break;
			}
		}

		if (lastIndexNeg == -1) {
			// no lost power in any position
			return 0;
		}

		initPower = Math.abs(p[lastIndexNeg]) + 1;

		for (int j = lastIndexNeg - 1; j >= 0; j--) {
			if (p[j] < 0) {
				initPower += Math.abs(p[j]);
			} else {
				initPower -= p[j];
				if (initPower < 1) {
					initPower = 1;
				}
			}
		}
		return initPower;
	}

	public static Set<Integer> findRepeatElement(int[] A) {
		Set<Integer> B = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] == A[j]) {
					B.add(A[i]);
					continue;
				}
			}
		}

		return B;
	}

	public static void findRepeatElementWithCounter(int[] A) {

		int counter = 1;

		Arrays.sort(A);
		int i,j;
		for ( i = 0; i < A.length; i=j) {
			for ( j = i + 1; j < A.length; j++) {
				if (A[i] == A[j]) {
					counter++;
				} else {
					break;
				}
			}

			if (counter > 1) {
				System.out
						.println("findRepeatElementWithCounter - Duplicate element: " + A[i] + ", number duplicate: " + counter);
				counter = 1;
			}
		}

	}

	public static Set<Integer> findRepeatElementHashMap(int[] A) {
		Set<Integer> B = new HashSet<>();
		Map<Integer, Integer> arrMap = new HashMap<>();

		for (int i : A) {
			Integer count = arrMap.get(i);
			if (count == null) {
				arrMap.put(i, 1);
			} else {
				arrMap.put(i, ++count);
			}
		}
		// get key set
		for (int k : arrMap.keySet()) {
			if (arrMap.get(k) > 1) {
				B.add(k);
			}
		}

		return B;
	}

	public static int numberOfPair(int[] a, int k) {

		Set<Integer> numberPair = new HashSet<>();
		// we sort input array
		Arrays.sort(a);
		// don't process element that > k after sorting. for optimization
		int indexOf = -1;
		if (a[a.length - 1] > k) {
			while (indexOf == -1) {
				indexOf = Arrays.binarySearch(a, k++);
			}
			indexOf++;
		} else {
			indexOf = a.length;
		}

		// traverse whole input array
		for (int i = 0; i < indexOf; i++) {
			for (int j = i + 1; j < indexOf; j++) {
				if (a[i] + a[j] == k) {
					numberPair.add(a[i]);
					break;
				}
			}
		}

		return numberPair.size();
	}

	public static void main(String[] args) {
		int[] C = { 6, 12, 3, 9, 3, 5, 1, 6, 6, 6 };
		int pair = numberOfPair(C, 6);
		System.out.println("numberOfPair: " + pair);

		int[] array = { -5, 4, -2, 3, 1, -1, -6, -1, 0, 5 };
		int var = ironMan(array);
		System.out.println("IronMan minimum power: " + var);

		int[] A = { 1, 5, 2, 4, 8, 9, 3, 1, 4, 0, 4 };
		Set<Integer> B = findRepeatElementHashMap(A);
		System.out.println("find Repeat Element: " + B);

		findRepeatElementWithCounter(A);

		int[] array1 = { 1, 2, 3, 4 };
		int[] array2 = {};

		array2 = Arrays.copyOfRange(array1, 1, array1.length);
		for (int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
		}

		Runnable runnable = () -> System.out.println("runnable in Lambda expression");
		Thread t = new Thread(runnable);
		t.start();

		boolean match = IntStream.range(2, 10).anyMatch((i) -> i >= 9);
		System.out.println(match);

		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(s->System.out.println(s));

		int[] ints = { 1, 2, 3 };
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		System.out.println("Convert int array to List: " + list);

		String input = "bye bye bye bye world world world 300 ABC";
		String pattern1 = "(\\w+)(\\W+\\1)*";
		String pattern2 = "[\\w\\s]{1,}";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern1, 2);
		Pattern r2 = Pattern.compile(pattern2, 2);

		// Now create matcher object.
		Matcher m = r.matcher(input);
		Matcher m2 = r2.matcher(input);

		while (m.find()) {
			System.out.println("group 0: " + m.group());
			System.out.println("group 1: " + m.group(1));
			System.out.println("group 2: " + m.group(2));
			// replace duplicate word
			input = input.replaceAll(m.group(0), m.group(1));
		}
		{
			System.out.println("Maches: " + m2.matches());
			System.out.println("replace: " + input);
		}
		// https://stackoverflow.com/questions/2823016/regular-expression-for-consecutive-duplicate-words
		// http://www.java2s.com/Tutorials/Java/Java_Regular_Expression/0070__Java_Regex_Groups.htm
		// https://algorithms.tutorialhorizon.com/find-the-two-repeating-elements-in-a-given-array-6-approaches/
	
		Integer.parseInt("1");
	}
	
	

}
