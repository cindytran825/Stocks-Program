package model;

import java.util.List;

/**
 * visual representation of any chart that can display the data of any Analyzable data classes.
 */
public interface DataChart {

  /**
   * this decides the timespan that the chart will have.
   *
   * @param result is the difference between the two dates.
   *               that was given as the range.
   * @return a strng that determines the type of timespan.
   */
  String decideTimespan(int result);

  /**
   * gets the value of each date given.
   * it takes the string from the decidetimespan method and.
   * makes sure that the values that were caluclating is on the correct date.
   *
   * @param firstDate   is the first date of the timespan the user input.
   * @param secondDate  is the second date of the time span the user input.
   * @param result      is the difference between the first and second date.
   * @param decide      the string that decides the timespan.
   * @param listOfDates is the list of dates that we get the vlaue from.
   * @return the list of values that has been caluclated and later displayed in the chart.
   */
  List<Double> timeValue(
          MyDate firstDate,
          MyDate secondDate,
          int result,
          String decide,
          List<String> listOfDates);

  /**
   * this takes the startDate and endDate and uses the compareTo method
   * from the MyDateWithImpl class to get the difference between the two dates.
   * Based on the difference, it decides the timespan and calls the method getBarChart
   * in the DataChart class to create the chart.
   *
   * @param name      the name of the portfolio that the user input
   * @param startDate the starting date that the user input to get the value
   * @param endDate   the ending date that the user input to get the value
   */
  String getChart(String name, String startDate, String endDate, Analyzable analyzable);
}
