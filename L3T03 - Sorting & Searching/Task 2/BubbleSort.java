import java.util.ArrayList;

public class BubbleSort {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> myList = new ArrayList<>();
        myList.add("blue");
        myList.add("six");
        myList.add("hello");
        myList.add("game");
        myList.add("unorthodox");
        myList.add("referee");
        myList.add("ask");
        myList.add("zebra");
        myList.add("run");
        myList.add("flex");

        // Print the original list
        System.out.println("Original list: " + myList);

        // Call the bubbleSort method to sort the list
        bubbleSort(myList);

        // Print the sorted list
        System.out.println("Sorted list: " + myList);
    }

    // Bubble Sort algorithm implementation for ArrayList of Strings
    public static void bubbleSort(ArrayList<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j <= n - i - 1; j++) {
                // Compare adjacent elements and swap if they are in the wrong order
                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    // Swap elements
                    String temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
