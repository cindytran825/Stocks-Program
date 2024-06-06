package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This represents a stock portfolio. A user can create, edit, and view the portfolio
 */
public class Portfolio {

  private final String portfolioName;
  private final Map<String, Integer> listInventories;
  private final String reference;


  public Portfolio(String portfolioName, String storageLocation, boolean loadPrevious) {
    this.portfolioName = portfolioName;
    this.listInventories = new HashMap<>();
    this.reference = storageLocation + "/" + portfolioName + ".csv";
    // adding csv file
    // "StocksCindy/UserPortfolio/
    File portFile = new File(reference);
    if (loadPrevious) {
      try {
        Scanner scan = new Scanner(portFile);
        while (scan.hasNext()) {
          String line = scan.nextLine();
          String[] parts = line.split(",");
          listInventories.put(parts[0], Integer.parseInt(parts[1]));
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      try {
        FileWriter fw = new FileWriter(portFile);
        fw.write("");
        fw.close();
      } catch (IOException e1) {
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String key : listInventories.keySet()) {
      sb.append(String.format("%s : %d\n", key, listInventories.get(key)));
    }
    return sb.toString();
  }


  private Map<String, Integer> deepCopy(Map<String, Integer> original) {
    Map<String, Integer> copy = new HashMap<>(original.size());
    for (String key : original.keySet()) {
      copy.put(key, original.get(key));
    }
    return copy;
  }

  public Map<String, Integer> getListInventories() {
    return deepCopy(this.listInventories);
  }

  public Map<String, Integer> editPortfolio(
          String companyName,
          int share
  ) throws IllegalArgumentException {
    if (share < 0) {
      throw new IllegalArgumentException();
    }
    // will eventually implement checker for budget and volume to ensure shares don't exceed
    // once buying gets implemented
    listInventories.put(companyName, share);

    File portFile = new File(reference);
      try {
        Scanner scan = new Scanner(portFile);
        FileWriter fw = new FileWriter(portFile);
        while (scan.hasNext()) {
          String line = scan.nextLine();
          fw.write(line + "\n");
        }
        fw.write(companyName + "," + share + "\n");
        fw.close();
      } catch (IOException e) {
        // doesn't matter
      }

    return deepCopy(listInventories);
  }

  public double getValue(String date, String pathToStock) {
    double totalValue = 0.0;
    Stock stock;
    List<Double> closePrices;
    double closePrice;
    int dateIndex;
    for (String ticker : listInventories.keySet()) {
      stock = new Stock(ticker, pathToStock);
      closePrices = stock.getClose();
      dateIndex = stock.getClosestDateIndex(date, false);
      closePrice = closePrices.get(dateIndex);
      totalValue += closePrice * listInventories.get(ticker);
    }
    return totalValue;
  }

  public String getPortfolioName() {
    return portfolioName;
  }
}
