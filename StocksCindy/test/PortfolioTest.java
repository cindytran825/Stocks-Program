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
  String testFolderPath;

  @Before
  public void setUP() {
    testFolderPath = "StocksCindy/test/testingCSV";
    emptyBob = new PortfolioWithImpl(
            "Empty Bob",
            testFolderPath,
            testFolderPath,
            false);
    bob = new PortfolioWithImpl(
            "Bob",
            testFolderPath,
            testFolderPath,
            false);
    bart = new PortfolioWithImpl(
            "bart",
            testFolderPath,
            testFolderPath,
            true);
    bob.editPortfolio("GOOG", 5000.0);
    bob.editPortfolio("testStockFormat", 123.0);


    // needed test case for making sure files are writing and that bart is properly found
  }

  @Test
  public void testBuy() throws FileNotFoundException {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    // check if it adds
    emptyBob.buyStock("GOOG", 1000, "2024-06-03");
    Map<String, Double> expected1 = new HashMap<>();
    expected1.put("GOOG", 1000.0);
    assertEquals(expected1, emptyBob.getListInventories());

    // check if it is written to file
    File emptybob = new File("StocksCindy/test/testingCSV/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0,GOOG,2024-06-03,", scan.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 10005.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-03,", scan1.nextLine());

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected3 = new HashMap<>();
    expected3.put("GOOG", 20006.0);
    assertEquals(expected3, bart.getListInventories());

    // check if it is written to file
    File bart2 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan2 = new Scanner(bart2);
    assertEquals("action,share,stock,timestamp,", scan2.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan2.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-03,", scan2.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-03,", scan2.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuyNotChronological() throws FileNotFoundException {
    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.buyStock("GOOG", 10001, "2024-05-30");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 10005.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-05-30,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuyDateNotExist() throws FileNotFoundException {
// need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.buyStock("GOOG", 10001, "2024-06-10");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 10005.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-10,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test
  public void testSell() throws FileNotFoundException {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    // check if it adds
    emptyBob.buyStock("GOOG", 1000, "2024-06-03");
    Map<String, Double> expected1 = new HashMap<>();
    expected1.put("GOOG", 1000.0);
    assertEquals(expected1, emptyBob.getListInventories());

    emptyBob.sellStock("GOOG", 100, "2024-06-03");
    expected1.put("GOOG", 900.0);
    assertEquals(expected1, emptyBob.getListInventories());

    emptyBob.buyStock("GOOG", 200, "2024-06-03");
    expected1.put("GOOG", 1100.0);
    assertEquals(expected1, emptyBob.getListInventories());

    emptyBob.sellStock("GOOG", 1100, "2024-06-03");
    expected1.put("GOOG", 0.0);
    assertEquals(expected1, emptyBob.getListInventories());

    // check if it is written to file
    File emptybob = new File("StocksCindy/test/testingCSV/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,100.0,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("buy,200.0,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,1100.0,GOOG,2024-06-03,", scan.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    Map<String, Double> expected2 = new HashMap<>();
    bart.buyStock("GOOG", 10001, "2024-06-03");
    expected2.put("GOOG", 10005.0);
    assertEquals(expected2, bart.getListInventories());

    bart.sellStock("GOOG", 5000, "2024-06-03");
    expected2.put("GOOG", 5005.0);
    assertEquals(expected2, bart.getListInventories());

    bart.buyStock("GOOG", 10001, "2024-06-03");
    expected2.put("GOOG", 15006.0);
    assertEquals(expected2, bart.getListInventories());

    bart.sellStock("GOOG", 15005, "2024-06-03");
    expected2.put("GOOG", 1.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,5000.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,15005.0,GOOG,2024-06-03,", scan1.nextLine());

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected3 = new HashMap<>();
    expected3.put("GOOG", 10002.0);
    assertEquals(expected3, bart.getListInventories());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellNotChronological() throws FileNotFoundException {
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.sellStock("GOOG", 4.0, "2024-05-30");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 0.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,4.0,GOOG,2024-05-30,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellDateNotInData() throws FileNotFoundException {
    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.sellStock("GOOG", 4.0, "2024-06-10");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 0.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File("StocksCindy/test/testingCSV/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,4.0,GOOG,2024-06-10,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellWhenNotEnough() throws FileNotFoundException {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    // check if it adds
    Map<String, Double> expected1 = new HashMap<>();
    emptyBob.buyStock("GOOG", 1000, "2024-06-03");
    expected1.put("GOOG", 1000.0);
    assertEquals(expected1, emptyBob.getListInventories());

    // selling more than available
    emptyBob.sellStock("GOOG", 2000, "2024-06-03");
    expected1.put("GOOG", -1000.0);
    assertEquals(expected1, emptyBob.getListInventories());

    // check if it is written to file
    File emptybob = new File("StocksCindy/test/testingCSV/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,2000.0,GOOG,2024-06-03,", scan.nextLine());
  }

  @Test
  public void testGetInventory() {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    Map<String, Double> expected1 = new HashMap<>();
    expected1.put("GOOG", 5000.0);
    expected1.put("testStockFormat", 123.0);
    assertEquals(expected1, bob.getListInventories());

    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 4.0);
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
            "GOOG : 5000.000000\ntestStockFormat : 123.000000\n", bob.toString());
  }
}
