//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Linked Sorting, an Implementation of Linked Lists
// Course: CS 300 Fall 2021
//
// Author: Max Rountree
// Email: mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: n/a
// Partner Email: n/a
// Partner Lecturer's Name: n/a
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a node to be used in the LinkedBookshelf class, using an abstract data type
 */
public class LinkedNode<T> {
  private T data; // the data this node encapsulates
  private LinkedNode<T> next; // the next node in the list, after this node

  /**
   * Constructor with only the data, leaves the next node null
   * 
   * @param data the data this node should encapsulate
   */
  public LinkedNode(T data) {
    this.data = data;
    this.next = null;
  }

  /**
   * Constructor where both the data to encapsulate and the next node are given
   * 
   * @param data the data this node should encapsulate
   * @param next the next node in the LinkedList
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }

  /**
   * Getter for the data this node encapsulates
   * 
   * @return the data this node encapsulates
   */
  public T getData() {
    return data;
  }

  /**
   * Getter for the node after this node in the LinkedList
   * 
   * @return the next node in the LinkedList
   */
  public LinkedNode<T> getNext() {
    return next;
  }

  /**
   * Setter for the node after this node in the LinkedList
   * 
   * @param next the node to set this node's next value to
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

  /**
   * This class' toString implementation
   * 
   * @return the string representation of the data this node encapsulates
   */
  public String toString() {
    return this.data.toString();
  }
}
