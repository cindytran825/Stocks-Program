import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.Controller;
import model.Model;
import model.Stocks;
import controller.StocksController;
import model.StocksModel;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ControllerTest {
  Readable rd;
  View view;
  Controller controller;
  Model model;


  @Before
  public void setUp() {
    model = new StocksModel();
//    ap = new StringBuilder();
  }

  @Test
  public void testCreatePort() {
    rd = new StringReader("port-create Bob");

  }










}
