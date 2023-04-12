//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Exceptional Climbing: an Improved Climbing Tracker with Exception Handling
// Course:   CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    n/a
// Partner Email:   n/a
// Partner Lecturer's Name: n/a
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by name and describe how they helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.zip.DataFormatException;

/**
 *  An improved climbing tracker tester, checking for the correct exceptions and handling
 *  them accordingly
 */
public class ExceptionalClimbingTester {
  
  /**
   * Helper method to avoid almost identical code duplication for testSendClimb and testFailClimb
   * @return true if all tests pass, false otherwise
   */
  private static boolean testGenericClimb(boolean isSend) {
    // create some local variables
    String[] arr = new String[] {"V3", "V2", "V2", "V5", null, null, null};
    int numArr = 4;
    
    // valid input should cause no exceptions
    try {
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V6");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V6");
    } catch (Exception e) {
      // we should not get any errors, test failed
      return false;
    }
    
    // invalid grades should cause IllegalArgumentException
    try {
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "W3");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "W3");
      
      // we should immediately go to the catch block, error if we don't get an error from this
      return false;
    } catch (IllegalArgumentException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("W3 is not a valid grade")) return false;
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    // another invalid grade test
    try {
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V24");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V24");
      
      // we should immediately go to the catch block, error if we don't get an error from this
      return false;
    } catch (IllegalArgumentException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("V24 is not a valid grade")) return false;
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    // another invalid grade test
    try {
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V04");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V04");
      
      // we should immediately go to the catch block, error if we don't get an error from this
      return false;
    } catch (IllegalArgumentException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("V04 is not a valid grade")) return false;
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    // test behavior when array is full
    try {
      // set our arr to a full one
      arr = new String[] {"V2", "V2", "V5", "V3"};
      numArr = 4;
      
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V2");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V2");
      
      // test fails if we don't have an error to catch
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing, this is expected behavior, and we want to continue with our other tests
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    // test behavior when array has null elements between 0 and numSend
    try {
      // set our arr to an invalid array (null values not consolidated)
      arr = new String[] {"V2", null, "V5", "V6", null, null};
      
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V2");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V2");
      
      // test fails if we don't have an error to catch
      return false;
    } catch (DataFormatException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("invalid oversize array")) return false;
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    // test behavior when array has invalid size
    try {
      // set our numArr to an invalid value
      numArr = -2;
      
      // call ExceptionalClimbing.sendClimb or ExceptionalClimbing.failClimb depending on our mode
      if (isSend) numArr = ExceptionalClimbing.sendClimb(arr, numArr, "V3");
      else numArr = ExceptionalClimbing.failClimb(arr, numArr, "V3");
      
      // test fails if we don't have an error to catch
      return false;
    } catch (DataFormatException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("invalid oversize array")) return false;
    } catch (Exception e) {
      // we had some other error, test failed
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests ExceptionalClimbing.sendClimb for edge cases
   * @return true if all tests pass, false otherwise
   */
  public static boolean testSendClimb() {
    return testGenericClimb(true);
  }
  
  /**
   * Tests ExceptionalClimbing.failClimb for edge cases
   * @return true if all tests pass, false otherwise
   */
  public static boolean testFailClimb() {
    return testGenericClimb(false);
  }
  
  /**
   * Tests ExceptionalClimbing.getStats for edge cases
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetStats() {
    // create some local variables
    String[] send = new String[] {"V2", "V3", "V4", "V5"};
    int numSend = 4;
    String[] fail = new String[] {"V3", "V4", "V5", "V6"};
    int numFail = 4;
    int historyLength = 3;
    
    // valid input should cause no exceptions (one OR two populated arrays)
    try {
      // call our getStats method with both tables populated
      ExceptionalClimbing.getStats(send, numSend, fail, numFail, historyLength);
      
      // call our getStats method with only one table populated
      fail = new String[] {};
      numFail = 0;
      ExceptionalClimbing.getStats(send, numSend, fail, numFail, historyLength);
    } catch (Exception e) {
      // we should get no errors from this, if we do the test has failed
      return false;
    }
    
    // passing two empty arrays should cause a RuntimeException
    try {
      // set our send array to a new, empty array and call our getStats method
      send = new String[] {};
      numSend = 0;
      ExceptionalClimbing.getStats(send, numSend, fail, numFail, historyLength);
      
      // we fail the test here if calling the method with two empty arrays doesn't throw an error
      return false;
    } catch (RuntimeException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("no climbs provided")) return false;
    } catch (Exception e) {
      // some other error happened, which should not occur and the test fails
      return false;
    }
    
    // passing a negative historyLength should cause an IllegalArgumentException
    try {
      // set our arrays to real values
      send = new String[] {"V2", "V3"};
      numSend = 2;
      fail = new String[] {"V4", "V5"};
      numFail = 2;
      
      // set our historyLength to an invalid value (a negative number), and call our getStats method
      historyLength = -4;
      ExceptionalClimbing.getStats(send, numSend, fail, numFail, historyLength);
      
      // if we reach this point without an error having been thrown, the test has failed
      return false;
    } catch (IllegalArgumentException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals(historyLength + " is not a valid history length")) return false;
    } catch (Exception e) {
      // some other error happened, test failed
      return false;
    }
    
    // passing a zero historyLength should also cause an IllegalArgumentException
    try {
      // set our historyLength to an invalid value (zero this time), and call our getStats method
      historyLength = 0;
      ExceptionalClimbing.getStats(send, numSend, fail, numFail, historyLength);
      
      // if we reach this point without an error having been thrown, the test has failed
      return false;
    } catch (IllegalArgumentException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals(historyLength + " is not a valid history length")) return false;
    } catch (Exception e) {
      // some other error happened, test failed
      return false;
    }
    
    // test has passed at this point
    return true;
  }
  
  /**
   * Tests ExceptionalClimbing.getHistogram for edge cases
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetHistogram() {
    // create some local variables
    String[] send = new String[] {"V2", "V3", "V4", "V5"};
    int numSend = 4;
    String[] fail = new String[] {"V3", "V4", "V5", "V6"};
    int numFail = 4;
    
    // valid input (one or two populated arrays) should cause no exceptions
    try {
      // call our method with valid inputs
      ExceptionalClimbing.getHistogram(send, numSend, fail, numFail);
      
      // set our fail array to a new, empty array to test for only one array being populated
      fail = new String[] {};
      numFail = 0;
      
      // call our method with valid inputs
      ExceptionalClimbing.getHistogram(send, numSend, fail, numFail);
    } catch (Exception e) {
      // if we encounter any errors with valid inputs, the test has failed
      return false;
    }
    
    // passing two empty array should throw a RuntimeException
    try {
      // set our string array to a new, empty array to have both arrays now be empty
      send = new String[] {};
      numSend = 0;
      
      // call our method with invalid inputs
      ExceptionalClimbing.getHistogram(send, numSend, fail, numFail);
      
      // we should not reach this point, if we can call the method without errors the test fails
      return false;
    } catch (RuntimeException e) {
      // check if our error message equals exactly what it should be, if it does then do nothing
      if (!e.getMessage().equals("no climbs provided")) return false;
    } catch (Exception e) {
      // another kind of exception was thrown, the test has failed
      return false;
    }
    
    // test has passed at this point
    return true;
   
  }
  
  /**
   * Helper method to run all of our tests in order, to evaluate the ExceptionalClimbing class
   * @return whether all tests succeeded or not
   */
  public static boolean runAllTests() {
    return ( testSendClimb() && testFailClimb() && testGetStats() && testGetHistogram() );
  }

  /**
   * Entry point for the program
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    boolean passed = runAllTests();
    if (passed) System.out.println("All tests passed");
    else System.out.println("One or more tests failed");
  }
  

}
