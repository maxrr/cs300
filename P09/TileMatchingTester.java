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
import java.util.NoSuchElementException;

/**
 * Tests our implementations of our methods that we've constructed for P09
 */
public class TileMatchingTester {

  /**
   * Tests our Tile.equals() method
   * 
   * @return true if all tests pass, false if any failed
   */
  @SuppressWarnings("unlikely-arg-type")
  public static boolean tileEqualsTester() {
    try {
      // make some nodes to experiment with
      Tile black = new Tile(Color.BLACK);
      Tile blue = new Tile(Color.BLUE);
      Tile orange = new Tile(Color.ORANGE);
      Tile yellow = new Tile(Color.YELLOW);

      // make our tiles into an array
      Tile[] tiles = new Tile[] {black, blue, orange, yellow};

      // iterate through our tiles array and make sure that none of them are equal to ones that they
      // shouldn't be
      for (Tile t1 : tiles) {
        for (Tile t2 : tiles) {
          if ((t1 == t2 && !t1.equals(t2)) || (t1 != t2 && t1.equals(t2)))
            return false;
        }
      }

      // test against string
      if (black.equals("sample string"))
        return false;

      // test against int
      if (black.equals(35))
        return false;

      // test against null
      if (black.equals(null))
        return false;
    } catch (Exception e) {
      // our Tile.equals() should never throw any exceptions
      return false;
    }

    // if we've reached here, nothing has gone wrong, so our tests have all succeeded
    return true;
  }

