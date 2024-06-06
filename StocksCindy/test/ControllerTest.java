import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import Model.StocksController;
import Model.StocksModel;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class ControllerTest {
  Readable rd;
  Appendable ap;
  StocksController controller;
  StocksModel model;


  @Before
  public void setUp() {
    model = new StocksModel();
    ap = new StringBuilder();
  }


  @Test
  public void testForWelcome() {
    rd = new StringReader("q");
    controller = new StocksController(model, ap, rd);
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
  public void testWelcome() {
    rd = new StringReader("q");
    controller = new StocksController(model, ap, rd);
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
                    "Enter in a number corresponding to the action you'd like to take!";

    StringBuilder testString = new StringBuilder();
    String[] output1 = ap.toString().split("\n");
    for (int i = 0; i < 8; i++) {
      testString.append(output1[i]).append("\n");
    }
    testString.append(output1[8]);
    assertEquals(expectedWelcome, testString.toString());
  }
  @Test
  public void testForFarewell() {
    rd = new StringReader("quit");
    controller = new StocksController(model, ap, rd);
    controller.goControl();

    String expectedResponse = "Thank you for using our program!\n";
    String[] output = ap.toString().split("Input number: ");
    String testString = output[1];
    assertEquals(expectedResponse, testString);
  }








}
