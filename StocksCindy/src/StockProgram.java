import java.io.InputStreamReader;

import controller.StocksController;
import model.Model;
import model.StocksModel;
import view.GUIView;
import view.StockProgramView;
import view.GUIViewImpl;
import view.View;

/**
 * the stock program that displays the program in the console.
 */
public class StockProgram {

  public static void execute() {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;

    // view should be generated here
    Model model = new StocksModel();
    View view = new StockProgramView();
    GUIView guiView = new GUIViewImpl();

    StocksController controller = new StocksController(model, view, rd, guiView);
    controller.goControl();
  }
}