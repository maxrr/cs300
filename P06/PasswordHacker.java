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
 * This class models a routine whose only goal is to break into a LockBox.
 */
public class PasswordHacker {

  private LockBox toPick; // this PasswordHacker's associated LockBox
  private int passwordLength; // the LockBox's password length

  /**
   * Creates this PasswordHacker and its respective LockBox
   * 
   * @param passwordLength the length of the LockBox's password to generate
   */
  public PasswordHacker(int passwordLength) {
    this.passwordLength = passwordLength;
    this.toPick = new LockBox(passwordLength);
  }

  /**
   * Quickly and easily uses the "hacked" password to open the lockbox Complexity: O(1)
   */
  public void hack() {
    // re-lock our LockBox
    toPick.reset();

    // unlock it by "hacking" it
    toPick.authenticate(toPick.hackMe());
  }

  /**
   * Bruteforces the LockBox's password starting at 0000 (for passwordLength=4) and ending at 9999
   * Complexity: O(2^n)
   */
  public void bruteForce() {
    // re-lock our LockBox
    toPick.reset();

    // iterate through all possiblities
    int i = 0;
    while (!toPick.isOpen()) {
      // try this possibility
      toPick.authenticate(this.generateGuess(i));
      i++;
    }
  }

  /**
   * Generates a new guess at the LockBox's password linearly
   * 
   * @param count the current iteration of this guess
   * @return the created String guess to feed the LockBox
   */
  public String generateGuess(int count) {
    // intialize our string that we're gonna add to
    String str = "" + count;
    
    // if we're over the length then trim it down
    if (str.length() > this.passwordLength) {
      return str.substring(str.length() - this.passwordLength);
    }
    
    // iterate and concatenate
    while (str.length() < this.passwordLength) {
      str = "0" + str;
    }
    
    // return our constructed string
    return str;
  }

}
