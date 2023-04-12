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
 * This class provides methods for tracking progress in a user's bouldering progress
 */
public class ClimbingTracker {
  
  /**
   * Checks whether a given 'grade' string is valid or not
   * 
   * @param grade the grade string to check validity of
   * @return true if the grade string is valid, false otherwise
   */
  public static boolean isValidGrade(String grade) {
    return (grade.length() == 2
        && (grade.charAt(0) == 'V' || grade.charAt(0) == 'v') 
        && Character.isDigit( grade.charAt(1) ));
        // if grades go more than 2 digits, this method will break!
  }
  
  /**
   * Analyzes an array of grades and returns the highest grade
   * 
   * @param arr an oversize string array containing climb grades
   * @param numArr the size of the 'arr' array
   * @return the highest grade present in the 'arr' array
   */
  private static int getHighestGrade(String[] arr, int numArr) {
    int highest = 0;
    for (int i = 0; i < numArr; i++) {
      int grade = Integer.parseInt(arr[i].substring(1));
      if (grade > highest) {
        highest = grade;
      }
    }
    return highest;
  }
  
  /**
   * Attempts to add a completed grade to the 'send' array
   * 
   * @param send an oversize string array containing pre-existing completed climb grades
   * @param numSend the size of the 'send' array
   * @param grade the grade string to be added
   * @return the new size of the 'send' array if successful, otherwise returns the same size as
   *     before the call to the method
   */
  public static int sendClimb(String[] send, int numSend, String grade) {
    // ensure that numSend and grade are valid parameters
    if (numSend < send.length && isValidGrade(grade)) {
      send[numSend] = grade;
      numSend++;
    }
    return numSend;
  }
  
  /**
   * Attempts to add a failed grade to the 'fail' array
   * 
   * @param fail an oversize array containing pre-existing failed climb grades
   * @param numFail the size of the 'fail' array
   * @param grade the grade string to be added
   * @return the new size of the 'fail' array if successful, otherwise returns the same size as
   *     before the call to the method
   */
  public static int failClimb(String[] fail, int numFail, String grade) {
    // same parameters and behavior as sendClimb, could be abstracted to one method
    if (numFail < fail.length && isValidGrade(grade)) {
      fail[numFail] = grade;
      numFail++;
    }
    return numFail;
  }
  
  /**
   * Returns a formatted string with the average climb grade over the most recent historyLength
   *     number of climbs for both send and fail
   * 
   * @param send an oversize array containing pre-existing completed climb grades
   * @param numSend the size of the 'send' array
   * @param fail an oversize array containing pre-existing failed climb grades
   * @param numFail the size of the 'fail' array
   * @param historyLength the number of climbs to look over, starting with most recent, in both
   *     the 'send' and 'fail' arrays
   * @return a formatted string containing average climb grades for failures and successes
   */
  public static String getStats(String[] send, int numSend, String[] fail, int numFail,
      int historyLength) {
    
    /* 
     * initialize our strings to '--', so that if we don't have a defined 'send' or 'fail', we
     * still have a nicely-formatted output
     */
    String sendAvg = "--";
    int sendHistoryLength = numSend - Math.max(numSend - historyLength, 0);
    
    if (sendHistoryLength > 0 && send.length > 0) {
      int sendTotal = 0;
      for (int i = numSend - 1; i >= Math.max(numSend - historyLength, 0); i--) {
        sendTotal += Integer.parseInt(send[i].substring(1));
      }
      sendAvg = "" + (double) sendTotal / (double) sendHistoryLength;
    }
    
    /*
     * same as above, would be helpful to abstract if we need it any more than
     * these two instances
     */
    String failAvg = "--";
    int failHistoryLength = numFail - Math.max(numFail - historyLength, 0);
    
    if (failHistoryLength > 0 && fail.length > 0) {
      int failTotal = 0;
      for (int i = numFail - 1; i >= Math.max(numFail - historyLength, 0); i--) {
        failTotal += Integer.parseInt(fail[i].substring(1));
      }
      failAvg = "" + ((double) failTotal / (double) failHistoryLength);
    }
    
    String assembledReturnString = "send: " + sendAvg + "\nfail: " + failAvg; 
    return assembledReturnString;
  }
  
  /**
   * Returns a formatted string with the number of climbs at each grade (zero to highest), with
   *     failures reported first as a '-', and successes reported second as a '+'
   * 
   * @param send an oversize array containing pre-existing completed climb grades
   * @param numSend the size of the 'send' array
   * @param fail an oversize array containing pre-existing failed climb grades
   * @param numFail the size of the 'fail' array
   * @return a formatted string containing the number of failures and successes
   */
  public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
    if (numSend == 0 && numFail == 0) {
      return "Error: no data to display";
    }
    
    // calculate our highest grade so we know how high we have to loop
    int highestSend = getHighestGrade(send, numSend);
    int highestFail = getHighestGrade(fail, numFail);
    int highestGrade = Math.max(highestSend, highestFail);
    
    // make a new perfect-size array to store how many of each grade we have
    String[] gradeReport = new String[highestGrade + 1];
    
    for (int i = 0; i < gradeReport.length; i++) {
      gradeReport[i] = "V" + i + ": ";
    }
    
    // add our failures
    for (int i = 0; i < numFail; i++) {
      int grade = Integer.parseInt(fail[i].substring(1));
      gradeReport[grade] += "- ";
    }
    
    // add our passes
    for (int i = 0; i < numSend; i++) {
      int grade = Integer.parseInt(send[i].substring(1));
      gradeReport[grade] += "+ ";
    }
    
    // concatenate our 'gradeReport' array with newlines
    String assembledReturnString = String.join("\n", gradeReport);
    return assembledReturnString;
  }

}
