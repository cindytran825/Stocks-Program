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
      String[] columnNames = line.split(",");

      // creates columns
      for (String name : columnNames) {
        map.put(name, new ArrayList<>());
      }

      String[] columnElements;
      while (csvRead.hasNext()) {
        line = csvRead.nextLine();
        columnElements = line.split(",");
        //System.out.println(columnElements[0] + columnElements[1]);

        // assign each different column element to their respective columns
        for (int i = 0; i < columnNames.length; i++) {
          map.get(columnNames[i]).add(columnElements[i]);
        }
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
    for (String item : original) {
      copy.add(item);
    }
    return copy;
  }

  public List<String> getColumn(String key) {
    return deepCopy(map.get(key));
  }

}
