import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class is the entire program that prompts user for an integer, and then asks the user to
 * enter the corresponding number of integers for a list. It sorts it and outputs the consecutive
 * numbers (either two or more or none) the list holds and the largest consecutive sequence and
 * outputs first lengthiest sequence if there are multiple separate consecutive sequence that are
 * the same length.
 * 
 * @author He Jian John Wang
 */
public class ConsecutiveNumberFinder {
  /**
   * The entry point of the ConsecutiveNumberFinder program. This method prompts the user to enter a
   * series of integers and performs various operations on these integers. It first asks for the
   * number of integers the user will enter (between 2 and 10). Then, it allows the user to input
   * these integers either all in one line or across multiple lines. After gathering the inputs, the
   * program calculates and displays the consecutive numbers and the largest consecutive sequence
   * among the entered numbers.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> numbers = new ArrayList<>();

    int numberOfIntegers = 0;
    System.out
        .println("Enter an integer (between 2 and 10) for your future" + "list size or length: ");
    while (true) {
      try {
        if (scanner.hasNextInt()) {
          numberOfIntegers = scanner.nextInt();
          if (numberOfIntegers >= 2 && numberOfIntegers <= 10) {
            break;
          } else {
            System.out.println("Invalid number. Please enter a number" + "between 2 and 10:");
          }
        } else {
          if (scanner.hasNext()) {
            System.out.println("Invalid input. Please enter a number" + "between 2 and 10:");
            scanner.next(); // Consume the non-integer input
          } else {
            throw new NoSuchElementException("No input provided");
          }
        }
      } catch (NoSuchElementException e) {
        System.out.println("Input error: " + e.getMessage());
        return; // Exit the program gracefully
      }
    }

    System.out.println("Enter " + numberOfIntegers + " integers (you can enter "
        + "them on one line or multiple lines):");
    int count = 0;
    while (count < numberOfIntegers) {
      if (scanner.hasNextInt()) {
        numbers.add(scanner.nextInt());
        count++;
      } else {
        System.out.println("Invalid input. Please enter only integers.");
        scanner.next();
      }
    }

    ArrayList<Integer> consecutiveNumbers = findConsecutiveNumbers(numbers);
    System.out.println("Consecutive numbers: " + consecutiveNumbers);

    ArrayList<Integer> largestConsecutiveSequence;
    largestConsecutiveSequence = findLargestConsecutiveSequence(numbers);

    System.out.println("Largest consecutive sequence: " + largestConsecutiveSequence);

    scanner.close();
  }

  /**
   * This method finds consecutive numbers in the given ArrayList.
   * 
   * @param inputNums ArrayList of integers.
   * @return ArrayList containing the consecutive numbers.
   */
  public static ArrayList<Integer> findConsecutiveNumbers(ArrayList<Integer> inputNums) {
    if (inputNums == null || inputNums.isEmpty()) {
      return new ArrayList<>();
    }

    for (int i = 0; i < inputNums.size(); i++) {
      for (int j = i + 1; j < inputNums.size(); j++) {
        if (inputNums.get(i) > inputNums.get(j)) {
          int temp = inputNums.get(i);
          inputNums.set(i, inputNums.get(j));
          inputNums.set(j, temp);
        }
      }
    }

    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i < inputNums.size() - 1; i++) {
      if (inputNums.get(i) + 1 == inputNums.get(i + 1)
          || inputNums.get(i) - 1 == inputNums.get(i + 1)) {
        if (!result.contains(inputNums.get(i))) {
          result.add(inputNums.get(i));
        }
        result.add(inputNums.get(i + 1));
      }
    }
    return result;
  }

  /**
   * This method finds the largest consecutive sequence of numbers in the given ArrayList.
   * 
   * @param inputNums ArrayList of integers.
   * @return ArrayList containing the largest consecutive sequence.
   */
  public static ArrayList<Integer> findLargestConsecutiveSequence(ArrayList<Integer> inputNums) {
    if (inputNums == null || inputNums.isEmpty()) {
      return new ArrayList<>();
    }

    for (int i = 0; i < inputNums.size(); i++) {
      for (int j = i + 1; j < inputNums.size(); j++) {
        if (inputNums.get(i) > inputNums.get(j)) {
          int temp = inputNums.get(i);
          inputNums.set(i, inputNums.get(j));
          inputNums.set(j, temp);
        }
      }
    }

    ArrayList<Integer> largestSequence = new ArrayList<>();
    ArrayList<Integer> currentSequence = new ArrayList<>();

    for (int i = 0; i < inputNums.size() - 1; i++) {
      currentSequence.add(inputNums.get(i));

      if (inputNums.get(i) + 1 != inputNums.get(i + 1)) {
        if (currentSequence.size() > largestSequence.size()) {
          largestSequence = new ArrayList<>(currentSequence);
        }
        currentSequence.clear();
      }
    }

    currentSequence.add(inputNums.get(inputNums.size() - 1));
    if (currentSequence.size() > largestSequence.size()) {
      largestSequence = new ArrayList<>(currentSequence);
    }

    return largestSequence;
  }
}
