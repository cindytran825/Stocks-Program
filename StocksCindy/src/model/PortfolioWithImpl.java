package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This represents a stock portfolio. A user can create, edit, and view the portfolio
 */
public class PortfolioWithImpl implements Portfolio {

  private final String portfolioName;
  private final Map<String, Double> listInventories;
  private final String reference;

  /**
   * it also creates a new porfolio as a csv file when the user creates one.
   *
   * @param portfolioName   name of portfolio.
   * @param storageLocation location of the file.
   * @param loadPrevious    whether or not the file exists.
   */
  public PortfolioWithImpl(String portfolioName, String storageLocation, boolean loadPrevious) {
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
          listInventories.put(parts[0], Double.parseDouble(parts[1]));
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
        //
      }
    }
  }

  /**
   * turns the list of inventories to a string.
   * also reformatting it in the file.
   *
   * @return a string of the inventories.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String key : listInventories.keySet()) {
      sb.append(String.format("%s : %f\n", key, listInventories.get(key)));
    }
    return sb.toString();
  }


  private Map<String, Double> deepCopy(Map<String, Double> original) {
    Map<String, Double> copy = new HashMap<>(original.size());
    for (String key : original.keySet()) {
      copy.put(key, original.get(key));
    }
    return copy;
  }

  /**
   * gets the list of tickers+shares that.
   * user inputs to the portfolio.
   *
   * @return a map of the port of inventory.
   */
  @Override
  public Map<String, Double> getListInventories() {
    return deepCopy(this.listInventories);
  }


  /**
   * this is used the edit the portfolio.
   * after it is created and the user wants to add.
   * anything in it, it is called.
   *
   * @param companyName the ticker the user inputs.
   * @param share       the share for that ticker.
   * @return a map of the ticker and share.
   * @throws IllegalArgumentException when the share is negative.
   */
  @Override
  public Map<String, Double> editPortfolio(
          String companyName,
          double share
  ) throws IllegalArgumentException {
    if (share < 0) {
      throw new IllegalArgumentException();
    }
    // will eventually implement checker for budget and volume to ensure shares don't exceed
    // once buying gets implemented
    listInventories.put(companyName, share);

    File portFile = new File(reference);
    try {
      FileWriter fw = new FileWriter(portFile);
      for (String key : listInventories.keySet()) {
        fw.write(key + "," + listInventories.get(key) + "\n");
      }
      fw.close();
    } catch (IOException e) {
      // doesn't matter
    }

    return deepCopy(listInventories);
  }

  /**
   * is called when the user wants to get the value of a portfolio.
   *
   * @param date        the date they input.
   * @param pathToStock string of located file.
   * @return a double(value).
   */
  @Override
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

  /**
   * gets the name of the portfolio.
   *
   * @return a string of that name.
   */
  public String getPortfolioName() {
    return portfolioName;
  }
}
