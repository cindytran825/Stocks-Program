package model;

import java.util.List;

/**
 * This class represents all data associated with a stock.
 */
public interface Stocks extends Analyzable {

  /**
   * calculates the total net gain (or the difference) between a specified time period.
   * When given start dates or end dates that do NOT have stock data (weekends or holidays),
   * it will assume the closest dates in between the given time period.
   *
   * @param startDate the starting date for comparison
   * @param endDate   the ending date for comparison
   * @return the difference between the ending price and the starting price
   * @throws IllegalArgumentException when there are no data points available for the associated
   *                                  time period
   */
  double calculateNetGain(String startDate, String endDate) throws IllegalArgumentException;

  /**
   * returns the moving average of a stock given a specified starting date and the last x days.
   * "X days" refer to days with data points available, and it begins counting from the
   * starting date (1 day is just starting date).
   *
   * @param startDate starting date to calculate moving average
   * @param lastX     the last x days from starting date (moving average period)
   * @return the moving average of a specified
   * @throws IllegalArgumentException when there are not enough days with data points to
   *                                  satisfy the given last x days
   */
  double getMovingAverage(String startDate, double lastX) throws IllegalArgumentException;

  /**
   * returns a list of crossover days (when the closing price of that day is greater that the
   * x-day moving average) given a specified starting date and the last x days. "X days"
   * refer to days with data points available, and it begins counting from the starting
   * date (1 day is just starting date).
   *
   * @param startDate starting date to calculate crossover days
   * @param lastX     the last x days from starting date (crossover period)
   * @return a list of all the crossover days
   */
  List<String> getCrossOver(String startDate, double lastX);

//  /**
//   * This gets the value of a column given a valid, specific date and an existing column.
//   *
//   * @param date date in the format YYYY-MM-DD
//   * @param column column name
//   * @return the value in the specified column at the specified date
//   * @throws IllegalArgumentException when the specified date or column doesn't exist as a data
//   * point
//   */
//  double getColumnValue(String date, String column) throws IllegalArgumentException;

  /**
   * returns the ticker.
   *
   * @return ticker
   */
  String getTicker();

  /**
   * returns all available dates with data points.
   *
   * @return a list of dates in the data.
   */
  List<String> getTimestamp();

  /**
   * returns all open prices available.
   *
   * @return a list of all open prices
   */
  List<Double> getOpen();

  /**
   * returns all high prices available.
   *
   * @return a list of all high prices
   */
  List<Double> getHigh();

  /**
   * returns all low prices available.
   *
   * @return a list of all low prices
   */
  List<Double> getLow();

  /**
   * returns all closing prices available.
   *
   * @return a list of all closing prices
   */
  List<Double> getClose();

  /**
   * returns a list of all volume data points available.
   *
   * @return a list of all volume data points.
   */
  List<Double> getVolume();


}
