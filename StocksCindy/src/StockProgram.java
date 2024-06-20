import java.io.InputStreamReader;

import controller.StocksController;
import model.Model;
import model.StocksModel;
import view.GUIView;
import view.StockProgramTextView;
import view.GUIViewImpl;
import view.TextView;

/**
 * the stock program that displays the program in the console.
 */
public class StockProgram {

  public static void execute() {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;

    // view should be generated here
    Model model = new StocksModel();
    TextView view = new StockProgramTextView();
    GUIView guiView = new GUIViewImpl(model.getPortfolioNames().split("\\n"));

    StocksController controller = new StocksController(model, view, rd, guiView);
    controller.goControl();
  }
}