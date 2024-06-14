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
   * this creates the string of the bar chart that is going
   * to display in the view.
   *
   * @return a string that represents the bar chart.
   */
  String getBarChart(
          MyDate firstDate,
          String decide,
          List<String> listOfDates,
          List<Double> listOfValues);
}
