package view;

/**
 * the view which does the display.
 * is called in the controller.
 */
public interface View {

  /**
   * this prints the menu, first thing user sees.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  void printMenu() throws IllegalStateException;

  /**
   * display to tell user to input the ticker.
   * @throws IllegalStateException when there's exception.
   */
  void getTicker() throws IllegalStateException;

  /**
   * display of of file message.
   * @throws IllegalStateException when there's exception.
   */
  void getNameOfFile(String name) throws IllegalStateException;

  /**
   * display name portfolio.
   * @throws IllegalStateException when there's exception.
   */
  void namePort() throws IllegalStateException;

  /**
   * display input stock.
   * @throws IllegalStateException when there's exception.
   */
  void stockAdd() throws IllegalStateException;

  /**
   * display input ticker.
   * @throws IllegalStateException when there's exception.
   */
  void tickerType() throws IllegalStateException;

  /**
   * display input name.
   * @throws IllegalStateException when there's exception.
   */
  void nameNew() throws IllegalStateException;

  /**
   * display user input.
   * @throws IllegalStateException when there's exception.
   */
  void inputNumber() throws IllegalStateException;

  /**
   * display message date.
   * @throws IllegalStateException when there's exception.
   */
  void getDateUser1() throws IllegalStateException;

  /**
   * display message date.
   * @throws IllegalStateException when there's exception.
   */
  void getDateUser2() throws IllegalStateException;

  /**
   * display message date.
   * @throws IllegalStateException when there's exception.
   */
  void getDateUser3() throws IllegalStateException;

  /**
   * display print.
   * @throws IllegalStateException when there's exception.
   */
  void print(String msg) throws IllegalStateException;

  /**
   * display success.
   * @throws IllegalStateException when there's exception.
   */
  void success() throws IllegalStateException;

  /**
   * display value.
   * @throws IllegalStateException when there's exception.
   */
  void printPortValue(String value) throws IllegalStateException;

  /**
   * display net gain. 
   * @throws IllegalStateException when there's exception.
   */
  void printNetGain(String value, String date1, String date2) throws IllegalStateException;

  /**
   * display days. 
   * @throws IllegalStateException when there's exception.
   */
  void getDays() throws IllegalStateException;

  /**
   * display moving average. 
   * @throws IllegalStateException when there's exception.
   */
  void movingAvg(String value) throws IllegalStateException;

  /**
   * display crossover. 
   * @throws IllegalStateException when there's exception.
   */
  void printCrossover(String value) throws IllegalStateException;

  /**
   * display terminating message. 
   * @throws IllegalStateException when there's exception.
   */
  void terminating() throws IllegalStateException;

  /**
   * display empty. 
   * @throws IllegalStateException when there's exception.
   */
  void emptyLine() throws IllegalStateException;

  /**
   * display the stock names. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void printStockNames(String list) throws IllegalStateException;

  /**
   * display sucess. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void printSuccessAddStock(String ticker) throws IllegalStateException;

  /**
   * display the path. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void getPath() throws IllegalStateException;

  /**
   * display name file. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void getNameFile() throws IllegalStateException;

  /**
   * display invalid date. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidDate() throws IllegalStateException;

  /**
   * display invalid number. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidNumber() throws IllegalStateException;

  /**
   * display invalid file. 
   * @throws IllegalStateException when there's exception.
   */
  // TODO NEED TO ADD TO MOCKS AND TESTS
  void invalidFile() throws IllegalStateException;

  /**
   * this prints the goodbye message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  void goodbye() throws IllegalStateException;

  /**
   * this prints the welcome message.
   *
   * @throws IllegalStateException when it's at an illegal state.
   */
  void welcomeMessage() throws IllegalStateException;

  /**
   * display invalid command. 
   * @throws IllegalStateException when there's exception.
   */
  void invalidCommand() throws IllegalStateException;
}
