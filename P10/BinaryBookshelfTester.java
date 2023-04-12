//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf, an Implementation of Binary Search Trees with Books
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
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A suite of testers for the BinaryBookshelf class, to make sure all of its implemented methods
 * function as they are supposed to
 */
public class BinaryBookshelfTester {

  /**
   * Method to ensure that the generic class TreeNode is valid
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean testTreeNode() {
    try {
      // Scenario 1: a single TreeNode with no children
      {
        // make a node
        TreeNode<String> one = new TreeNode<String>("One");

        // ensure that all accessors return correct values
        if (!one.getData().equals("One"))
          return false;
        if (one.getLeft() != null)
          return false;
        if (one.getRight() != null)
          return false;
      }

      // Scenario 2: a simple collection of TreeNodes
      {
        // make some nodes
        TreeNode<String> one = new TreeNode<String>("One");
        TreeNode<String> two = new TreeNode<String>("Two");
        TreeNode<String> three = new TreeNode<String>("Three");

        // set (and verify) Two's left to One
        two.setLeft(one);
        if (!two.getLeft().equals(one))
          return false;
        if (two.getRight() != null)
          return false;

        // set (and verify) Two's right to Three
        two.setRight(three);
        if (!two.getLeft().equals(one))
          return false;
        if (!two.getRight().equals(three))
          return false;

        // set (and verify) Two's left back to null
        two.setLeft(null);
        if (two.getLeft() != null)
          return false;
        if (!two.getRight().equals(three))
          return false;

        // set (and verify) Two's right back to null
        two.setRight(null);
        if (two.getLeft() != null)
          return false;
        if (two.getRight() != null)
          return false;
      }

      // Scenario 3: multiple-arg constructor
      {
        // make some nodes
        TreeNode<String> one = new TreeNode<String>("One");
        TreeNode<String> three = new TreeNode<String>("Three");
        TreeNode<String> two = new TreeNode<String>("Two", one, three);

        // ensure left and right was set correctly
        if (!two.getLeft().equals(one))
          return false;
        if (!two.getRight().equals(three))
          return false;
      }
    } catch (Exception e) {
      // we expect no errors from this implementation
      return false;
    }
    // if we've gotten this far, then all tests have passed
    return true;
  }

  /**
   * Method to ensure that the basic methods of a BinaryBookshelf are valid, before any Books have
   * been added to the shelf
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean testEmptyTree() {
    // Scenario 1: invalid constructor inputs
    {
      try {
        // create an empty BinaryBookshelf with an empty Attribute array
        Attribute[] a = new Attribute[0];
        new BinaryBookshelf(a);
      } catch (IllegalArgumentException e) {
        // do nothing, this is expected behavior
      } catch (Exception e) {
        // no other errors should occur
        return false;
      }

      try {
        // create an empty BinaryBookshelf with an Attribute array of length != 4
        Attribute[] a = new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.AUTHOR,
            Attribute.PAGECOUNT, Attribute.ID, Attribute.AUTHOR};
        new BinaryBookshelf(a);
      } catch (IllegalArgumentException e) {
        // do nothing, this is expected behavior
      } catch (Exception e) {
        // no other errors should occur
        return false;
      }

      try {
        // create an empty BinaryBookshelf with an Attribute array of length = 4, with two of the
        // same attribute
        Attribute[] a = new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.AUTHOR,
            Attribute.PAGECOUNT};
        new BinaryBookshelf(a);
      } catch (IllegalArgumentException e) {
        // do nothing, this is expected behavior
      } catch (Exception e) {
        // no other errors should occur
        return false;
      }

      try {
        // create an empty BinaryBookshelf with an Attribute array of length = 4, with all unique
        // values, but a value other than AUTHOR at index = 0
        Attribute[] a =
            new Attribute[] {Attribute.ID, Attribute.TITLE, Attribute.AUTHOR, Attribute.PAGECOUNT};
        new BinaryBookshelf(a);
      } catch (IllegalArgumentException e) {
        // do nothing, this is expected behavior
      } catch (Exception e) {
        // no other errors should occur
        return false;
      }
    }

    // Scenario 2: valid input
    {
      try {
        // Creating empty BinaryBookshelves with array length = 4, containing unique values, trying
        // all combinations of values following
        Attribute[] a =
            new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
        BinaryBookshelf b = new BinaryBookshelf(a);

        a = new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT, Attribute.ID};
        b = new BinaryBookshelf(a);

        a = new Attribute[] {Attribute.AUTHOR, Attribute.ID, Attribute.TITLE, Attribute.PAGECOUNT};
        b = new BinaryBookshelf(a);

        a = new Attribute[] {Attribute.AUTHOR, Attribute.ID, Attribute.PAGECOUNT, Attribute.TITLE};
        b = new BinaryBookshelf(a);

        a = new Attribute[] {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
        b = new BinaryBookshelf(a);

        a = new Attribute[] {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.TITLE, Attribute.ID};
        b = new BinaryBookshelf(a);


        // Verify that size() returns 0, that it isEmpty(), that its toString() returns an empty
        // String, and that its getRoot() is null
        if (b.size() != 0)
          return false;
        if (!b.isEmpty())
          return false;
        if (!b.toString().equals(""))
          return false;
        if (b.getRoot() != null)
          return false;

        // Verify that getAttributeOrder() works correctly
        String attr = b.getAttributeOrder();
        if (!attr.equals("1:AUTHOR 2:PAGECOUNT 3:TITLE 4:ID"))
          return false;

        // Verify that contains() returns false for any input
        if (b.contains(null))
          return false;
        if (b.contains(new Book("Sample Book 1", 2)))
          return false;
        if (b.contains(new Book("Sample Book 2", 200, "Last", "First")))
          return false;

        // Verify that getBooksByAuthor() returns an empty ArrayList
        ArrayList<Book> gbba = b.getBooksByAuthor("");
        if (gbba.size() != 0)
          return false;
      } catch (Exception e) {
        // no errors are expected in this implementation
        System.out.println(e);
        return false;
      }
    }

    // if we've gotten this far, then all tests have passed
    return true;
  }

  /**
   * Method to ensure that the BinaryBookshelf insertBook() method works as expected
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean testInsertBook() {
    try {
      // Scenario 1: inserting into an empty tree
      // make a valid BinaryBookshelf and ensure it's empty
      Attribute[] a =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf b = new BinaryBookshelf(a);
      if (!b.isEmpty())
        return false;

      // insert a single book into the BinaryBookshelf and ensure the BB is now not empty
      Book lotr = new Book("Lord of the Rings", 9025, "Tolkien", "J.R.R.");
      b.insertBook(lotr);
      if (b.isEmpty())
        return false;

      // verify that getRoot() returns a node that contains the Book we just added
      if (!b.getRoot().getData().equals(lotr))
        return false;

      // Scenario 2: inserting a unique, smaller value into a non-empty tree
      Book nl = new Book("Nine Lives", 20, "LeGuin", "Ursula");
      b.insertBook(nl);

      // ensure the methods worked correctly
      if (b.size() != 2)
        return false;
      if (!b.getRoot().getLeft().getData().equals(nl))
        return false;

      // Scenario 3: insert a value with a shared author attribute
      Book hobb = new Book("The Hobbit", 200, "Tolkien", "J.R.R.");
      b.insertBook(hobb);

      // ensure the methods worked correctly
      if (b.size() != 3)
        return false;
      if (!b.getRoot().getRight().getData().equals(hobb))
        return false;
    } catch (Exception e) {
      // no errors should occur in scenarios 1, 2, or 3
      return false;
    }

    // Scenario 4: inserting a duplicate value
    try {
      Attribute[] a =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf b = new BinaryBookshelf(a);
      Book lotr = new Book("Lord of the Rings", 9025, "Tolkien", "J.R.R.");
      b.insertBook(lotr);
      b.insertBook(lotr);

      // if we reach here without erroring, our errors are not being thrown correctly
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // the only type of error we should have in this scenario is an IllegalArgumentException
      return false;
    }

    // if we've gotten this far, then all tests have passed
    return true;
  }

  /**
   * Method to ensure that the BinaryBookshelf contains() method works as expected
   * 
   * @return true if all tests pass, false if any fail
   */
  public static boolean testContains() {
    try {
      // Scenario 1: non-recursive case
      // create a BB with a single node
      Attribute[] a =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf b = new BinaryBookshelf(a);
      Book lotr = new Book("Lord of the Rings", 9025, "Tolkien", "J.R.R.");
      b.insertBook(lotr);

      // ensure our BinaryBookshelf.contains() returns true and false for correct and false inputs,
      // respectively
      if (!b.contains(lotr))
        return false;
      Book hobb = new Book("The Hobbit", 200, "Tolkien", "J.R.R.");
      if (b.contains(hobb))
        return false;

      // Scenario 2: recursive case
      Book nl = new Book("Nine Lives", 20, "LeGuin", "Ursula");
      Book tran = new Book("Transcendent Kingdom", 250, "Gyasi", "Yaa");
      Book phys = new Book("Die Physiker", 95, "Duerrenmatt", "Friedrich");
      Book hehb = new Book("Haltet Euer Herz Bereit", 280, "Leo", "Maxim");
      TreeNode<Book> root = b.getRoot();
      root.setRight(new TreeNode<Book>(hobb));
      root.getRight().setLeft(new TreeNode<Book>(nl));
      root.getRight().setRight(new TreeNode<Book>(tran));
      root.setLeft(new TreeNode<Book>(phys));

      // check root
      if (!b.contains(lotr))
        return false;

      // check leaf
      if (!b.contains(phys))
        return false;

      // check internal
      if (!b.contains(hobb))
        return false;

      // check nonentry
      if (b.contains(hehb))
        return false;
    } catch (Exception e) {
      // no errors are expected for either scenario of this method
      return false;
    }

    // if we've gotten this far, then all tests have passed
    return true;
  }

