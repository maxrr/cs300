//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P01 Climbing Tracker, a Simple Progress Tracker for Climbing
// Course:  CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:            n/a
// Partner Email:           n/a
// Partner Lecturer's Name: n/a
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: n/a
// Online Sources:  
//     https://stackoverflow.com/questions/5283444/
//     convert-array-of-strings-into-a-string-in-java/5283753 - I was unsure if there was an 
//     included way to concatenate an array of strings, or if I would have to make my own method.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class provides methods to test our other class, ClimbingTracker. 
 */
public class ClimbingTrackerTester {

  /**
   * Contains a single call to runAllTests, and that is all
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    boolean tests = runAllTests();
    if (tests) {
      System.out.println("All tests succeeded");
    } else {
      System.out.println("One or more tests failed");
    }
  }
  
  /**
   * Tests ClimbingTracker's sendClimb method
   * 
   * @return true if all of the tests were successful, false otherwise
   */
  public static boolean testSendClimb() {
    String[] successfulClimbs = new String[3];
    int successfulClimbsSize = 0;
    
    /*
     *  run tests and compare successfulClimbsSize and the exact output of successfulClimbs, if
     *  either don't match then halt execution
     */
    
    successfulClimbsSize = ClimbingTracker.sendClimb(successfulClimbs, successfulClimbsSize, "V3");
    String[] sample = new String[] {"V3", null, null, null, null};
    if (successfulClimbsSize != 1 || successfulClimbs.equals(sample)) return false;
    
    successfulClimbsSize = ClimbingTracker.sendClimb(successfulClimbs, successfulClimbsSize, "V1");
    sample = new String[] {"V3", "V1", null, null, null};
    if (successfulClimbsSize != 2 || successfulClimbs.equals(sample)) return false;
    
    successfulClimbsSize = ClimbingTracker.sendClimb(successfulClimbs, successfulClimbsSize, "V1");
    sample = new String[] {"V3", "V1", "V1", null, null};
    if (successfulClimbsSize != 3 || successfulClimbs.equals(sample)) return false;
    
    successfulClimbsSize = ClimbingTracker.sendClimb(successfulClimbs, successfulClimbsSize, "V0");
    if (successfulClimbsSize != 3 || successfulClimbs.equals(sample)) return false;
    
    return true;
  }
  
  /**
   * Tests ClimbingTracker's failClimb method
   * 
   * @return true if all of the tests were successful, false otherwise
   */
  public static boolean testFailClimb() {
    String[] failedClimbs = new String[3];
    int failedClimbsSize = 0;
    
    /*
     *  run tests and compare failedClimbsSize and the exact output of failedClimbs, if
     *  either don't match then halt execution
     */
    
    failedClimbsSize = ClimbingTracker.failClimb(failedClimbs, failedClimbsSize, "V3");
    String[] sample = new String[] {"V3", null, null, null, null};
    if (failedClimbsSize != 1 || failedClimbs.equals(sample)) return false;
    
    failedClimbsSize = ClimbingTracker.failClimb(failedClimbs, failedClimbsSize, "V1");
    sample = new String[] {"V3", "V1", null, null, null};
    if (failedClimbsSize != 2 || failedClimbs.equals(sample)) return false;
    
    failedClimbsSize = ClimbingTracker.failClimb(failedClimbs, failedClimbsSize, "V1");
    sample = new String[] {"V3", "V1", "V1", null, null};
    if (failedClimbsSize != 3 || failedClimbs.equals(sample)) return false;
    
    failedClimbsSize = ClimbingTracker.failClimb(failedClimbs, failedClimbsSize, "V0");
    if (failedClimbsSize != 3 || failedClimbs.equals(sample)) return false;
    
    return true;
  }
  
  /**
   * Validates an output from ClimbingTracker.getStats
   * 
   * @param input the string output from ClimbingTracker.getStats
   * @param checkSend the string to check the 'send:' field against
   * @param checkFail the string to check the 'fail:' field against
   * @return true if output matches checkSend and checkFail, false otherwise
   */
  private static boolean testStatsReturn(String input, String checkSend, String checkFail) {
    String sendString = input.substring(6, input.indexOf("\n"));
    String failString = input.substring(input.indexOf("\n") + 7);
    
    return (sendString.equals(checkSend) && failString.equals(checkFail));
  }
  
