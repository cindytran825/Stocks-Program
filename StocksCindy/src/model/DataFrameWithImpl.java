package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This is a simple, and not a complete implementation of DataFrame. It can perform fundamental.
 * tasks such as accessing columns and storing information, but it does not currently support.
 * table mutations.
 */
public class DataFrameWithImpl implements DataFrame {
  protected final Map<String, List<String>> map;
  protected final List<String> keyOrder;

  /**
   * constructor that reads the information from the csv file and registers it to a map.
   * with the keys being decided by the top row, and all the following elements are added into.
   * their columns.
   *
   * @param csv csv file reference path.
   */

  public DataFrameWithImpl(String csv) {
    this.map = new HashMap<>();
    this.keyOrder = new ArrayList<>();
    try {
      File csvFile = new File(csv);
      Scanner csvRead = new Scanner(csvFile);
      String line = csvRead.nextLine();
      String[] columnNames = line.split(",");

      // creates columns
      for (String name : columnNames) {
        map.put(name, new ArrayList<>());
        keyOrder.add(name);
      }

      String[] columnElements;
      while (csvRead.hasNext()) {
        line = csvRead.nextLine();
        columnElements = line.split(",");

        // assign each different column element to their respective columns
        for (int i = 0; i < columnNames.length; i++) {
          map.get(columnNames[i]).add(columnElements[i]);
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Set<String> getColumnNames() {
    return map.keySet();
  }

  /**
   * makes a copy of the list.
   *
   * @param original original strings in list.
   * @return a new arraylist.
   */
  private List<String> deepCopy(List<String> original) {
    List<String> copy = new ArrayList<>(original.size());
    copy.addAll(original);
    return copy;
  }

  @Override
  public List<String> getColumn(String key) {
    return deepCopy(map.get(key));
  }

  @Override
  public int getColumnSize() {
    int size = 0;
    for (String key : map.keySet()) {
      size = map.get(key).size();
    }
    return size;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    List<List<String>> columns = new ArrayList<>();
    for (String key : keyOrder) {
      string.append(key).append(",");
      columns.add(map.get(key));
    }
    string.append("\n");
    for (int i = 0; i < getColumnSize(); i++) {
      for (List<String> column : columns) {
        string.append(column.get(i)).append(",");
      }
      string.append("\n");
    }
    return string.toString().strip();
  }
}
