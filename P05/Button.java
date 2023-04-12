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

public class Button implements TankListener {

  private static final int WIDTH = 85; // the width of this button
  private static final int HEIGHT = 32; // the height of this button
  protected static FishTank tank; // the tank this button belongs to

  private float x; // the x-position of this button
  private float y; // the y-position of this button
  protected String label; // the text this button will have on it

  /**
   * Creates a new Button at a given position within the display window
   * 
   * @param label the text that will be drawn on this button
   * @param x     the x-position of this button
   * @param y     the y-position of this button
   */
  public Button(String label, float x, float y) {
    this.x = x;
    this.y = y;
    this.label = label;
  }

  /**
   * Setter for Button.tank; used for processing/graphics
   * 
   * @param tank
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * Draws this Button to the screen
   */
  @Override
  public void draw() {
    // TODO Auto-generated method stub
    tank.stroke(0); // set line value to black
    
    int fillColor = 200; // set fill color to light gray by default
    if (this.isMouseOver()) fillColor = 100; // set fill color to dark gray if mouse is hovering
    tank.fill(fillColor);
    
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0);
    tank.text(label, x, y);
  }

  /**
   * Callback method that is run whenever this Button has a mouse click on it
   */
  @Override
  public void mousePressed() {
    System.out.println("A button was pressed");
  }

  /**
   * Callback method that is run whenever this Button has a mouse released on it
   */
  @Override
  public void mouseReleased() {} // placeholder

  /**
   * Determines whether the cursor is hovering over this Button
   * 
   * @return true if the cursor is currently over this Button, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // mouse needs to be within the bounds of this Button's rectangular bounding box
    return (tank.mouseX > this.x - WIDTH / 2.0f)
        && (tank.mouseX < this.x + WIDTH / 2.0f)
        && (tank.mouseY > this.y - HEIGHT / 2.0f)
        && (tank.mouseY < this.y + HEIGHT / 2.0f);
  }

}
