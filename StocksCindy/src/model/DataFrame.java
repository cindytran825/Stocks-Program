package model;

import java.util.List;
import java.util.Set;

/**
 * This data class represents a data frame (similar to python's pandas dataframe). It stores
 * information from given csv files and separate the csv into their corresponding columns with
 * the column title being decided by the top line. Every line must have the same amount of items
 * for it to be stored in the data frame.
 */
public interface DataFrame {

  /**
   * get a set with the name of each column.
   *
   * @return a set of column names
   */
  Set<String> getColumnNames();

  /**
   * This gets all elements in a specified column.
   *
   * @param key the column name
   * @return a list of column elements.
   */
  List<String> getColumn(String key);

  /**
   * This gets the length of the column (not including the column names).
   *
   * @return size of column
   */
  int getColumnSize();
}
