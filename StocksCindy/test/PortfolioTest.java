import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Portfolio;

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
    emptyBob = new Portfolio("Empty Bob", "StocksCindy/test/testingCSV", false);
    bob = new Portfolio("Bob", "StocksCindy/test/testingCSV", false);
    bart = new Portfolio("bart", "StocksCindy/test/testingCSV", true);
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

//  /**
//   * this tests then user adds one company to a portfolio.
//   */
//  @Test
//  public void testPortfolio() {
//    Portfolio portfolio = new Portfolio();
//    Map<String, Integer> inventory = new HashMap<>();
//
//    String companyName = "APPL";
//    Integer quantity = 50;
//    inventory.put(companyName, quantity);
//
//    List<Map<String, Integer>> output = portfolio.addToPortfolio(companyName, quantity);
//    assertEquals(output, portfolio.getListInventories());
//
//  }
//
//  /**
//   * this tests the cases when the user wants.
//   * to add more than one companies in the portfolio.
//   */
//  @Test
//  public void testMultipleHashPortfolio() {
//    Portfolio portfolio = new Portfolio();
//    Map<String, Integer> inventory = new HashMap<>();
//
//    String companyName = "AMZN";
//    Integer quantity = 10;
//    List<Map<String, Integer>> output = portfolio.addToPortfolio(companyName, quantity);
//    assertEquals(output, portfolio.getListInventories());
//
//    companyName = "APPL";
//    quantity = 20;
//    List<Map<String, Integer>> output2 = portfolio.addToPortfolio(companyName, quantity);
//    assertEquals(output2, portfolio.getListInventories());
//
//    companyName = "RDDT";
//    quantity = 2;
//    List<Map<String, Integer>> output3 = portfolio.addToPortfolio(companyName, quantity);
//    assertEquals(output3, portfolio.getListInventories());
//  }
//

  /**
   * this tests the createNewPortfolio() method in Portfolio.
   * MockPortfolioTesting class was created for this test.
   * because the original method sent the created.
   * portfolio to the user directory. The createNewPortfolio() in the.
   * mock puts it into test directory.
   */
//  @Test
//  public void testAddToPortfolio() {
//    MockPortfolioTesting portfolio = new MockPortfolioTesting();
//    String name = "testPortfolio";
//
//    //adds stock into portfolio and creates the list of stocks
//    String companyName = "APPL";
//    Integer quantity = 20;
//    List<Map<String, Integer>> listInventories = portfolio.addToPortfolio(companyName, quantity);
//    File file = new File("StocksCindy/test/testFilePortfolio");
//    //calls the createNewPortfolio method.
//    portfolio.createNewPortfolio(name, listInventories);
//    File direct = new File(name);
//    File[] files = direct.listFiles();
//
//    assertEquals("testFilePortfolio", file.getName());
//    }


}
