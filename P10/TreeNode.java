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

/**
 * A generic binary tree node
 *
 * @param <T> the type of data stored in this TreeNode
 */
public class TreeNode<T> {
  private T data; // the data contained in this node
  private TreeNode<T> left; // the left child of this node
  private TreeNode<T> right; // the right child of this node

  /**
   * Constructs a node with the given data and no children
   * 
   * @param data the data to be assigned to this node
   */
  public TreeNode(T data) {
    this.data = data;
  }

  /**
   * Constructs a node with the given data and children
   * 
   * @param data  the data to be assigned to this node
   * @param left  the left child of this node
   * @param right the right child of this node
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Accessor for the data contained in the node
   * 
   * @return the data contained in the node
   */
  public T getData() {
    return this.data;
  }

  /**
   * Accessor for the left child of the node
   * 
   * @return the left child of this node
   */
  public TreeNode<T> getLeft() {
    return this.left;
  }

  /**
   * Accessor for the right child of the node
   * 
   * @return the right child of this node
   */
  public TreeNode<T> getRight() {
    return this.right;
  }

  /**
   * Change the left child reference of this node; may be null
   * 
   * @param left the new left child of this node
   */
  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  /**
   * Change the right child reference of this node; may be null
   * 
   * @param right the new right child of this node
   */
  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

  /**
   * Returns a string representation of this node's data
   * 
   * @return the string representation of this node's data
   */
  public String toString() {
    return "";
  }
}
