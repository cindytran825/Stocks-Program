package view;

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
  void editPortfolioMenu();
}
