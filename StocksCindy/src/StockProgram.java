import java.io.InputStreamReader;

import controller.StocksController;
import model.Stocks;
import model.StocksModel;

public class StockProgram {
  public static void main(String[] args) {
    Stocks model = new StocksModel();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    StocksController controller = new StocksController(model, ap, rd);
    controller.goControl();
  }
}
