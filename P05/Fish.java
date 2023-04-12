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
 * This class models a fish that will go in our fish tank
 */
public class Fish extends TankObject {

  private int speed; // the swimming speed of this Fish
  private boolean isSwimming; // whether this Fish is swimming or not

  /**
   * Here we initialize this Fish's private fields as well as call the TankObject constructor, this
   * method will spawn the fish at random x and y coordinates
   * 
   * @param speed             the speed this Fish will have when swimming
   * @param fishImageFileName the file name of the image of this Fish
   */
  public Fish(int speed, String fishImageFileName) {
    // call the TankObject constructor with a random x and y-coordinate and our image name
    super(tank.randGen.nextInt(tank.width), tank.randGen.nextInt(tank.height), fishImageFileName);

    // throw an IllegalArgumentException if we are passed a negative speed
    if (speed <= 0)
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    this.speed = speed;

    // the fish do not start swimming
    this.isSwimming = false;
  }

  /**
   * Will spawn a new orange fish with a speed of 5
   */
  public Fish() {
    // call our overloaded constructor
    this(5, "images/orange.png");
  }

  // Overrides the draw() method implemented in the parent class.
  // This method sets the position of this fish to follow the
  // mouse moves if it is dragging, calls its swim() method
  // if it is swimming, and draws it to the display window.
  // You can use a partial overriding (call draw() method of
  // the super class and adds the behavior specific to drawing a fish.
  /**
   * Overrides the draw() method implemented in TankObject, sets the position of the fish to follow
   * the mouse if it is dragging, calls its swim() method if it's swimming, and draws it to the
   * display window
   */
  @Override
  public void draw() {
    // swim if we should be
    if (this.isSwimming) {
      this.swim();
    }

    // do the rest of our TankObject draw function
    super.draw();
  }

  /**
   * Getter for this Fish's isSwimming parameter
   * 
   * @return true if the fish is swimming, false otherwise
   */
  public boolean isSwimming() {
    return this.isSwimming;
  }

  /**
   * Starts swimming this fish
   */
  public void startSwimming() {
    this.isSwimming = true;
  }

  /**
   * Stops swimming this fish
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }

  /**
   * Gets the speed of this fish
   * 
   * @return this Fish's speed parameter
   */
  public int speed() {
    return this.speed;
  }

  /**
   * Moves horizontally the fish one speed step from left to right
   */
  public void swim() {
    this.setX((this.getX() + this.speed()) % TankObject.tank.width);
  }


}
