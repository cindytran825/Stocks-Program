package Model;

import java.io.File;
import java.util.List;

import Model.Stocks;

/**
 * the StocksModel is where all the implementation is at.
 * methods are called in the controller.
 */
public class StocksModel extends Stock {
  Stock stock;
   String ticker;
   String path;

  public StocksModel() {
    this.ticker = null;
    this.path = null;
  }

  public StocksModel(String ticker) {
    this.ticker = ticker;
    this.path = "StocksCindy/CSVFiles/" + ticker + ".csv";
  }

  private boolean checkIfFileExist() {
      File file = new File(path);
      return file.exists();
  }

  protected void getStock(API api) {
    if (checkIfFileExist()) {
      stock = new Stock(ticker, path);
    } else {
      api.addStock(ticker);
    }
  }
}
