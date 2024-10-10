import java.util.Arrays;

public class SortandSearch {
    public static void main(String[] args) {
        int[] array = { 27, -3, 4, 5, 35, 2, 1, -40, 7, 18, 9, -1, 16, 100 };

        // Implementing Linear Search
        int target = 9;
        int linearSearchResult = linearSearch(array, target);

        if (linearSearchResult != -1) {
            System.out.println("Linear Search: Found " + target + " at index " + linearSearchResult);
        } else {
            System.out.println("Linear Search: " + target + " not found in the array");
        }

        // Implementing Binary Search
        int binarySearchResult = binarySearch(array, target);

        if (binarySearchResult != -1) {
            System.out.println("Binary Search: Found " + target + " at index " + binarySearchResult);
        } else {
            System.out.println("Binary Search: " + target + " not found in the array");
        }

        // Implementing Insertion Sort
        insertionSort(array);
        System.out.println("Sorted Array: " + Arrays.toString(array));

        // Performing Binary Search on the sorted array
        int sortedBinarySearchResult = binarySearch(array, target);

        if (sortedBinarySearchResult != -1) {
            System.out.println(
                    "Binary Search on Sorted Array: Found " + target + " at index " + sortedBinarySearchResult);
        } else {
            System.out.println("Binary Search on Sorted Array: " + target + " not found in the array");
        }
    }

    // Linear Search
    private static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Element found, return the index
            }
        }
        return -1; // Element not found
    }

    // Binary Search
    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Element found, return the index
            }

            if (arr[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return -1; // Element not found
    }

    // Insertion Sort
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift elements to make space for the key
                j--;
            }

            arr[j + 1] = key; // Insert the key into its correct position
        }
    }
}

// Linear search iterates through the elements of the array one by one until it
// finds the target element or reaches the end of the array.
// In the worst case, linear search has a time complexity of O(n),
// where n is the size of the array. Despite being a simple algorithm, its
// efficiency is reasonable for small lists, and it is easy to implement.

// I would use the **linear search algorithm** in this case because the provided
// array is not sorted,
// and the list is relatively small. Linear search is straightforward to
// implement and is effective for unsorted lists.
// Given the size and unsorted nature of the array,
// the linear search algorithm provides a practical and efficient solution for
// finding the target element.