package com.alcuvi.algorithms;

public class BinarySearch {

	// Return the index in the array of the element searched.
	public static int binarySearch(int a[], int key) {
		int lo = 0;
		int hi = a.length - 1;

		while (lo <= hi) {

			int mid = lo + (hi - lo) / 2;

			if (key < a[mid]) {
				hi = mid - 1;

			} else if (key > a[mid]) {
				lo = mid + 1;

			} else {
				return mid;

			}
		}
		return -1;
	}
}
