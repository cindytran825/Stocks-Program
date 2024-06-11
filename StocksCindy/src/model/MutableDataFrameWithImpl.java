package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a mutable data frame. It can perform fundamental tasks such as accessing columns and
 * storing information like DataFrameWithImpl, but it does support simple table mutations
 * like adding columns and rows (only to the last index).
 */
public class MutableDataFrameWithImpl extends DataFrameWithImpl implements MutableDataFrame {

  /**
   * constructor that reads the information from the csv file and registers it to a map.
   * with the keys being decided by the top row, and all the following elements are added into.
   * their columns.
   *
   * @param csv csv file reference path.
   */
  public MutableDataFrameWithImpl(String csv) {
    super(csv);
  }

  @Override
  public void addLastRow(
          Map<String, List<String>> newRowElements
  ) throws IllegalArgumentException {
    Set<String> inputKey = newRowElements.keySet();
    Set<String> mapKey = map.keySet();
    if (!(inputKey.containsAll(mapKey) && mapKey.containsAll(inputKey))) {
      throw new IllegalArgumentException("Input is not formatted correctly.");
    }
    for (String key : inputKey) {
      map.get(key).addAll(newRowElements.get(key));
    }
  }

  @Override
  public void addNewColumn(
          String columnName,
          List<String> columnElements
  ) throws IllegalArgumentException {
    for (String key : map.keySet()) {
      if (columnElements.size() != map.get(key).size()) {
        throw new IllegalArgumentException("Column size does not match.");
      }
    }
    map.put(columnName, columnElements);
  }

  @Override
  public int getColumnSize() {
    int size = 0;
    for (String key : map.keySet()) {
      size = map.get(key).size();
    }
    return size;
  }

}
