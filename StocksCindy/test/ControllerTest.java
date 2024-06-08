import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Controller;
import model.Model;
import controller.StocksController;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 * test for the controller use mock of model and view.
 * this tests if the controller is able to read the information
 * that the user inputs and goes into the mock model
 */
public class ControllerTest {

  /**
   * tests if the controller is reading the inputs
   * when a user makes a new portfolio.
   */
  @Test
  public void testCreatePort() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-create wow AMZN 32 f quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();
    //just to show that the controller does go to the mock model
    assertEquals("Creating portfolio wow\n"
            +
            "check file StocksCindy/CSVFiles/AMZN.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to fix something in exisitng portfolio.
   */
  @Test
  public void testPortManage() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-manage wow TSLA 2 f quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "check file StocksCindy/UserPortfolio/wow.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see an existing portfolio.
   */
  @Test
  public void testPortView() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-view wow quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "check file StocksCindy/UserPortfolio/wow.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see get the value.
   */
  @Test
  public void testPortEval() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-eval wow 2020-01-14 quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "check file StocksCindy/UserPortfolio/wow.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see if there is a gain or loss.
   */
  @Test
  public void testStockEval() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-eval AMZN 2020-01-01 2020-01-06 quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("check file StocksCindy/CSVFiles/AMZN.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see the average.
   */
  @Test
  public void testStockAVG() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-avg AMZN 2020-01-01 2020-01-06 quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("check file StocksCindy/CSVFiles/AMZN.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see the x-day crossover.
   */
  @Test
  public void testStockCross() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-cross AMZN 2020-01-01 6 quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("check file StocksCindy/CSVFiles/AMZN.csv\n", log.toString());
  }


}
