package view;

import java.io.IOException;

/**
 * View of the program, it should connect to the controller.
 * which calls the methods in the model.
 */
public class StockProgramView {
  private Appendable appendable;

  public StockProgramView() {
    this.appendable = System.out;
  }

  public StockProgramView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * this writes the message.
   *
   * @param message a string.
   * @throws IllegalStateException when the input isn't a string.
   */
  public void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * this prints the menu, first thing user sees.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  public void printMenu() throws IllegalStateException {
    // need to be able to upload their own stock data (csv file) or look up (this is sorta in between)
    // create portfolio
    // read portfolio
    // check stock gain or loss over a specified time period
    // x-day moving average for a specified date and specified value of x
    // determine which days are x-day crossovers for a specified date and specified value of x

    writeMessage("1. Create new portfolio" + System.lineSeparator());
    writeMessage("2. Add to existing portfolio" + System.lineSeparator());
    writeMessage("3. View existing portfolios" + System.lineSeparator());
    writeMessage("4. Examine gain/loss" + System.lineSeparator());
    writeMessage("5. Examine x-day move average" + System.lineSeparator());
    writeMessage("6. Determine which days are x-day crossover" + System.lineSeparator());
    writeMessage("'quit' to quit" + System.lineSeparator());
    writeMessage("Enter in a number corresponding to the action you'd like to take!"
            + System.lineSeparator());

  }

  public void getTickerDate() throws IllegalStateException {
    writeMessage("Ticker: ");
  }

  public void getDateUser() throws IllegalStateException {
    writeMessage("Type the first date (YYYY-MM-DD): ");
  }

  public void inputNumber() throws IllegalStateException {
    writeMessage("Input number: ");
  }

  public void nameNew() throws IllegalStateException {
    writeMessage("Name of new portfolio: ");
  }

  public void tickerType() throws IllegalStateException {
    writeMessage("Enter Ticker (type 'f' to complete portfolio): ");
  }

  public void stockAdd() throws IllegalStateException {
    writeMessage("How many shares do you want to add for this stock? ");
  }

  public void namePort() throws IllegalStateException {
    writeMessage("Type just the NAME of the portfolio: ");
  }


  /**
   * this prints the goodbye message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  public void goodbye() throws IllegalStateException {
    writeMessage("Thank you for using our program!" + System.lineSeparator());

  }
  /**
   * this prints the welcome message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  public void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }




}
