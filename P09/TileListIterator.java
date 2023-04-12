//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Tile Matching Game, a Simple Game using Stacks and LinkedNodes
// Course:   CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for linked Node<Tile>s, containing basic iterative methods like hasNext() and next()
 */
public class TileListIterator implements Iterator<Tile> {

  private Node next; // a private instance field to store the next node in the list

  /**
   * Creates a new TileListIterator that
   * 
   * @param head
   */
  public TileListIterator(Node head) {
    // initialize our next field as the first Node in the List
    next = head;
  }

  /**
   * Getter for whether this TileListIterator has another Tile after the current one
   * 
   * @return true if this TileListIterator has another Tile, false otherwise
   */
  @Override
  public boolean hasNext() {
    // if our next node isn't null, then 
    return (this.next != null);
  }

  /**
   * Getter for this TileListIterator's next Tile in the List
   * 
   * @return the next Tile in this List
   */
  @Override
  public Tile next() {
    // if we don't have any more tiles, then throw an exception
    if (this.next == null)
      throw new NoSuchElementException("There are no more tiles in the iteration");
    
    // get the value of the next tile
    Tile t = this.next.getTile(); 
    
    // iterate to the following tile
    this.next = this.next.getNext();
    
    // return the value of the tile we retrieved earlier
    return t;
  }

}
