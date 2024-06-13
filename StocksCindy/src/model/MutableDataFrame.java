package model;

import java.util.List;
import java.util.Map;

/**
 * This data class represents a data frame (similar to python's pandas dataframe). It stores
 * information from given csv files and separate the csv into their corresponding columns with
 * the column title being decided by the top line. Every line must have the same amount of items
 * for it to be stored in the data frame. This class is able to handle row and column additions
 * (allow simple mutations like adding rows and columns).
 */
public interface MutableDataFrame extends DataFrame {

  /**
   * adds a row to the end of the DataFrame table.
   *
   * @param newRowElements a map of the row addition with the elements to be added. To add a row,
   *                       the argument must be a map that has the same keys as the original
   *                       dataframe. If an empty value is to be added, the key must still be there
   *                       and assigned in the list with an empty string.
   * @throws IllegalArgumentException the argument did not have all the keys in the column
   *                                  (bad format).
   */
  void addLastRow(Map<String, List<String>> newRowElements) throws IllegalArgumentException;

  /**
   * creates a new column to the dataframe.
   *
   * @param columnName     key of column or name of column
   * @param columnElements a list of elements that will be added in the column
   * @throws IllegalStateException if the column does not have the same length as the number of
   *                               rows in the dataframe (bad format).
   */
  void addNewColumn(
          String columnName,
          List<String> columnElements
  ) throws IllegalArgumentException;
}
