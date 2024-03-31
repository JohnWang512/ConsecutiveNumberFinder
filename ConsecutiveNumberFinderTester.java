import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains testing methods for the app methods in ConsecutiveNumberFinder class.
 * 
 * @author He Jian John Wang
 */
public class ConsecutiveNumberFinderTester {
  /**
   * This calls the testConsecutiveNumberFinderApp method and prints out the result.
   * 
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    System.out.println("Success: " + testConsecutiveNumberFinderApp());
  }

  /**
   * This has various test cases that call the app methods to verify they work according to the
   * descriptions in the app method header comments. If all the test cases pass, then true is
   * returned, otherwise false.
   *
   * @return true when all test cases pass, false otherwise.
   */
  public static boolean testConsecutiveNumberFinderApp() {
    boolean error = false;

    // Test cases for findConsecutiveNumbers
    if (!testFindConsecutiveNumbers()) {
      error = true;
    }

    // Test cases for findLargestConsecutiveSequence
    if (!testFindLargestConsecutiveSequence()) {
      error = true;
    }

    return !error;
  }

  /**
   * Test cases for findConsecutiveNumbers
   * 
   * @return true when all test cases passes
   */
  private static boolean testFindConsecutiveNumbers() {
    boolean error = false;

    { // Checks if the method correctly identifies and returns consecutive numbers
      // from a mixed list. The list includes both consecutive and non-consecutive
      // numbers.
      ArrayList<Integer> nums = new ArrayList<>();
      nums.add(3);
      nums.add(5);
      nums.add(4);
      nums.add(8);
      nums.add(9);
      ArrayList<Integer> expected = new ArrayList<>();
      expected.add(3);
      expected.add(4);
      expected.add(5);
      expected.add(8);
      expected.add(9);
      ArrayList<Integer> actual = ConsecutiveNumberFinder.findConsecutiveNumbers(nums);
      if (!actual.equals(expected)) {
        error = true;
        System.out
            .println("testFindConsecutiveNumbers 1) Expected: " + expected + " Actual: " + actual);
      }
    }

    { // Verifies the method's behavior with a list where no numbers are consecutive.
      ArrayList<Integer> nums = new ArrayList<>();
      nums.add(10);
      nums.add(20);
      nums.add(30);
      nums.add(40);
      ArrayList<Integer> expected = new ArrayList<>();
      ArrayList<Integer> actual = ConsecutiveNumberFinder.findConsecutiveNumbers(nums);
      if (!actual.equals(expected)) {
        error = true;
        System.out
            .println("testFindConsecutiveNumbers 2) Expected: " + expected + " Actual: " + actual);
      }
    }

    return !error;
  }

  /**
   * Test cases for findLargestConsecutiveSequence
   * 
   * @return true when all test cases passes
   */
  private static boolean testFindLargestConsecutiveSequence() {
    boolean error = false;

    { // Tests the ability of the method to identify the largest consecutive sequence,
      // including negative numbers.
      ArrayList<Integer> nums = new ArrayList<>();
      nums.add(-3);
      nums.add(-1);
      nums.add(-2);
      nums.add(5);
      nums.add(7);
      nums.add(6);
      ArrayList<Integer> expected = new ArrayList<>();
      expected.add(-3);
      expected.add(-2);
      expected.add(-1);
      ArrayList<Integer> actual = ConsecutiveNumberFinder.findLargestConsecutiveSequence(nums);
      if (!actual.equals(expected)) {
        error = true;
        System.out.println(
            "testFindLargestConsecutiveSequence 1) Expected: " + expected + " Actual: " + actual);
      }
    }

    { // Evaluates the method's performance with a list containing multiple smaller
      // consecutive sequences.
      ArrayList<Integer> nums = new ArrayList<>();
      nums.add(1);
      nums.add(3);
      nums.add(2);
      nums.add(10);
      nums.add(12);
      nums.add(15);
      ArrayList<Integer> expected = new ArrayList<>();
      expected.add(1);
      expected.add(2);
      expected.add(3);
      ArrayList<Integer> actual = ConsecutiveNumberFinder.findLargestConsecutiveSequence(nums);
      if (!actual.equals(expected)) {
        error = true;
        System.out.println(
            "testFindLargestConsecutiveSequence 2) Expected: " + expected + " Actual: " + actual);
      }
    }

    if (error) {
      System.out.println("Error(s) in test cases.");
    } else {
      System.out.println("All test cases passed.");
    }
    return !error;
  }
}
