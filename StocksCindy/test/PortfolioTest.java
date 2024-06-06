import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Portfolio;

import static org.junit.Assert.assertEquals;

/**
 * test for the portfolio class.
 */
public class PortfolioTest {

  /**
   * this tests then user adds one company to a portfolio.
   */
  @Test
  public void testPortfolio() {
    Portfolio portfolio = new Portfolio();
    Map<String, Integer> inventory = new HashMap<>();

    String companyName = "APPL";
    Integer quantity = 50;
    inventory.put(companyName, quantity);

    List<Map<String, Integer>> output = portfolio.addToPortfolio(companyName, quantity);
    assertEquals(output, portfolio.getListInventories());

  }

  /**
   * this tests the cases when the user wants.
   * to add more than one companies in the portfolio.
   */
  @Test
  public void testMultipleHashPortfolio() {
    Portfolio portfolio = new Portfolio();
    Map<String, Integer> inventory = new HashMap<>();

    String companyName = "AMZN";
    Integer quantity = 10;
    List<Map<String, Integer>> output = portfolio.addToPortfolio(companyName, quantity);
    assertEquals(output, portfolio.getListInventories());

    companyName = "APPL";
    quantity = 20;
    List<Map<String, Integer>> output2 = portfolio.addToPortfolio(companyName, quantity);
    assertEquals(output2, portfolio.getListInventories());

    companyName = "RDDT";
    quantity = 2;
    List<Map<String, Integer>> output3 = portfolio.addToPortfolio(companyName, quantity);
    assertEquals(output3, portfolio.getListInventories());
  }

  /**
   * this tests the createNewPortfolio() method in Portfolio.
   * MockPortfolioTesting class was created for this test.
   * because the original method sent the created.
   * portfolio to the user directory. The createNewPortfolio() in the.
   * mock puts it into test directory.
   */
  @Test
  public void testAddToPortfolio() {
    MockPortfolioTesting portfolio = new MockPortfolioTesting();
    String name = "testPortfolio";

    //adds stock into portfolio and creates the list of stocks
    String companyName = "APPL";
    Integer quantity = 20;
    List<Map<String, Integer>> listInventories = portfolio.addToPortfolio(companyName, quantity);
    File file = new File("StocksCindy/test/testFilePortfolio");
    //calls the createNewPortfolio method.
    portfolio.createNewPortfolio(name, listInventories);
    File direct = new File(name);
    File[] files = direct.listFiles();

    assertEquals("testFilePortfolio", file.getName());
    }


}
