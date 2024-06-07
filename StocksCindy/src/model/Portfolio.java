package model;

import java.util.Map;

/**
 * interface represents the portfolios that the user makes.
 * it allows them to create and edit them.
 * the methods in portfolio is used to call in the model which is.
 * called in the controller.
 */
public interface Portfolio {
  /**
   * gets the list of tickers+shares that the.
   * user inputs to the portfolio.
   * calls deep copy that copies the map.
   * @return a map of the port of inventory.
   */
  Map<String, Integer> getListInventories();

  /**
   * turns the list of inventories to a string.
   * also reformatting it in the file.
   * @return a string of the inventories.
   */
  public String toString();

  /**
   * this is used the edit the portfolio.
   * after it is created and the user wants to add.
   * anything in it, it is called.
   * @param companyName the ticker the user inputs.
   * @param share the share for that ticker.
   * @return a map of the ticker and share.
   * @throws IllegalArgumentException when the share is negative.
   */
  Map<String, Integer> editPortfolio(
          String companyName,
          int share
  ) throws IllegalArgumentException;

  /**
   * is called when the user wants to get the value of a portfolio.
   * @param date the date they input.
   * @param pathToStock string of located file.
   * @return a double(value).
   */
  double getValue(String date, String pathToStock);

  /**
   * gets the name of the portfolio.
   * @return a string of that name.
   */
  String getPortfolioName();

}
