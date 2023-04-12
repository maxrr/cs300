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
import java.util.NoSuchElementException;

public class FolderExplorer {
  /**
   * Provides a list of all files and directories in the folder currentDirectory
   * 
   * @param currentDirectory the directory to look for files and directories in
   * @return a list of the names of all files and directories in the given folder currentDirectory
   * @throws NotDirectoryException if the provided currentDirectory does not exist or is not a
   *                               directory
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {
    // if currentDirectory is not a directory, error with a message
    if (currentDirectory == null || !currentDirectory.isDirectory())
      throw new NotDirectoryException("The supplied currentDirectory is not a directory!");

    // make a new ArrayList to return
    ArrayList<String> ret = new ArrayList<String>();

    // the files in the supplied directory
    String[] files = currentDirectory.list();

    // loop through our files and add it to the ArrayList
    for (String f : files) {
      ret.add(f);
    }

    // return our constructed ArrayList
    return ret;
  }

  /**
   * Recursive method, lists the names of all the files (but not directories) in the given directory
   * and its sub-directories
   * 
   * @param currentDirectory the directory to recurse on
   * @return a list of all files (not directories) in the given folder and its subfolders
   * @throws NotDirectoryException if the provided directory does not exist or is not a directory
   */
  public static ArrayList<String> getDeepContents(File currentDirectory)
      throws NotDirectoryException {
    // if currentDirectory is not a directory, error with a message
    if (currentDirectory == null || !currentDirectory.isDirectory())
      throw new NotDirectoryException("The supplied currentDirectory is not a directory!");

    // make a new ArrayList to return
    ArrayList<String> ret = new ArrayList<String>();

    // the files in the supplied directory
    File[] files = currentDirectory.listFiles();

    // loop through our file and add them all to the ArrayList
    for (File f : files) {

      // if we're dealing with a single file, just add it
      if (f.isFile())
        ret.add(f.getName());

      // if we're dealing with a directory, recurse on it and add all of it
      if (f.isDirectory())
        ret.addAll(getDeepContents(f));

    }

    // return our constructed ArrayList
    return ret;
  }

  /**
   * Recursively searches the given directory and its sub-directories for an exact match to the
   * provided fileName
   * 
   * @param currentDirectory the directory to search (as well as its sub-directories)
   * @param fileName         the name of the file to search for
   * @return a path to the file if it exists
   * @throws NoSuchElementException if the search returns with no results found (if fileName is
   *                                null, if currentDirectory doesn't exist, or if currentDirectory
   *                                was not a directory)
   */
  public static String lookupByName(File currentDirectory, String fileName)
      throws NoSuchElementException {
    // if currentDirectory is not a directory, error with a message
    if (currentDirectory == null || !currentDirectory.isDirectory())
      throw new NoSuchElementException("The supplied currentDirectory is not a directory!");

    if (fileName == null)
      throw new NoSuchElementException("Must supply a file name to search for!");

    // the files in the supplied directory
    File[] files = currentDirectory.listFiles();

    // loop through our file and add them all to the ArrayList
    for (File f : files) {

      // if we're dealing with a single file, just add it
      if (f.isFile())
        if (f.getName().equals(fileName))
          return f.getPath();


      // if we're dealing with a directory, recurse on it and add all of it
      if (f.isDirectory()) {
        try {
          return lookupByName(f, fileName);
        } catch (NoSuchElementException e) {
          // do nothing, we want to suppress this error from happening on recursion
        }
      }
    }

    // ...otherwise throw an error and scream that we didn't find it!
    throw new NoSuchElementException(
        "Search operation did not find a file with an exact file name match.");
  }

  /**
   * Recursively searches the given folder and its sub-directories for all files that contain
   * fileName in part of their name
   * 
   * @param currentDirectory the directory to search plus its sub-directories
   * @param key              the search term to filter files by
   * @return an ArrayList with all the names of files that matched (if currentDirectory is not a
   *         directory, will return an empty ArrayList)
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {
    // make a new ArrayList to return
    ArrayList<String> ret = new ArrayList<String>();

    // if currentDirectory is not a directory, error with a message
    if (currentDirectory == null || !currentDirectory.isDirectory() || key == null)
      return ret;

    // the files in the supplied directory
    File[] files = currentDirectory.listFiles();

    // loop through our file and add them all to the ArrayList
    for (File f : files) {

      // if we're dealing with a single file, just add it
      if (f.isFile())
        if (f.getName().indexOf(key) != -1)
          ret.add(f.getName());

      // if we're dealing with a directory, recurse on it and add all of it
      if (f.isDirectory())
        ret.addAll(lookupByKey(f, key));

    }

    // return our constructed ArrayList
    return ret;
  }

  /**
   * Recursively searches the given folder and its sub-directories for all files whose size is
   * within the given max and min values, inclusively
   * 
   * @param currentDirectory
   * @param sizeMin
   * @param sizeMax
   * @return an ArrayList of all files whose sizes are within the boundaries, returns an empty
   *         ArrayList if the search returns with no results found (or if currentDirectory is not a
   *         directory)
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {
    // make a new ArrayList to return
    ArrayList<String> ret = new ArrayList<String>();

    // if currentDirectory is not a directory, error with a message
    if (currentDirectory == null || !currentDirectory.isDirectory())
      return ret;

    // the files in the supplied directory
    File[] files = currentDirectory.listFiles();

    // loop through our file and add them all to the ArrayList
    for (File f : files) {

      // if we're dealing with a single file, just add it
      if (f.isFile())
        if (f.length() >= sizeMin && f.length() <= sizeMax)
          ret.add(f.getName());

      // if we're dealing with a directory, recurse on it and add all of it
      if (f.isDirectory())
        ret.addAll(lookupBySize(f, sizeMin, sizeMax));

    }

    // return our constructed ArrayList
    return ret;
  }
}
