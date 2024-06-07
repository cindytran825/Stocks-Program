package model;

import java.io.File;
import java.util.List;

/**
 * the StocksModel is where all the implementation is at. It holds all the methods that
 * are called in the controller.
 */
public class StocksModel implements Model {
  private final String stockFolderPath;
  private final String portfolioFolderPath;
  private final StockApi api;

  /**
   *
   */
  public StocksModel() {
    this.stockFolderPath = "StocksCindy/CSVFiles";
    this.portfolioFolderPath = "StocksCindy/UserPortfolio";
    this.api = new AlphaVantageApi(); // whichever api
  }


  /**
   * this checks if the file exists in the directory.
   *
   * @param ticker is the ticker that the user input.
   * @return a boolean.
   */
  private boolean checkIfFileExist(String ticker) {
    String path = stockFolderPath + "/" + ticker + ".csv";
    File file = new File(path);
    return file.exists();
  }

  public Stock generateStock(String ticker) {
    if (!checkIfFileExist(ticker)) {
      api.addStock(ticker);
    }
    Stock stock = new Stock(ticker, stockFolderPath);
    return stock;
  }

  //===========================================================================

  /**
   * this creates a new portfolio and is called in the controller.
   *
   * @param name is the name of the portfolio the user inputs.
   */
  public void createPortfolio(String name) {
    Portfolio newPortfolio = new Portfolio(name, portfolioFolderPath, false);
  }

  /**
   * @param name
   * @param ticker
   * @param shares
   */
  public void managePortfolio(String name, String ticker, int shares) {
    Portfolio existingPortfolio = new Portfolio(name, portfolioFolderPath, true);
    existingPortfolio.editPortfolio(ticker, shares);
  }

  /**
   * called when the user asks for the value in their portfolio.
   * it takes the date that they input and the portfolio location. (name)
   *
   * @param name the name that the user input for their portfolio.
   * @param date the date that the user inputs.
   * @return a double representing the value.
   */
  public String evaluatePortfolio(String name, String date) {
    Portfolio existingPortfolio = new Portfolio(name, portfolioFolderPath, true);
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
  public String evaluateStock(String ticker, String startDate, String endDate) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.calculateNetGain(startDate, endDate));
  }

  /**
   * @param ticker
   * @param startDate
   * @param lastX
   * @return
   */
  public String movingAverage(String ticker, String startDate, int lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.getMovingAverage(startDate, lastX));
  }

  public String getCrossoverDays(String ticker, String startDate, int lastX) {
    Stock stock = new Stock(ticker, stockFolderPath);
    List<String> days = stock.getCrossOver(startDate, lastX);
    StringBuilder sb = new StringBuilder();
    for (String day : days) {
      sb.append(day + "\n");
    }
    return sb.toString();
  }

  public String getPortfolioNames() {
    // initializes portfolios
    File direct = new File(portfolioFolderPath);
    File[] files = direct.listFiles();
    StringBuilder sb = new StringBuilder();
    for (File file : files) {
      String fileName = file.getName().replace(".csv", "");
      sb.append(fileName + "\n");
    }
    return sb.toString();
  }

  public String getPortfolio(String name) {
    Portfolio existingPortfolio = new Portfolio(name, portfolioFolderPath, true);
    return existingPortfolio.toString();
  }

  /**
   * create portfolio
   * manage existing portfolio
   * evaluate existing portfolio
   * view existing portfolio
   * examine gain or loss of stock
   * examine moving average of stock
   * determine which days are crossover days
   */

}
