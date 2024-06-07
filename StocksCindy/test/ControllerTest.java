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
 *
 */
public class ControllerTest {

  @Test
  public void testCreatePort() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-create wow AMZN 32 f quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("Creating portfolio wow\nAMZN 32", log.toString());
  }
  @Test
  public void testPortManage() {
    StringBuilder log = new StringBuilder();
    Readable rd = new StringReader("port-manage wow TSLA 2 f quit");
    Scanner scan = new Scanner(rd);

    View view = new ViewMock(log);
    Model mockModel = new ModelMock(log);
    Controller controller = new StocksController(mockModel, view, rd);
    controller.goControl();

    assertEquals("Creating portfolio wow\nAMZN 32\nTSLA 2", log.toString());
  }

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

}
