package model;

/**
 * represents the model. It does the implementations.
 * and it is called in the controller when it picks up the user inputs.
 */
public interface Model extends Observable{


  /**
   * this checks if the file exists.
   *
   * @param path path to the csv file.
   * @return true if file exists, otherwise if not.
   */
  boolean checkIfFileExist(String path);

  /**
   * checks if the date is valid.
   *
   * @param date a date input.
   * @return true if the date is of format YYYY-MM-DD and are numbers.
   */
  boolean checkIfDate(String date);


  /**
   * checks if the specified string is a number.
   *
   * @param n any string
   * @return true if the provided string is a number
   */
  boolean checkIfNumber(String n);

  /**
   * allows the user to upload the file.
   *
   * @param ticker gets the ticker user input.
   * @param path   a path of the file.
   * @return a string.
   */
  String uploadStock(String ticker, String path);

  /**
   * generates the stock.
   *
   * @param ticker the company user inputs.
   */
  void generateStock(String ticker);

  /**
   * this creates a new portfolio and is called in the controller.
   *
   * @param name is the name of the portfolio the user inputs.
   */
  void createPortfolio(String name);

  /**
   * called when the user wants to anything to an existing portfolio.
   *
   * @param name   of the portfolio.
   * @param ticker of the company.
   * @param shares the user inputs.
   */
  void managePortfolio(String name, String ticker, double shares);

  /**
   * called when the user asks for the value in their portfolio.
   * it takes the date that they input and the portfolio location. (name)
   *
   * @param name the name that the user input for their portfolio.
   * @param date the date that the user inputs.
   * @return a double representing the value.
   */
  String evaluatePortfolio(String name, String date);

  /**
   * it returns how much gain and how much loss.
   * negative if there is loss and positive double for gain.
   *
   * @param ticker    the ticker that the user input.
   * @param startDate the startDate.
   * @param endDate   the end date.
   * @return a double representing the gain/loss.
   */
  String evaluateStock(String ticker, String startDate, String endDate);

  /**
   * this gets the x-day moving average of a stock.
   * and calls the getMovingAverage method in the Stock class.
   *
   * @param ticker    is the ticker of the company user inputs.
   * @param startDate the starting date user inputs.
   * @param lastX     integer user inputs.
   * @return a string that calls method.
   */
  String movingAverage(String ticker, String startDate, double lastX);

  /**
   * This gets the x-day crossovers for a specified stock over a specified date range.
   *
   * @param ticker    the ticker user inputs.
   * @param startDate start date the user inputs.
   * @param lastX     the intger user inputs.
   * @return the days as a string.
   */
  String getCrossoverDays(String ticker, String startDate, double lastX);

  /**
   * gets the name of the portfolios in the files.
   * that were created.
   *
   * @return a string of the names of the portfolios.
   */
  String getPortfolioNames();

  /**
   * gets the portfolio used in the controller.
   *
   * @param name the user inputs for the portfolio.
   * @return a string.
   */
  String getPortfolio(String name);

  /**
   * the names of the stocks.
   *
   * @return a string of the name of stocks.
   */
  String getStockNames();

}
