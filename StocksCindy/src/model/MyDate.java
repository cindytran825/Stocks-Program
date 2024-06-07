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

}
