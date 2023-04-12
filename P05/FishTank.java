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

import java.util.Random;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is our main entry point into our new GUI application, and defines the methods that we
 * will use to populate our fish tank and have lots of virtual fish fun!
 */
public class FishTank extends PApplet {

  private PImage backgroundImage; // PImage object representing our background image
  protected ArrayList<TankListener> objects; // List storing interactive objects
  protected Random randGen; // Generator of random numbers

  private TankObject flower;
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  @Override
  /**
   * This method sets the size of this PApplet to 800px wide and 600px tall
   */
  public void settings() {
    size(800, 600);
  }

  @Override
  /**
   * This method defines initial environment properties like screen size, loads the background image
   * and fonts as the program starts, and initializes all data fields
   */
  public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");

    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);

    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
    // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // load the background image and store the loaded image to backgroundImage
    this.backgroundImage = this.loadImage("./images/background.png");

    // create an empty array list of objects
    objects = new ArrayList<TankListener>();

    // set randGen to the reference of a new Random object
    randGen = new Random();

    // set our TankObject's FishTank reference to this FishTank
    TankObject.setProcessing(this);

    // set our decoration objects to TankObjects
    this.flower = new TankObject(430, 60, "images/flower.png");
    this.log = new TankObject(580, 470, "images/log.png");
    this.shell = new TankObject(65, 520, "images/shell.png");
    this.ship = new TankObject(280, 535, "images/ship.png");

    // add our decorations to our objects list
    this.objects.add(this.flower);
    this.objects.add(this.log);
    this.objects.add(this.shell);
    this.objects.add(this.ship);

    // create our black fish
    this.objects.add(new BlackFish(this.log, this.flower));
    this.objects.add(new BlackFish(this.shell, this.flower));
    
    // set our buttons' processing env to this FishTank
    Button.setProcessing(this);
    
    // create and place our buttons in our env
    this.objects.add(new AddBlueFishButton(43, 16));
    this.objects.add(new AddOrangeFishButton(129, 16));
    this.objects.add(new AddYellowFishButton(215, 16));
    this.objects.add(new ClearTankButton(301, 16));
    
  }

  /**
   * Continuously draws and updates the application display window
   */
  @Override
  public void draw() {
    // clear the display window by drawing the background image
    this.image(backgroundImage, this.width / 2, this.height / 2);

    // traverse the objects list and draw each of the objects to this display window
    for (TankListener i : this.objects) {
      i.draw();
    }

  }

  @Override
  /**
   * This method is a callback method and is called each time the user presses the mouse
   */
  public void mousePressed() {
    // traverse the objects list and call mousePressed method
    // of the first object being clicked in the list
    for (TankListener i : this.objects) {
      if (i.isMouseOver()) {
        i.mousePressed();
        break;
      }
    }
  }

  @Override
  /**
   * This method is a callback method and is called each time the user releases the mouse
   */
  public void mouseReleased() {
    // traverse the objects list and call each object's mouseReleased() method
    for (TankListener i : this.objects) {
      i.mouseReleased();
    }
  }

  /**
   * This method adds an instance of TankListener to the objects ArrayList
   * 
   * @param object the item to add to the objects ArrayList
   */
  public void addObject(TankListener object) {
    this.objects.add(object);
  }

  // Callback method called each time the user presses a key
  @Override
  /**
   * This method is a callback method and is called each time the user presses a key
   */
  public void keyPressed() {
    // To be implemented later in the next sections
    switch (Character.toUpperCase(this.key)) {
      case 'O': // create a new orange fish
        this.objects.add(new Fish());
        break;
      case 'Y': // create a new yellow fish
        this.objects.add(new Fish(2, "images/yellow.png"));
        break;
      case 'B': // create a new blue fish
        this.objects.add(new BlueFish());
      case 'R': // traverse objects and remove a fish if the mouse is hovering over it
        for (TankListener i : this.objects) {
          if (i instanceof Fish && i.isMouseOver()) {
            this.objects.remove(i);
            break;
          }
        }
        break;
      case 'S': // call startSwimming on every Fish in objects
        for (TankListener i : this.objects) {
          if (i instanceof Fish) {
            ((Fish) i).startSwimming();
          }
        }
        break;
      case 'X': // call stopSwimming on every Fish in objects
        for (TankListener i : this.objects) {
          if (i instanceof Fish) {
            ((Fish) i).stopSwimming();
          }
        }
        break;
      case 'C': // remove all instances of Fish from the tank
        this.clear();
        break;
    }
  }

  /**
   * Removes all instances of class Fish from objects
   */
  public void clear() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        this.objects.remove(i);
        i--;
      }
    }
  }

  /**
   * This method is our main entry point to our application
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    // Initialize our window and set its name to FishTank
    PApplet.main("FishTank");
  }

}
