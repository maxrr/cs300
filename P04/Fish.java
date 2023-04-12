//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 2000, an Improved Fish Tank Simulation
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

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models the base class for a fish in our fish tank simulation
 */
public class Fish {
  // declare static data fields
  static private PApplet processing;
  static private int oldMouseX = 0;
  static private int oldMouseY = 0;

  // declare instance data fields
  private PImage image;
  private float x;
  private float y;
  private int speed;
  private boolean isDragging;
  private boolean isSwimming;

  /**
   * Creates a new fish object located at a specific (x, y) position of the display window
   * 
   * @param processing        PApplet object that represents the display window
   * @param x                 x-position of the image of this fish in the display window
   * @param y                 y-position of the image of this fish in the display window
   * @param speed             the swimming speed of this fish
   * @param fishImageFileName file name of the image used to create the fish
   */
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    // initialize static and instance fields
    Fish.processing = processing;
    this.image = processing.loadImage(fishImageFileName);
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.isDragging = false;
    this.isSwimming = false;
  }

  /**
   * Creates a new fish object located at the center of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Fish(PApplet processing) {
    // just call our other constructor
    this(processing, processing.width / 2, processing.height / 2, 5,
        "images/orange.png");
  }

  /**
   * Returns the image of type PImage of this fish
   * 
   * @return this fish's image
   */
  public PImage getImage() {
    // getter of the image instance field
    return this.image;
  }

  /**
   * Returns the x-position of the fish in the display window
   * 
   * @return this fish's x-position
   */
  public float getPositionX() {
    // getter of the x-position of this fish
    return this.x;
  }

  /**
   * Returns the y-position of the fish in the display window
   * 
   * @return this fish's y-position
   */
  public float getPositionY() {
    // getter of the x-position of this fish
    return this.y;
  }

  /**
   * Moves this fish with dx and dy
   * 
   * @param dx the amount to add to this fish's x-position
   * @param dy the amount to add to this fish's y-position
   */
  public void move(int dx, int dy) {
    // add dx and dy to this fish's x and y position
    this.x += dx;
    this.y += dy;
  }

  /**
   * Checks whether this fish is being dragged
   * 
   * @return true if fish is being dragged, false otherwise
   */
  public boolean isDragging() {
    // getter of the isDragging instance field
    return this.isDragging;
  }

  /**
   * Starts dragging this fish
   */
  public void startDragging() {
    // sets oldMouseX and oldMouseY to the current x and y positions of the mouse
    Fish.oldMouseX = processing.mouseX;
    Fish.oldMouseY = processing.mouseY;

    // sets the isDragging data field of this fish to true
    this.isDragging = true;
  }

  /**
   * Stops dragging this fish
   */
  public void stopDragging() {
    // sets the isDragging data field of this fish to false
    this.isDragging = false;
  }

  /**
   * Checks if the mouse is over this specific fish
   * 
   * @return true if the mouse is over the specific Fish object (i.e. over the image of the Fish),
   *         false otherwise
   */
  public boolean isMouseOver() {
    // set a local variable here to avoid repetition
    PImage fImg = this.getImage();

    /*
     * Here, we need to check if the mouse is between the left and right sides of the fish image,
     * that's the first two checks. Afterwards, we need to check if the mouse is between the top and
     * bottom sides of the fish image. If all four checks pass, then the mouse is over the fish.
     * However, if any one of these checks fail, we should return false.
     */

    return (processing.mouseX > this.getPositionX() - fImg.width / 2) // mouse > left side
        && (processing.mouseX < this.getPositionX() + fImg.width / 2) // mouse < right side
        && (processing.mouseY > this.getPositionY() - fImg.height / 2) // mouse > top side
        && (processing.mouseY < this.getPositionY() + fImg.height / 2); // mouse < bottom side
  }

  /**
   * Encourages this fish to start swimming
   */
  public void startSwimming() {
    // stop the fish being dragged and let it know that it's being dragged
    this.stopDragging();
    this.isSwimming = true;
  }

  /**
   * Stops this fish from swimming any more
   */
  public void stopSwimming() {
    // set isSwimming back to false, since it's no longer swimming
    this.isSwimming = false;
  }

  /**
   * Moves this fish horizontally one speed step from left to right
   */
  public void swim() {

    // move the fish to the right one speed step
    this.move(this.speed, 0);

    // wrap the fish around if it goes past the screen boundaries
    if (this.x > processing.width)
      this.x %= processing.width;
  }

  /**
   * Draws this fish to the display window, sets the position of the fish to follow the mouse if
   * being dragged
   */
  public void draw() {
    if (this.isDragging) {
      /*
       * if the fish is dragging, move it to follow the moves of the mouse and set the old mouse
       * values to the correct ones
       */
      this.move(processing.mouseX - Fish.oldMouseX, processing.mouseY - Fish.oldMouseY);
      Fish.oldMouseX = processing.mouseX;
      Fish.oldMouseY = processing.mouseY;
    }

    if (this.isSwimming) {
      // do swim behavior if this fish is swimming
      this.swim();
    }

    // draw this fish to the display window
    processing.image(this.image, this.x, this.y);
  }
}
