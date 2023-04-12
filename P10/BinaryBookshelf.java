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
 * A binary search tree version of our sorted Bookshelf, where all Books are sorted based on (in
 * decreasing order of importance) the Attributes contained in the sortList. Books must be compared
 * first based on their authors, and then on the other Attributes in the order they appear in the
 * sortList.
 */
public class BinaryBookshelf {
  private TreeNode<Book> root; // the root node of the BST
  private int size; // the number of nodes currently contained in the BST
  private Attribute[] sortList; // the ordered array of attributes by which the BST nodes are sorted

  /**
   * One-argument constructor, initializes an empty BinaryBookshelf
   * 
   * @param sortList an ordered array of Attributes, must begin with AUTHOR and contain exactly one
   *                 copy of each Attribute in the enum
   * 
   * @throws IllegalArgumentException if the sortList argument is not a four-element array of unique
   *                                  attributes, beginning with Attribute.AUTHOR
   */
  public BinaryBookshelf(Attribute[] sortList) throws IllegalArgumentException {
    if (sortList.length != 4 || sortList[0] != Attribute.AUTHOR)
      throw new IllegalArgumentException(
          "Array must have four elements and must begin with AUTHOR");

    // ensure unique input
    boolean[] chk = new boolean[] {false, true, false, false};
    for (int i = 1; i < sortList.length; i++) {
      switch (sortList[i]) {
        case TITLE:
          chk[0] = true;
          break;
        case PAGECOUNT:
          chk[2] = true;
          break;
        case ID:
          chk[3] = true;
          break;
        default:
          throw new IllegalArgumentException(
              "Array must contain only unique, valid Attribute ENUMS");
      }
    }
    for (boolean b : chk) {
      if (!b)
        throw new IllegalArgumentException("Array must contain all Attribute ENUMS at least once");
    }

    // initialize fields
    this.size = 0;
    this.sortList = sortList;
  }

  /**
   * Resets the BST to be empty
   */
  public void clear() {
    this.size = 0;
    this.root = null;
  }

  /**
   * OPTIONAL: helper method to compare two Book objects according to the sortList of attributes.
   * 
   * @param one the first book to compare
   * @param two the second book to compare
   * @return a negative value if one < two, a positive value if one > two, and 0 if they are equal
   */
  protected int compareToHelper(Book one, Book two) {
    for (int i = 0; i < sortList.length; i++) {
      Attribute a = sortList[i];

      int c = one.compareTo(two, a);
      if (c != 0)
        return c;
    }
    return 0;
  }

  /**
   * Searches for the input book in the bookshelf. This must be implemented recursively; do not call
   * toString() in this method.
   * 
   * complexity: O(log n)
   * 
   * @param book the book to search for
   * @return true if the book is present in the shelf, false otherwise
   */
  public boolean contains(Book book) {
    return containsHelper(book, this.root);
  }

  /**
   * Recursive helper method; searches for the input book in the subtree rooted at current
   * 
   * @param book    the query book to search for
   * @param current the root of the current subtree
   * @return true if the book is contained in this subtree, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    // if we've iterated to a null node, return false
    if (current == null)
      return false;

    // if we've found the node we want, return true
    if (current.getData().equals(book))
      return true;

    // check to see if we have at least one child
    if (current.getLeft() != null || current.getRight() != null) {
      // recurse through left side if book < current
      if (compareToHelper(book, current.getData()) < 0) {
        return containsHelper(book, current.getLeft());
      }
      // recurse through right side if book > current
      else if (compareToHelper(book, current.getData()) > 0) {
        return containsHelper(book, current.getRight());
      }
    }

    // if we don't have a left or right child then return false
    return false;
  }

  /**
   * Provides a String-formatted list of the attributes in the order in which they are used, for
   * example: "1:AUTHOR 2:PAGECOUNT 3:TITLE 4:ID"
   * 
   * @return a String-formatted list of the sorting attributes
   */
  public String getAttributeOrder() {
    String assemble = "";
    for (int i = 0; i < sortList.length; i++) {
      Attribute a = sortList[i];
      assemble += (i + 1) + ":" + a + " ";
    }

    // cut off the last space
    return assemble.substring(0, assemble.length() - 1);
  }

