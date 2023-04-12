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

import java.util.Random;

/**
 * This class models a locked *something* that needs a password to be unlocked, maybe a combination
 * lock?
 */
public class LockBox {

  public static Random randGen; // the shared pseudo-random number generator for all LockBoxes
  private String password; // this LockBox's password
  private boolean isOpen; // whether this LockBox is open or not

  /**
   * Creates this LockBox along with a randomly generated password (with only numbers)
   * 
   * @param passwordLength the String length of this LockBox's password to generate
   */
  public LockBox(int passwordLength) {
    // initialize our random number generator if we haven't already
    if (LockBox.randGen == null)
      LockBox.randGen = new Random();

    // disallow password lengths at or less than zero
    if (passwordLength <= 0)
      throw new IllegalArgumentException("Invalid password length, must be >0.");

    // initialize our password to an empty string
    this.password = "";

    // create our password
    for (int i = 0; i < passwordLength; i++) {
      this.password += LockBox.randGen.nextInt(10);
    }
  }

  /**
   * Will check `guess` against this LockBox's password
   * 
   * @param guess the guessed password to check validity of
   */
  public void authenticate(String guess) {
    this.isOpen = this.password.equals(guess);
  }

  /**
   * Getter for this LockBox's password
   * 
   * @return the password of this LockBox
   */
  public String hackMe() {
    return this.password;
  }

  /**
   * Getter for this LockBox's isOpen
   * 
   * @return whether this LockBox is open or not
   */
  public boolean isOpen() {
    return this.isOpen;
  }

  /**
   * Resets this LockBox to a locked state
   */
  public void reset() {
    this.isOpen = false;
  }

}