  /**
   * Method to ensure that the BinaryBookshelf getBooksByAuthor() method works as expected
   * 
   * @return true if all tests pass, false if any fail
   */
  public static boolean testGetBooksByAuthor() {
    try {
      // Scenario 1:
      Attribute[] a =
          new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      BinaryBookshelf b = new BinaryBookshelf(a);
      Book lotr = new Book("Lord of the Rings", 9025, "Tolkien", "J.R.R.");
      b.insertBook(lotr);

      // get the list of books filtered by author
      ArrayList<Book> found = b.getBooksByAuthor("Tolkien, J.R.R.");
      if (found.size() != 1)
        return false;

      ArrayList<Book> notFound = b.getBooksByAuthor("One, Another");
      if (notFound.size() != 0)
        return false;

      // Scenario 2: recursive case
      Book hobb = new Book("The Hobbit", 200, "Tolkien", "J.R.R.");
      Book hehb = new Book("Haltet Euer Herz Bereit", 220, "Leo", "Maxim");
      Book tran = new Book("Transcendent Kingdom", 250, "Gyasi", "Yaa");
      Book phys = new Book("Die Physiker", 95, "Duerrenmatt", "Friedrich");
      TreeNode<Book> root = b.getRoot();
      root.setRight(new TreeNode<Book>(hobb));
      root.setLeft(new TreeNode<Book>(phys));
      root.getRight().setRight(new TreeNode<Book>(tran));
      root.getLeft().setRight(new TreeNode<Book>(hehb));

      // look for books with these authors:
      found = b.getBooksByAuthor("Tolkien, J.R.R.");
      if (found.size() != 2)
        return false;

      found = b.getBooksByAuthor("Leo, Maxim");
      if (found.size() != 1)
        return false;

      found = b.getBooksByAuthor("LeGuin, Ursula");
      if (found.size() != 0)
        return false;
    } catch (Exception e) {
      // no errors are expected for either scenario
      return false;
    }

    // if we've gotten this far, then all tests have passed
    return true;
  }

  /**
   * Main entry point 
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    System.out.println(testTreeNode());
    System.out.println(testEmptyTree());
    System.out.println(testInsertBook());
    System.out.println(testContains());
    System.out.println(testGetBooksByAuthor());
  }

}
