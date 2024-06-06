package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * the StocksModel is where all the implementation is at.
 * methods are called in the controller.
 */
public class StocksModel implements Model {
  private final String stockFolderPath;
  private final String portfolioFolderPath;

  /**
   *
   */
  public StocksModel() {
    this.stockFolderPath = "StocksCindy/CSVFiles";
    this.portfolioFolderPath = "StocksCindy/UserPortfolio";
  }

//  @Override
//  public void execute(s)


  /**
   * this checks if the file exists in the directory.
   * @param ticker is the ticker that the user input.
   * @return a boolean.
   */
  private boolean checkIfFileExist(String ticker) {
      String path = stockFolderPath + "/" + ticker + ".csv";
      File file = new File(path);
      return file.exists();
  }

  /**
   *
   * @param api
   * @param ticker
   * @return
   */
  public Stock generateStock(StockApi api, String ticker) {
    if (!checkIfFileExist(ticker)) {
      api.addStock(ticker);
    }
    Stock stock = new Stock(ticker, stockFolderPath);
    return stock;
  }

  //===========================================================================

  /**
   * this creates a new portfolio and is called in the controller.
   * @param name is the name of the portfolio the user inputs.
   */
  public void createPortfolio(String name) {
    Portfolio newPortfolio = new Portfolio(name, portfolioFolderPath, false);
  }

  /**
   *
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
   * @param ticker the ticker that the user input.
   * @param startDate the startDate.
   * @param endDate the end date.
   * @return a double representing the gain/loss.
   */
  public String evaluateStock(String ticker, String startDate, String endDate) {
    Stock stock = new Stock(ticker, stockFolderPath);
    return String.valueOf(stock.calculateNetGain(startDate, endDate));
  }

  /**
   *
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
   *
   */

  //  /**
//   * this creates the portfolio.
//   * it puts it in the "UserPortfolio" directory.
//   *
//   * @param name            is the name of the portfolio that the user inputs.
//   * @param listInventories this is the list of the current inventories in the portfolio.
//   */
//  public void createNewPortfolio(String name, Map<String, Integer> listInventories) {
//    String directory = "StocksCindy/UserPortfolio/";
//    try {
//      File fw = new File(directory + name + ".csv");
//      FileWriter writer = new FileWriter(fw);
//      for (String key : listInventories.keySet()) {
//        writer.write(key + "," + listInventories.get(key) + "\n");
//      }
//      writer.close();
//    } catch (IOException e) {
//      // doesn't matter
//    }
//  }
//
//  /**
//   * this returns the names of the existing portfolios.
//   * this is called in the controller for the 2nd and 3rd bullet of menu.
//   */
//  public List<String> getNamesFile() {
//    String directory = "StocksCindy/UserPortfolio/";
//    File direct = new File(directory);
//    File[] files = direct.listFiles();
//    List<String> names = new ArrayList<>();
//    for (File file : files) {
//      names.add(file.getName());
//    }
//    return names;
//  }
//
//  /**
//   * when the user wants to add anything to an existing portfolio, this is called.
//   * It takes in the array in the existing file and creates a new file.
//   * with the data from the original data and the new data that the user is inputting.
//   *
//   * @param inputPrt        the name of the file that the user inputs.
//   *                        this is called in the controller.
//   * @param listInventories the arraylist of the new data to be added to the new file.
//   */
//  public void editExistingPortfolio(String inputPrt, List<Map<String, Integer>> listInventories) {
//    String temp = "StocksCindy/UserPortfolio/temp.csv";
//    String actualFile = "StocksCindy/UserPortfolio/" + inputPrt + ".csv";
//    File oldFile = new File(actualFile);
//    File newFile = new File(temp);
//    try {
//      FileWriter fw = new FileWriter(temp, true);
//      BufferedWriter bw = new BufferedWriter(fw);
//      PrintWriter pw = new PrintWriter(bw);
//      scanner = new Scanner(new File(actualFile));
//
//      String line;
//      while (scanner.hasNext()) {
//        line = scanner.nextLine();
//        fw.write(listInventories.toString());
//        fw.write(line); //this adds everything so far
//      }
//
//      scanner.close();
//      pw.flush();
//      pw.close();
//      oldFile.delete();
//      File dump = new File(actualFile);
//      newFile.renameTo(dump);
//
//      //if the name of the file cannot be found, it throws message.
//    } catch (Exception e) {
//      System.out.println("File cannot be found.");
//      newFile.delete();
//    }
//
//  }

}
