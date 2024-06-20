import java.io.InputStreamReader;

import controller.StocksController;
import model.Model;
import model.StocksModel;
import view.StockProgramTextView;
import view.TextView;

/**
 * the stock program that displays text-view of the program in the console.
 */
public class StockProgram {

  /**
   * This method sets up and runs the text-based stock program.
   */
  public static void execute() {
    Readable rd = new InputStreamReader(System.in);

    // view should be generated here
    Model model = new StocksModel();
    TextView view = new StockProgramTextView();

    StocksController controller = new StocksController(model, view, rd);
    controller.goControl();
  }
}