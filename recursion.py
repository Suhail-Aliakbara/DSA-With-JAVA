def sum_of_numbers(n):
    if n == 0:
        return 0
    else:
        return n + sum_of_numbers(n-1)

# Test the function
# print(sum_of_numbers(5))  # Output: 15

def fibonacci(n):
    if n <= 0:
        return "Input should be positive integer."
    elif n == 1 or n == 2:
      return  1
    return fibonacci(n-1) + fibonacci(n-2)

# Test the function
# print(fibonacci(8))  # Output: 21