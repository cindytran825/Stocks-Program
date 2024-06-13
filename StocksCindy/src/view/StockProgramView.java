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

    // TODO CHANGE (UPDATE IT TO BE UP TO DATE)
    writeMessage("Create new portfolio [port-create]\n");
    writeMessage("Manage portfolio [port-manage]\n");
    writeMessage("View existing portfolios [port-view]\n");
    writeMessage("Evaluate existing portfolios [port-eval]\n");
    writeMessage("Examine gain/loss [stock-eval]\n");
    writeMessage("Examine x-day move average [stock-avg]\n");
    writeMessage("Determine which days are x-day crossover [stock-cross]\n");
    writeMessage("View what stock datas are on file [stock-list]\n");
    writeMessage("Download stock data from an API [stock-download]\n");
    writeMessage("Upload your own stock data in a csv file [stock-upload]\n");
    writeMessage("Quit [quit]\n");
    writeMessage("Menu [menu]\n");
    writeMessage("Enter instruction to the action you'd like to take!\n");

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
  public void getDay() {
    writeMessage("Enter in the day: ");
  }

  @Override
  public void getMonth() {
    writeMessage("Enter in the month: ");
  }

  @Override
  public void getYear() {
    writeMessage("Enter in the year: ");
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
  public void userInput() {
    writeMessage("Enter Command: ");
  }

  @Override
  public void getName(String object) {writeMessage("Enter in the name of " + object + ": ");}

  @Override
  public void getShares() {writeMessage("Enter in the number of shares: ");}

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
    writeMessage("Successfully added a new portfolio." + "\n");
  }

  @Override
  public void printPortValue(String value) {
    writeMessage("The value of this portfolio is: $" + value + "\n");
  }

  @Override
  public void printNetGain(String value, String date1, String date2) {
    writeMessage("Between " + date1 + " to " + date2 + " "
            + "the stock had a net gain of: $" + value + "\n");
  }

  @Override
  public void getDays() {
    writeMessage("Enter in the last x-days since the current date: ");
  }

  @Override
  public void movingAvg(String value) {
    writeMessage("The moving average is: " + value + "\n");
  }

  @Override
  public void printCrossover(String value) {
    writeMessage(value);
  }

  @Override
  public void terminating() {
    writeMessage("Terminating command..." + "\n");
  }

  @Override
  public void emptyLine() {
    writeMessage("\n");
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
  public void invalidShares() {
    writeMessage("Cannot make transaction with fractional shares.");
  }

  @Override
  public void goodbye() {
    writeMessage("Thank you for using our program!" + System.lineSeparator());

  }

  @Override
  public void newName() {
    writeMessage("Enter the name of the new portfolio: ");
  }

  // TODO NEED THE INTERFACE FOR CHART
  @Override
  public void returnBarChart(DataChart chart) {
    writeMessage(chart.getChart());
  }

  @Override
  public void editPortfolioMenu() {
    writeMessage("Actions you can perform on this portfolio: \n");
    writeMessage("Buy stock [buy]\n");
    writeMessage("Sell stock [sell]\n");
    writeMessage("Balance each stock to a percentage [balance]\n");
    writeMessage("To finish making changes to portfolio [finish]\n");
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
