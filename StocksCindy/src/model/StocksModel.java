package model;

import java.io.File;

/**
 * the StocksModel is where all the implementation is at.
 * methods are called in the controller.
 */
public class StocksModel extends Stock {

  private Stock stock;

  public StocksModel() {}

  private boolean checkIfFileExist(String ticker) {
      String path = "StocksCindy/CSVFiles/" + ticker + ".csv";
      File file = new File(path);
      return file.exists();
  }

  protected void getStock(StockApi api, String ticker) {
    if (checkIfFileExist(ticker)) {
      String path = "StocksCindy/CSVFiles/" + ticker + ".csv";
      stock = new Stock(ticker, path);
    } else {
      api.addStock(ticker);
    }
  }
}
