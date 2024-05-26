
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

# Example usage
arr = [4,5,6,7,0,1,2]
sorted_arr = rotated_and_sorted(arr, 0, len(arr)-1, 0);
print(sorted_arr)