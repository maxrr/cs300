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

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * This class represents a linked stack of tiles, implementing both java.lang.Iterable and StackADT
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile> {

  private Node top; // reference to the top Node of this stack
  private int size; // the number of Nodes in this TileStack

  /**
   * Creates an empty stack of tiles
   */
  public TileStack() {
    this.top = null;
    this.size = 0;
  }

  /**
   * Pushes the provided tile to the top of this stack
   */
  @Override
  public void push(Tile element) {
    // make a new node encapsulating our Tile
    Node n = new Node(element, this.top);

    // set the top of this stack to be our new Node
    this.top = n;

    // increment our size
    this.size++;
  }

  /**
   * Removes and returns the Tile at the top of this stack
   * 
   * @return the Tile at the top of this stack
   */
  @Override
  public Tile pop() {
    // if this stack is empty, throw an EmptyStackException
    if (this.isEmpty())
      throw new EmptyStackException();

    // store our top Node temporarily
    Node t = this.top;

    // advance our top Node down the heirarchy
    this.top = this.top.getNext();

    // decrement our size
    this.size--;

    // return the Tile associated with the Node we cached earlier
    return t.getTile();
  }

  /**
   * Returns the Tile at the top of this stack
   * 
   * @return the Tile at the top of this stack
   */
  @Override
  public Tile peek() {
    // if this stack is empty, throw an EmptyStackException
    if (this.isEmpty())
      throw new EmptyStackException();

    // otherwise, return the Tile associated with the Node on the top of this stack
    return this.top.getTile();
  }

  /**
   * Checks whether this stack is empty
   * 
   * @return true if empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return (this.size == 0);
  }

  /**
   * Returns the size of this stack
   * 
   * @return the size of this stack
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns an iterator to iterate through this stack starting from its top
   */
  @Override
  public Iterator<Tile> iterator() {
    // make a new TileListIterator using this stack's top Node
    return new TileListIterator(this.top);
  }

}
