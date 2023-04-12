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

import processing.core.PImage;

public class BlackFish extends Fish {

  private TankObject source; // the TankObject where this BlackFish will start
  private TankObject destination; // the TankObject where this BlackFish will move towards

  /**
   * Constructs using the superclass' overloaded constructor
   * 
   * @param source      the source decoration of this BlackFish
   * @param destination the destination decoration of this BlackFish
   */
  public BlackFish(TankObject source, TankObject destination) {
    // call our parent class' constructor
    super(2, "images/black.png");

    // set our source and destination to the supplied arguments
    this.source = source;
    this.destination = destination;
  }

  /**
   * Makes one speed move towards destination
   */
  public void moveTowardsDestination() {
    // example given in project documentation
    float dx = this.destination.getX() - this.getX();
    float dy = this.destination.getY() - this.getY();
    int d = (int) Math.sqrt(dx * dx + dy * dy);

    this.setX(this.getX() + (this.speed() * (this.destination.getX() - this.getX()) / d));
    this.setY(this.getY() + (this.speed() * (this.destination.getY() - this.getY()) / d));
  }

  /**
   * Returns true if this BlackFish is over another TankObject, false otherwise
   * 
   * @param other the TankObject to check overlap with
   * @return true if this BlackFish is over another TankObject, false otherwise
   */
  public boolean isOver(TankObject other) {
    // set some vars to avoid repeated method calls
    PImage thisImage = this.getImage(); // the image of this BlackFish
    PImage otherImage = other.getImage(); // the image of the TankObject we're checking against
    if (thisImage == null || otherImage == null)
      return false;

    int x1 = (int) this.getX() - thisImage.width / 2;
    int y1 = (int) this.getY() - thisImage.height / 2;
    int x2 = (int) this.getX() + thisImage.width / 2;
    int y2 = (int) this.getY() + thisImage.height / 2;

    int x3 = (int) other.getX() - otherImage.width / 2;
    int y3 = (int) other.getY() - otherImage.height / 2;
    int x4 = (int) other.getX() + otherImage.width / 2;
    int y4 = (int) other.getY() + otherImage.height / 2;

    // example given in project documentation
    if (x2 < x3 || x1 > x4 || y2 < y3 || y1 > y4)
      return false;
    return true;
  }

  /**
   * BlackFish has special swimming behavior, so we need to define its own swim method
   */
  @Override
  public void swim() {
    // if we're over our destination, then switch destination and source, otherwise take a step
    // towards our destination
    if (this.isOver(this.destination)) {
      TankObject temp = this.destination;
      this.destination = this.source;
      this.source = temp;
    }
    this.moveTowardsDestination();
  }

}
