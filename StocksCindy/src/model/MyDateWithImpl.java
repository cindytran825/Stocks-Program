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

  /**
   * Constructor for MyDate that checks if the provided date is a valid date.
   *
   * @param date date in the format YYYY-MM-DD
   * @throws IllegalArgumentException if the date is invalid and not a real date.
   */
  public MyDateWithImpl(String date) throws IllegalArgumentException {
    String[] dateSplit = date.split("-");

    // try catch block to catch invalid number input
    try {
      if (dateSplit[0].length() == 4) {
        this.year = Integer.parseInt(dateSplit[0]);
      }
      if (dateSplit[1].length() == 2) {
        this.month = Integer.parseInt(dateSplit[1]);
      }
      if (dateSplit[2].length() == 2) {
        this.day = Integer.parseInt(dateSplit[2]);
      } else {
        throw new IllegalArgumentException();
      }

      if (!checkIfValidDate(day, month, year)) {
        throw new IllegalArgumentException("Invalid date.");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }

  private boolean checkIfValidDate(int day, int month, int year) {
    final int monthLength = getMonthLength(month, year);
    return (day > 0 && day <= monthLength)
            && (0 < month && month <= 12)
            && (year >= 0);
  }

  @Override
  public int getMonthLength(int month, int year) {
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

  /**
   * this gets the next month and makes sure that when it advances to.
   * december, it goes to the next year.
   * this is called in the timeValue method in DataChart.
   *
   * @param month is the current month that is getting advanced.
   * @param year  is the year that its currently at.
   * @return the amount of days till the next month.
   */
  public int getNextMonth(int month, int year) {
    month = month + 1;
    if (month > 12) {
      month = 1;
      year = year + 1;
    }
    int monthLength = getMonthLength(month, year);
    return monthLength;
  }

  private boolean checkIfLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 100 == 0 && year % 400 == 0));
  }

  /**
   * advances the date if the date is on a weekend.
   * or isn't a part of the data in the api.
   *
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

  @Override
  public int getDay() {
    return day;
  }

  @Override
  public int getMonth() {
    return month;
  }

  @Override
  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("%04d-%02d-%02d", year, month, day);
  }

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

  @Override
  public boolean equals(Object otherDate) {
    return (otherDate instanceof MyDate other)
            && this.day == other.getDay()
            && this.month == other.getMonth()
            && this.year == other.getYear();
  }

  /**
   * this is used to call to advance to the end of the month.
   * used for the bar chart to get the value when there is a timespan.
   *
   * @param startDate is the startDate that the user input.
   * @return the difference between the start date and the last day of the month.
   */
  @Override
  public int getLastDate(MyDate startDate) {
    int addAmount = 0;
    int year = startDate.getYear();
    int month = startDate.getMonth();
    int day = startDate.getDay();
    int length = getMonthLength(month, year);
    addAmount = length - day;

    return addAmount;
  }

  /**
   * this is used ot help advance to the last day of the year.
   * this calculates the difference between the date.
   * the user input to the days of the year.
   *
   * @param startDate date user input for the start of timespan.
   *                  to get value for the bar chart.
   * @return the difference to get to the last day of the year.
   */
  @Override
  public int getEndYear(MyDate startDate) {
    int year = startDate.getYear();
    MyDate one = new MyDateWithImpl(1, 1, year);
    int result = startDate.compareTo(one);
    return 364 - result;

  }


  @Override
  public String getStringMonth() throws IllegalArgumentException {
    switch (month) {
      case 1:
        return "JAN";
      case 2:
        return "FEB";
      case 3:
        return "MAR";
      case 4:
        return "APR";
      case 5:
        return "MAY";
      case 6:
        return "JUN";
      case 7:
        return "JUL";
      case 8:
        return "AUG";
      case 9:
        return "SEP";
      case 10:
        return "OCT";
      case 11:
        return "NOV";
      case 12:
        return "DEC";
      default:
        throw new IllegalArgumentException("Invalid month integer.");
    }
  }

  @Override
  public String getStringMonth(int num) throws IllegalArgumentException {
    switch (num) {
      case 1:
        return "JAN";
      case 2:
        return "FEB";
      case 3:
        return "MAR";
      case 4:
        return "APR";
      case 5:
        return "MAY";
      case 6:
        return "JUN";
      case 7:
        return "JUL";
      case 8:
        return "AUG";
      case 9:
        return "SEP";
      case 10:
        return "OCT";
      case 11:
        return "NOV";
      case 12:
        return "DEC";
      default:
        throw new IllegalArgumentException("Invalid month integer.");
    }
  }
}