  /**
   * Returns a list of books in the bookshelf written by the given author
   * 
   * @param authorName the author name to filter on
   * @return a list of books by the author
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    // call the recursive method with an initial node of root
    return getBooksByAuthorHelper(authorName, this.root);
  }

  /**
   * Recursive helper method; returns a list of books in the subtree rooted at current which were
   * written by the given author
   * 
   * @param authorName the author name to filter on
   * @param current    the root of the current subtree
   * @return a list of books by the author in the current subtree
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    ArrayList<Book> list = new ArrayList<Book>();

    // if our start node is null, then return an empty list
    if (current == null)
      return list;

    // if our current node is by the author, then add it to our list to return
    if (current.getData().getAuthor().equals(authorName))
      list.add(current.getData());

    // if exists and authorName <= current, recurse through left tree
    if (current.getLeft() != null && current.getData().getAuthor().compareTo(authorName) >= 0)
      list.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));

    // if exists and authorName >= current, recurse through right tree
    if (current.getRight() != null && current.getData().getAuthor().compareTo(authorName) <= 0)
      list.addAll(getBooksByAuthorHelper(authorName, current.getRight()));

    return list;
  }

  /**
   * Returns a shallow copy of the root node so that test tree structures may be constructed
   * manually rather than by using the insertBook() method
   * 
   * @return a reference to the current root node
   */
  protected TreeNode<Book> getRoot() {
    return this.root;
  }

  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes Note:
   * you may wish to write helper methods for comparing Books according to the sortList, as well as
   * for inserting Books in a recursive manner.
   * 
   * @param book the Book object to be added to the BST
   * @throws IllegalArgumentException if this Book is already in the BST
   */
  public void insertBook(Book book) throws IllegalArgumentException {
    // increment our size
    this.size++;

    // if our root is undefined, then define it here
    if (this.root == null) {
      TreeNode<Book> t = new TreeNode<Book>(book);
      this.root = t;
      return;
    }

    // call recursive helper with the start being this tree's root
    insertBookHelper(book, this.root);
  }

  /**
   * Recursive helper method; adds the given Book to the subtree rooted at current.
   * 
   * @param book    the Book object to be added to the BST
   * @param current the root of the current subtree
   * @throws IllegalArgumentException if this Book is already in the BST
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current)
      throws IllegalArgumentException {
    // make a new node to insert
    TreeNode<Book> t = new TreeNode<Book>(book);

    // book < current -> use left subtree
    if (compareToHelper(book, current.getData()) < 0) {
      // if left is defined...
      if (current.getLeft() != null) {
        insertBookHelper(book, current.getLeft());
      }
      // if not, then define it
      else {
        current.setLeft(t);
      }
    }
    // book > current -> use right subtree
    else if (compareToHelper(book, current.getData()) > 0) {
      // if left is defined...
      if (current.getRight() != null) {
        insertBookHelper(book, current.getRight());
      }
      // if not, then define it
      else {
        current.setRight(t);
      }
    }
    // book = current -> book already exists
    else {
      throw new IllegalArgumentException("Book already exists in this tree");
    }
  }

  /**
   * Determine whether the BST is empty
   * 
   * Complexity: O(1)
   * 
   * @return true if the BST is empty, false otherwise
   */
  public boolean isEmpty() {
    return (this.size == 0);
  }

  /**
   * Get the number of nodes currently in the BST
   * 
   * Complexity: O(1)
   * 
   * @return the number of nodes currently in the BST
   */
  public int size() {
    return this.size;
  }

  /**
   * Creates and returns an in-order traversal of the BST, with each Book on a separate line
   * 
   * Complexity: O(n)
   * 
   * @return an in-order traversal of the BST, with each Book on a separate line
   */
  public String toString() {
    // call the recursive helper, starting at this tree's root
    return toStringHelper(this.root);
  }

  /**
   * Recursive helper method; creates and returns the String representation of the subtree rooted at
   * the current node
   * 
   * @param current the root of the current subtree
   * @return the string representation of this subtree
   */
  protected String toStringHelper(TreeNode<Book> current) {
    String assemble = "";

    if (current == null)
      return "";

    // if exists, recurse through left tree
    if (current.getLeft() != null)
      assemble += toStringHelper(current.getLeft());

    assemble += "\n" + current.getData().toString();

    // if exists, recurse through right tree
    if (current.getRight() != null)
      assemble += toStringHelper(current.getRight());

    return assemble.substring(2);
  }
}
