import org.junit.Test;

import java.io.StringReader;

import controller.Controller;
import controller.StocksController;
import model.Model;
import view.StockProgramView;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 * test for the view using the controller mock class.
 */
public class ViewTest {
  @Test
  /**
   * this tests for the welcome message when the user first runs the program.
   */
  public void testForWelcome() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "Create new portfolio [port-create]\n"
                    + "Manage portfolio [port-manage]\n"
                    + "View existing portfolios [port-view]\n"
                    + "Evaluate existing portfolios [port-eval]\n"
                    + "Examine gain/loss [stock-eval]\n"
                    + "Examine x-day move average [stock-avg]\n"
                    + "Determine which days are x-day crossover [stock-cross]\n"
                    + "View what stock datas are on file [stock-list]\n"
                    + "Download stock data from an API [stock-download]\n"
                    + "Quit [quit]\n"
                    + "Menu [menu]\n"
                    + "Enter instruction to the action you'd like to take!\n";

    String[] output = ap.toString().split("This is an");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);
  }

  /**
   * tests when the user inputs quit.
   */
  @Test
  public void testQuit() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "quit";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "Create new portfolio [port-create]\n"
                    + "Manage portfolio [port-manage]\n"
                    + "View existing portfolios [port-view]\n"
                    + "Evaluate existing portfolios [port-eval]\n"
                    + "Examine gain/loss [stock-eval]\n"
                    + "Examine x-day move average [stock-avg]\n"
                    + "Determine which days are x-day crossover [stock-cross]\n"
                    + "View what stock datas are on file [stock-list]\n"
                    + "Download stock data from an API [stock-download]\n"
                    + "Quit [quit]\n"
                    + "Menu [menu]";

    StringBuilder testString = new StringBuilder();
    String[] output1 = ap.toString().split("\n");
    for (int i = 0; i < 11; i++) {
      testString.append(output1[i]).append("\n");
    }
    testString.append(output1[11]);
    assertEquals(expectedWelcome, testString.toString());
  }

  /**
   * tests when the user inputs menu.
   */
  @Test
  public void testForMenu() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "menu";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedMenu =
            "Welcome to the stocks program!\n" +
                    "Create new portfolio [port-create]\n"
                    + "Manage portfolio [port-manage]\n"
                    + "View existing portfolios [port-view]\n"
                    + "Evaluate existing portfolios [port-eval]\n"
                    + "Examine gain/loss [stock-eval]\n"
                    + "Examine x-day move average [stock-avg]\n"
                    + "Determine which days are x-day crossover [stock-cross]\n"
                    + "View what stock datas are on file [stock-list]\n"
                    + "Download stock data from an API [stock-download]\n"
                    + "Quit [quit]";

    StringBuilder testString = new StringBuilder();
    String[] output1 = ap.toString().split("\n");
    for (int i = 0; i < 10; i++) {
      testString.append(output1[i]).append("\n");
    }
    testString.append(output1[10]);
    assertEquals(expectedMenu, testString.toString());
  }


  /**
   * For the first case on the menu.
   * where they create a portfolio.
   */
  @Test
  public void testForCreatePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port-create";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\nName of new portfolio: ";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("Successfully added a new portfolio.");
    String now = output2[0];
    assertEquals(expectedMenu, now.toString());
  }

  /**
   * For the second case on the menu.
   * where they manage a portfolio.
   */
  @Test
  public void testForManagePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port-manage";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\nType just the NAME of the portfolio: ";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("Enter Ticker ");
    String now = output2[0];
    assertEquals(expectedMenu, now.toString());
  }

  /**
   * this is after they input the name, it'll ask to input the ticker.
   */
  @Test
  public void testForManagePortfolio2() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port-manage";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "Enter Ticker (type 'f' to complete portfolio): ";

    String[] output = ap.toString().split("portfolio: ");
    String getOutput = output[1];
    String[] output2 = getOutput.split("How many shares do you want to add for ");
    String now = output2[0];
    assertEquals(expectedMenu, now.toString());
  }

  /**
   * For the third case on the menu.
   * where they view existing a portfolio.
   */
  @Test
  public void testForViewExistingPortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port-view";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\nType just the NAME of the portfolio: \n";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("Thank you for using our program!");
    String now = output2[0];
    assertEquals(expectedMenu, now.toString());
  }

  /**
   * For the fourth case on the menu.
   * Evaluate existing portfolios.
   */
  @Test
  public void testForEvaluatePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port-eval";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\nType just the NAME of the portfolio:";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split(" Enter the ticker:");
    String now = output2[0];
    assertEquals(expectedMenu, now.toString());
  }

  /**
   * For the fifth case on the menu.
   * Gain/loss.
   */
  @Test
  public void testForGainLoss() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-eval";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            " Type the starting date (YYYY-MM-DD): Type the ending date (YYYY-MM-DD): " +
                    "\n";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("ticker:");
    String now = output2[1];
    String[] wow = now.split("Thank you");
    String result = wow[0];
    assertEquals(expectedMenu, result.toString());

  }
  /**
   * For the six case on the menu.
   * Examine x-day move average
   */
  @Test
  public void testForAverage() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-avg";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            " Type the starting date (YYYY-MM-DD): Enter in the last x-days since the current date: ";


    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("ticker: Enter");
    String now = output2[1];
    String[] more = now.split("ticker:");
    String call = more[1];
    String[] wow = call.split("Thank you");
    String result = wow[0];
    assertEquals(expectedMenu, result.toString());
  }

  /**
   * tests for the crossover case.
   */
  @Test
  public void testForCrossover() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-cross";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "Type the starting date (YYYY-MM-DD): Enter in the last x-days since the current date: " +
                    "\n";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("ticker: ");
    String result = wow[1];
    String[] now = result.split("Thank you");
    String get = now[0];
    assertEquals(expectedMenu, get.toString());
  }

  /**
   * tests for the stocklist case.
   */
  @Test
  public void testForStockList() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-list";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\n\nEnter the ticker: " +
                    "\n";
    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("Enter in the reference path to the CSV file: ");
    String result = wow[0];
    assertEquals(expectedMenu, result.toString());
  }

  /**
   * tests for the download case.
   */
  @Test
  public void testForDownload() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-download";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "Type the starting date (YYYY-MM-DD): Enter in the last x-days since the current date: " +
                    "\n";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("ticker: ");
    String result = wow[1];
    String[] now = result.split("Thank you");
    String get = now[0];
    assertEquals(expectedMenu, get.toString());
  }

  /**
   * tests for the upload case.
   */
  @Test
  public void testForUpload() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "stock-upload";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "Type the starting date (YYYY-MM-DD): Enter in the last x-days since the current date: " +
                    "\n";

    String[] output = ap.toString().split("Enter instruction to the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("ticker: ");
    String result = wow[1];
    String[] now = result.split("Thank you");
    String get = now[0];
    assertEquals(expectedMenu, get.toString());
  }

}
