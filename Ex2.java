package Matala;

import java.util.LinkedList;
// Ofir Rubin; ID: 212831879
public class Ex2 {

	private static void mergeByHalf(int[] a, int wallAt, int stopAt) {
		// This function does not guarantee fully sorted arrays.merge function suppose to merge two
		// already sorted arrays, but I use like a "try" method. In many cases it might not even get into the while loop.
		// We use the same array as the secondary array.
		int[] temp = new int[a.length];
		System.arraycopy(a, 0, temp, 0, a.length);
		// We use the same way as merge function where we compare the two and then merging.
		int i = 0, j = wallAt + 1, k = 0; // Thus HalfAt + 1 > i so it covers all items.
		while (i <= wallAt && j <= stopAt) {
			if (a[i] < a[j]) temp[k++] = a[i++];
			else temp[k++] = a[j++];
		}
		while (i < a.length && i <= wallAt) { // Copy the remainning
			temp[k++] = a[i++];
		}
		System.arraycopy(temp, 0, a, 0, a.length); // Copying the partially sorted array.
	}

	public static void mergeSort2(int[] arr) {
		if (arr.length < 1) return;
		for (int i = 1; i < arr.length; i *= 2) {
			for (int j = 0; j < arr.length; j += 2 * i) { // Using j + i - 2 as it will cover all the items.
				mergeByHalf(arr, j + i - 2, Math.min(j + 2 * i - 1, arr.length - 1)); // Using Math.min to prevent getting out of the array.
			}
		}
	}
	
	public static void merge(int[] arr, int low, int mid, int high, int max_num) {
		// As long as every item is smaller than the sqrt of Integer.MAX_VALUE
		// Same concept as mergeByHalf; Using StartB as a wall and using them like different arrays.
		int startA = low, startB = mid + 1, k = low;
		while (startA <= mid && startB <= high) {
			if (arr[startA] % max_num <= arr[startB] % max_num)
				arr[k] = arr[k++] + (arr[startA ++] % max_num) * max_num;
			else
				arr[k] = arr[k++] + (arr[startB ++] % max_num) * max_num;
		}
		while (startA <= mid) // Adding remainning items from each part
			arr[k] = arr[k++] + (arr[startA++] % max_num) * max_num;
		while (startB <= high)
			arr[k] = arr[k++] + (arr[startB++] % max_num) * max_num;

		for (startA = low; startA <= high; startA++) // Resetting StartA and "reformating" the numbers
			arr[startA] = arr[startA] / max_num;
	}
	
	public static LinkedList<Integer> join(LinkedList<Integer>[] arr) {
		return null;
	}

	public static int diff(int[] arr) {
		// Using countring sort
		boolean[] count = new boolean[arr.length]; // starts with all false
		// O(n) like counting sort
		for (int i = 0; i < arr.length; i++) {
			count[arr[i] - 1] = true;
		}
		int maxDist = 0, dist = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!count[i]){  // false means difference
				dist ++;
			}
			else{
				maxDist = Math.max(dist, maxDist);
				dist = 1;
			}
		}
		return maxDist;

}

