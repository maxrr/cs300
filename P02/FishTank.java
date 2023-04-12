//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   P02 Fish Tank 1000, an Introduction to Graphical Applications
// Course:  CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:            n/a
// Partner Email:           n/a
// Partner Lecturer's Name: n/a
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: n/a
// Online Sources: 
//     https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html - To find
//     Character.toLowerCase and Character.toUpperCase
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class simulates behavior of fish inside of a fish tank using a GUI application
 */
public class FishTank {

  private static PApplet processing;
  private static PImage backgroundImage;
  private static Fish[] fishes;
  private static Random randGen;
  
  /**
   * Defines the initial environment properties of this application
   * @param processingObj a reference to the graphic display window of this application
   */
  public static void setup(PApplet processingObj) {
    // initialize all of our private parameters
    processing = processingObj;
    backgroundImage = processing.loadImage("images/background.png");
    fishes = new Fish[8];
    randGen = new Random();
  }
  
  /**
   * This method is called in FishTank.setup; Takes no arguments and is called every frame in an
   * infinite loop
   */
  public static void draw() {
    // draw the background image every frame to stop it looking like there are a ton of fishes
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    
    /*
     *  iterate through our 'fishes' array and draw all of the non-null references
     *  note to self: if we end up moving all null references to the end of the array in a later 
     *                project, make sure to break once we find a reference that's null
     */
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].draw();
      }
    }
  }
  
  /**
   * Checks if the mouse is over a specific Fish whose reference is provided as input parameter
   *
   * @param Fish reference to a specific fish
   * @return true if the mouse is over the specific Fish object (i.e. over
   * the image of the Fish), false otherwise
   */
  public static boolean isMouseOver(Fish fish) {
    // set a local variable here to avoid repetition
    PImage fImg = fish.getImage();
    
    /*
     * Here, we need to check if the mouse is between the left and right sides of the fish image,
     * that's the first two checks. Afterwards, we need to check if the mouse is between the top
     * and bottom sides of the fish image. If all four checks pass, then the mouse is over the
     * fish. However, if any one of these checks fail, we should return false.
     */
    
    return (processing.mouseX > fish.getPositionX() - fImg.width / 2)   // mouse > left side
        && (processing.mouseX < fish.getPositionX() + fImg.width / 2)   // mouse < right side
        && (processing.mouseY > fish.getPositionY() - fImg.height / 2)  // mouse > top side
        && (processing.mouseY < fish.getPositionY() + fImg.height / 2); // mouse < bottom side
  }
  
  /**
  * Callback method called each time the user presses the mouse
  */
  public static void mousePressed() {
    // iterate through our 'fishes' array and check if any are eligible to be dragged
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        if (isMouseOver(fishes[i])) {
          fishes[i].setDragging(true);
          break;
          // break after finding an eligible fish as we only want to drag one at a time
        }
      }
    }
  }
  
  /**
  * Callback method called each time the mouse is released
  */
  public static void mouseReleased() {
    /*
     * iterate through our 'fishes' array and set all of their 'dragging' parameters to false
     * note to self: if in a later project we are allowed to use more private parameters, use an
     *               int fishBeingDraggedIndex to avoid iteration and speed up this method!
     */
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].setDragging(false);
      }
    }
  }
  
  /**
  * Callback method called each time the user presses a key
  */
  public static void keyPressed() {
    /*
     *  create local variable 'key' here, in case processing.key changes during execution of this
     *  method; use toLowerCase to use one conditional instead of two
     */
    char key = Character.toLowerCase(processing.key);
    
    if (key == 'f') { // we want to make a new fish if we press f or F
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] == null) { 
          // if we find a null reference in our 'fishes' array, insert a new Fish object
          fishes[i] = new Fish(processing, 
              randGen.nextInt(processing.width),
              randGen.nextInt(processing.height));
          
          // break our loop, we only want to affect the first null entry we find
          break;
        }
      }
    } else if (key == 'r') { // we want to remove a fish we're hovering over if we press r or R
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] != null) {
          if (isMouseOver(fishes[i])) {
            /*
             *  if we find a fish that fits the bill, set its reference to null and break out of
             *  our loop
             */
            fishes[i] = null;
            break;
          }
        }
      }
    }
  }
  
  /**
   * Main entry point for our project; starts the application
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Utility.startApplication();
  }

}
