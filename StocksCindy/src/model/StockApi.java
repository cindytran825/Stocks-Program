package model;

/**
 * This is a class for stock APIs. All APIs will be responsible for adding Stock csv files
 */
public interface StockApi {

  /**
   * adds a stock csv file this repository.
   *
   * @param ticker ticker of STock that is to be added
   */
  void addStock(String ticker);
}
