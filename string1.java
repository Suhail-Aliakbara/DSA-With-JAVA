public class string1 {

  /*
   * 1021. Remove Outermost Parentheses
   * Input: s = "(()())(())(()(()))"
   * Output: "()()()()(())"
   */
  // Method to remove the outermost parentheses
  public String removeOuterParentheses(String s) {
    StringBuilder result = new StringBuilder();
    int count = 0;

    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        if (count > 0) {
          result.append(ch);
        }
        count++;
      } else {
        count--;
        if (count > 0) {
          result.append(ch);
        }
      }
    }
    return result.toString();
  }

  /*
   * 151. Reverse Words in a String
   * Input: s = "a good   example"
   * Output: "example good a"
   */

  public String reverseWord(String word) {
    StringBuilder reversed = new StringBuilder();
    for (int i = word.length() - 1; i >= 0; i--) {
      reversed.append(word.charAt(i));
    }
    return reversed.toString();
  }

  // Method to reverse words in a sentence
  public String reverseWords(String str) {
    // Clean up the input string by removing extra spaces
    String cleanedString = str.trim().replaceAll("\\s{2,}", " ");

    // Split the cleaned string into words
    String[] words = cleanedString.split(" ");

    // Reverse each word and combine into the result
    StringBuilder result = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      result.append(reverseWord(words[i]));
      if (i > 0) {
        result.append(" "); // Add space between words
      }
    }
    return result.toString();
  }

  /*
   * same code without using regex
   * where you use regex use this function instead
   * String cleanedString = cleanString(str);
   * // Instead of
   * String cleanedString = str.trim().replaceAll("\\s{2,}", " ");
   */

  // Method to clean up the input string without using regex
  public String cleanString(String str) {
    StringBuilder cleaned = new StringBuilder();
    boolean inWord = false;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch != ' ') {
        cleaned.append(ch);
        inWord = true;
      } else if (inWord) {
        cleaned.append(' ');
        inWord = false;
      }
    }
    // Remove trailing space if it exists
    if (cleaned.length() > 0 && cleaned.charAt(cleaned.length() - 1) == ' ') {
      cleaned.deleteCharAt(cleaned.length() - 1);
    }
    return cleaned.toString();
  }

  // same solution with 5 lines
  public String reverseWords1(String s) {
    String[] str = s.split(" ");
    s = "";
    for (int i = str.length - 1; i >= 0; i--) {
      s = s.trim() + " " + str[i];
    }
    return s.trim();
  }

}
