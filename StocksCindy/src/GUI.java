import controller.GUIController;
import javax.swing.JFrame;
import model.Model;
import model.StocksModel;
import view.GUIViewImpl;

/**
 * GUI stock main class.
 */
public class GUI {

  /**
   * This method sets up and runs the GUI-based stock program that allows changes to the portfolio.
   */
  public static void execute() {
    Model model = new StocksModel();
    GUIViewImpl.setDefaultLookAndFeelDecorated(false);
    GUIViewImpl guiView = new GUIViewImpl(model.getPortfolioNames().split("\\n"), model.getStockNames().split("\\n"));

    guiView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiView.setVisible(true);

    GUIController controller = new GUIController(model, guiView);
    controller.goControl();
  }
}
