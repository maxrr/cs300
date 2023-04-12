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

/**
 * This class implements a text-based driver for a tile matching game
 */
public class TileMatchingGame {
  private TileStack[] columns; // the stacks of tiles involved in this matching game stored as
                               // columns

  /**
   * Creates a new matching tile game with a given number of columns and initializes its contents. A
   * new matching tile game stores an empty TileStack at each of its columns.
   * 
   * @param columnCount the amount of columns this TileMatchingGame should have
   */
  public TileMatchingGame(int columnCount) {
    // ensure our parameter is valid
    if (columnCount <= 0)
      throw new IllegalArgumentException("columnCount must be >0");

    // initialize our columns field
    columns = new TileStack[columnCount];

    // restart our game
    this.restartGame();
  }

  /**
   * Restarts the game
   */
  public void restartGame() {
    // iterate through our columns field and make it a new, blank TileStack
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new TileStack();
    }
  }

  /**
   * Gets the number of columns in this tile matching game
   * 
   * @return the number of columns in this tile matching game
   */
  public int getColumnsNumber() {
    return this.columns.length;
  }

  /**
   * Drops a tile at the top of the stack located at the given column index. If tile will be dropped
   * at the top of an equal tile (of same color), both tiles will be removed from the stack of tiles
   * at column index.
   * 
   * @param tile  the tile to be dropped
   * @param index the index of the TileStack that this Tile should be dropped into
   */
  public void dropTile(Tile tile, int index) {
    // ensure our index is valid
    if (index < 0 || index > this.columns.length - 1)
      throw new IndexOutOfBoundsException();

    // if we have two matching tiles, then don't add and pop it (removing them both)
    if (!this.columns[index].isEmpty() && this.columns[index].peek().equals(tile))
      this.columns[index].pop();
    // otherwise, push our tile to the top of the stack since they shouldn't be removed
    else
      this.columns[index].push(tile);
  }

  /**
   * Removes all the tiles from a column with a given index
   * 
   * @param index the index of the column to clear
   */
  public void clearColumn(int index) {
    // ensure our index is valid
    if (index < 0 || index > this.columns.length - 1)
      throw new IndexOutOfBoundsException();

    // make the column a new, blank TileStack
    this.columns[index] = new TileStack();
  }

  /**
   * Returns a string representation of the stack of tiles at a given column index, and an empty
   * string "" if the stack is empty.
   * 
   * @param index the index that the stack of tiles being retrieved is located at
   * @return a string representation of the stack of tiles at the given column index
   */
  public String column(int index) {
    // ensure our index is valid
    if (index < 0 || index > this.columns.length - 1)
      throw new IndexOutOfBoundsException();

    // make a new string that we'll concatenate to
    String assemble = "";

    Iterator<Tile> iterator = this.columns[index].iterator();

    // iterate through the TileStack at the given index and concatenate it to the string
    while (iterator.hasNext()) {
      assemble += " " + iterator.next().toString();
    }

    // return the string we've assembled
    return (assemble.length() > 0 ? assemble.substring(1) : assemble);
  }

  /**
   * Returns a string representation of this tile matching game
   */
  @Override
  public String toString() {
    // make a new string that we'll use to assemble our printout
    String assemble = "GAME COLUMNS:\n";

    // iterate through this game's stacks and get its string representation, then add it to our
    // string
    for (int i = 0; i < this.columns.length; i++) {
      assemble += i + ": " + this.column(i) + "\n";
    }

    // return the printout we've constructed
    return assemble;
  }
}
