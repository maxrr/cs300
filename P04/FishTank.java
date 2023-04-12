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

import java.io.File;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class simulates behavior of fish inside of a fish tank using a GUI application
 */
public class FishTank {
  private static PApplet processing; // PApplet object which represents the graphic interface
                                     // of the Fish Tank application
  private static PImage backgroundImage; // PImage object which represents the background image
  private static Fish[] fishes; // array storing the current fishes present in the tank
  private static Random randGen; // Generator of random numbers
  private static int fishSpeed; // constant for storing the speed of all new fishes we will create
  private static Decoration[] decorations; // array storing all decorations in the fish tank

  private static String[] images = new String[] {"orange", "blue", "yellow", "black"};
  // fish color options
  private static int nextImageIndex; // index of the next fish image index in images (^)

  /**
   * Defines initial environment properties such as screen size and to load background images and
   * fonts as the program starts. It also initializes all data fields.
   * 
   * @param processingObj a PApplet object that represents the display window of the Fish Tank
   *                      application
   */
  public static void setup(PApplet processingObj) {
    // initialize all static fields
    FishTank.fishSpeed = 5;
    FishTank.processing = processingObj;
    FishTank.backgroundImage = processing.loadImage("images" + File.separator + "background.png");
    FishTank.fishes = new Fish[8];
    FishTank.randGen = new Random();

    // create our tank's decorations
    FishTank.decorations = new Decoration[4];
    FishTank.decorations[0] = new Decoration(processing, 430, 60, "images/flower.png");
    FishTank.decorations[1] = new Decoration(processing, 580, 470, "images/log.png");
    FishTank.decorations[2] = new Decoration(processing, 65, 520, "images/shell.png");
    FishTank.decorations[3] = new Decoration(processing, 280, 535, "images/ship.png");
  }

  /**
   * Continuously draws and updates the application display window
   * 
   */
  public static void draw() {
    // clear the display window by drawing the background image
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    // traverse the decorations array and draw all of the tank's decorations
    for (int i = 0; i < decorations.length; i++) {
      decorations[i].draw();
    }

    // traverse the fishes array and draw each of the fish present in the tank
    for (int i = 0; i < fishes.length; i++)
      if (fishes[i] != null)
        fishes[i].draw();
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // traverse the fishes array and start dragging a fish if the mouse is over it
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null && fishes[i].isMouseOver()) {
        fishes[i].startDragging();
        return; // only the fish at the lowest index will start dragging if there are fishes
                // overlapping
      }
    }

    // traverse the decorations array and start dragging a decoration if the mouse is over it
    for (int i = 0; i < decorations.length; i++) {
      if (decorations[i].isMouseOver()) {
        decorations[i].startDragging();
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // traverse the fishes array and stop dragging any fish
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null)
        fishes[i].stopDragging();
    }

    // traverse the decorations array and stop dragging any decoration
    for (int i = 0; i < decorations.length; i++) {
      decorations[i].stopDragging();
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(processing.key)) {
      case 'F': // add a new fish if the maximum numbers of fish allowed to be
                // present in the tank is not reached
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] == null) {
            fishes[i] = new Fish(processing, (float) randGen.nextInt(processing.width),
                (float) randGen.nextInt(processing.height), FishTank.fishSpeed,
                "images/" + images[nextImageIndex] + ".png");
            FishTank.nextImageIndex++;
            FishTank.nextImageIndex %= images.length;
            break;
          }
        }
        break;
      case 'R': // delete the clicked fish if any
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null && fishes[i].isMouseOver()) {
            fishes[i] = null;
            break;
          }
        }
        break;
      case 'S': // make all the fishes start swimming
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null) {
            fishes[i].startSwimming();
          }
        }
        break;
      case 'X': // make all the fishes stop swimming
        for (int i = 0; i < fishes.length; i++) {
          if (fishes[i] != null) {
            fishes[i].stopSwimming();
          }
        }
        break;

    }

  }

  /**
   * This main method starts the application
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // starts the application
    Utility.startApplication();

  }

}
