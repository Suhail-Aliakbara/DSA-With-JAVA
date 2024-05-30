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

  // ------------------Apply merge sort to sort an Array--------------

  public static String[] mergeArray(String[] str, int si, int ei) {
    if (si == ei) {
      String[] arr = { str[si] };
      return arr;
    }
    int mid = si + (ei - si) / 2;

    String[] arr1 = mergeArray(str, si, mid);
    String[] arr2 = mergeArray(str, mid + 1, ei);
    String[] arr3 = mergeString(arr1, arr2);
    return arr3;
  }

  public static String[] mergeString(String[] arr1, String[] arr2) {
    int n = arr1.length;
    int m = arr2.length;
    int i = 0;
    int j = 0;
    String temp[] = new String[n + m];
    int k = 0;

    while (i < n && j < m) {
      if (isAllpabaticallyCorrect(arr1[i], arr2[j])) {
        temp[k++] = arr1[i++];
      } else {
        temp[k++] = arr2[j++];
      }
    }

    while (i < n) {
      temp[k++] = arr1[i++];
    }
    while (j < m) {
      temp[k++] = arr2[j++];
    }

    return temp;
  }

  public static boolean isAllpabaticallyCorrect(String str1, String str2) {
    if (str1.compareTo(str2) < 0) {
      return true;
    }
    return false;
  }

  public static void printStringArray(String[] str) {
    for (int i = 0; i < str.length; i++) {
      System.out.print(str[i] + " ");
    }
  }

  // ---------------Retrun the majority element from array-----------

  public static int majorityElement(int arr[], int si, int ei) {
    if (si == ei) {
      return arr[si];
    }
    int mid = si + (ei - si) / 2;

    int left = majorityElement(arr, si, mid);
    int right = majorityElement(arr, mid + 1, ei);

    if (left == right) {
      return left;
    }

    int leftCount = countMajorityElement(arr, left, si, ei);
    int rightCount = countMajorityElement(arr, right, si, ei);

    return leftCount > rightCount ? left : right;
  }

  static int countMajorityElement(int arr[], int num, int si, int ei) {
    int count = 0;

    for (int i = si; i <= ei; i++) {
      if (num == arr[i])
        count++;
    }
    return count;
  }

  // -------------------------Inversion Count--------------------------

  public static int invertion(int arr[], int si, int ei) {
    int invCount = 0;

    if (ei > si) {
      int mid = si + (ei - si) / 2;
      invCount += invertion(arr, si, mid);
      invCount += invertion(arr, mid + 1, ei);
      invCount += mergeInversion(arr, si, mid + 1, ei);
    }
    return invCount;
  }

  public static int mergeInversion(int arr[], int si, int mid, int ei) {
    int i = si;
    int j = mid;
    int k = 0;
    int invCount = 0;

    int temp[] = new int[ei - si + 1];

    while (i < mid && j <= ei) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k] = arr[j];
        invCount += mid - i;
        k++;
        j++;
      }
    }
    while (i < mid) {
      temp[k++] = arr[i++];
    }
    while (j <= ei) {
      temp[k++] = arr[j++];
    }
    for (i = si, k = 0; i <= ei; i++, k++) {
      arr[i] = temp[k];
    }
    return invCount;
  }

  public static void main(String[] args) {
    int n[] = { 1, 20, 6, 4, 5 };
    // quickSort(n, 0, n.length - 1);
    // System.out.println(rotatedAndSorted(n, 0, n.length - 1, 2));
    // printFn(n);

    // String str[] = { "sun", "earth", "mars", "mercury" };
    // String[] arr = mergeArray(str, 0, str.length - 1);
    // printStringArray(arr);

    System.out.println(invertion(n, 0, n.length - 1));

  }
}
