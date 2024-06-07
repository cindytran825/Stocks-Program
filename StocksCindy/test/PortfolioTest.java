import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Portfolio;
import model.PortfolioWithImpl;

import static org.junit.Assert.assertEquals;

/**
 * test for the portfolio class.
 */
public class PortfolioTest {

  Portfolio emptyBob;
  Portfolio bob;
  Portfolio bart;

  @Before
  public void setUP() {
    emptyBob = new PortfolioWithImpl("Empty Bob", "StocksCindy/test/testingCSV", false);
    bob = new PortfolioWithImpl("Bob", "StocksCindy/test/testingCSV", false);
    bart = new PortfolioWithImpl("bart", "StocksCindy/test/testingCSV", true);
    bob.editPortfolio("GOOG", 5000);
    bob.editPortfolio("testStockFormat", 123);


    // needed test case for making sure files are writing and that bart is properly found
  }

  @Test
  public void testAddPortfolio() throws FileNotFoundException {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    emptyBob.editPortfolio("Test Ticker", 0);
    Map<String, Integer> expected1 = new HashMap<>();
    expected1.put("Test Ticker", 0);
    assertEquals(expected1, emptyBob.getListInventories());

    emptyBob.editPortfolio("Test Ticker 2", 543);
    Map<String, Integer> expected2 = new HashMap<>();
    expected2.put("Test Ticker 2", 543);
    expected2.put("Test Ticker", 0);
    assertEquals(expected2, emptyBob.getListInventories());

    File emptybob = new File("StocksCindy/test/testingCSV/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("Test Ticker 2,543", scan.nextLine());

    File bob = new File("StocksCindy/test/testingCSV/Bob.csv");
    Scanner scan2 = new Scanner(bob);
    assertEquals("GOOG,5000", scan2.nextLine());
    assertEquals("testStockFormat,123", scan2.nextLine());
  }

  @Test
  public void testGetInventory() {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    Map<String, Integer> expected1 = new HashMap<>();
    expected1.put("GOOG", 5000);
    expected1.put("testStockFormat", 123);
    assertEquals(expected1, bob.getListInventories());

    Map<String, Integer> expected2 = new HashMap<>();
    expected2.put("GOOG", 4);
    assertEquals(expected2, bart.getListInventories());
  }

  @Test
  public void testGetValue() {
    assertEquals(
            893553.66,
            bob.getValue("2024-06-03", "StocksCindy/test/testingCSV"),
            0.01
    );
    assertEquals(
            893553.66,
            bob.getValue("2024-06-15", "StocksCindy/test/testingCSV"),
            0.01
    );
    assertEquals(
            889147.88,
            bob.getValue("2024-05-30", "StocksCindy/test/testingCSV"),
            0.01
    );
  }

  @Test
  public void testToString() {
    assertEquals(
            "GOOG : 5000\ntestStockFormat : 123\n", bob.toString());
  }
}
