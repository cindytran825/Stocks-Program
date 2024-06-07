import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Controller;
import model.Model;
import model.Stocks;
import controller.StocksController;
import model.StocksModel;
import view.StockProgramView;
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
    assertEquals("Creating portfolio wow\nAMZN 32", log.toString());
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

    assertEquals("wow\nTSLA 2", log.toString());
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

    assertEquals("wow", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see get the value.
   */
  @Test
  public void testPortEval() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-eval wow 2020-01-01 quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("wow\nEvaluating portfolio 2020-01-01", log.toString());
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

    assertEquals("Evaluating stock AMZN 2020-01-01 2020-01-06", log.toString());
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

    assertEquals("Moving average AMZN 2020-01-01 2020-01-06", log.toString());
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

    assertEquals("Stock crossover AMZN 2020-01-01 6", log.toString());
  }


}
