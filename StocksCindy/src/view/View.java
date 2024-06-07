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
   * display input name.
   */
  void nameNew();

  /**
   * display user input.
   */
  void inputNumber();

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
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void printStockNames(String list);

  /**
   * display sucess.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void printSuccessAddStock(String ticker);

  /**
   * display the path.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void getPath();

  /**
   * display name file.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void getNameFile();

  /**
   * display invalid date.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidDate();

  /**
   * display invalid number.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidNumber();

  /**
   * display invalid file.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidFile();

  /**
   * display invalid stock message.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidStock();

  /**
   * this prints the goodbye message.
   */
  void goodbye();

  /**
   * this prints the welcome message.
   */
  void welcomeMessage();

  /**
   * display invalid command message
   */
  void invalidCommand();
}
