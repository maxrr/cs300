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
 * This class models a decorative item that can be placed in our fish tank, it has the same methods
 * and data fields as the Fish class, except for thos related to swimming
 */
public class Decoration {

  // declare static data fields
  static private PApplet processing;
  static private int oldMouseX = 0;
  static private int oldMouseY = 0;

  // declare instance data fields
  private PImage image;
  private float x;
  private float y;
  private boolean isDragging;

  /**
   * Creates a new decoration object at (x, y) with an image
   * 
   * @param processing    PApplet used as the display window of FishTank
   * @param x             x-position of the object
   * @param y             y-position of the object
   * @param imageFileName filename of the image to be loaded for this decoration
   */
  public Decoration(PApplet processing, float x, float y, String imageFileName) {
    // load up our static field
    Decoration.processing = processing;

    // load up our instance fields
    this.x = x;
    this.y = y;
    this.image = processing.loadImage(imageFileName);
  }

  /**
   * Returns the image of type PImage of this decoration
   * 
   * @return this decoration's image
   */
  public PImage getImage() {
    // getter of the image instance field
    return this.image;
  }

  /**
   * Returns the x-position of the decoration in the display window
   * 
   * @return this decoration's x-position
   */
  public float getPositionX() {
    // getter of the x-position of this decoration
    return this.x;
  }

  /**
   * Returns the y-position of the decoration in the display window
   * 
   * @return this decoration's y-position
   */
  public float getPositionY() {
    // getter of the x-position of this decoration
    return this.y;
  }

  /**
   * Checks whether this decoration is being dragged
   * 
   * @return true if decoration is being dragged, false otherwise
   */
  public boolean isDragging() {
    // getter of the isDragging instance field
    return this.isDragging;
  }

  /**
   * Starts dragging this decoration
   */
  public void startDragging() {
    // sets oldMouseX and oldMouseY to the current x and y positions of the mouse
    Decoration.oldMouseX = processing.mouseX;
    Decoration.oldMouseY = processing.mouseY;

    // sets the isDragging data field of this decoration to true
    this.isDragging = true;
  }

  /**
   * Stops dragging this decoration
   */
  public void stopDragging() {
    // sets the isDragging data field of this decoration to false
    this.isDragging = false;
  }

  /**
   * Checks if the mouse is over this specific decoration
   * 
   * @return true if the mouse is over the specific Decoration object (i.e. over the image of the
   *         Decoration), false otherwise
   */
  public boolean isMouseOver() {
    // set a local variable here to avoid repetition
    PImage fImg = this.getImage();

    /*
     * Here, we need to check if the mouse is between the left and right sides of the image, that's
     * the first two checks. Afterwards, we need to check if the mouse is between the top and bottom
     * sides of the image. If all four checks pass, then the mouse is over the image. However, if
     * any one of these checks fail, we should return false.
     */

    return (processing.mouseX > this.getPositionX() - fImg.width / 2) // mouse > left side
        && (processing.mouseX < this.getPositionX() + fImg.width / 2) // mouse < right side
        && (processing.mouseY > this.getPositionY() - fImg.height / 2) // mouse > top side
        && (processing.mouseY < this.getPositionY() + fImg.height / 2); // mouse < bottom side
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
   * Draws this fish to the display window, sets the position of the fish to follow the mouse if
   * being dragged
   */
  public void draw() {
    if (this.isDragging) {
      /*
       * if the fish is dragging, move it to follow the moves of the mouse and set the old mouse
       * values to the correct ones
       */
      this.move(processing.mouseX - Decoration.oldMouseX, processing.mouseY - Decoration.oldMouseY);
      Decoration.oldMouseX = processing.mouseX;
      Decoration.oldMouseY = processing.mouseY;
    }

    // draw this fish to the display window
    processing.image(this.image, this.x, this.y);
  }

}
