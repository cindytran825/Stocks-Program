import org.junit.Test;

import controller.Controller;
import model.Model;
import view.StockProgramView;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 * test for the view using the controller mock class.
 */
public class ViewTest {
  @Test
  public void testForWelcome() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "Create new portfolio [port-create]\n" +
                    "Manage portfolio and make changes to it [port-manage]\n" +
                    "View existing portfolios and analyze data [port-view]\n" +
                    "View what stock datas are on file [stock-list]\n" +
                    "View existing stocks and analyze data [stock-view]\n" +
                    "Download stock data from an API [stock-download]\n" +
                    "Upload your own stock data in a csv file [stock-upload]\n" +
                    "Quit [quit]\n" +
                    "Menu [menu]\n" +
                    "Enter instruction to the action you'd like to take!\n";

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
                    "Create new portfolio [port-create]\n" +
                    "Manage portfolio and make changes to it [port-manage]\n" +
                    "View existing portfolios and analyze data [port-view]\n" +
                    "View what stock datas are on file [stock-list]\n" +
                    "View existing stocks and analyze data [stock-view]\n" +
                    "Download stock data from an API [stock-download]\n" +
                    "Upload your own stock data in a csv file [stock-upload]\n" +
                    "Quit [quit]\n" +
                    "Menu [menu]\n" +
                    "Enter instruction to the action you'd like to take!\n" +
                    "Thank you for using our program!";

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
                    "Create new portfolio [port-create]\n" +
                    "Manage portfolio and make changes to it [port-manage]\n" +
                    "View existing portfolios and analyze data [port-view]\n" +
                    "View what stock datas are on file [stock-list]\n" +
                    "View existing stocks and analyze data [stock-view]\n" +
                    "Download stock data from an API [stock-download]\n" +
                    "Upload your own stock data in a csv file [stock-upload]\n" +
                    "Quit [quit]\n" +
                    "Menu [menu]\n" +
                    "Enter instruction to the action you'd like to take!";

    StringBuilder testString = new StringBuilder();
    String[] output1 = ap.toString().split("\n");
    for (int i = 0; i < 10; i++) {
      testString.append(output1[i]).append("\n");
    }
    testString.append(output1[10]);
    assertEquals(expectedMenu, testString.toString());
  }


  /**
   * When there is in invalid input.
   */
  @Test
  public void testForInvalidPortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new StockProgramView(ap);
    String userInput = "port";
    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();
    String expectedMenu =
            "\nThis is an invalid command!\n" +
                    "Thank you for using our program!\n";

    String[] output = ap.toString().split(" to take!");
    String getOutput = output[1];
    assertEquals(expectedMenu, getOutput);
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

    String[] output = ap.toString().split("Enter instruction to "
            +
            "the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("Enter Ticker ");
    String now = output2[0];
    assertEquals(expectedMenu, now);
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
    String[] output2 = getOutput.split("How many shares "
            +
            "do you want to add for ");
    String now = output2[0];
    assertEquals(expectedMenu, now);
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

    String[] output = ap.toString().split("Enter instruction to the"
            +
            " action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("Thank you for using our program!");
    String now = output2[0];
    assertEquals(expectedMenu, now);
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

    String[] output = ap.toString().split("Enter instruction to "
            +
            "the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split(" Enter the ticker:");
    String now = output2[0];
    assertEquals(expectedMenu, now);
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
            " Type the starting date (YYYY-MM-DD): "
                    +
                    "Type the ending date (YYYY-MM-DD): "
                    +
                    "\n";

    String[] output = ap.toString().split("Enter instruction t"
            +
            "o the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("ticker:");
    String now = output2[1];
    String[] wow = now.split("Thank you");
    String result = wow[0];
    assertEquals(expectedMenu, result);

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
            " Type the starting date (YYYY-MM-DD): Enter in the l"
                    +
                    "ast x-days since the current date: ";


    String[] output = ap.toString().split("Enter instruction to"
            +
            " the action you'd like to take!");
    String getOutput = output[1];
    String[] output2 = getOutput.split("ticker: Enter");
    String now = output2[1];
    String[] more = now.split("ticker:");
    String call = more[1];
    String[] wow = call.split("Thank you");
    String result = wow[0];
    assertEquals(expectedMenu, result);
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
            "Type the starting date (YYYY-MM-DD): Enter"
                    +
                    " in the last x-days since the current date: "
                    +
                    "\n";

    String[] output = ap.toString().split("Enter instru"
            +
            "ction to the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("ticker: ");
    String result = wow[1];
    String[] now = result.split("Thank you");
    String get = now[0];
    assertEquals(expectedMenu, get);
  }

  /**
   * tests for the stocklist case.
   * it would return a list of stocks.
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
            "\n\n";
    String[] output = ap.toString().split("Enter instruction to "
            +
            "the action you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("Thank ");
    String result = wow[0];
    assertEquals(expectedMenu, result);
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
            "\nEnter the ticker: \n";

    String[] output = ap.toString().split("tion you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("Enter the ticker this file represents: ");
    String result = wow[0];
    String[] hello = result.split("Thank you ");
    String there = hello[0];
    assertEquals(expectedMenu, there);

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
            "\nEnter in the reference path to the CSV file: ";

    String[] output = ap.toString().split("tion you'd like to take!");
    String getOutput = output[1];
    String[] wow = getOutput.split("Enter the ticker this file represents: ");
    String result = wow[0];
    assertEquals(expectedMenu, result);
  }

}
