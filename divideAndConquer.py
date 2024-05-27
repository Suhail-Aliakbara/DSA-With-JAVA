
#---------------- Merge sort---------------
def merge_sort(arr):
  if len(arr) <= 1:
    return arr
  
  mid = len(arr) // 2
  left = arr[:mid]
  right = arr[mid:]
  
  left = merge_sort(left)
  right = merge_sort(right)
  
  return merge(left, right)

def merge(left, right):
  result = []
  i = j = 0
  
  while i < len(left) and j < len(right):
    if left[i] < right[j]:
      result.append(left[i])
      i += 1
    else:
      result.append(right[j])
      j += 1
  
  while i < len(left):
    result.append(left[i])
    i += 1
  
  while j < len(right):
    result.append(right[j])
    j += 1
  
  return result

arr = [5, 2, 9, 1, 7, 6, 3]
sorted_arr = merge_sort(arr)
# print(sorted_arr)

#---------------- Quick sort---------------
def quick_sort(arr):
  if len(arr) <= 1:
    return arr
  else:
    pivot = arr[0]
    less = [x for x in arr[1:] if x <= pivot]
    greater = [x for x in arr[1:] if x > pivot]
    return quick_sort(less) + [pivot] + quick_sort(greater)


#---------------- Binary search on rotated and sorted array---------------

def rotated_and_sorted(arr, si, ei, key):
    if si > ei:
        return -1

    mid = si + (ei - si) // 2

    if arr[mid] == key:
        return mid

    if arr[si] <= arr[mid]:
        if arr[si] <= key and key < arr[mid]:
            return rotated_and_sorted(arr, si, mid - 1, key)
        else:
            return rotated_and_sorted(arr, mid + 1, ei, key)
    else:
        if arr[mid] < key and key <= arr[ei]:
            return rotated_and_sorted(arr, mid + 1, ei, key)
        else:
            return rotated_and_sorted(arr, si, mid - 1, key)

#---------------- Sort String array using merge---------------

def merge_array(str, si, ei):
    if si == ei:
        return [str[si]]

    mid = si + (ei - si) // 2

    arr1 = merge_array(str, si, mid)
    arr2 = merge_array(str, mid + 1, ei)
    arr3 = merge_string(arr1, arr2)
    return arr3

def merge_string(arr1, arr2):
    n = len(arr1)
    m = len(arr2)
    i = 0
    j = 0
    temp = [None] * (n + m)
    k = 0

    while i < n and j < m:
        if is_alphabetically_correct(arr1[i], arr2[j]):
            temp[k] = arr1[i]
            i += 1
        else:
            temp[k] = arr2[j]
            j += 1
        k += 1

    while i < n:
        temp[k] = arr1[i]
        i += 1
        k += 1

    while j < m:
        temp[k] = arr2[j]
        j += 1
        k += 1

    return temp

def is_alphabetically_correct(str1, str2):
    return str1 < str2

def print_string_array(str):
    for s in str:
        print(s, end=" ")


# Example usage
arr = [4,5,6,7,0,1,2]
sorted_arr = rotated_and_sorted(arr, 0, len(arr)-1, 0);
# print(sorted_arr)
arr1 = merge_array(arr, 0, len(arr)-1)
print_string_array(arr1);