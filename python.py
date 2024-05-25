
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

def quick_sort(arr):
  if len(arr) <= 1:
    return arr
  else:
    pivot = arr[0]
    less = [x for x in arr[1:] if x <= pivot]
    greater = [x for x in arr[1:] if x > pivot]
    return quick_sort(less) + [pivot] + quick_sort(greater)



# Example usage
arr = [5, 2, 9, 1, 7, 6, 3]
sorted_arr = merge_sort(arr)
print(sorted_arr)