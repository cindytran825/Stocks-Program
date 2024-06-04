package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StockDataFrame {

  private final List<String> columnKeys;
  private final List<List<String>> columnItems;

  private final Map<String, List<String>> map;

  public StockDataFrame(String csv) {
    //this stuff should be in dataframe super class, but this is for testing
    this.columnKeys = new ArrayList<>();
    this.columnItems = new ArrayList<>();
    this.map = new HashMap<>();

    try {
      File csvFile = new File(csv);
      Scanner csvRead = new Scanner(csvFile);
      String line = csvRead.nextLine();
      String[] columns = line.split(",");
      this.columnKeys.addAll(Arrays.asList(columns));

      // matches the size of the items with the keys
      for (int i = 0; i < columns.length; i++) {
        columnItems.add(new ArrayList<>());
      }

      List<List<String>> tempColumnItems = new ArrayList<>(columnItems);
      while (csvRead.hasNext()) {
        line = csvRead.nextLine();
        columns = line.split(",");

        // assign each different column element to their respective columns
        for (int i = 0; i < columnKeys.size(); i++) {
          tempColumnItems.get(i).add(columns[i]);
        }
      }
      columnItems.addAll(tempColumnItems);

      // merge all this information into a hashmap
      for (int i = 0; i < columns.length; i++) {
        map.put(columnKeys.get(i), columnItems.get(i));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Set<String> getColumnNames() {
    return map.keySet();
  }

  private List<String> deepCopy(List<String> original) {
    List<String> copy = new ArrayList<>(original.size());
    for (String item : copy) {
      copy.add(item);
    }
    return copy;
  }

  public List<String> getColumn(String key) {
    return deepCopy(map.get(key));
  }

}
