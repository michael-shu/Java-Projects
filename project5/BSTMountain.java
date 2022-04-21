package project5;

import java.util.ArrayList;

/**
 * This class entails all methods/variables needed for the
 * generation of the BST Mountain. This Mountain is made
 * up of RestStops and it is designed for the Hiker to
 * safely get down from
 * 
 * @author Michael Shu
 * @version 5/2/21
 */
public class BSTMountain {

  /**
   * The RestStop class is the node class for this
   * mountain. Each RestStop has a label that determines
   * its placement in the tree. They can have supplies for
   * the Hiker to pick up, or obstacles for the Hiker to
   * surpass.
   * 
   * @author Michael Shu
   * @version 5/2/21
   */
  public class RestStop implements Comparable {

    // left and right references for a binary tree
    private RestStop left;
    private RestStop right;

    // This is the "data" part of the node
    private ArrayList<String> obstacles;
    private ArrayList<String> supplies;
    private String label;

    /**
     * RestStop constructor
     * 
     * @param label     of the reststop
     * @param obstacles at the reststop
     * @param supplies  at the reststop
     */
    private RestStop(String label,
        ArrayList<String> obstacles,
        ArrayList<String> supplies) {
      this.label = label;
      this.obstacles = obstacles;
      this.supplies = supplies;
    }

    /**
     * This method will always be run as entry.compareTo,
     * since 'a'.compareTo('b) returns -1. Makes more
     * sense to get -1 and go left, instead of positive 1
     * and go left.
     * 
     * However, this is a preferential choice. Could
     * easily swap o and this and then code BSTMountain
     * accordingly.
     * 
     * Negative->left Positive->right
     * 
     * @param o The RestStop we want to compare
     * 
     * @return negative if o's label is alphabetically
     *         lower, positive if o's label is
     *         alphabetically higher, 0 if it's the same
     *         object, if it's not a reststop, or if it
     *         has the same label value
     */
    public int compareTo(Object o)
        throws NullPointerException {
      /*
       * Need to check if object is null, if its not this
       * object, and if its an actual instance of
       * RestStop.
       */
      if (o == null)
        throw new NullPointerException("Object is null");

      if (o == this)
        return 0;

      if (!(o instanceof RestStop))
        return 0;

      // Otherwise, convert to RestStop
      RestStop O = (RestStop) o;

      // Use Character.compareTo to compare labels
      return this.label.compareTo(O.label);
    }

    /**
     * Basic get methods to acess reference to each
     * private variable
     * 
     * @return reference to the variable
     */
    public String getLabel() {
      return label;
    }

    public ArrayList<String> getObs() {
      return obstacles;
    }

    public ArrayList<String> getSups() {
      return supplies;
    }

    public RestStop getRight() {
      return right;
    }

    public RestStop getLeft() {
      return left;
    }
  }

  private RestStop root;
  /*
   * This height is the maximum possible height of the
   * tree. It starts from index 1 so height of 5 means 5
   * nodes, not 6
   */
  private int height;

  /*
   * These are String arrays of the valid
   * obstacles/supplies that are allowed in the tree
   */
  private String[] validObs = { "fallen tree", "river" };
  private String[] validSup = { "food", "raft", "axe" };

  /**
   * Basic constructor setting root to null
   */
  public BSTMountain() {
    root = null;
  }

  /**
   * This is a wrapper function that calls mountainCreator
   * each time.
   * 
   * @param temp a single line of the file that you pass
   *             in, parsed into a string array
   */
  public void createMountain(String[] temp) {
    mountainCreator(temp);
    mountainHeight();
  }

  /**
   * This method is called once per main method run, to
   * generate the height of the mountain. Calls maxHeight
   * on root and gets the method started
   */
  private void mountainHeight() {
    if (root == null)
      return;

    height = maxHeight(root);
  }

  /**
   * This is where the mountain is created. It creates a
   * label and an arraylist for both obstacles/ supplies,
   * and iterates through temp, adding them to
   * obstacles/supplies. After, it creates a new node
   * using those 3 values, and then calls another method
   * to add it to the tree
   * 
   * @param temp each line of your input file
   */
  private void mountainCreator(String[] temp) {
    String label = temp[0];
    ArrayList<String> supplies = new ArrayList<String>();
    ArrayList<String> obstacles = new ArrayList<String>();

    /*
     * For loop that has two nested for loops to run
     * through both validObs, and validSups and check them
     * against each temp value. If valid, adds them and
     * removes the value. If not, it just skips over it
     */
    for (int i = 1; i < temp.length; i++) {
      for (int j = 0; j < validObs.length; j++) {
        if (temp[i].equals(validObs[j])) {
          obstacles.add(temp[i]);
          temp[i] = "";
        }

        // Need to code a special case for "fallen tree"
        // because parsing will make it [fallen,tree]
        if (temp[i].equals("fallen")
            && temp[i + 1].equals("tree")) {
          obstacles.add("fallen tree");
          temp[i] = "";
        }
      }

      for (int k = 0; k < validSup.length; k++) {
        if (temp[i].equals(validSup[k])) {
          supplies.add(temp[i]);
          temp[i] = "";
        }
      }
    }

    // Creates new RestStop
    RestStop x = new RestStop(label, obstacles, supplies);
    // If this is the 1st RestStop, set it to root.

    if (root == null)
      root = x;
    // Otherwise, insert it.
    else
      insertNode(root, x);
  }

  /**
   * This node uses compareto to recursively travel down
   * the tree. When it reaches a null point, then we know
   * that we have reached the end and can return entry,
   * thus connecting it to the left/right of head of
   * previous recursive calls
   * 
   * @param head  This is the node we are at in the tree
   * @param entry This is the node that we want to insert
   *              in the tree
   * 
   * @return head so the method can recursively travel
   *         down the tree
   */
  private RestStop insertNode(RestStop head,
      RestStop entry) {

    if (head == null) {
      return entry;
    }
    int x = entry.compareTo(head);

    if (x < 0) {
      head.left = insertNode(head.left, entry);
    } else if (x > 0) {
      head.right = insertNode(head.right, entry);
    }
    return head;
  }

  /**
   * Method recursively travels through the tree and
   * iterates until it gets to null
   * 
   * @param rs this will always be the root at first
   * 
   * @return the maximum height of the mountain that root
   *         belongs to
   */
  private int maxHeight(RestStop rs) {
    if (rs == null)
      return 0;
    else {

      /* compute the depth of each subtree */
      int lHeight = maxHeight(rs.left);
      int rHeight = maxHeight(rs.right);

      /* use the larger one */
      if (lHeight > rHeight)
        return (lHeight + 1);
      else
        return (rHeight + 1);
    }
  }

  /**
   * Get methods for height/root to be used by Hiker
   * 
   * @return int height or root reference
   */
  public int getHeight() {
    return height;
  }

  public RestStop getRoot() {
    return root;
  }
}
