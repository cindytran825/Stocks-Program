package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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


  /**
   * this checks if the file exists.
   * @param path name of the csv file (excl
   * @return a boolean.
   */
  @Override
  public boolean checkIfFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }

  /**
   * check if the date is in correct format.
   * @param date string inputted.
   * @return boolean.
   */
  @Override
  public boolean checkIfDate(String date) {
    try {
      String[] dateInfo = date.split("-");
      // just initializing, no need to store
      new MyDateWithImpl(
              Integer.parseInt(dateInfo[0]),
              Integer.parseInt(dateInfo[1]),
              Integer.parseInt(dateInfo[2]));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * checks if the number can be a long.
   * @param n string inputted.
   * @return boolean.
   */
  @Override
  public boolean checkIfNumber(String n) {
    try {
      Long.parseLong(n);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * allows the user to upload the file.
   *
   * @param ticker gets the ticker user input.
   * @param path   a path of the file.
   * @return a string.
   */
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

  /**
   * generates the stock.
   * @param ticker the company user inputs.
   */
  @Override
  public void generateStock(String ticker) {
    if (!checkIfFileExist(stockFolderPath + "/" + ticker + ".csv")) {
      api.addStock(ticker);
    }
    Stock stock = new Stock(ticker, stockFolderPath);
  }


  /**
   * this creates a new portfolio and is called in the controller.
   *
   * @param name is the name of the portfolio the user inputs.
   */
  @Override
  public void createPortfolio(String name) {
    Portfolio newPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, false);
  }


  /**
   * called when the user wants to anything to an existing portfolio.
   * @param name of the portfolio.
   * @param ticker of the company.
   * @param shares the user inputs.
   */
  @Override
  public void managePortfolio(String name, String ticker, int shares) {
    Portfolio existingPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, true);
    existingPortfolio.editPortfolio(ticker, shares);
  }

  /**
   * called when the user asks for the value in their portfolio.
   * it takes the date that they input and the portfolio location(name).
   *
   * @param name the name that the user input for their portfolio.
   * @param date the date that the user inputs.
   * @return a double representing the value.
   */
  @Override
  public String evaluatePortfolio(String name, String date) {
    Portfolio existingPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, true);
    return String.valueOf(existingPortfolio.getValue(date, stockFolderPath));
  }

  /**
   * it returns how much gain and how much loss.
   * negative if there is loss and positive double for gain.
   *
   * @param ticker    the ticker that the user input.
   * @param startDate the startDate.
   * @param endDate   the end date.
   * @return a double representing the gain/loss.
   */
  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.calculateNetGain(startDate, endDate));
  }

  /**
   * this gets the x-day moving average of a stock.
   * and calls the getMovingAverage method in the Stock class.
   *
   * @param ticker    is the ticker of the company user inputs.
   * @param startDate the starting date user inputs.
   * @param lastX     integer user inputs.
   * @return a string that calls method.
   */
  @Override
  public String movingAverage(String ticker, String startDate, int lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.getMovingAverage(startDate, lastX));
  }

  /**
   * This gets the x-day crossovers for a specified stock over a specified date range.
   *
   * @param ticker    the ticker user inputs.
   * @param startDate start date the user inputs.
   * @param lastX     the integer user inputs.
   * @return the days as a string.
   */
  @Override
  public String getCrossoverDays(String ticker, String startDate, int lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    List<String> days = stock.getCrossOver(startDate, lastX);
    StringBuilder sb = new StringBuilder();
    for (String day : days) {
      sb.append(day + "\n");
    }
    return sb.toString();
  }

  /**
   * gets the name of the portfolios in the files.
   * that were created.
   *
   * @return a string of the names of the portfolios.
   */
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

  /**
   * gets the portfolio used in the controller.
   *
   * @param name the user inputs for the portfolio.
   * @return a string.
   */
  @Override
  public String getPortfolio(String name) {
    Portfolio existingPortfolio = new PortfolioWithImpl(name, portfolioFolderPath, true);
    return existingPortfolio.toString();
  }

  /**
   * the names of the stocks.
   *
   * @return a string of the name of stocks.
   */
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


}
