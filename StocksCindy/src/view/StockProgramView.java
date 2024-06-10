package view;

import java.io.IOException;

/**
 * View of the program, it should connect to the controller.
 * which calls the methods in the model.
 */
public class StockProgramView implements View {
  private Appendable appendable;

  /**
   * empty constructor for the view. Automatically assigns it to System.out when given no argument.
   */
  public StockProgramView() {
    this.appendable = System.out;
  }

  /**
   * constructor that takes in the appendable.
   *
   * @param appendable any appendable that view can add strings to
   */
  public StockProgramView(Appendable appendable) {
    this.appendable = appendable;
  }

  private void writeMessage(String message) {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void printMenu() {

    writeMessage("Create new portfolio [port-create]" + System.lineSeparator());
    writeMessage("Manage portfolio [port-manage]" + System.lineSeparator());
    writeMessage("View existing portfolios [port-view]" + System.lineSeparator());
    writeMessage("Evaluate existing portfolios [port-eval]" + System.lineSeparator());
    writeMessage("Examine gain/loss [stock-eval]" + System.lineSeparator());
    writeMessage("Examine x-day move average [stock-avg]" + System.lineSeparator());
    writeMessage("Determine which days are x-day crossover "
            +
            "[stock-cross]" + System.lineSeparator());
    writeMessage("View what stock datas are on file [stock-list]"
            + System.lineSeparator());
    writeMessage("Download stock data from an API " +
            "[stock-download]" + System.lineSeparator());
    writeMessage("Upload your own stock data in a csv file"
            +
            " [stock-upload]" + System.lineSeparator());
    writeMessage("Quit [quit]" + System.lineSeparator());
    writeMessage("Menu [menu]" + System.lineSeparator());
    writeMessage("Enter instruction to the action you'd like to take!"
            + System.lineSeparator());

  }

  @Override
  public void getTicker() {
    writeMessage("Enter the ticker: ");
  }

  @Override
  public void getNameOfFile(String name) {
    writeMessage("Existing files are: \n" + name);
  }

  @Override
  public void getDateUser1() {
    writeMessage("Type the date to use for evaluation (YYYY-MM-DD): ");
  }

  @Override
  public void getDateUser2() {
    writeMessage("Type the starting date (YYYY-MM-DD): ");
  }

  @Override
  public void getDateUser3() {
    writeMessage("Type the ending date (YYYY-MM-DD): ");
  }

  @Override
  public void inputNumber() {
    writeMessage("Enter Command: ");
  }

  @Override
  public void nameNew() {
    writeMessage("Name of new portfolio: ");
  }

  @Override
  public void tickerType() {
    writeMessage("Enter Ticker (type 'f' to complete portfolio): ");
  }

  @Override
  public void stockAdd() {
    writeMessage("How many shares do you want to add for this stock? ");
  }

  @Override
  public void namePort() {
    writeMessage("Type just the NAME of the portfolio: ");
  }

  @Override
  public void print(String msg) {
    writeMessage(msg);
  }

  @Override
  public void success() {
    writeMessage("Successfully added a new portfolio." + System.lineSeparator());
  }

  @Override
  public void printPortValue(String value) {
    writeMessage("The value of this portfolio is: $" + value + System.lineSeparator());
  }

  @Override
  public void printNetGain(String value, String date1, String date2) {
    writeMessage("Between " + date1 + " to " + date2 + " "
            + "the stock had a net gain of: $" + value + System.lineSeparator());
  }

  @Override
  public void getDays() {
    writeMessage("Enter in the last x-days since the current date: ");
  }

  @Override
  public void movingAvg(String value) {
    writeMessage("The moving average is: " + value + System.lineSeparator());
  }

  @Override
  public void printCrossover(String value) {
    writeMessage(value);
  }

  @Override
  public void terminating() {
    writeMessage("Terminating command..." + System.lineSeparator());
  }

  @Override
  public void emptyLine() {
    writeMessage(System.lineSeparator());
  }

  @Override
  public void printStockNames(String list) {
    writeMessage("Here are available stock datas:\n" + list);
  }

  @Override
  public void printSuccessAddStock(String ticker) {
    writeMessage("Successfully added " + ticker + " stock.\n");
  }

  @Override
  public void getPath() {
    writeMessage("Enter in the reference path to the CSV file: ");
  }

  @Override
  public void getNameFile() {
    writeMessage("Enter the ticker this file represents: ");
  }

  @Override
  public void invalidDate() {
    writeMessage("Invalid date entered.");
  }

  @Override
  public void invalidNumber() {
    writeMessage("Invalid number entered.");
  }

  @Override
  public void invalidFile() {
    writeMessage("Invalid file entered.");
  }

  @Override
  public void invalidStock() {
    writeMessage("Cannot find stock on file.");
  }

  @Override
  public void goodbye() {
    writeMessage("Thank you for using our program!" + System.lineSeparator());

  }

  @Override
  public void welcomeMessage() {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * display invalid.
   *
   * @throws IllegalStateException .
   */
  @Override
  public void invalidCommand() throws IllegalStateException {
    writeMessage("This is an invalid command!" + System.lineSeparator());
  }

}
