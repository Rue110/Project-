package sortingalgorithm;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithm {
  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }
  public static void mergeSort(int[] arr, int l, int r) {
    if (l < r) {
      int m = l + (r - l) / 2;
      mergeSort(arr, l, m);
      mergeSort(arr, m+1, r);
      merge(arr, l, m, r);
    }
  }
  public static void merge(int[] arr, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;
    int[]leftArr = new int[n1];
    int[] rightArr = new int[n2];
    for (int i = 0; i < n1; i++) {
      leftArr[i] = arr[l + i];
    }
    for (int j = 0; j < n2; j++) {
      rightArr[j] = arr[m + 1 + j];
    }
    int i = 0, j = 0;
    int k = l;
    while (i < n1 && j < n2) {
      if (leftArr[i] <= rightArr[j]) {
        arr[k] = leftArr[i];
        i++;
      } else {
        arr[k] = rightArr[j];
        j++;
      }
      k++;
    }
    while (i < n1) {
      arr[k] = leftArr[i];
      i++;
      k++;
    }
    while (j < n2) {
      arr[k] = rightArr[j];
      j++;
      k++;
    }
  }
  public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }
  public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
  }

  public static void main(String[] args) {
    int[][] arrSizes = { { 10, 10000, 100000 } };
    String[] arrTypes = { "Random", "Sorted", "Reverse Sorted", "Partially Sorted" };
    for (int i = 0; i < arrSizes.length; i++) {
      System.out.println("Input size: " + Arrays.toString(arrSizes[i]));
      for (int j = 0; j < arrTypes.length; j++) {
        System.out.println("Input type: " + arrTypes[j]);
        for (int k = 0; k < arrSizes[i].length; k++) {
          int[] arr = new int[arrSizes[i].length];
          if (arrTypes[j].equals("Random")) {
            Random rand = new Random();
            for (int l = 0; l < arr.length; l++) {
              arr[l] = rand.nextInt(1000);
            }
          } else if (arrTypes[j].equals("Sorted")) {
            for (int l = 0; l < arr.length; l++) {
              arr[l] = l;
            }
          } else if (arrTypes[j].equals("Reverse Sorted")) {
            for (int l = 0; l < arr.length; l++) {
              arr[l] = arr.length - l - 1;
            }
          } else if (arrTypes[j].equals("Partially Sorted")) {
            Random rand = new Random();
            for (int l = 0; l < arr.length; l++) {
              arr[l] = l;
            }
            for (int l = 0; l < arr.length / 10; l++) {
              int idx1 = rand.nextInt(arr.length);
              int idx2 = rand.nextInt(arr.length);
              int temp = arr[idx1];
              arr[idx1] = arr[idx2];
              arr[idx2] = temp;
            }
          }
          int[] arrCopy1 = Arrays.copyOf(arr, arr.length);
          long startTime1 = System.nanoTime();
          bubbleSort(arrCopy1);
          long endTime1 = System.nanoTime();
          long duration1 = (endTime1 - startTime1) / 1000000; // Time in milliseconds
          long space1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
          int[] arrCopy2 = Arrays.copyOf(arr, arr.length);
          long startTime2 = System.nanoTime();
          mergeSort(arrCopy2, 0, arrCopy2.length - 1);
          long endTime2 = System.nanoTime();
          long duration2 = (endTime2 - startTime2) / 1000000; // Time in milliseconds
          long space2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
          int[] arrCopy3 = Arrays.copyOf(arr, arr.length);
          long startTime3 = System.nanoTime();
          quickSort(arrCopy3, 0, arrCopy3.length - 1);
          long endTime3 = System.nanoTime();
          long duration3 = (endTime3 - startTime3) / 1000000; // Time in milliseconds
          long space3 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
          System.out.println("Input size: " + arrSizes[i][k]);
          System.out.println("Bubble Sort - Time: " + duration1 + " ms, Space: " + space1 + " bytes");
          System.out.println("Merge Sort - Time: " + duration2 + " ms, Space: " + space2 + " bytes");
          System.out.println("Quick Sort - Time: " + duration3 + " ms, Space: " + space3 + " bytes");
          System.out.println();
        }
      }
    }
  }
}