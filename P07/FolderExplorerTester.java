//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07, a Recursive Folder Content Explorer
// Course:   CS 300 Fall 2021
//
// Author:   Max Rountree
// Email:    mrrountree@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:            n/a
// Partner Email:           n/a
// Partner Lecturer's Name: n/a
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         n/a
// Online Sources:  n/a
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is a tester for our FolderExplorer class (but you could probably tell from the name)
 */
public class FolderExplorerTester {
  /**
   * Tests FolderExplorer.getContents (on the 'cs300' directory)
   * 
   * @param folder the folder to verify the integrity of (this method will only work properly with
   *               the 'cs300' directory)
   * @return true if all tests passed, false if any failed
   */
  public static boolean testGetContents(File folder) {
    try {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades",
      // "lecture notes", "programs", "reading notes", "syllabus.txt",
      // and "todo.txt" only.
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }
      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests FolderExplorer.getDeepContents' base case only
   * 
   * @param f the directory to test the base case of
   * @return true if all tests passed, false if any failed
   */
  public static boolean testDeepGetContentsBaseCase(File f) {
    // for this test, we need a folder that has no subfolders, so we use the 'reading notes' folder
    try {
      // get the files in the folder
      ArrayList<String> files =
          FolderExplorer.getDeepContents(new File(f.getPath() + File.separator + "reading notes"));
      // we should have exactly four files
      if (files.size() != 4) {
        // our test fails if we don't have exactly four files
        System.out.println("Problem detected: reading notes folder must contain four elements");
        return false;
      }
    } catch (NotDirectoryException e) {
      // we shouldn't get any errors from this, if we do then our method and test fail
      System.out.println(
          "Problem detected: Your FolderExplorer.getDeepContents() has thrown an unexpected "
              + "exception.");
      return false;
    }

    // if we haven't returned false by now, then all our tests passed
    return true;
  }

  /**
   * Tests FolderExplorer.getDeepContents' recursive cases
   * 
   * @param f the directory to test the recursive cases on
   * @return true if all tests passed, false if any failed
   */
  public static boolean testDeepListRecursiveCase(File f) {
    // for this test, we need a folder that has subfolders, so we use the 'programs' folder
    try {
      // get the files in the folder
      ArrayList<String> files =
          FolderExplorer.getDeepContents(new File(f.getPath() + File.separator + "programs"));
      // we should have exactly eight files
      if (files.size() != 8) {
        // our test fails if we don't have exactly eight files
        System.out.println("Problem detected: programs folder must contain eight elements");
        return false;
      }
    } catch (NotDirectoryException e) {
      // we shouldn't get any errors from this, if we do then our method and test fail
      System.out.println(
          "Problem detected: Your FolderExplorer.getDeepContents() has thrown an unexpected "
              + "exception.");
      return false;
    }

    // if we haven't returned false by now, then all our tests passed
    return true;
  }

  /**
   * Tests FolderExplorer.lookupByName's return values
   * 
   * @param f the directory to test lookupByName on (will be cs300)
   * @return true if all tests passed, false if any failed
   */
  public static boolean testLookupByFileName(File f) {
    // for this test, we're going to look for programs/p01/ClimbingTracker.java
    try {
      String path = FolderExplorer.lookupByName(f, "ClimbingTracker.java");
      if (!path.equals("cs300" + File.separator + "programs" + File.separator + "p01"
          + File.separator + "ClimbingTracker.java")) {
        System.out
            .println("Problem detected: ClimbingTracker.java's path did not match expected value");
        return false;
      }
    } catch (NoSuchElementException e) {
      System.out
          .println("Problem detected: Your FolderExplorer.lookupByName() has thrown an unexpected "
              + "exception.");
      return false;
    }

    // for this second test, we're going to look for programs/writeUps/Program02.pdf
    try {
      String path = FolderExplorer.lookupByName(f, "Program02.pdf");
      if (!path.equals("cs300" + File.separator + "programs" + File.separator + "writeUps"
          + File.separator + "Program02.pdf")) {
        System.out.println("Problem detected: Program02.pdf's path did not match expected value");
        return false;
      }
    } catch (NoSuchElementException e) {
      System.out
          .println("Problem detected: Your FolderExplorer.lookupByName() has thrown an unexpected "
              + "exception.");
      return false;
    }

    // if we haven't returned false by now, then all our tests passed
    return true;
  }

  /**
   * Tests FolderExplorer.lookupByKey's return values
   * 
   * @param f the directory to test lookupBykey on (will be cs300)
   * @return true if all tests passed, false if any failed
   */
  public static boolean testLookupByKeyBaseCase(File f) {
    // for this test, we're going to look for the syllabus.txt file
    ArrayList<String> results = FolderExplorer.lookupByKey(f, "syllabus");

    // list our expected results
    ArrayList<String> expectedResults = new ArrayList<String>();
    expectedResults.add("syllabus.txt");

    // our entries from results should be in expectedResults! (if not, our test failed)
    for (String s : results) {
      if (!expectedResults.contains(s)) {
        System.out.println(
            "Problem detected: Your FolderExplorer.lookupByKey() does not match the expected "
                + "results");
        return false;
      }
    }

    // our actual results should be the same size as our expected results (if not, our test failed)
    if (results.size() != expectedResults.size()) {
      System.out
          .println("Problem detected: Your FolderExplorer.lookupByKey() has returned incorrect "
              + "results");
      return false;
    }

    // if we haven't returned false by now, then all our tests passed
    return true;
  }

  /**
   * Tests FolderExplorer.lookupBySize's return values
   * 
   * @param f the directory to test lookupBySize on (will be cs300)
   * @return true if all tests passed, false if any failed
   */
  public static boolean testLookupBySize(File f) {
    // for this test, we're going to try and find files that have contents
    ArrayList<String> results = FolderExplorer.lookupBySize(f, 10, 99999999999999L);

    // list our expected results
    ArrayList<String> expectedResults = new ArrayList<String>();
    expectedResults.add("Inheritance.txt");
    expectedResults.add("Recursion.txt");
    expectedResults.add("Program01.pdf");
    expectedResults.add("Program02.pdf");
    expectedResults.add("Program03.pdf");
    expectedResults.add("syllabus.txt");
    expectedResults.add("todo.txt");

    // our entries from results should be in expectedResults! (if not, our test failed)
    for (String s : results) {
      if (!expectedResults.contains(s)) {
        System.out.println(
            "Problem detected: Your FolderExplorer.lookupBySize() does not match the expected "
                + "results");
        return false;
      }
    }

    // our actual results should be the same size as our expected results (if not, our test failed)
    if (results.size() != expectedResults.size()) {
      System.out
          .println("Problem detected: Your FolderExplorer.lookupBySize() has returned incorrect "
              + "results");
      return false;
    }

    // if we haven't returned false by now, then all our tests passed
    return true;
  }

  /**
   * Main entry point
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    File testDir = new File("cs300");
    System.out.println("testGetContents: " + testGetContents(testDir));
    System.out.println("testDeepGetContentsBaseCase: " + testDeepGetContentsBaseCase(testDir));
    System.out.println("testDeepListRecursiveCase: " + testDeepListRecursiveCase(testDir));
    System.out.println("testLookupByFileName: " + testLookupByFileName(testDir));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(testDir));
    System.out.println("testLookupBySize: " + testLookupBySize(testDir));
  }
}
