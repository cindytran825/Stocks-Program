package model;

public interface DataChart {

  /**
   * this takes the startDate and endDate and uses the compareTo method
   * from the MyDateWithImpl class to get the difference between the two dates.
   * Based on the difference, it decides the timespan and calls the method getBarChart
   * in the DataChart class to create the bar chart.
   *
   * @param name the name of the portfolio that the user input
   * @param startDate the starting date that the user input to get the value
   * @param endDate the ending date that the user input to get the value
   */
  String getChart(String name, String startDate, String endDate, Analyzable analyzable);

  /**
   *
   *
   * 
   * 
   * 
   * 
   * 
   * gets the ti
   * @param result
   * @return
   */
  String decideTimespan(int result);
}
