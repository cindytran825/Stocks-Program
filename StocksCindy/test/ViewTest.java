import org.junit.Test;

import java.io.StringReader;

import controller.Controller;
import controller.StocksController;
import model.Model;
import view.View;

import static org.junit.Assert.assertEquals;

public class ViewTest {
//  Readable rd;
//  View view;
//  Controller controller;
//  Model model;

  @Test
  public void testForWelcome() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

//  @Test
//  public void testWelcome() {
//    rd = new StringReader("q");
//    controller = new StocksController(model, view, rd);
//    controller.goControl();
//
//    String expectedWelcome =
//            "Welcome to the stocks program!\n" +
//                    "1. Create new portfolio\n" +
//                    "2. Add to existing portfolio\n" +
//                    "3. View existing portfolios\n" +
//                    "4. Examine gain/loss\n" +
//                    "5. Examine x-day move average\n" +
//                    "6. Determine which days are x-day crossover\n" +
//                    "'quit' to quit\n" +
//                    "Enter in a number corresponding to the action you'd like to take!";
//
//    StringBuilder testString = new StringBuilder();
//    String[] output1 = view.toString().split("\n");
//    for (int i = 0; i < 8; i++) {
//      testString.append(output1[i]).append("\n");
//    }
//    testString.append(output1[8]);
//    assertEquals(expectedWelcome, testString.toString());
//  }
//  @Test
//  public void testForFarewell() {
//    rd = new StringReader("quit");
//    controller = new StocksController(model, view, rd);
//    controller.goControl();
//
//    String expectedResponse = "Thank you for using our program!\n";
//    String[] output = view.toString().split("Input number: ");
//    String testString = output[1];
//    assertEquals(expectedResponse, testString);
//  }

  @Test
  public void testForQuit() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "quit";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }


  @Test
  public void testForMenu() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "quit";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

  /**
   * For the first case on the menu.
   * where they create a portfolio.
   */
  @Test
  public void testForCreatePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

  /**
   * For the second case on the menu.
   * where they manage a portfolio.
   */
  @Test
  public void testForManagePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

  /**
   * For the third case on the menu.
   * where they view existing a portfolio.
   */
  @Test
  public void testForViewExistingPortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

  /**
   * For the fourth case on the menu.
   * Evaluate existing portfolios.
   */
  @Test
  public void testForEvaluatePortfolio() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }

  /**
   * For the fifth case on the menu.
   * Gain/loss.
   */
  @Test
  public void testForGainLoss() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }
  /**
   * For the six case on the menu.
   * Examine x-day move average
   */
  @Test
  public void testForAverage() {
    Appendable ap = new StringBuilder();
    Model mockModel = new ModelMock();
    View view = new ViewMock(ap);
    String userInput = "1";

    Controller controller = new ControllerMock(mockModel, view, userInput);
    controller.goControl();

    String expectedWelcome =
            "Welcome to the stocks program!\n" +
                    "1. Create new portfolio\n" +
                    "2. Add to existing portfolio\n" +
                    "3. View existing portfolios\n" +
                    "4. Examine gain/loss\n" +
                    "5. Examine x-day move average\n" +
                    "6. Determine which days are x-day crossover\n" +
                    "'quit' to quit\n" +
                    "Enter in a number corresponding to the action you'd like to take!" + "\n";

    String[] output = ap.toString().split("Input number: ");
    String getOutput = output[0];
    assertEquals(expectedWelcome, getOutput);

  }




}
