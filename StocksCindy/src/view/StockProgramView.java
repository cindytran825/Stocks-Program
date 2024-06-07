package view;

import java.io.IOException;

/**
 * View of the program, it should connect to the controller.
 * which calls the methods in the model.
 */
public class StockProgramView implements View {
  private Appendable appendable;

  /**
   * empty contructor for the view.
   */
  public StockProgramView() {
    this.appendable = System.out;
  }

  /**
   * contructor that takes in the appendable.
   * @param appendable
   */
  public StockProgramView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * this writes the message.
   *
   * @param message a string.
   * @throws IllegalStateException when the input isn't a string.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * writes the menu.
   * @throws IllegalStateException  .
   */
  @Override
  public void printMenu() throws IllegalStateException {

    writeMessage("Create new portfolio [port-create]" + System.lineSeparator());
    writeMessage("Manage portfolio [port-manage]" + System.lineSeparator());
    writeMessage("View existing portfolios [port-view]" + System.lineSeparator());
    writeMessage("Evaluate existing portfolios [port-eval]" + System.lineSeparator());
    writeMessage("Examine gain/loss [stock-eval]" + System.lineSeparator());
    writeMessage("Examine x-day move average [stock-avg]" + System.lineSeparator());
    writeMessage("Determine which days are x-day crossover [stock-cross]" + System.lineSeparator());
    writeMessage("View what stock datas are on file [stock-list]" + System.lineSeparator());
    writeMessage("Download stock data from an API [stock-download]" + System.lineSeparator());
    writeMessage("Quit [quit]" + System.lineSeparator());
    writeMessage("Menu [menu]" + System.lineSeparator());
    writeMessage("Enter instruction to the action you'd like to take!"
            + System.lineSeparator());

  }

  /**
   * instructs the user to enter the ticker.
   * @throws IllegalStateException .
   */
  @Override
  public void getTicker() throws IllegalStateException {
    writeMessage("Enter the ticker: ");
  }

  /**
   * returns the names of the files.
   * @param name
   * @throws IllegalStateException .
   */
  @Override
  public void getNameOfFile(String name) throws IllegalStateException {
    writeMessage("Existing files are: \n" + name);
  }

  /**
   * tells user to input date.
   * @throws IllegalStateException .
   */
  @Override
  public void getDateUser1() throws IllegalStateException {
    writeMessage("Type the date to use for evaluation (YYYY-MM-DD): ");
  }

  /**
   * tells user to input date.
   * @throws IllegalStateException .
   */
  @Override
  public void getDateUser2() throws IllegalStateException {
    writeMessage("Type the starting date (YYYY-MM-DD): ");
  }

  /**
   * tells user to input date.
   * @throws IllegalStateException .
   */
  @Override
  public void getDateUser3() throws IllegalStateException {
    writeMessage("Type the ending date (YYYY-MM-DD): ");
  }

  /**
   * input number.
   * @throws IllegalStateException .
   */
  @Override
  public void inputNumber() throws IllegalStateException {
    writeMessage("Enter Command: ");
  }

  /**
   * instructs user input name of portfolio.
   * @throws IllegalStateException .
   */
  @Override
  public void nameNew() throws IllegalStateException {
    writeMessage("Name of new portfolio: ");
  }

  /**
   * tells user to input the ticker.
   * @throws IllegalStateException .
   */
  @Override
  public void tickerType() throws IllegalStateException {
    writeMessage("Enter Ticker (type 'f' to complete portfolio): ");
  }

  /**
   * asks how many shares.
   * @throws IllegalStateException .
   */
  @Override
  public void stockAdd() throws IllegalStateException {
    writeMessage("How many shares do you want to add for this stock? ");
  }

  /**
   * type the name of the portfolio.
   * @throws IllegalStateException .
   */
  @Override
  public void namePort() throws IllegalStateException {
    writeMessage("Type just the NAME of the portfolio: ");
  }

  /**
   * print the msg.
   * @param msg a string. .
   * @throws IllegalStateException .
   */
  @Override
  public void print(String msg) throws IllegalStateException {
    writeMessage(msg);
  }

