//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Fish Tank 3000, a More Practical Virtual Fish Tank
// Course: CS 300 Fall 2021
//
// Author: Max Rountree
// Email: mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a specific type of fish (blue fish) that can be added to our FishTank
 */
public class BlueFish extends Fish {

  /**
   * Constructs using the superclass' overloaded constructor
   */
  public BlueFish() {
    super(2, "images/blue.png");
  }

  /**
   * This blue fish swims in the other direction than other fish, so we must override the swim
   * method to implement its swimming properly; after all, fish can't swim backwards!
   */
  @Override
  public void swim() {
    float x = this.getX() - this.speed();
    if (x <= 0) { x = TankObject.tank.width; } 
    this.setX(x);
  }

}
