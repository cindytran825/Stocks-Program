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

  int getLastDate(MyDate startDate);

  int getMonthLength(int month, int year);

  int getEndYear(MyDate startDate);
}
