package view;

import java.io.IOException;

/**
 * View of the program, it should connect to the controller.
 * which calls the methods in the model.
 */
public class StockProgramView implements View {
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

    writeMessage("Create new portfolio [port-create]" + System.lineSeparator());
    writeMessage("Manage portfolio [port-manage]" + System.lineSeparator());
    writeMessage("View existing portfolios [port-view]" + System.lineSeparator());
    writeMessage("Evaluate existing portfolios [port-eval]" + System.lineSeparator());
    writeMessage("Examine gain/loss [stock-eval]" + System.lineSeparator());
    writeMessage("Examine x-day move average [stock-avg]" + System.lineSeparator());
    writeMessage("Determine which days are x-day crossover [stock-cross]" + System.lineSeparator());
    writeMessage("Quit [quit]" + System.lineSeparator());
    writeMessage("Menu [menu]" + System.lineSeparator());
    writeMessage("Enter instruction to the action you'd like to take!"
            + System.lineSeparator());

  }


  public void getTicker() throws IllegalStateException {
    writeMessage("Enter the ticker: ");
  }

  public void getNameOfFile(String name) throws IllegalStateException {
    writeMessage("Existing files are: \n" + name);
  }

  public void getDateUser1() throws IllegalStateException {
    writeMessage("Type the date to use for evaluation (YYYY-MM-DD): ");
  }

  public void getDateUser2() throws IllegalStateException {
    writeMessage("Type the starting date (YYYY-MM-DD): ");
  }

  public void getDateUser3() throws IllegalStateException {
    writeMessage("Type the ending date (YYYY-MM-DD): ");
  }

  public void inputNumber() throws IllegalStateException {
    writeMessage("Enter Command: ");
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

  public void printPortfolio(String portfolio) throws IllegalStateException {
    writeMessage(portfolio);
  }

  public void success() throws IllegalStateException {
    writeMessage("Successfully added a new portfolio." + System.lineSeparator());
  }

  public void printPortValue(String value) throws IllegalStateException {
    writeMessage("The value of this portfolio is: $" + value + System.lineSeparator());
  }

  public void printNetGain(String value, String date1, String date2) throws IllegalStateException {
    writeMessage("Between " + date1 + " to " + date2 + " "
            + "the stock had a net gain of: $" + value + System.lineSeparator());
  }

  public void getXDays() throws IllegalStateException {
    writeMessage("Enter in the last x-days since the current date: ");
  }

  public void movingAvg(String value) throws IllegalStateException {
    writeMessage("The moving average is: " + value + System.lineSeparator());
  }

  public void printCrossover(String value) throws IllegalStateException {
    writeMessage("The following days are crossover days in the specified time period: \n"
            + value + System.lineSeparator());
  }

  public void terminating() throws IllegalStateException {
    writeMessage("Terminating command..." + System.lineSeparator());
  }

  @Override
  public void emptyLine() throws IllegalStateException {
    writeMessage(System.lineSeparator());
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

  public void invalidCommand() throws IllegalStateException {
    writeMessage("This is an invalid command!" + System.lineSeparator());
  }


}
