package view;

import model.Analyzable;
import model.DataChart;

/**
 * the view which does the display.
 * is called in the controller.
 */
public interface View {

  /**
   * this prints the menu, first thing user sees.
   */
  void printMenu();

  /**
   * display to tell user to input the ticker.
   */
  void getTicker();

  /**
   * display of file message.
   */
  void getNameOfFile(String name);

  /**
   * display name portfolio.
   */
  void namePort();

  /**
   * display input stock.
   */
  void stockAdd();

  /**
   * display input ticker.
   */
  void tickerType();

  /**
   * display a query for name of specified object.
   */
  void getName(String object);

  /**
   * display a prompt for shares.
   */
  void getShares();

  /**
   * display user input.
   */
  void userInput();

  /**
   * display the query for a day.
   */
  void getDay();

  /**
   * displays the query for a month.
   */
  void getMonth();

  /**
   * displays the query for a year.
   */
  void getYear();

  /**
   * display message date.
   */
  void getDateUser1();

  /**
   * display message date.
   */
  void getDateUser2();

  /**
   * display message date.
   */
  void getDateUser3();

  /**
   * display print.
   */
  void print(String msg);

  /**
   * display success.
   */
  void success();

  /**
   * display value.
   */
  void printPortValue(String value);

  /**
   * display net gain.
   */
  void printNetGain(String value, String date1, String date2);

  /**
   * display days.
   */
  void getDays();

  /**
   * display moving average.
   */
  void movingAvg(String value);

  /**
   * display crossover.
   */
  void printCrossover(String value);

  /**
   * display terminating message.
   */
  void terminating();

  /**
   * display empty.
   */
  void emptyLine();

  /**
   * display the stock names.
   */
  void printStockNames(String list);

  /**
   * display instruction for balance command.
   */
  void balanceFormat();

  /**
   * display success message when portfolio balance worked.
   */
  void balanceSuccess();

  /**
   * display sucess.
   */
  void printSuccessAddStock(String ticker);

  /**
   * display the path.
   */
  void getPath();

  /**
   * display name file.
   */
  void getNameFile();

  /**
   * display invalid date.
   */
  void invalidDate();

  /**
   * display invalid number.
   */
  void invalidNumber();

  /**
   * display invalid file.
   */
  void invalidFile();

  /**
   * display invalid stock message.
   */
  void invalidStock();

  /**
   * display invalid stock shares message.
   */
  void invalidShares();

  /**
   * this prints the goodbye message.
   */
  void goodbye();

  /**
   * this prints the welcome message.
   */
  void welcomeMessage();

  /**
   * display invalid command message.
   */
  void invalidCommand();

  /**
   * display the query for a portfolio's new name upon creation.
   */
  void newName();

  /**
   * displays the given bar chart.
   *
   * @param chart bar chart
   *              TODO NEED THE INTERFACE OF THE DATACHART
   */
//  void returnBarChart(DataChart chart);

  /**
   * displays the types of action the user can perform on the portfolio that will edit
   * the portfolio (buy, sell, balance, etc.).
   */
  void printEditPortfolioMenu();

  /**
   * displays a message to let the user know that they are entering the date for the start of
   * the evaluation period.
   */
  void startDate();

  /**
   * displays a message to let the user know that they are entering the date for the end of
   * the evaluation period.
   */
  void endDate();

  /**
   * display that there are not enough shares for the transaction.
   */
  void insufficientShares();

  /**
   * display that the percentages provided do not equate to 100% exactly.
   */
  void invalidPercentage();

  /**
   * displays a message to let the user know that the logging activity they tried to do
   * was not entered in chronological time or that the stock data does not exist for that day.
   */
  void notChronologicalOrDataInvalid();

  /**
   * displays the types of action the user can perform on the stock that will analyze the stock
   * (growth, moving average, crossover days, bar chart, etc.).
   */
  void printStockViewMenu();

  /**
   * displays the types of action the user can perform on the portfolio that will analyze
   * the portfolio (value, composition, distribution, chart, etc.)
   */
  void printPortfolioViewMenu();


  void getWhichChart();

  void returnBarChartPortfolio(DataChart chart, String name, String startDate, String endDate, Analyzable existingPortfolio);
  void returnBarChartStock(DataChart chart, String name, String startDate, String endDate, String ticker, Analyzable stock);

}
