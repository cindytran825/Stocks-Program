import org.junit.Test;

import java.io.StringReader;

import controller.Controller;
import controller.StocksController;
import model.Model;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * test for the controller use mock of model and view
 * this tests if the controller is able to read the information
 * that the user inputs and goes into the mock model.
 */
public class ControllerTest {

  @Test
  public void testCreatePort() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-create wow AMZN 32 f quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();
    //just to show that the controller does go to the mock model
    assertEquals("Creating portfolio wow\n", log.toString());
  }

  @Test
  public void testPortManage() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-manage f quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "Cannot find stock on file. ", log.toString());
  }

  @Test
  public void testCorrectPortManage() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-manage bart TSLA 2 f quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "Cannot find stock on file. ", log.toString());
  }

  @Test
  public void testPortView() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-view cindy finish quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "check file StocksCindy/UserPortfolio/cindy.csv\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see get the value.
   */
  @Test
  public void testPortEval() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-eval stock-list quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get stock\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see get the value.
   */

  @Test
  public void testPortEval2() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-manage cindy buy GOOG 10 finish quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get portfolio names\n" +
            "check file StocksCindy/UserPortfolio/cindy.csv\n" +
            "check file StocksCindy/CSVFiles/GOOG.csv\n" +
            "check whole number 10\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see if there is a gain or loss.
   */
  @Test
  public void testStockEval() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-view AMZN value 06 01 2020 10 01 2020 finish quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get stock\n" +
            "check file StocksCindy/CSVFiles/AMZN.csv\n" +
            "if date 2020-01-06\n", log.toString());
  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see the average.
   */
  @Test
  public void testStockAVG() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-view GOOG moving-avg 01 01 2020 20 finish quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get stock\ncheck file StocksCindy/CSVFiles/GOOG.csv\n" +
            "if date 2020-01-01\n", log.toString());

  }

  /**
   * tests if the controller is reading the inputs
   * when a user wants to see the x-day crossover.
   */
  @Test
  public void testStockCross() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-view AMZN crossover 01 01 2020 10 finish quit");

    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("get stock\ncheck file StocksCindy/CSVFiles/AMZN.csv\n" +
            "if date 2020-01-01\n", log.toString());
  }

  /**
   * this for the case for when an invalid ticker is entered.
   */
  @Test
  public void testInvalidTicker() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-view RDDT crossover 01 01 2020 10 finish quit");
    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();
    assertEquals("get stock\nCannot find stock on file. ", log.toString());
  }

  /**
   * this is for the case when an invalid date is entered.
   */
  @Test
  //(expected = IllegalArgumentException.class)
  public void testInvalidDate() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("stock-view AMZN crossover 01 0100 2020 10 finish quit");
    TextView view = new TextViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();
    assertEquals("get stock\ncheck file StocksCindy/CSVFiles/AMZN.csv\n" +
            "if date 2020-0100-01\n", log.toString());
  }

}
