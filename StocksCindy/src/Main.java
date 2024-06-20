/**
 * This is the Main class.
 */
public class Main {
  /**
   * This is the main method that read inputs from the terminal.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      GUI.execute();
    } else if (args.length == 1 && args[0].equals("-text")) {
      StockProgram.execute();
    } else {
      System.out.println("Error input.");
      System.exit(1);
    }
  }
}
