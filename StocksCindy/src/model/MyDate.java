package model;

/**
 * Class representation of dates.
 */
public interface MyDate extends Comparable<MyDate> {

  /**
   * Advance the current date by x days.
   *
   * @param days number of days the date is to be advanced by.
   */
  void advance(int days);

  /**
   * gets the day value.
   *
   * @return day
   */
  int getDay();

  /**
   * gets the month value.
   *
   * @return month
   */
  int getMonth();

  /**
   * gets the year value.
   *
   * @return year
   */
  int getYear();

  /**
   * gets a 3-letter representation of a month (month name), given the month number.
   *
   * @return 3-letter string of month (first three letter  of month name)
   * @throws IllegalArgumentException when given a month number that doesn't exist
   */
  String getStringMonth() throws IllegalArgumentException;

  /**
   * gets a 3-letter representation of a month (month name), given the month number.
   *
   * @param num the month number
   * @return 3-letter string of month (first three letter  of month name)
   * @throws IllegalArgumentException when given a month number that doesn't exist
   */
  String getStringMonth(int num) throws IllegalArgumentException;

  /**
   * this is used to call to advance to the end of the month.
   * used for the bar chart to get the value when there is a timespan.
   *
   * @param startDate is the startDate that the user input.
   * @return the difference between the start date and the last day of the month.
   */
  int getLastDate(MyDate startDate);

  /**
   * this gets the length of the month called on.
   *
   * @param month the current month that has been advanced.
   * @param year  the year of that month.
   * @return the amount of days in the current month.
   */
  int getMonthLength(int month, int year);

  /**
   * this calculates the amount of days in the next month.
   * it also checks if its december and advances to the next year.
   *
   * @param month is the current month that we are checking.
   * @param year  is the year to that month.
   * @return the amount of days in the next month.
   */
  int getNextMonth(int month, int year);

  /**
   * this gets the difference between the date to the end of the.
   * year. This is used when the timespan is every year.
   * It gets the difference to the last day of the year.
   *
   * @param startDate is the startDate of the range that the user input.
   * @return the difference between the current date to the last day of the year.
   */
  int getEndYear(MyDate startDate);
}
