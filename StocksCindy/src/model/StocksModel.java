package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * the StocksModel is where all the implementation is at. It holds all the methods that
 * are called in the controller.
 */
public class StocksModel implements Model {
  private final String stockFolderPath;
  private final String portfolioFolderPath;
  private final StockApi api;

  /**
   * empty constructor that just has the paths of the folders.
   * to locate them and api to access the data.
   */
  public StocksModel() {
    this.stockFolderPath = "StocksCindy/CSVFiles";
    this.portfolioFolderPath = "StocksCindy/UserPortfolio";
    this.api = new AlphaVantageApi(); // whichever api
  }

  @Override
  public boolean checkIfFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }

  @Override
  public boolean checkIfDate(String date) {
    try {
      String[] dateInfo = date.split("-");
      // just initializing, no need to store
      new MyDateWithImpl(
              Integer.parseInt(dateInfo[2]),
              Integer.parseInt(dateInfo[1]),
              Integer.parseInt(dateInfo[0]));
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  @Override
  public boolean checkIfNumber(String n) {
    try {
      Double.parseDouble(n);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public boolean checkIfWholeNumber(String n) {
    try {
      double num = Double.parseDouble(n);
      return num % 1 == 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private String getLatestPortfolioTransactionDate(Portfolio portfolio) {
    String log = portfolio.getLog();
    String[] lines = log.split("\n");
    if (lines.length <= 1) {
      return "";
    }
    String[] lastLine = lines[lines.length - 1].split(",");
    return lastLine[lastLine.length - 1];
  }

  @Override
  public boolean checkIfChronologicalPortfolio(String name, String inputDate) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name, portfolioFolderPath, stockFolderPath, true);
    String latest = this.getLatestPortfolioTransactionDate(existingPortfolio);
    if (latest.isEmpty()) {
      return true;
    }
    MyDate input = new MyDateWithImpl(inputDate);
    MyDate last = new MyDateWithImpl(latest);
    return input.compareTo(last) >= 0;
  }

  @Override
  public boolean checkIfStockDataExist(String ticker, String inputDate) {
    Stock stock = new Stock(ticker, stockFolderPath);
    List<String> timestamps = stock.getTimestamp();
    return timestamps.contains(inputDate);
  }

  @Override
  public boolean checkIfPortfolioChronologicalAndDataExist(
          String portfolioName,
          String inputDate
  ) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            portfolioName, portfolioFolderPath, stockFolderPath, true);
    if (!checkIfChronologicalPortfolio(portfolioName, inputDate)) {
      return false;
    }
    Map<String, Double> inventory = existingPortfolio.getListInventories();
    Set<String> stockTickers = inventory.keySet();
    for (String ticker : stockTickers) {
      if (!checkIfStockDataExist(ticker, inputDate)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String uploadStock(String ticker, String path) {
    File original = new File(path);
    File newFile = new File(stockFolderPath + "/" + ticker + ".csv");
    try {
      Scanner scan = new Scanner(original);
      FileWriter fw = new FileWriter(newFile);
      while (scan.hasNext()) {
        String line = scan.nextLine();
        fw.write(line);
      }
      fw.close();
      return "File was uploaded successfully.";
    } catch (FileNotFoundException e) {
      return "File was not found.";
    } catch (IOException e) {
      return "Something went wrong when you were trying to upload the file.";
    }
  }

  @Override
  public void generateStock(String ticker) {
    if (!checkIfFileExist(stockFolderPath + "/" + ticker + ".csv")) {
      api.addStock(ticker);
    }
    Stock stock = new Stock(ticker, stockFolderPath);
  }

  @Override
  public void createPortfolio(String name) {
    Portfolio newPortfolio = new PortfolioWithImpl(
            name, portfolioFolderPath, stockFolderPath, false);
  }

  @Override
  public void managePortfolio(String name, String ticker, double shares) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name, portfolioFolderPath, stockFolderPath, true);
    // existingPortfolio.editPortfolio(ticker, shares);
  }


  @Override
  public String evaluatePortfolio(String name, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name, portfolioFolderPath, stockFolderPath, true);
    return String.valueOf(existingPortfolio.getValue(date, portfolioFolderPath));
  }

  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.calculateNetGain(startDate, endDate));
  }

  @Override
  public String movingAverage(String ticker, String startDate, double lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.getMovingAverage(startDate, lastX));
  }

  @Override
  public String barChartPortfolioInitialized(
          String name,
          String firstDate,
          String lastDate

  ) {
    Analyzable existingPortfolio = new PortfolioWithImpl(
            name, portfolioFolderPath, stockFolderPath, true);
    BarChartWithImpl data = new BarChartWithImpl(name,
            firstDate, lastDate, existingPortfolio, stockFolderPath);
    return data.getBarChart(stockFolderPath, firstDate, lastDate, existingPortfolio);
  }

  @Override
  public String barChartStockInitialized(
          String name, String firstDate, String lastDate, String ticker) {
    Analyzable stock = new Stock(ticker, stockFolderPath);
    BarChartWithImpl data = new BarChartWithImpl(name, firstDate, lastDate, stock, stockFolderPath);
    return data.getBarChart(stockFolderPath, firstDate, lastDate, stock);
  }

  @Override
  public boolean checkSharesNotEnough(String portName, String ticker, double shares) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            portName,
            portfolioFolderPath,
            stockFolderPath,
            true);
    Map<String, Double> inventory = existingPortfolio.getListInventories();
    return inventory.get(ticker) < shares;
  }


  @Override
  public String getCrossoverDays(String ticker, String startDate, double lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    List<String> days = stock.getCrossOver(startDate, lastX);
    StringBuilder sb = new StringBuilder();
    for (String day : days) {
      sb.append(day + "\n");
    }
    return sb.toString();
  }

  @Override
  public String getPortfolioNames() {
    File direct = new File(portfolioFolderPath);
    File[] files = direct.listFiles();
    StringBuilder sb = new StringBuilder();
    for (File file : files) {
      String fileName = file.getName().replace(".csv", "");
      sb.append(fileName + "\n");
    }
    return sb.toString();
  }

  @Override
  public Map<String, Double> getPortfolioStocks(String name) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name,
            portfolioFolderPath,
            stockFolderPath,
            true);
    return existingPortfolio.getListInventories();
  }

  @Override
  public String getPortfolioDistribution(String name, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name,
            portfolioFolderPath,
            stockFolderPath,
            true);
    StringBuilder string = new StringBuilder();
    Map<String, Double> composition = existingPortfolio.getDistribution(date);
    for (String key : composition.keySet()) {
      string.append(String.format("%s : %.4f\n", key, composition.get(key)));
    }
    return string.toString().strip();
  }

  @Override
  public String getPortfolioComposition(String name, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name,
            portfolioFolderPath,
            stockFolderPath,
            true);
    StringBuilder string = new StringBuilder();
    Map<String, Double> composition = existingPortfolio.getComposition(date);
    for (String key : composition.keySet()) {
      string.append(String.format("%s : %.4f\n", key, composition.get(key)));
    }
    return string.toString().strip();
  }

  @Override
  public void balance(String date, String name, Map<String, Double> percentages) {
    Portfolio existingPortfolio = new PortfolioWithImpl(
            name,
            portfolioFolderPath,
            stockFolderPath,
            true);
    existingPortfolio.rebalance(percentages, date);
  }

  @Override
  public String getStockNames() {
    File direct = new File(stockFolderPath);
    File[] files = direct.listFiles();
    StringBuilder sb = new StringBuilder();
    for (File file : files) {
      String fileName = file.getName().replace(".csv", "");
      sb.append(fileName + "\n");
    }
    return sb.toString();
  }

  @Override
  public void buyStock(String name, String ticker, String share, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, stockFolderPath,
            true);
    existingPortfolio.buyStock(ticker, Double.parseDouble(share), date);
  }

  @Override
  public void sellStock(String name, String ticker, String share, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, stockFolderPath,
            true);
    existingPortfolio.sellStock(ticker, Double.parseDouble(share), date);
  }


}
