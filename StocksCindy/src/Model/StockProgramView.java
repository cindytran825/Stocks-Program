package Model;

import java.io.InputStreamReader;

/**
 * View of the program, it should connect to the controller.
 * which calls the methods in the model.
 */
public class StockProgramView {
  public static void main(String[] args) {
    Stocks model = new StocksModel();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    StocksController controller = new StocksController(model, ap, rd);
    controller.goControl();
  }
}
