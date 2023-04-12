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
 * This class models a LinkedList of Book objects, with the ability to sort the collection of Books
 * by certain Attributes out lined in Attribute.java
 */
public class LinkedBookshelf {
  private LinkedNode<Book> front; // the node at the front of this LinkedList
  private LinkedNode<Book> back; // the node at the back of this LinkedList
  private int size; // the size of this LinkedList (# of nodes)
  private Attribute sortedBy; // how this LinkedList is sorted (using Attribute.java)

  /**
   * Constructor with no parameters, creates an empty bookshelf sorted by ID
   */
  public LinkedBookshelf() {
    this.front = null;
    this.back = null;
    this.size = 0;
    this.sortedBy = Attribute.ID;
  }

  /**
   * Getter for this LinkedList's size
   * 
   * @return the size of this LinkedList
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns whether or not this LinkedList is empty (size = 0)
   * 
   * @return whether or not this LinkedList is empty
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the String representation of this LinkedList (value of sortedBy and then each Book's
   * String representation on a new line
   * 
   * @return the String representation of this LinkedList
   */
  public String toString() {
    // make a string to start out with
    String assemble = "" + this.sortedBy;

    // traverse our LinkedList and print our stuff
    LinkedNode<Book> walker = front;
    while (walker != null) {
      assemble += "\n" + walker.toString();
      walker = walker.getNext();
    }

    // return the string we made
    return assemble;
  }

  /**
   * Getter for the node at the specified index of this LinkedList
   * 
   * @param index the index to lookup
   * @return the node at the specified index
   */
  public LinkedNode<Book> getNode(int index) {
    // if the index is invalid, throw an IndexOutOfBoundsException
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException();
    }

    // traverse the link (index) amount of times
    LinkedNode<Book> walker = front;
    for (int i = 0; i < index; i++) {
      walker = walker.getNext();
    }

    return walker;
  }

  /**
   * Getter for the data at the specified index of this LinkedList
   * 
   * @param index the index to lookup
   * @return the data of the node at the specified index
   */
  public Book get(int index) {
    // if the index is invalid, throw an IndexOutOfBoundsException
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException();
    }

    // traverse the link (index) amount of times
    LinkedNode<Book> walker = front;
    for (int i = 0; i < index; i++) {
      walker = walker.getNext();
    }

    // return the data of the node we land on
    return walker.getData();
  }

  /**
   * Getter for the data of the node at the head of this LinkedList
   * 
   * @return the data of the node at the head of this LinkedList
   */
  public Book getFirst() {
    return this.front.getData();
  }

  /**
   * Getter for the data of the node at the the back of this LinkedList
   * 
   * @return the data of the node at the back of this LinkedList
   */
  public Book getLast() {
    return this.back.getData();
  }

  /**
   * Restores this LinkedBookshelf to an empty state
   */
  public void clear() {
    // reset the front, back, and size
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  /**
   * Adds the provded Book object to the end of the LinkedList and increases this LinkedBookshelf's
   * size by one, does not consider the value of sortedBy
   * 
   * @param toAdd the Book to add to this LinkedBookshelf
   */
  public void appendBook(Book toAdd) {
    // make a new node with the supplied Book
    LinkedNode<Book> node = new LinkedNode<Book>(toAdd);

    // if we've got an empty LinkedList then set front, back, and size accordingly
    if (this.front == null) {
      this.front = node;
      this.back = node;
      this.size = 1;
    } else { // if we aren't working with an empty LinkedList, then only set the back node and
             // increment size
      this.back.setNext(node);
      this.back = node;
      this.size++;
    }
  }

  /**
   * Inserts the provided Book object at the correct location in the lit, which you may assume has
   * been sorted based on the value of sortedBy, and increases this LinkedBookshelf's size
   * accordingly
   * 
   * @param toAdd the book to insert in this LinkedBookshelf
   */
  public void insertBook(Book toAdd) {
    // make a new node with the supplied Book
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);

    // if we've got an empty LinkedList then just initialize it
    if (this.front == null) {
      this.front = newNode;
      this.back = newNode;
      this.size = 1;
    } else { // if we aren't working with an empty LinkedList, then insert at the right point

      // start at the front of the list
      LinkedNode<Book> node = this.front;

      // if we should be inserting at the front, then do so
      if (node.getData().compareTo(toAdd, this.sortedBy) > 0) {

        // set our new node's next parameter to the current front
        newNode.setNext(node);

        // set our new front
        this.front = newNode;

        // increment our size
        this.size++;

      } else {

        // iterate through the list until we find a value that is greater than the node
        while (node.getNext() != null
            && node.getNext().getData().compareTo(toAdd, this.sortedBy) < 0) {
          node = node.getNext();
        }

        // get the existing connection of the nodes we're gonna insert between
        LinkedNode<Book> next = node.getNext();

        // set the node we'll insert after's next parameter to our new node
        node.setNext(newNode);

        // set our new node's next parameter to next's next parameter
        newNode.setNext(next);

        // increment this LinkedBookshelf's size by 1
        this.size++;

      }
    }
  }

  /**
   * Runs an insertion sort on the provided LinkedBookshelf, using the given Attribute for comparing
   * Book objects
   * 
   * @param b        the LinkedBookshelf to run the insertion sort on
   * @param sortedBy the Attribute to sort the LinkedBookshelf by
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    // if our LinkedBookshelf is of size 0 or 1, then it's already sorted
    if (b.size() <= 1) {
      return;
    }

    // start sorting by wht we should be
    b.sortedBy = sortedBy;

    // sever our list and keep the remainder
    LinkedNode<Book> split = b.getNode(1);

    // clear our connections in the original list
    b.front.setNext(null);

    // initialize our walker to the beginning of the remainder of the list (index 1)
    LinkedNode<Book> walker = split;

    // iterate through the rest of our list and perform the insertion sort
    while (walker != null) {

      b.insertBook(walker.getData());
      walker = walker.getNext();
    }
  }
}
