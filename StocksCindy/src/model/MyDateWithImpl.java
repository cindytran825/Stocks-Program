package model;


/**
 * Class representation of valid dates, keeping track of only day, month, and year.
 */
public class MyDateWithImpl implements MyDate {
  private int day;
  private int month;
  private int year;

  /**
   * Constructor for MyDate that checks if the provided date is a valid date.
   *
   * @param day   the date.
   * @param month the month.
   * @param year  the year.
   * @throws IllegalArgumentException if the date is invalid and not a real date.
   */
  public MyDateWithImpl(int day, int month, int year) throws IllegalArgumentException {
    if (!checkIfValidDate(day, month, year)) {
      throw new IllegalArgumentException("Invalid date.");
    }
    this.day = day;
    this.month = month;
    this.year = year;
  }

  private boolean checkIfValidDate(int day, int month, int year) {
    final int monthLength = getMonthLength(month, year);
    return (day > 0 && day <= monthLength)
            && (0 < month && month <= 12)
            && (year >= 0);
  }

  private int getMonthLength(int month, int year) {
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        return checkIfLeapYear(year) ? 29 : 28;
      default:
        return -1;
    }
  }

  private boolean checkIfLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 100 == 0 && year % 400 == 0));
  }

  /**
   * advances the date if the date is on a weekend.
   * or isn't a part of the data in the api.
   * @param days number of days the date is to be advanced by.
   */
  @Override
  public void advance(int days) {
    int monthLength;
    int defensiveDay = day;
    int defensiveMonth = month;
    int defensiveYear = year;
    int increment = days < 0 ? -1 : 1;

    while (days != 0) {
      monthLength = getMonthLength(defensiveMonth, defensiveYear);
      if (checkIfValidDate(defensiveDay + increment, defensiveMonth, defensiveYear)) {
        defensiveDay += increment;
      } else {
        // if the days are negative
        if (defensiveDay + increment < 1) {
          if (defensiveMonth + increment < 1) {
            if (defensiveYear + increment < 0) {
              break;
            }
            defensiveDay = 31;
            defensiveMonth = 12;
            defensiveYear--;
          } else {
            defensiveMonth--;
            defensiveDay = getMonthLength(defensiveMonth, defensiveYear);
          }
        } else if (defensiveDay + increment > monthLength) { // if the days are positive
          defensiveDay = 1;
          if (defensiveMonth < 12) {
            defensiveMonth++;
          } else {
            defensiveMonth = 1;
            defensiveYear++;
          }
        }
      }
      days -= increment;
    }
    this.day = defensiveDay;
    this.month = defensiveMonth;
    this.year = defensiveYear;
  }

  /**
   * gets the day.
   * @return
   */
  @Override
  public int getDay() {
    return day;
  }

  /**
   * gets the month.
   * @return
   */
  @Override
  public int getMonth() {
    return month;
  }

  /**
   * gets the year.
   * @return
   */
  @Override
  public int getYear() {
    return year;
  }

  /**
   * formats the date.
   * @return the date.
   */
  @Override
  public String toString() {
    return String.format("%04d-%02d-%02d", year, month, day);
  }

  /**
   * checks if the two dates are the same.
   * @param other the other date compared.
   * @return a boolean.
   */
  @Override
  public boolean equals(Object other) {
    return (other instanceof MyDateWithImpl otherDate)
            && this.day == otherDate.getDay()
            && this.month == otherDate.getMonth()
            && this.year == otherDate.getYear();

  }

  /**
   * gets the difference of the dates.
   * @param other the object to be compared.
   * @return an integer.
   */
  @Override
  public int compareTo(MyDate other) {
    MyDate start = new MyDateWithImpl(1, 1, 0);

    MyDate thisCopy = new MyDateWithImpl(day, month, year);
    MyDate otherCopy = new MyDateWithImpl(other.getDay(), other.getMonth(), other.getYear());

    int daysSinceStart1 = 0;
    int daysSinceStart2 = 0;
    while (!thisCopy.equals(start)) {
      thisCopy.advance(-1);
      daysSinceStart1++;
    }
    while (!otherCopy.equals(start)) {
      otherCopy.advance(-1);
      daysSinceStart2++;
    }
    return daysSinceStart1 - daysSinceStart2;
  }
}