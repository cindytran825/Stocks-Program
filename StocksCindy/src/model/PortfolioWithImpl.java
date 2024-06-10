package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
  private final String stockDirectory;
  private final MutableDataFrame log;

  /**
   * it also creates a new portfolio as a csv file when the user creates one.
   * The portfolio file must be in a proper format of [timestamp,action,ticker,share,close].
   * Timestamp refers to the date of transaction in the format of YYYY-MM-DD. Action refers
   * to the action performed such as 'buy' or 'sell'. Ticker refers to the stock that is
   * involved in the transaction, represented by their ticker. Share refers to the quantity
   * that is involved in the transaction. Close refers to the price per share on the specified
   * date. It must have these keywords at the top of the file, separated by a comma.
   * The csv file is the log of the portfolio, and portfolio data will be initialized
   * from reading this log csv file.
   *
   * @param portfolioName   name of portfolio.
   * @param storageLocation location of the file.
   * @param loadPrevious    whether the file exists.
   */
  public PortfolioWithImpl(String portfolioName, String storageLocation, boolean loadPrevious) {
    this.portfolioName = portfolioName;
    this.listInventories = new HashMap<>();
    this.reference = storageLocation + "/" + portfolioName + ".csv";
    this.stockDirectory = "StocksCindy/CSVFiles";
    // adding csv file
    // "StocksCindy/UserPortfolio/
    File portFile = new File(reference);
    if (loadPrevious && portFile.exists()) {
      try {
        Scanner scan = new Scanner(portFile);
        while (scan.hasNext()) {
          String line = scan.nextLine();
          String[] parts = line.split(",");
          // if sell
          if (parts[1].equals("sell")) {
            listInventories.put(
                    parts[2],
                    listInventories.get(parts[2]) - Double.parseDouble(parts[3]));
          } else if (parts[1].equals("buy")) {
            listInventories.put(
                    parts[2],
                    Double.parseDouble(parts[3]) + listInventories.get(parts[2]));
          }
        }
      } catch (FileNotFoundException e) {
        // doesn't matter
      }
    } else {
      try {
        FileWriter fw = new FileWriter(portFile);
        fw.write("timestamp,buy,stock,share");
        fw.close();
      } catch (IOException e1) {
        // doesn't matter
      }
    }
    this.log = new MutableDataFrameWithImpl(reference);
  }

  /**
   * turns the list of inventories to a string
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
  @Override // TODO MAKE THIS PRIVATE NOT PUBLIC
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
      List<List<String>> columns = new ArrayList<>();
      // write the column titles
      FileWriter fw = new FileWriter(portFile);
      StringBuilder line = new StringBuilder();
      for (String key : log.getColumnNames()) {
        line.append(key).append(",");
        columns.add()
      }
      line.append("\n");

      // add rows
      int columnSize = log.getColumnSize();
      for (int i = 0; i < columnSize; i++) {
        for (String key : log.getColumnNames()) {
          line.append(log.getColumn)
        }
      }




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

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public void buyStock(
          String ticker,
          double shares, 
          String date
  ) throws IllegalArgumentException {
    // assuming that the model / controller will check if it's a valid ticker
    // and a valid date (YYYY-MM-DD, doesn't have to be a date in the data)

    // check that the date is valid (in chronological order)
    List<String> dateList = this.log.getColumn("timestamp");
    MyDate latestDate = new MyDateWithImpl(dateList.getLast());
    MyDate inputDate = new MyDateWithImpl(date);
    if (inputDate.compareTo(latestDate) < 0) {
      throw new IllegalArgumentException(
              "Date entered must be chronologically after " + latestDate + ".");
    }
    // check if the date is an available data point
    if (!dateList.contains(inputDate.toString())) {
      throw new IllegalArgumentException("Cannot purchase on a date with no stock data.");
    }
    
    this.editPortfolio(ticker, listInventories.get(ticker) + shares);

    // adding to log
    logActivity(ticker, shares, inputDate, "buy");

    // TODO WRITE TEST
  }

  // TODO MENTION IN DESIGN README THAT THE EDITPORTFOLIO FUNCTION WILL BE MADE PRIVATE
  // TODO ALSO NEED TO CHANGE PORTFOLIO SO THAT IT IS TRACKING / UPDATING THE AMOUNT OF STOCKS CORRECTLY
  @Override
  public void sellStock(
          String ticker,
          double shares,
          String date
  ) throws IllegalArgumentException {
    // assuming that the model / controller will check if it's a valid ticker
    // and a valid date (YYYY-MM-DD format, doesn't have to be a date in the data)

    // check that the date is valid (in chronological order)
    List<String> dateList = this.log.getColumn("timestamp");
    MyDate latestDate = new MyDateWithImpl(dateList.getLast());
    MyDate inputDate = new MyDateWithImpl(date);
    if (inputDate.compareTo(latestDate) < 0) {
      throw new IllegalArgumentException(
              "Date entered must be chronologically after " + latestDate + ".");
    }
    // check if the date is an available data point
    if (!dateList.contains(inputDate.toString())) {
      throw new IllegalArgumentException("Cannot purchase on a date with no stock data.");
    }
    
    // can sell
    if (listInventories.containsKey(ticker) && listInventories.get(ticker) >= shares) {

      this.editPortfolio(ticker, listInventories.get(ticker) - shares);

      // adding to log
      logActivity(ticker, shares, inputDate, "sell");
    } else {
      throw new IllegalArgumentException("Not enough shares to sell specified stock.");
    }
    // TODO WRITE TEST
    // TODO HAVE IT TRACK THE COST FOR THAT DATE TOO
  }

  /**
   * Registers the activity into the CSV file log and the DataFrame.
   *
   * @param ticker stock ticker
   * @param shares shares involved in transaction
   * @param inputDate date of transaction
   * @param activity purpose of transaction (buy/sell)
   */
  private void logActivity(String ticker, double shares, MyDate inputDate, String activity) {
    Map<String, List<String>> newTransaction = new HashMap<>();
    newTransaction.put("timestamp", List.of(inputDate.toString()));
    newTransaction.put("action", List.of(activity));
    newTransaction.put("ticker", List.of(ticker));
    newTransaction.put("share", List.of(String.valueOf(shares)));

    // get closing price
    Stock tempStock = new Stock(ticker, stockDirectory);
    double value = tempStock.getMovingAverage(inputDate.toString(), 1);
    newTransaction.put("close", List.of(String.valueOf(value)));

    // add to log and also write to file
    this.log.addLastRow(newTransaction);
  }

}