  /**
   * Tests ClimbingTracker's getStats method
   * 
   * @return true if all of the tests were successful, false otherwise
   */
  public static boolean testGetStats() {
    
    /*
     * make some sample inputs and check exactly against outputs from ClimbingTracker.getStats,
     * if any tests return false from testStatsReturn, then return false, otherwise true
     */
    
    String[] testPass = new String[] {"V0", "V0", "V1", "V2", "V4", null, null};
    String[] testFail = new String[] {"V3", "V3", "V2", "V1", null, null, null};
    boolean test1 = testStatsReturn(ClimbingTracker.getStats(testPass, 5, testFail, 4, 6),
        "1.4",
        "2.25");
    
    testPass = new String[] {"V0", "V0", "V3", "V3"};
    boolean test2 = testStatsReturn(ClimbingTracker.getStats(testPass, 4, testFail, 4, 3),
        "2.0",
        "2.0");
    
    testFail = new String[] {};
    boolean test3 = testStatsReturn(ClimbingTracker.getStats(testPass, 4, testFail, 0, 4),
        "1.5",
        "--");
    
    testPass = new String[] {};
    boolean test4 = testStatsReturn(ClimbingTracker.getStats(testPass, 0, testFail, 0, 9999),
        "--",
        "--");
    
    testPass = new String[] {"V2", "V2", "V3", "V4", "V1", "V5"};
    testFail = new String[] {"V2", "V2", "V3", "V4", "V1", "V5"};
    boolean test5 = testStatsReturn(ClimbingTracker.getStats(testPass, 6, testFail, 6, 2),
        "3.0",
        "3.0");
    
    return (test1 && test2 && test3 && test4 && test5);
  }
  
  /**
   * Tests ClimbingTracker's getHistogram method
   * 
   * @return true if all of the tests were successful, false otherwise
   */
  public static boolean testGetHistogram() {
    
    /*
     * another test that checks the exactness of the output, removed newlines to make the code
     * more readable, and more easily changable for future tests
     * 
     * (note: this might be a good chunk to abstract)
     */
    
    String[] testPass = new String[] {"V0", "V0", "V1", "V2", "V4", null, null};
    String[] testFail = new String[] {"V3", "V3", "V2", "V1", null, null, null};
    String test1 = ClimbingTracker.getHistogram(testPass, 5, testFail, 4);
    if (!test1.replaceAll("\n", "").equals("V0: + + V1: - + V2: - + V3: - - V4: + ")) 
      return false;
    
    testPass = new String[] {"V0", "V0", "V3", "V3"};
    String test2 = ClimbingTracker.getHistogram(testPass, 4, testFail, 4);
    if (!test2.replaceAll("\n", "").equals("V0: + + V1: - V2: - V3: - - + + ")) 
      return false;
    
    testFail = new String[] {};
    String test3 = ClimbingTracker.getHistogram(testPass, 4, testFail, 0);
    if (!test3.replaceAll("\n", "").equals("V0: + + V1: V2: V3: + + ")) 
      return false;
    
    testPass = new String[] {};
    String test4 = ClimbingTracker.getHistogram(testPass, 0, testFail, 0);
    if (!test4.replaceAll("\n", "").equals("Error: no data to display")) 
      return false;
    
    testPass = new String[] {"V2", "V2", "V3", "V4", "V1", "V5"};
    testFail = new String[] {"V2", "V2", "V3", "V4", "V1", "V5"};
    String test5 = ClimbingTracker.getHistogram(testPass, 6, testFail, 6);
    if (!test5.replaceAll("\n", "").equals("V0: V1: - + V2: - - + + V3: - + V4: - + V5: - + "))
      return false;
    
    return true;
  }
  
  /**
   * Runs all tests defined in this class
   * 
   * @return true if the myriad of tests are all successful, false if any of them fail
   */
  public static boolean runAllTests() {
    return ( testSendClimb() && testFailClimb() && testGetStats() && testGetHistogram() );
  }

}
