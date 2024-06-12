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

  // TODO ADD CLOSE OR NOT? also change order to make it alphabetical

  /**
   * it also creates a new portfolio as a csv file when the user creates one.
   * The portfolio file must be in a proper format of [action,stock,share,timestamp].
   * Action refers to the action performed such as 'buy' or 'sell'. Stock refers to the stock
   * that is involved in the transaction, represented by their ticker. Share refers to the quantity
   * that is involved in the transaction. Timestamp refers to the date of transaction in the format
   * of YYYY-MM-DD. The file must have these keywords at the top of the file, separated by a comma.
   * The csv file is the log of the portfolio, and portfolio data will be initialized
   * from reading this log csv file.
   *
   * @param portfolioName   name of portfolio.
   * @param storageLocation location of the file.
   * @param loadPrevious    whether the file exists.
   */
  public PortfolioWithImpl(
          String portfolioName,
          String storageLocation,
          String stockDirectory,
          boolean loadPrevious) {
    this.portfolioName = portfolioName;
    this.listInventories = new HashMap<>();
    this.reference = storageLocation + "/" + portfolioName + ".csv";
    this.stockDirectory = stockDirectory;
    // adding csv file
    // "StocksCindy/UserPortfolio/
    File portFile = new File(reference);
    if (loadPrevious && portFile.exists()) {
      try {
        Scanner scan = new Scanner(portFile);
        while (scan.hasNext()) {
          String line = scan.nextLine();
          String[] parts = line.split(",");
          double oldValue = listInventories.containsKey(parts[2])
                  ? listInventories.get(parts[2]) : 0;
          // if sell
          if (parts[0].equals("sell")) {
            listInventories.put(
                    parts[2],
                    -Double.parseDouble(parts[1]) + oldValue);
          } else if (parts[0].equals("buy")) {
            listInventories.put(
                    parts[2],
                    Double.parseDouble(parts[1]) + oldValue);
          }
        }
      } catch (FileNotFoundException e) {
        // doesn't matter
      }
    } else {
      try {
        FileWriter fw = new FileWriter(portFile);
        fw.write("action,share,stock,timestamp");
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


  private Map<String, Double> editPortfolio(
          String companyName,
          double share
  ) throws IllegalArgumentException {
    if (share < 0) {
      throw new IllegalArgumentException();
    }
    // will eventually implement checker for budget and volume to ensure shares don't exceed
    // once buying gets implemented
    listInventories.put(companyName, share);
    return deepCopy(listInventories);
  }

//  @Override
//  public double getValue(String date, String pathToStock) {
//    double totalValue = 0.0;
//    Stock stock;
//    List<Double> closePrices;
//    double closePrice;
//    int dateIndex;
//    for (String ticker : listInventories.keySet()) {
//      stock = new Stock(ticker, pathToStock);
//      closePrices = stock.getClose();
//      dateIndex = stock.getClosestDateIndex(date, false);
//      closePrice = closePrices.get(dateIndex);
//      totalValue += closePrice * listInventories.get(ticker);
//    }
//    return totalValue;
//  }


  // this had to change previous implementation in the comment above
  @Override
  public double getValue(String date, String pathToStock) {
    double totalValue = 0.0;
    Stock stock;
    List<Double> closePrices;
    double closePrice;
    int dateIndex;
    Map<String, Double> composition = getComposition(date);
    for (String ticker : composition.keySet()) {
      stock = new Stock(ticker, pathToStock);
      closePrices = stock.getClose();
      dateIndex = stock.getClosestDateIndex(date, false);
      closePrice = closePrices.get(dateIndex);
      totalValue += closePrice * composition.get(ticker);
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
    checkDateValidity(date, ticker);
    MyDate inputDate = new MyDateWithImpl(date);

    double originalPrice = listInventories.containsKey(ticker) ? listInventories.get(ticker) : 0;
    double rounded = Double.parseDouble(String.format("%.04f", originalPrice + shares));
    listInventories.put(ticker, rounded);

    // adding to log
    logActivity(ticker, shares, inputDate, "buy");
  }

  private boolean checkDateChronology(
          String lastDate,
          String date,
          List<String> dateList
  ) {

    MyDate latestDate = new MyDateWithImpl(lastDate);
    MyDate inputDate = new MyDateWithImpl(date);
    // check if the date is an available data point
    return dateList.contains(inputDate.toString())
            && inputDate.compareTo(latestDate) >= 0;
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

    // TODO MAKE SURE TO CHECK IN CONTROLLER THATTHEY CAN'T BUY OR SELL FRACTIONAL SHARES
    // TODO CHECK DATE VALIDITY REFACTOR
    // check that the date is valid (in chronological order)
    checkDateValidity(date, ticker);
    MyDate inputDate = new MyDateWithImpl(date);

    // can sell
    if (listInventories.containsKey(ticker) && listInventories.get(ticker) >= shares) {
      double originalPrice = listInventories.containsKey(ticker) ? listInventories.get(ticker) : 0;
      double rounded = Double.parseDouble(String.format("%.04f", originalPrice - shares));
      listInventories.put(ticker, rounded);
      // adding to log
      logActivity(ticker, shares, inputDate, "sell");
    } else {
      throw new IllegalArgumentException("Not enough shares to sell specified stock.");
    }
  }

  /**
   * Registers the activity into the CSV file log and the DataFrame.
   *
   * @param ticker    stock ticker
   * @param shares    shares involved in transaction
   * @param inputDate date of transaction
   * @param activity  purpose of transaction (buy/sell)
   */
  private void logActivity(String ticker, double shares, MyDate inputDate, String activity) {
    Map<String, List<String>> newTransaction = new HashMap<>();
    newTransaction.put("timestamp", List.of(inputDate.toString()));
    newTransaction.put("action", List.of(activity));
    newTransaction.put("stock", List.of(ticker));
    newTransaction.put("share", List.of(String.format("%.4f", shares)));

    // add to log and also write to file
    this.log.addLastRow(newTransaction);

    File portFile = new File(reference);
    try {
      List<List<String>> columns = new ArrayList<>();
      // write the column titles
      FileWriter fw = new FileWriter(portFile);
      StringBuilder line = new StringBuilder();
      for (String key : log.getColumnNames()) {
        line.append(key).append(",");
        columns.add(log.getColumn(key));
      }
      line.append("\n");

      // add rows
      int columnSize = log.getColumnSize();
      for (int i = 0; i < columnSize; i++) {
        for (List<String> column : columns) {
          line.append(column.get(i)).append(",");
        }
        line.append("\n");
      }
      fw.write(line.toString().strip());
      fw.close();
    } catch (IOException e) {
      // doesn't matter
    }
  }


  @Override
  public void rebalance(
          Map<String, Double> percentages,
          String date
  ) throws IllegalArgumentException {

    //Map<String, Double> comp = getComposition(date);

    // checks if the date has information available AND it's chronological
    double totalPercentage = 0.0;
    for (String ticker : listInventories.keySet()) {
      checkDateValidity(date, ticker);
      totalPercentage += percentages.get(ticker);
    }
    if (totalPercentage != 1.0) {
      throw new IllegalArgumentException("Total percentage does not equal to 1.0.");
    }

    // applying the percentages
    double totalValue = getValue(date, stockDirectory);
    double closedPrice;
    double target;
    double currentValue;
    double totalShares;
    double percentage;
    Stock stock;
    for (String ticker : percentages.keySet()) {
      stock = new Stock(ticker, stockDirectory);
      closedPrice = stock.getMovingAverage(date, 1);
      currentValue = listInventories.get(ticker) * closedPrice;
      percentage = percentages.get(ticker);
      target = totalValue * percentage;
      // buy more to reach target
      if (target > currentValue) {
        totalShares = (target - currentValue) / closedPrice;
        totalShares = Double.parseDouble(String.format("%.4f", totalShares));
        buyStock(ticker, totalShares, date);
      } else if (target < currentValue) {
        // sell more to reach target
        totalShares = (currentValue - target) / closedPrice;
        totalShares = Double.parseDouble(String.format("%.4f", totalShares));
        sellStock(ticker, totalShares, date);
      } else {
        continue;
      }
    }

  }

  private void checkDateValidity(String date, String ticker) {
    Stock stock = new Stock(ticker, stockDirectory);
    List<String> dateList = stock.getTimestamp();
    List<String> transactionDates = this.log.getColumn("timestamp");
    String latestTransactionDate =
            !transactionDates.isEmpty() ? transactionDates.getLast() : "0000-01-01";
    if (!checkDateChronology(latestTransactionDate, date, dateList)) {
      throw new IllegalArgumentException("Invalid date.");
    }
  }


  @Override
  public Map<String, Double> getComposition(String date) throws IllegalStateException {
    List<String> actionLog = log.getColumn("action");
    List<String> shareLog = log.getColumn("share");
    List<String> stockLog = log.getColumn("stock");
    List<String> dateLog = log.getColumn("timestamp");

    Map<String, Double> inventory = new HashMap<>();
    MyDate inputDate = new MyDateWithImpl(date);
    MyDate logDate;

    int columnSize = log.getColumnSize();
    double shares;
    String stock;
    String action;
    if (columnSize != 0) {
      for (int i = 0; i < columnSize; i++) {
        logDate = new MyDateWithImpl(dateLog.get(i));

        // if the date in the log is past the given date
        if (logDate.compareTo(inputDate) <= 0) {
          stock = stockLog.get(i);
          action = actionLog.get(i);

          // evaluating action
          switch (action) {
            case "buy":
              shares = inventory.containsKey(stock)
                      ? inventory.get(stock) + Double.parseDouble(shareLog.get(i))
                      : Double.parseDouble(shareLog.get(i));
              break;
            case "sell":
              shares = inventory.containsKey(stock)
                      ? inventory.get(stock) - Double.parseDouble(shareLog.get(i))
                      : Double.parseDouble(shareLog.get(i));
              break;
            default:
              throw new IllegalStateException("Unknown action.");
          }
          inventory.put(stock, shares);
        }
      }
    }
    return inventory;
  }
}
