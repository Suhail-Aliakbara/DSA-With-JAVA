import java.util.*;

public class DivideAndConquer {

  public static void mergeSort(int arr[], int si, int ei) {
    if (si >= ei) {
      return;
    }
    int mid = si + (ei - si) / 2;
    mergeSort(arr, si, mid);
    mergeSort(arr, mid + 1, ei);
    merge(arr, si, mid, ei);
  }

  public static void merge(int arr[], int si, int mid, int ei) {
    int temp[] = new int[ei - si + 1];
    int i = si;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= ei) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= ei) {
      temp[k++] = arr[j++];
    }
    for (i = si, k = 0; k < temp.length; i++, k++) {
      arr[i] = temp[k];
    }
  }

  public static void printFn(int arr[]) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  // ------------quick Sort--------------------

  public static void quickSort(int arr[], int si, int ei) {
    if (si >= ei)
      return;

    int partitionIdx = partition(arr, si, ei);
    quickSort(arr, si, partitionIdx - 1);
    quickSort(arr, partitionIdx + 1, ei);
  }

  public static int partition(int arr[], int si, int ei) {
    int pivot = arr[ei];
    int i = si - 1;
    for (int j = si; j < ei; j++) {
      if (pivot > arr[j]) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    i++;
    int temp = arr[i];
    arr[i] = pivot;
    arr[ei] = temp;
    return i;
  }

  // --------------Search in Rotated and sorted array-----------------
  public static int rotatedAndSorted(int arr[], int si, int ei, int key) {
    if (si > ei) {
      return -1;
    }

    int mid = si + (ei - si) / 2;

    if (arr[mid] == key) {
      return mid;
    }
    if (arr[si] <= arr[mid]) {
      if (arr[si] <= key && key < arr[mid]) {
        return rotatedAndSorted(arr, si, mid - 1, key);
      } else {
        return rotatedAndSorted(arr, mid + 1, ei, key);
      }
    } else {
      if (arr[mid] < key && key <= arr[ei]) {
        return rotatedAndSorted(arr, mid + 1, ei, key);
      } else {
        return rotatedAndSorted(arr, si, mid - 1, key);
      }
    }
  }

  public static void main(String[] args) {
    int n[] = { 2, 3, 4, 0, 1 };
    // quickSort(n, 0, n.length - 1);
    System.out.println(rotatedAndSorted(n, 0, n.length - 1, 2));
    // printFn(n);
  }
}
