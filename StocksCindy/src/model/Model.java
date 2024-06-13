package model;

import java.util.Map;

/**
 * represents the model. It does the implementations.
 * and it is called in the controller when it picks up the user inputs.
 */
public interface  Model {


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

//  /**
//   * gets the portfolio used in the controller.
//   *
//   * @param name the user inputs for the portfolio.
//   * @return a string.
//   */
//  String getPortfolio(String name);

  /**
   * the names of the stocks.
   *
   * @return a string of the name of stocks.
   */
  String getStockNames();

  /**
   * @param name
   * @param firstDate
   * @param lastDate
   * @return
   */
  // TODO DOCUMENTATION
  String barChartInitialized(String name, String firstDate, String lastDate);

  void buyStock(String name, String ticker, String share, String date);

  void sellStock(String name, String ticker, String share, String date);

  Map<String, Double> getPortfolioStocks(String name);

  void balance(String date, String name, Map<String, Double> percentages);

  String getPortfolioComposition(String name, String date);

  String getPortfolioDistribution(String name, String date);

  /**
   * Checks if the user's input date is entered in chronological order based on the latest
   * transaction date in the log.
   *
   * @param name      name of portfolio
   * @param inputDate the checked date
   * @return true if the input date is chronological to the latest transaction in the log
   */
  boolean checkIfChronologicalPortfolio(String name, String inputDate);

  /**
   * Checks if the stock data exist for given date.
   *
   * @param ticker    stock ticker
   * @param inputDate date that is to be checked
   * @return true if there is data available for that input date
   */
  boolean checkIfStockDataExist(String ticker, String inputDate);

  /**
   * Checks if the action is the latest action and there's stock data available for that day.
   *
   * @param portfolioName name of portfolio
   * @param inputDate     date to be checked
   * @return true if the input date is the latest date and there's stock data available for that
   * date
   */
  boolean checkIfPortfolioChronologicalAndDataExist(String portfolioName, String inputDate);

  /**
   * Checks if the provided string is a whole number.
   *
   * @param string any string
   * @return true if it is an integer, false if otherwise
   */
  boolean checkIfWholeNumber(String string);

  /**
   * Check if there are sufficient shares for the action (selling).
   *
   * @param portName name of portfolio
   * @param ticker name of stock involved in action
   * @param shares quantity of shares for action
   * @return true if there are not enough shares for the action
   */
  boolean checkSharesNotEnough(String portName, String ticker, double shares);

  /**
   * this is called when the user wants to get the bar chart for a stock.
   * calling the method getChart in DataChart class.
   *
   * @param name      the name the user inputs.
   * @param firstDate the first date of the range they input.
   * @param lastDate  the last date of the range they input.
   * @param ticker    the ticker of the stock.
   * @return a string of the bar chart.
   */
  String barChartStockInitialized(String name, String firstDate, String lastDate, String ticker);

  /**
   * this is called when the user wants to get the bar chart for a portfolio.
   * it calls the methods getChart method in DataChart class.
   *
   * @param name      the name of the portfolio.
   * @param firstDate the first date of the range they input.
   * @param lastDate  the last date of the range they input.
   * @return a string of the bar chart that is called later in the view.
   */
  String barChartPortfolioInitialized(String name, String firstDate, String lastDate);

}
