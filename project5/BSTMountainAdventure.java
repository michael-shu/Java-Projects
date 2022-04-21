package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main method of BSTMountain. It uses a
 * scanner to take in a file, and then parses each line of
 * the file using a single space as the delimiter.
 * 
 * @throws Exception             if anything other than
 *                               the file name is passed
 *                               through
 * 
 * @throws FileNotFoundException if the program can't find
 *                               the file
 * 
 * @author Michael
 *
 */
public class BSTMountainAdventure {

  public static void main(String[] args)
      throws Exception {
    if (args.length > 1)
      throw new Exception(
          "Only the file name is allowed as input");

    if (args.length == 0)
      throw new Exception("Nothing passed as input");

    try {
      File file = new File(args[0]);
      Scanner sc = new Scanner(file);
      sc.useDelimiter(" ");

      BSTMountain mountain = new BSTMountain();

      while (sc.hasNext()) {
        String[] temp = sc.nextLine().split(" ");
        mountain.createMountain(temp);
      }
      Hiker hiker = new Hiker();
      hiker.Travel(mountain.getRoot(),
          mountain.getHeight() - 1);

    }

    catch (FileNotFoundException e) {
      throw new FileNotFoundException(
          "The system was unable to find your file");
    }

  }

}