  /**
   * Tests our TileListIterator's implementation
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean tileListIteratorTester() {
    // case 1: all valid inputs, no errors
    try {
      // make a list that looks like: BLACK -> BLUE -> YELLOW -> BLACK -> ORANGE
      Node head = new Node(new Tile(Color.BLACK),
          new Node(new Tile(Color.BLUE), new Node(new Tile(Color.YELLOW),
              new Node(new Tile(Color.BLACK), new Node(new Tile(Color.ORANGE))))));

      // instantiate a new TileListIterator
      TileListIterator l = new TileListIterator(head);

      // ensure the order of our list is valid
      if (!l.hasNext())
        return false;
      if (!l.next().equals(new Tile(Color.BLACK)))
        return false;

      if (!l.hasNext())
        return false;
      if (!l.next().equals(new Tile(Color.BLUE)))
        return false;

      if (!l.hasNext())
        return false;
      if (!l.next().equals(new Tile(Color.YELLOW)))
        return false;

      if (!l.hasNext())
        return false;
      if (!l.next().equals(new Tile(Color.BLACK)))
        return false;

      if (!l.hasNext())
        return false;
      if (!l.next().equals(new Tile(Color.ORANGE)))
        return false;

      // we shouldn't have any more elements...
      if (l.hasNext())
        return false;
    } catch (Exception e) {
      // we shouldn't encounter any errors
      return false;
    }

    // case 2: hasNext() and next() on an empty list
    try {
      // make an empty list
      TileListIterator l = new TileListIterator(null);

      // we shouldn't have any elements
      if (l.hasNext())
        return false;

      // this line should error
      l.next();

      // if we reach here without an error, our method has not been properly written
      return false;
    } catch (NoSuchElementException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't get any other exceptions though
      return false;
    }

    // if we've reached here, nothing has gone wrong, so our tests have all succeeded
    return true;
  }

  /**
   * Tests our TileStack's implementation
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean tileStackTester() {
    // case 1: checking isEmpty (and push())
    try {
      // make a new stack and populate it
      TileStack t1 = new TileStack();
      t1.push(new Tile(Color.BLACK));

      // the stack should not be empty at this time
      if (t1.isEmpty())
        return false;

      // make another new stack and leave it empty
      TileStack t2 = new TileStack();

      // the stack should be empty at this time
      if (!t2.isEmpty())
        return false;
    } catch (Exception e) {
      // we shouldn't encounter any errors in this case
      return false;
    }

    // case 2: check size() (and push())
    try {
      // make a new stack and populate it
      TileStack t1 = new TileStack();
      t1.push(new Tile(Color.BLACK));

      // the stack should have a size of one at this time
      if (t1.size() != 1)
        return false;

      // make another new stack and leave it empty
      TileStack t2 = new TileStack();

      // the stack should be empty and have a size of zero
      if (t2.size() != 0)
        return false;
    } catch (Exception e) {
      // we shouldn't encounter any errors in this case
      return false;
    }

    // case 3: check peek() (no error)
    try {
      // make a new stack and populate it
      TileStack t1 = new TileStack();
      t1.push(new Tile(Color.BLACK));

      // the tile on the top of the stack should be black
      if (!t1.peek().equals(new Tile(Color.BLACK)))
        return false;
    } catch (Exception e) {
      // we shouldn't encounter any errors in this case
      return false;
    }

    // case 4: check peek() (error expected)
    try {
      // make a new stack and keep it empty
      TileStack t1 = new TileStack();

      // this line should error...
      t1.peek();

      // ...so if we get here, our implementation is false
      return false;
    } catch (EmptyStackException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't encounter any errors other than EmptyStackException in this case
      return false;
    }

    // case 5: check pop() (no error)
    try {
      // make a new stack and populate it
      TileStack t1 = new TileStack();
      t1.push(new Tile(Color.BLACK));
      t1.push(new Tile(Color.ORANGE));

      // pop the top element from t1
      Tile t = t1.pop();

      // the tile we just popped should be orange
      if (!t.equals(new Tile(Color.ORANGE)))
        return false;

      // pop again
      t = t1.pop();

      // this one should be black
      if (!t.equals(new Tile(Color.BLACK)))
        return false;
    } catch (Exception e) {
      // we shouldn't encounter any errors in this case
      return false;
    }

    // case 6: check pop() (error expected)
    try {
      // make a new stack and keep it empty
      TileStack t1 = new TileStack();

      // this line should error...
      t1.pop();

      // ...so if we get here, our implementation is false
      return false;
    } catch (EmptyStackException e) {
      // do nothing, this is expected behavior
    } catch (Exception e) {
      // we shouldn't encounter any errors other than EmptyStackException in this case
      return false;
    }

    // case 7: check iterator()
    try {
      // make a new stack and populate it
      TileStack t1 = new TileStack();
      t1.push(new Tile(Color.BLACK));
      t1.push(new Tile(Color.BLUE));
      t1.push(new Tile(Color.BLACK));
      t1.push(new Tile(Color.ORANGE));
      t1.push(new Tile(Color.YELLOW));
      t1.push(new Tile(Color.YELLOW));

      // make a new iterator from our TileStack
      Iterator<Tile> iterator = t1.iterator();

      // first element should be a yellow Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.YELLOW)))
        return false;

      // second element should be a yellow Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.YELLOW)))
        return false;

      // third element should be a orange Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.ORANGE)))
        return false;

      // fourth element should be a black Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.BLACK)))
        return false;

      // fifth element should be a blue Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.BLUE)))
        return false;

      // sixth element should be a black Tile
      if (!iterator.hasNext())
        return false;
      if (!iterator.next().equals(new Tile(Color.BLACK)))
        return false;

    } catch (Exception e) {
      // we shouldn't encounter any errors in this case
      return false;
    }

    // if we've reached here, nothing has gone wrong, so our tests have all succeeded
    return true;
  }

  /**
   * Tests our TileMatchingGame's implementation
   * 
   * @return true if all tests passed, false if any failed
   */
  public static boolean tileMatchingGameTester() {
    try {
      // make a new TileMatchingGame with a column length of 4
      TileMatchingGame g = new TileMatchingGame(4);
      
      // ensure our constructor set the field right, and that our getter works correctly
      if (g.getColumnsNumber() != 4)
        return false;
      
      // drop some tiles in our TileMatchingGame
      g.dropTile(new Tile(Color.BLACK), 0);
      g.dropTile(new Tile(Color.BLUE), 1);
      g.dropTile(new Tile(Color.ORANGE), 1);
      g.dropTile(new Tile(Color.YELLOW), 3);
      g.dropTile(new Tile(Color.BLUE), 2);
      
      // ensure that dropTile() worked correctly and that column() works correctly
      if (!g.column(0).equals("BLACK"))
        return false;
      if (!g.column(1).equals("ORANGE BLUE"))
        return false;
      if (!g.column(2).equals("BLUE"))
        return false;
      if (!g.column(3).equals("YELLOW"))
        return false;
      
      // ensure that toString() works correctly
      String ts = g.toString();
      if (!ts.equals("GAME COLUMNS:\n0: BLACK\n1: ORANGE BLUE\n2: BLUE\n3: YELLOW\n"))
        return false;

      // ensure that clearColumn() works correctly
      g.clearColumn(2);
      if (!g.column(2).equals(""))
        return false;

      // use dropTile() to delete some tiles
      g.dropTile(new Tile(Color.BLACK), 0);
      g.dropTile(new Tile(Color.ORANGE), 1);
      g.dropTile(new Tile(Color.YELLOW), 3);

      
      // ensure that dropTile() worked correctly and that column() still works correctly
      if (!g.column(0).equals(""))
        return false;
      if (!g.column(1).equals("BLUE"))
        return false;
      if (!g.column(2).equals(""))
        return false;
      if (!g.column(3).equals(""))
        return false;
      
      // ensure that toString() works correctly
      ts = g.toString();
      if (!ts.equals("GAME COLUMNS:\n0: \n1: BLUE\n2: \n3: \n"))
        return false;
      
    } catch (Exception e) {
      System.out.println("Errored");
      System.out.println(e);
      // we shouldn't encounter any errors in this case
      return false;
    }

    // if we've reached here, nothing has gone wrong, so our tests have all succeeded
    return true;
  }

  /**
   * Main entry point for our program
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    System.out.println(tileEqualsTester());
    System.out.println(tileListIteratorTester());
    System.out.println(tileStackTester());
    System.out.println(tileMatchingGameTester());
  }

}
