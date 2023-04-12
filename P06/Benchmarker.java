//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P06 Benchmarking Hacks, an Application to Bruteforce Passwords
// Course:   CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a benchmarking routine that will pit a PasswordHacker's bruteForce() and hack()
 * methods against each other
 */
public class Benchmarker {

  /**
   * Helper method that returns the average of an array of double values
   * 
   * @param arr the double array to find the average value of
   * @return the average value of the double array
   */
  private static double avg(double[] arr) {
    // our running total
    double tot = 0.0;

    // iterate and add
    for (int i = 0; i < arr.length; i++) {
      tot += arr[i];
    }

    // avg formula
    return tot / arr.length;
  }

  /**
   * Returns the time (in milliseconds) it took to execute the bruteForce() method of the supplied
   * PasswordHacker
   * 
   * @param ph the PasswordHacker to run bruteForce on
   * @return the time it took to execute this PasswordHacker's bruteForce() method
   */
  public static long timeBruteForce(PasswordHacker ph) {
    // log our current time
    long time = System.currentTimeMillis();

    // run our long-lasting algorithm
    ph.bruteForce();

    // return the difference in time
    return System.currentTimeMillis() - time;
  }

  /**
   * Returns the time (in milliseconds) it took to execute the hack() method of the supplied
   * PasswordHacker
   * 
   * @param ph the PasswordHacker to run hack on
   * @return the time it took to execute this PasswordHacker's hack() method
   */
  public static long timeHack(PasswordHacker ph) {
    // log our current time
    long time = System.currentTimeMillis();

    // run our much faster "hacking" algorithm
    ph.hack();

    // return the difference in time
    return System.currentTimeMillis() - time;
  }

  /**
   * Runs bruteForce() and hack() on many PasswordHackers of the same passwordLength, and returns
   * the average runtime of these methods as a nicely-formatted String
   * 
   * @param passwordLength the String length of the passwords to generate and subsequently
   *                       bruteForce() or hack()
   * @param numRuns        the number of times to repeat execution (larger = closer to true avg)
   * @return a nicely-formatted String detailing the results of the race
   */
  public static String race(int passwordLength, int numRuns) {
    // make some new arrays to hold the times we will gather
    double[] bruteForce = new double[numRuns];
    double[] hack = new double[numRuns];

    // iterate on numRuns and run bruteForce() and hack() that many times
    for (int i = 0; i < numRuns; i++) {
      PasswordHacker ph = new PasswordHacker(passwordLength);
      bruteForce[i] = timeBruteForce(ph);
      hack[i] = timeHack(ph);
    }

    // return a nicely-formatted string
    return "Brute force " + passwordLength + ": " + avg(bruteForce) + "\nHack " + passwordLength
        + ": " + avg(hack);
  }

  /**
   * Main entry point
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {

    // run a race with certain parameters
    System.out.println(race(3, 1));
    
  }

}
