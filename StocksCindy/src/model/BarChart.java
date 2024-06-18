package model;

import java.util.List;

/**
 * A representation of any bar chart.
 */
public interface BarChart extends DataChart {

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
