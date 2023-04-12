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

public class TankObject implements TankListener {

  protected static FishTank tank; // the tank that all TankObjects should use for processing

  protected PImage image; // the image that this TankObject uses

  private float x; // the x-position of this TankObject
  private float y; // the y-position of this TankObject
  private boolean isDragging; // whether this TankObject is being dragged or not

  private static int oldMouseX; // the cursor's x-position on the last frame (used when dragging)
  private static int oldMouseY; // the cursor's y-position on the last frame (used when dragging)

  /**
   * Our lovely constructor...
   * 
   * @param x             the x-coordinate of where this TankObject will begin
   * @param y             the y-coordinate of where this TankObject will begin
   * @param imageFileName the file name of the image that this TankObject will use
   */
  public TankObject(float x, float y, String imageFileName) {
    // set all of our instance variables to the ones provided in the constructor
    this.x = x;
    this.y = y;
    this.isDragging = false;

    // initialize our oldMouseX and oldMouseY parameters
    TankObject.oldMouseX = 0;
    TankObject.oldMouseY = 0;

    // we need to make sure tank isn't null before using it!
    if (tank != null) {
      this.image = tank.loadImage(imageFileName);
    }
  }

  /**
   * Sets the PApplet graphic display window for all TankObjects
   * 
   * @param tank the FishTank object to set
   */
  public static void setProcessing(FishTank tank) {
    // not an instance variable, so we refer to the class with TankObject`
    TankObject.tank = tank;
  }

  /**
   * Moves this TankObject by dx and dy pixels
   * 
   * @param dx move this many pixels on the x-axis
   * @param dy move this many pixels on the y-axis
   */
  public void move(int dx, int dy) {
    // set our instance variables to themselves + dx and dy respectively
    this.setX(this.getX() + dx);
    this.setY(this.getY() + dy);
  }

  /**
   * Getter for the x-coordinate
   * 
   * @return the x-coordinate of this TankObject
   */
  public float getX() {
    return this.x;
  }

  /**
   * Getter for the y-coordinate
   * 
   * @return the y-coordinate of this TankObject
   */
  public float getY() {
    return this.y;
  }

  /**
   * Setter for the x-coordinate
   * 
   * @param x the x-coordinate of this TankObject
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Setter for the y-coordinate
   * 
   * @param y the y-coordinate of this TankObject
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Getter for the image of this TankObject
   * 
   * @return the image of this TankObject
   */
  public PImage getImage() {
    return this.image;
  }

  /**
   * Getter for the isDragging parameter of this TankObject
   * 
   * @return true if this TankObject is being dragged, false otherwise
   */
  public boolean isDragging() {
    return this.isDragging;
  }

  /**
   * Starts dragging this TankObject
   */
  public void startDragging() {
    TankObject.oldMouseX = TankObject.tank.mouseX;
    TankObject.oldMouseY = TankObject.tank.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this TankObject
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draws this TankObject including its dragging stuff
   */
  @Override
  public void draw() {
    if (this.isDragging()) {
      /*
       * if the TankObject is dragging, move it to follow the moves of the mouse and set the old
       * mouse values to the correct ones
       */
      this.move(TankObject.tank.mouseX - TankObject.oldMouseX,
          TankObject.tank.mouseY - TankObject.oldMouseY);
      TankObject.oldMouseX = TankObject.tank.mouseX;
      TankObject.oldMouseY = TankObject.tank.mouseY;
    }

    TankObject.tank.image(this.image, this.x, this.y);
  }

  /**
   * This method determines if the mouse is over this TankObject
   * 
   * @return true if mouse is over this TankObject, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // set local variables here to avoid repetition
    PImage fImg = this.getImage();
    int mouseX = TankObject.tank.mouseX;
    int mouseY = TankObject.tank.mouseY;

    /*
     * Here, we need to check if the mouse is between the left and right sides of the image, that's
     * the first two checks. Afterwards, we need to check if the mouse is between the top and bottom
     * sides of the image. If all four checks pass, then the mouse is over the image. However, if
     * any one of these checks fail, we should return false.
     */

    return (mouseX > this.getX() - fImg.width / 2) // mouse > left side
        && (mouseX < this.getX() + fImg.width / 2) // mouse < right side
        && (mouseY > this.getY() - fImg.height / 2) // mouse > top side
        && (mouseY < this.getY() + fImg.height / 2); // mouse < bottom side
  }

  @Override
  /**
   * Method to be used when the mouse has been clicked on this TankObject
   */
  public void mousePressed() {
    // when we click on this, we want it to start being dragged
    this.startDragging();
  }

  @Override
  /**
   * Method to be used when the mouse has been released
   */
  public void mouseReleased() {
    // when we're no longer clicking, we want it to stop being dragged
    this.stopDragging();
  }

}
