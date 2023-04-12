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

public class AddOrangeFishButton extends Button {

  /**
   * Create a new orange Fish by clicking this button
   * @param x the x-position of this Button
   * @param y the y-position of this Button
   */
  public AddOrangeFishButton(float x, float y) {
    super("Add Orange", x, y);
  }
  
  /**
   * Define our custom behavior for when this Button is clicked
   */
  @Override
  public void mousePressed() {
    // make and add our new Fish
    Button.tank.objects.add(new Fish());
  }
  
}
