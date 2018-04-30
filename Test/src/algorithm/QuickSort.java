package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class QuickSort {
	public static void main(String[] args) {
		Integer[] x = { 9, 2, 4, 7, 3, 7, 10 };
		System.out.println(Arrays.toString(x));

		int low = 0;
		int high = x.length - 1;

		quickSort(x, low, high);
		System.out.println(Arrays.toString(x));
		// using Java API
		x[0] = 12;
		Arrays.sort(x, low, high + 1);
		System.out.println(Arrays.toString(x));
		// binary search API
		int binSearch = Arrays.binarySearch(x, 12);
		System.out.println("Bin search: " + binSearch);
		// using max-heap to sort
		x[0] = 13;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		maxHeap.addAll(Arrays.asList(x));

		System.out.print("Min heap: ");
		maxHeap.forEach(item -> {
			System.out.print(item.intValue() + " ");
		});

	}

	public static void quickSort(Integer[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}

			while (arr[j] > pivot) {
				j--;
			}

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(arr, low, j);

		if (high > i)
			quickSort(arr, i, high);
	}
}