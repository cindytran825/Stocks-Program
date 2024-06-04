import java.io.IOException;
import java.util.Scanner;

public class StocksController {
  private String date;
  private Appendable appendable;
  private Readable readable;


  StocksController(String date, Appendable appendable, Readable readable) {
    //null exception?
    this.date = date;
    this.appendable = appendable;
    this.readable = readable;
  }

  //ask to put the two dates
  //another screen with what they want to do with those dates
  //call on the methods that implement
  //portfolio, buying stocks

  //
  public void goControl() throws IllegalStateException {
    Scanner scan = new Scanner(readable);
    boolean quit = false;
    String date = this.date;

    this.welcomeMessage();

    while(!quit) {

    }


  }

  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  //this should print the menu
  private void printMenu() throws IllegalStateException {
    writeMessage("__ " + System.lineSeparator());
    writeMessage("__"
            +
            "___" + System.lineSeparator());
    writeMessage("__"
            +
            "__" + System.lineSeparator());
    writeMessage("__" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  //not that necessary
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }
}
