//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P11, an Assignment Planner Using Min-Heaps
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {
    // make a new AssignmentQueue with an invalid capacity
    try {
      new AssignmentQueue(-1);

      // if we get here without erroring, our implementation does not throw errors when it should
      return false;
    } catch (IllegalArgumentException e) {
      // do nothing, we expect this kind of error to be thrown
    } catch (Exception e) {
      // we should only encounter IllegalArgumentExceptions
      return false;
    }

    // make a new AssignmentQueue with a valid capacity, should not throw errors and should be empty
    // and have size of 0
    try {
      AssignmentQueue a = new AssignmentQueue(5);
      if (!a.isEmpty())
        return false;
      if (a.size() != 0)
        return false;
    } catch (Exception e) {
      // we expect no errors
      return false;
    }

    // if we've reached here then all our tests have passed
    return true;
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException - Calling enqueue on a
   * queue which is empty should add the Assignment, and increment the size of the queue - Calling
   * enqueue on a non-empty queue should add the Assignment at the proper position, and increment
   * the size of the queue. Try add at least 5 assignments - Calling peek on a non-empty queue
   * should always return the Assignment with the earliest due date - Calling enqueue on a full
   * queue should throw an IllegalStateException - Calling enqueue with a null Assignment should
   * throw a NullPointerException
   *
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {
    // calling peek on an empty queue should throw NoSuchElementException
    try {
      AssignmentQueue a = new AssignmentQueue(5);
      a.peek();

      // if we get here without erroring, our implementation is wrongly implemented
      return false;
    } catch (NoSuchElementException e) {
      // do nothing, this is expected
    } catch (Exception e) {
      // we expect no other errors
      return false;
    }


    try {
      // calling enqueue on an empty queue should add the Assignment and increment the size of the
      // queue
      Assignment a1 = new Assignment("Work on P11", 12, 9, 23);
      AssignmentQueue a = new AssignmentQueue(5);
      a.enqueue(a1);

      if (!a.peek().equals(a1) || a.size() != 1 || a.isEmpty())
        return false;


      // calling enqueue on a non-empty queue should add the Assignment at the proper position and
      // increment the size of the queue (try 5), calling peek on a non-empty queue should always
      // return the Assignment with the earliest due date
      Assignment a2 = new Assignment("Read for homework", 12, 9, 21);
      Assignment a3 = new Assignment("Watch new Money Heist episodes", 12, 3, 14);
      Assignment a4 = new Assignment("Call parents", 12, 5, 9);
      Assignment a5 = new Assignment("Water fake plants", 10, 23, 11);

      a.enqueue(a2);
      if (!a.peek().equals(a2) || a.size() != 2 || a.isEmpty())
        return false;

      a.enqueue(a3);
      if (!a.peek().equals(a3) || a.size() != 3 || a.isEmpty())
        return false;

      a.enqueue(a4);
      if (!a.peek().equals(a3) || a.size() != 4 || a.isEmpty())
        return false;

      a.enqueue(a5);
      if (!a.peek().equals(a5) || a.size() != 5 || a.isEmpty())
        return false;
    } catch (Exception e) {
      // we expect no errors for these cases
      return false;
    }

    // calling enqueue on a full queue should throw an IllegalStateException
    try {
      AssignmentQueue a = new AssignmentQueue(2);
      Assignment a1 = new Assignment("Work on P11", 12, 9, 23);
      Assignment a2 = new Assignment("Read for homework", 12, 9, 21);
      Assignment a3 = new Assignment("Watch new Money Heist episodes", 12, 3, 14);
      a.enqueue(a1);
      a.enqueue(a2);
      a.enqueue(a3);

      // if we get here without erroring, our implementation is incorrect
      return false;
    } catch (IllegalStateException e) {
      // do nothing, this is expected
    } catch (Exception e) {
      // we expect no other error types
      return false;
    }

    // calling enqueue with a null Assignment should throw a NullPointerException
    try {
      AssignmentQueue a = new AssignmentQueue(20);
      a.enqueue(null);

      // if we get here without erroring, our implementation is incorrect
      return false;
    } catch (NullPointerException e) {
      // do nothing, this is expected
    } catch (Exception e) {
      // we expect no other error types
      return false;
    }

    // if we've reached here then all our tests have passed
    return true;
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {
    try {
      // make a sample queue
      AssignmentQueue a = new AssignmentQueue(8);

      Assignment a1 = new Assignment("Work on P11", 12, 9, 23);
      Assignment a2 = new Assignment("Read for homework", 12, 9, 21);
      Assignment a3 = new Assignment("Watch new Money Heist episodes", 12, 3, 14);
      Assignment a4 = new Assignment("Call parents", 12, 5, 9);
      Assignment a5 = new Assignment("Water fake plants", 10, 23, 11);
      Assignment a6 = new Assignment("Study for CS final", 12, 19, 23);

      a.enqueue(a1);
      a.enqueue(a2);
      a.enqueue(a3);
      a.enqueue(a4);
      a.enqueue(a5);
      a.enqueue(a6);

      // peek must return without removing the assignment with the highest priority in the queue
      if (!a.peek().equals(a5) || a.size() != 6 || a.isEmpty())
        return false;

      // dequeue must remove, and return the assignment with the highest priority in the queue, the
      // size must be decremented by one each time the dequeue() method is successfully called
      Assignment dq1 = a.dequeue();
      if (!dq1.equals(a5) || a.size() != 5 || a.isEmpty())
        return false;
      Assignment dq2 = a.dequeue();
      if (!dq2.equals(a3) || a.size() != 4 || a.isEmpty())
        return false;
      Assignment dq3 = a.dequeue();
      if (!dq3.equals(a4) || a.size() != 3 || a.isEmpty())
        return false;
      Assignment dq4 = a.dequeue();
      if (!dq4.equals(a2) || a.size() != 2 || a.isEmpty())
        return false;
      Assignment dq5 = a.dequeue();
      if (!dq5.equals(a1) || a.size() != 1 || a.isEmpty())
        return false;

      // calling dequeue on a size of one should leave only an empty queue
      Assignment dq6 = a.dequeue();
      if (!dq6.equals(a6) || a.size() != 0 || !a.isEmpty())
        return false;
    } catch (Exception e) {
      // we expect no errors from these tests
      return false;
    }

    // calling peek on an empty queue should throw NoSuchElementException
    try {
      AssignmentQueue a = new AssignmentQueue(5);
      a.peek();

      // if we get here without erroring, our implementation is incorrect
      return false;
    } catch (NoSuchElementException e) {
      // do nothing, this is expected
    } catch (Exception e) {
      // we expect no other types of errors
      return false;
    }

    // calling dequeue on an empty queue should throw NoSuchElementException
    try {
      AssignmentQueue a = new AssignmentQueue(5);
      a.dequeue();

      // if we get here without erroring, our implementation is incorrect
      return false;
    } catch (NoSuchElementException e) {
      // do nothing, this is expected
    } catch (Exception e) {
      // we expect no other types of errors
      return false;
    }

    // if we've reached here then all our tests have passed
    return true;
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following
   * scenarios: - clear can be called on an empty queue with no errors - clear can be called on a
   * non-empty queue with no errors - After calling clear, the queue should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {
    try {
      AssignmentQueue a = new AssignmentQueue(5);
      Assignment a1 = new Assignment("Work on P11", 12, 9, 23);
      Assignment a2 = new Assignment("Read for homework", 12, 9, 21);
      Assignment a3 = new Assignment("Watch new Money Heist episodes", 12, 3, 14);
      a.enqueue(a1);
      a.enqueue(a2);
      a.enqueue(a3);
      a.clear();
      
      try {
        a.dequeue();
        
        // if we reach here without erroring, our implementation is false
        return false;
      } catch (NoSuchElementException e) {
        // do nothing, this is expected
      } catch (Exception e) {
        // we only expect NoSuchElementException
        return false;
      }

      AssignmentQueue b = new AssignmentQueue(2);
      b.clear();
      
      try {
        b.dequeue();
        
        // if we reach here without erroring, our implementation is false
        return false;
      } catch (NoSuchElementException e) {
        // do nothing, this is expected
      } catch (Exception e) {
        // we only expect NoSuchElementException
        return false;
      }
    } catch (Exception e) {
      // we expect no errors from these implementations
      return false;
    }

    // if we've reached here then all our tests have passed
    return true;
  }

  /**
   * Tests all the methods of the AssignmentQueue class
   * 
   */
  public static boolean runAllTests() {
    return (testConstructor() && testEnqueue() && testDequeuePeek() && testClear());
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("constructor: " + testConstructor());
    System.out.println("enqueue: " + testEnqueue());
    System.out.println("dequeue + peek: " + testDequeuePeek());
    System.out.println("clear: " + testClear());
    System.out.println("");
    System.out.println("all: " + runAllTests());
  }
}
