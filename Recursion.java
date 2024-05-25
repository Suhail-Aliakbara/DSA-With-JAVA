import java.lang.reflect.Array;
import java.util.*;

public class Recursion {

  public static int Sum_N(int n) {
    if (n == 1) {
      return 1;
    }
    int sum = Sum_N(n - 1) + n;
    return sum;
  }

  public static int fibonnaci_Num(int n) {
    if (n <= 2)
      return 1;
    return fibonnaci_Num(n - 1) + fibonnaci_Num(n - 2);
  }

  public static boolean isSorted(int arr[], int i) {
    if (i == arr.length - 1)
      return true;
    if (arr[i] > arr[i + 1]) {
      return false;
    }
    return isSorted(arr, i + 1);
  }

  public static int first_occurence(int arr[], int key, int i) {
    if (i == arr.length) {
      return -1;
    }
    if (arr[i] == key) {
      return i;
    }
    return first_occurence(arr, key, i + 1);
  }

  public static int Last_occurence(int arr[], int key, int i) {
    if (i == arr.length)
      return -1;
    int isFound = Last_occurence(arr, key, i + 1);
    if (isFound == -1 && arr[i] == key) {
      return i;
    }
    return isFound;
  }

  public static int power(int x, int n) {
    if (n == 0) {
      return 1;
    }
    int halfpower = power(x, n / 2);
    int power = halfpower * halfpower;
    if (n % 2 != 0) {
      power *= x;
    }
    return power;
  }

  public static int tillingProblem(int n) {
    if (n == 0 || n == 1)
      return 1;
    return tillingProblem(n - 1) + tillingProblem(n - 2);
  }

  public static void removeDuplicate(String name, StringBuilder str, int i,
      boolean check[]) {
    if (i == name.length()) {
      System.out.println(str);
      return;
    }
    char ch = name.charAt(i);
    if (check[ch - 'a'] == true) {
      removeDuplicate(name, str, i + 1, check);
    } else {
      check[ch - 'a'] = true;
      removeDuplicate(name, str.append(ch), i + 1, check);
    }
  }

  public static void BinaryStringProblem(int n, String str, int i, int lp) {
    if (n == i) {
      System.out.println(str);
      return;
    }
    BinaryStringProblem(n, str + '0', i + 1, 0);
    if (lp != 1) {
      BinaryStringProblem(n, str + '1', i + 1, 1);
    }
  }

  public static void repeatNum(int arr[], int i, int key) {
    if (i == arr.length) {
      return;
    }
    if (arr[i] == key) {
      System.out.print(i + " ");
    }
    repeatNum(arr, i + 1, key);
  }

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

  public static void main(String[] args) {
    int n[] = { 5, 8, 3, 2, 7, 8, 2, 3, 2 };
    // repeatNum(n, 0, 2);
    quickSort(n, 0, n.length - 1);
    printFn(n);

  }
}
