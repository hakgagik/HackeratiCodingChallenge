package problem1;

import java.util.Arrays;

/**
 * A class for housing the findConsecutiveRuns method.
 * @author Gagik Hakobyan
 */
public class ConsecutiveIntegers {

	public static void main(String[] args) {
		int[] input = new int[] {1, 2, 3, 5, 10, 9, 8, 9, 10, 11, 7};
		int[] output = findConsecutiveRuns(input);
		System.out.println(Arrays.toString(output));
	}

	/**
	 * Find triples of consecutive integers (ascending or descending) in an unsorted array.
	 *
	 * @param input Input array of integers.
	 * @return Returns the starting index of each triple in array format.
	 * If no triples are found, returns null.
	 * */
	public static int[] findConsecutiveRuns(int[] input) {

		// Keep track of the difference between current element and next element,
		// as well as previous element and current element.
		int nextDiff = 0;
		int prevDiff;

		// How many consecutive sets of integers we've found.
		int outputCount = 0;

		// Initialize an array to keep track of the starting indices of consecutive ints we've found so far.
		int[] outputBuffer = new int[input.length];


		for (int i = 0; i < input.length - 1; i++) {
			prevDiff = nextDiff;
			nextDiff = input[i + 1] - input[i];

			// If the difference between this and previous = the difference between this and next,
			// check if that difference is +- 1. If so, we've found a consecutive triple.
			if (nextDiff == prevDiff) {
				if (prevDiff == 1 || prevDiff == -1) {
					outputBuffer[outputCount] = i - 1;
					outputCount++;
				}
			}
		}

		// Clean up the output and return it
		if (outputCount > 0) {
			int[] output = new int[outputCount];
			System.arraycopy(outputBuffer, 0, output, 0, outputCount);
			return output;
		} else {
			return null;
		}
	}
}
