package model;

import java.util.Map;

/**
 * interface represents the portfolios that the user makes
 * it allows them to create and edit them
 * the methods in portfolio is used to call in the model which is
 * called in the controller.
 */
public interface Portfolio {

  /**
   * gets the map of tickers and shares that the
   * user inputs to the portfolio
   * calls deep copy that copies the map.
   *
   * @return a map of the port of inventory.
   */
  Map<String, Double> getListInventories();

  /**
   * turns the list of inventories to a string
   * also reformatting it in the file (Stock : shares)
   *
   * @return a string of the inventories.
   */
  public String toString();

  /**
   * is called when the user wants to get the value of a portfolio.
   *
   * @param date        the date they input in the format (YYYY-MM-DD)
   * @param pathToStock the directory of located file
   * @return the value of the portfolio
   */
  double getValue(String date, String pathToStock);

  /**
   * gets the name of the portfolio.
   *
   * @return a string of that name.
   */
  String getPortfolioName();

  /**
   * This is a buying function of a portfolio. It can add stock shares to the portfolio using
   * the associated CLOSING price that comes with the specified purchasing date.
   *
   * @param ticker stock ticker
   * @param shares quantity for purchase
   * @param date   the date the transaction is being made in the format (YYYY-MM-DD)
   * @throws IllegalArgumentException when there's an attempt to make a purchase with a date
   *                                  that is not chronological to previous transactions, or
   *                                  the given date is a date where there are no stock data
   *                                  available (closed market).
   */
  void buyStock(String ticker, double shares, String date) throws IllegalArgumentException;

  /**
   * This is a selling function of a portfolio. It can remove stock shares from the portfolio
   * using the associated CLOSING price that comes with the specified purchasing date.
   *
   * @param ticker stock ticker
   * @param shares quantity for sale
   * @param date   date the transaction is being made in the format (YYYY-MM-DD)
   * @throws IllegalArgumentException when there's an attempt to make a purchase with a date
   *                                  that is not chronological to previous transactions, or
   *                                  the given date is a date where there are no stock data
   *                                  available (closed market). Also when there's an attempt
   *                                  to sell more shares than what is available in the portfolio
   */
  void sellStock(String ticker, double shares, String date) throws IllegalArgumentException;

  /**
   * This method gets the portfolio's stock composition (the stocks and its shares)
   * at the time of the specified date.
   *
   * @param date any date for evaluation of stock composition in the format (YYYY-MM-DD)
   * @return a map of the portfolio's composition (stock : shares)
   * @throws IllegalStateException when an unknown action is read from the log
   */
  Map<String, Double> getComposition(String date) throws IllegalStateException;

  /**
   * This method equalizes the current portfolio's stock shares to the specified percentages.
   * For example, let us assume that on a particular date, the price per share of Netflix, Google,
   * Microsoft, and Apple were $15, $30, $10 and $30 respectively. The values of these stocks
   * (price per share multiplied by number of shares) would be $375, $300, $250 and $150
   * respectively, bringing the total value of the portfolio to $1075. If the intended
   * distribution of these stocks in the portfolio was supposed to be (25%, 25%, 25%, 25%),
   * the portfolio should have $268.75 worth of each stock. Therefore, the investor will have
   * to sell $106.25 worth of Netflix stock (7.0833 shares) and $31.25 worth of Google stock
   * (1.0417 shares)to purchase $18.75 worth of Microsoft stock (1.875 shares) and $118.75
   * (3.9583 shares) of Apple stock.
   *
   * @param percentages percentage distribution to each stock
   * @param date        a date in the format of (YYYY-MM-DD).
   * @throws IllegalArgumentException when an invalid date is given (bad format, not chronological
   *                                  to transaction history, or a date with no data available).
   *                                  Or, when the percentages don't add up to 100%
   */
  void rebalance(
          Map<String, Double> percentages,
          String date
  ) throws IllegalArgumentException;

  /**
   * This gets the distribution of the portfolio's stock values on a specified date.
   * Any invalid dates will be checked in getComposition.
   *
   * @param date a date in the format YYYY-MM-DD
   * @return the values associated with each stock
   */
  Map<String, Double> getDistribution(String date);

  /**
   * this takes the startDate and endDate and uses the compareTo method.
   * from the MyDateWithImpl class to get the difference between the two dates.
   * Based on the difference, it decides the timespan and calls the method getBarChart.
   * in the DataChart class to create the bar chart.
   * @param name the name of the portfolio that the user input.
   * @param startDate the starting date that the user input to get the value.
   * @param endDate the ending date that the user input to get the value.
   */
  String getChart(String name, String startDate, String endDate);

}
