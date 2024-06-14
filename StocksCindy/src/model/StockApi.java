package model;

/**
 * This is a class for stock APIs. All APIs will be responsible for adding Stock csv files
 */
public interface StockApi {

  /**
   * adds a stock csv file this repository. The stock pulled from the api should have
   * timestamp, open, low, high, close, volume information.
   *
   * @param ticker ticker of Stock that is to be added
   */
  void addStock(String ticker);
}
