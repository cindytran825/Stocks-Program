package model;

/**
 * This is a class that is a representation of any classes / objects that are able to have its
 * values evaluated by data charts.
 */
public interface Analyzable {

  /**
   * Gets the value at a certain designated time (row) to represent the change over time.
   *
   * @param date           date to evaluate value of analyzable
   * @param stockDirectory directory where the file that is being evaluated is kept
   * @return the value of the analyzable at a certain time
   */
  double getValue(String date, String stockDirectory);
}
