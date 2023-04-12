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
 * This class contains multiple methods to test our implementation of LinkedBookshelf and LinkedNode
 */
public class LinkedBookshelfTester {
  /**
   * This method test our implementation of LinkedNode, using at least one constructor, one
   * accessor, and one mutator
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean testLinkedNode() {
    // make a test node
    LinkedNode<String> testStringNode = new LinkedNode<String>("bread loaf");

    // verify the setting of data in the initializer and the getter work
    if (!testStringNode.getData().equals("bread loaf")) {
      return false;
    }

    // make a new test node to link after our first test node
    LinkedNode<String> testNextNode = new LinkedNode<String>("twenty-three");

    // verify the setting of data in the initializer and the getter work
    if (!testNextNode.getData().equals("twenty-three")) {
      return false;
    }

    // make them go in the right order
    testStringNode.setNext(testNextNode);

    // make sure they actually went in the right order
    if (testStringNode.getNext() != testNextNode) {
      return false;
    }

    // all tests passed, so return true
    return true;
  }

  /**
   * Tests the correctness of LinkedBookshelf's clear() method
   * 
   * @return true if all tests passed, false otherwise
   */
  public static boolean testClear() {
    // reset our book IDs
    Book.resetGenerator();

    // make a new LinkedBookshelf
    LinkedBookshelf shelf = new LinkedBookshelf();
    shelf.appendBook(new Book("The Hobbit", 600));
    shelf.appendBook(new Book("Harry Potter", 1400));
    shelf.appendBook(new Book("The Hungry Caterpillar", 16));
    shelf.appendBook(new Book("The Hardy Boys", 180));

    // we should have 4 items
    if (shelf.size() != 4)
      return false;

    // do the magic
    shelf.clear();

    // we should definitely have an empty shelf at this point, so check if size = 0
    if (shelf.size() != 0)
      return false;

    try {
      // try to get the item at index 0
      shelf.get(0);
      return false;
    } catch (IndexOutOfBoundsException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't get any other errors either
      return false;
    }

    // test to make sure the 'front' field of LinkedBookshelf is cleared
    try {
      shelf.getFirst();
      // we shouldn't get here, so return false if we do
      return false;
    } catch (NullPointerException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't get any other errors
      return false;
    }

    // test to make sure the 'back' field of LinkedBookshelf is cleared
    try {
      shelf.getLast();
      // we shouldn't get here, so return false if we do
      return false;
    } catch (NullPointerException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't get any other errors
      return false;
    }

    // all tests passed, so return true
    return true;
  }

  /**
   * Tests the correctness of LinkedBookshelf's appendBook() method
   * 
   * @return true if all tests passed, false otherwise
   */
  public static boolean testAddBooks() {
    // reset our book IDs
    Book.resetGenerator();

    // make a new LinkedBookshelf
    LinkedBookshelf shelf = new LinkedBookshelf();

    // add a sample book to it
    shelf.appendBook(new Book("The Hobbit", 600));

    // ensure we incremented the shelf's size attribute
    if (shelf.size() != 1)
      return false;

    // ensure that our .get() method doesn't give us an IndexOutOfBoundsException
    try {

      // ensure the correct item was appended
      if (!shelf.get(0).getTitle().equals("The Hobbit") || shelf.get(0).getPageCount() != 600)
        return false;

      // add another sample book
      shelf.appendBook(new Book("Harry Potter", 1400));

      // ensure we incremented the shelf's size attribute
      if (shelf.size() != 2)
        return false;

      // ensure the correct item was appended
      if (!shelf.get(1).getTitle().equals("Harry Potter") || shelf.get(1).getPageCount() != 1400)
        return false;

    } catch (IndexOutOfBoundsException e) {
      // do nothing, this is expected behavior for an incorrect implementation of get()
    } catch (Exception e) {
      // however, we shouldn't get any other errors...
      return false;
    }



    // all tests passed, so return true
    return true;
  }

  /**
   * Tests the correctness of LinkedBookshelf's sort() method
   * 
   * @return true if all tests passed, false otherwise
   */
  public static boolean testSortBooks() {
    // reset our book IDs
    Book.resetGenerator();

    // make a new LinkedBookshelf
    LinkedBookshelf shelf = new LinkedBookshelf();

    // create some sample books (from the doc)
    Book b1 = new Book("Good Omens", 288, "Gaiman", "Niel");
    Book b2 = new Book("FEED", 608, "Grant", "Mira");
    Book b3 = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book b4 = new Book("2001", 296, "Clarke", "Arthur C");

    // add all the books to the shelf
    shelf.appendBook(b1);
    shelf.appendBook(b2);
    shelf.appendBook(b3);
    shelf.appendBook(b4);

    // sort by title
    LinkedBookshelf.sort(shelf, Attribute.TITLE);

    // ensure that our .get() doesn't throw any IndexOutOfBoundsExceptions

    try {

      // check correct order of books
      if (shelf.get(0) != b4 || shelf.get(1) != b2 || shelf.get(2) != b1 || shelf.get(3) != b3)
        return false;

      // sort by author
      LinkedBookshelf.sort(shelf, Attribute.AUTHOR);

      // check correct order of books
      if (shelf.get(0) != b4 || shelf.get(1) != b1 || shelf.get(2) != b2 || shelf.get(3) != b3)
        return false;

      // sort by pagecount
      LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);

      // check correct order of books
      if (shelf.get(0) != b1 || shelf.get(1) != b4 || shelf.get(2) != b3 || shelf.get(3) != b2)
        return false;

      // sort by id
      LinkedBookshelf.sort(shelf, Attribute.ID);

      // check correct order of books
      if (shelf.get(0) != b1 || shelf.get(1) != b2 || shelf.get(2) != b3 || shelf.get(3) != b4)
        return false;

    } catch (IndexOutOfBoundsException e) {
      // do nothing, this is expected behavior for an incorrect implementation
    } catch (Exception e) {
      // however, we shouldn't get any other errors...
      return false;
    }

    // all tests passed, so return true
    return true;
  }

  /**
   * Runs all testmethods and returns true if and only if all test methods return true
   * 
   * @return true if all test methods return true, false otherwise
   */
  public static boolean runAllTests() {
    // get our test results
    boolean linked = testLinkedNode();
    boolean clear = testClear();
    boolean add = testAddBooks();
    boolean sort = testSortBooks();

    // if we passed all of our tests then return true
    if (linked && clear && add && sort) {
      return true;
    }

    // if we didn't pass our tests, print which one we failed...
    System.out.println("linked: " + linked);
    System.out.println("clear: " + clear);
    System.out.println("add: " + add);
    System.out.println("sort: " + sort);

    // ...and return false
    return false;
  }

  /**
   * Main entry point for application
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    // tell us if we passed all of our tests!
    if (runAllTests()) {
      System.out.println("All tests passed!");
    } else {
      System.out.println("One or more tests failed.");
    }
  }
}
