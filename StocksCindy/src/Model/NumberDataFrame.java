package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumberDataFrame {

  private final List<String> columnKeys;
  private final List<List<Double>> columnData;

  public NumberDataFrame(String csv) {
    columnKeys = new ArrayList<>();
    columnData = new ArrayList<>();
    try {
      File csvFile = new File(csv);
      Scanner csvRead = new Scanner(csvFile);
      String line = csvRead.nextLine();

      columnKeys.addAll(Arrays.asList(line.split(",")));
      while (csvRead.hasNext()) {

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }




}