  /**
   * when they are able to create portfolio.
   * @throws IllegalStateException .
   */
  @Override
  public void success() throws IllegalStateException {
    writeMessage("Successfully added a new portfolio." + System.lineSeparator());
  }

  /**
   * for the value of portfolio.
   * @param value
   * @throws IllegalStateException .
   */
  @Override
  public void printPortValue(String value) throws IllegalStateException {
    writeMessage("The value of this portfolio is: $" + value + System.lineSeparator());
  }

  /**
   * display next gain.
   * @param value string.
   * @param date1 string.
   * @param date2 string.
   * @throws IllegalStateException .
   */
  @Override
  public void printNetGain(String value, String date1, String date2) throws IllegalStateException {
    writeMessage("Between " + date1 + " to " + date2 + " "
            + "the stock had a net gain of: $" + value + System.lineSeparator());
  }

  /**
   * get the day from user.
   * @throws IllegalStateException .
   */
  @Override
  public void getDays() throws IllegalStateException {
    writeMessage("Enter in the last x-days since the current date: ");
  }

  /**
   * get ht eaverage from user.
   * @param value of portfolio.
   * @throws IllegalStateException .
   */
  @Override
  public void movingAvg(String value) throws IllegalStateException {
    writeMessage("The moving average is: " + value + System.lineSeparator());
  }

  /**
   * display the crossover days.
   * @param value the value.
   * @throws IllegalStateException .
   */
  @Override
  public void printCrossover(String value) throws IllegalStateException {
    writeMessage("The following days are crossover days in the specified time period: \n"
            + value + System.lineSeparator());
  }

  /**
   * terminating command.
   * @throws IllegalStateException .
   */
  @Override
  public void terminating() throws IllegalStateException {
    writeMessage("Terminating command..." + System.lineSeparator());
  }

  /**
   * empty.
   * @throws IllegalStateException .
   */
  @Override
  public void emptyLine() throws IllegalStateException {
    writeMessage(System.lineSeparator());
  }

  /**
   * display the stock.
   * @param list of the stocks in portfolio.
   * @throws IllegalStateException .
   */
  @Override
  public void printStockNames(String list) throws IllegalStateException {
    writeMessage("Here are available stock datas:\n" + list);
  }

  /**
   * display the stock when added.
   * @param ticker ticker.
   * @throws IllegalStateException .
   */
  @Override
  public void printSuccessAddStock(String ticker) throws IllegalStateException {
    writeMessage("Successfully added " + ticker + " stock.\n");
  }

  /**
   * display the reference.
   * @throws IllegalStateException .
   */
  @Override
  public void getPath() throws IllegalStateException {
    writeMessage("Enter in the reference path to the CSV file: ");
  }

  /**
   * display message.
   * @throws IllegalStateException.
   */
  @Override
  public void getNameFile() throws IllegalStateException {
    writeMessage("Enter the ticker this file represents: ");
  }

  /**
   * display message.
   * @throws IllegalStateException .
   */
  @Override
  public void invalidDate() throws IllegalStateException {
    writeMessage("Invalid date entered.");
  }

  /**
   * display message.
   * @throws IllegalStateException .
   */
  @Override
  public void invalidNumber() throws IllegalStateException {
    writeMessage("Invalid number entered.");
  }

  /**
   * display message.
   * @throws IllegalStateException .
   */

  @Override
  public void invalidFile() throws IllegalStateException {
    writeMessage("Invalid file entered.");
  }

  /**
   * display thank you.
   * @throws IllegalStateException .
   */
  @Override
  public void goodbye() throws IllegalStateException {
    writeMessage("Thank you for using our program!" + System.lineSeparator());

  }

  /**
   * display welcome message.
   * @throws IllegalStateException .
   */
  @Override
  public void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * display invalid.
   * @throws IllegalStateException .
   */
  @Override
  public void invalidCommand() throws IllegalStateException {
    writeMessage("This is an invalid command!" + System.lineSeparator());
  }


}
