import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.DataChart;
import model.MyDate;
import model.MyDateWithImpl;
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
  Portfolio cindy;
  String testFolderPath;

  /**
   * set up for testing.
   */
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
    cindy = new PortfolioWithImpl(
            "cindy",
            testFolderPath,
            testFolderPath,
            true);
    bob.buyStock("GOOG", 5000.0, "2024-05-29");
    bob.buyStock("testStockFormat", 123.0, "2024-05-30");


    // needed test case for making sure files are writing and that bart is properly found
  }

  /**
   * test buy method.
   * @throws FileNotFoundException is thrown when the file given isn't found.
   */
  @Test
  public void testBuy() throws FileNotFoundException {
    assertEquals(new HashMap<>(), emptyBob.getListInventories());

    // check if it adds
    emptyBob.buyStock("GOOG", 1000, "2024-06-03");
    Map<String, Double> expected1 = new HashMap<>();
    expected1.put("GOOG", 1000.0);
    assertEquals(expected1, emptyBob.getListInventories());

    // check if it is written to file
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,GOOG,2024-06-03,", scan.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 10005.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-03,", scan1.nextLine());

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected3 = new HashMap<>();
    expected3.put("GOOG", 20006.0);
    assertEquals(expected3, bart.getListInventories());

    // check if it is written to file
    File bart2 = new File(testFolderPath + "/bart.csv");
    Scanner scan2 = new Scanner(bart2);
    assertEquals("action,share,stock,timestamp,", scan2.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan2.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-03,", scan2.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-03,", scan2.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
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
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-05-30,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
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
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-10,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
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
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,100.0000,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("buy,200.0000,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,1100.0000,GOOG,2024-06-03,", scan.nextLine());

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
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,5000.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("buy,10001.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,15005.0000,GOOG,2024-06-03,", scan1.nextLine());

    bart.buyStock("GOOG", 10001, "2024-06-03");
    Map<String, Double> expected3 = new HashMap<>();
    expected3.put("GOOG", 10002.0);
    assertEquals(expected3, bart.getListInventories());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSellNotChronological() throws FileNotFoundException {
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");

    bart.sellStock("GOOG", 4.0, "2024-05-30");
    Map<String, Double> expected2 = new HashMap<>();
    expected2.put("GOOG", 0.0);
    assertEquals(expected2, bart.getListInventories());

    // check if it is written to file
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,4.0000,GOOG,2024-05-30,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
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
    File bart1 = new File(testFolderPath + "/bart.csv");
    Scanner scan1 = new Scanner(bart1);
    assertEquals("action,share,stock,timestamp,", scan1.nextLine());
    assertEquals("buy,4.0000,GOOG,2024-06-03,", scan1.nextLine());
    assertEquals("sell,4.0000,GOOG,2024-06-10,", scan1.nextLine());

    // need to reset the file-writing since it'll write and save to it
    bart = new PortfolioWithImpl("bart", testFolderPath, testFolderPath, false);
    bart.buyStock("GOOG", 4.0, "2024-06-03");
  }

  /**
   *
   * @throws FileNotFoundException
   */
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
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,2000.0000,GOOG,2024-06-03,", scan.nextLine());
  }

  /**
   *
   */
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

  /**
   *
   */
  @Test
  public void testGetComposition() {
    Map<String, Double> expected = new HashMap<>();
    expected.put("GOOG", 5000.0);
    assertEquals(expected, bob.getComposition("2024-05-29"));
    expected.put("testStockFormat", 123.0);
    assertEquals(expected, bob.getComposition("2024-06-03"));

    bob.sellStock("GOOG", 1000.0, "2024-06-03");
    expected.put("GOOG", 4000.0);
    assertEquals(expected, bob.getComposition("2024-06-03"));

    assertEquals(new HashMap<>(), bob.getComposition("2000-01-01"));
  }

  /**
   *
   * @throws FileNotFoundException
   */
  @Test
  public void testRebalance() throws FileNotFoundException {
    emptyBob.buyStock("AAPL", 1000, "2024-05-07");
    emptyBob.buyStock("GOOG", 500, "2024-05-07");
    emptyBob.buyStock("testStockFormat", 25, "2024-05-30");
    Map<String, Double> percentages = new HashMap<>();
    percentages.put("AAPL", 0.33);
    percentages.put("GOOG", 0.33);
    percentages.put("testStockFormat", 0.34);
    emptyBob.rebalance(percentages, "2024-06-03");

    Map<String, Double> expected = emptyBob.getListInventories();

    assertEquals(485.740169, expected.get("AAPL"), 0.01);
    assertEquals(540.351823, expected.get("GOOG"), 0.01);
    assertEquals(556.726121, expected.get("testStockFormat"), 0.01);

    // check if it is written to file
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,AAPL,2024-05-07,", scan.nextLine());
    assertEquals("buy,500.0000,GOOG,2024-05-07,", scan.nextLine());
    assertEquals("buy,25.0000,testStockFormat,2024-05-30,", scan.nextLine());

    // after rebalance
    assertEquals("buy,40.3518,GOOG,2024-06-03,", scan.nextLine());
    assertEquals("sell,514.2598,AAPL,2024-06-03,", scan.nextLine());
    assertEquals("buy,531.7261,testStockFormat,2024-06-03,", scan.nextLine());
  }

  /**
   *
   * @throws FileNotFoundException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRebalanceBadDateNoData() throws FileNotFoundException {
    emptyBob.buyStock("AAPL", 1000, "2024-05-07");
    emptyBob.buyStock("GOOG", 500, "2024-05-07");
    emptyBob.buyStock("testStockFormat", 25, "2024-05-30");
    Map<String, Double> percentages = new HashMap<>();
    percentages.put("AAPL", 0.33);
    percentages.put("GOOG", 0.33);
    percentages.put("testStockFormat", 0.34);
    emptyBob.rebalance(percentages, "3000-06-03");

    Map<String, Double> expected = emptyBob.getListInventories();

    assertEquals(485.740169, expected.get("AAPL"), 0.01);
    assertEquals(540.351823, expected.get("GOOG"), 0.01);
    assertEquals(556.726121, expected.get("testStockFormat"), 0.01);

    // check if it is written to file
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,AAPL,2024-05-07,", scan.nextLine());
    assertEquals("buy,500.0000,GOOG,2024-05-07,", scan.nextLine());
    assertEquals("buy,25.0000,testStockFormat,2024-05-30,", scan.nextLine());

    // after rebalance
    assertEquals("buy,40.3518,GOOG,3000-06-03,", scan.nextLine());
    assertEquals("sell,514.2598,AAPL,3000-06-03,", scan.nextLine());
    assertEquals("buy,531.7261,testStockFormat,3000-06-03,", scan.nextLine());
  }

  /**
   *
   * @throws FileNotFoundException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRebalanceBadDateNotChronological() throws FileNotFoundException {
    emptyBob.buyStock("AAPL", 1000, "2024-05-07");
    emptyBob.buyStock("GOOG", 500, "2024-05-07");
    emptyBob.buyStock("testStockFormat", 25, "2024-05-31");
    Map<String, Double> percentages = new HashMap<>();
    percentages.put("AAPL", 0.33);
    percentages.put("GOOG", 0.33);
    percentages.put("testStockFormat", 0.34);
    emptyBob.rebalance(percentages, "2024-05-30");

    Map<String, Double> expected = emptyBob.getListInventories();

    assertEquals(485.740169, expected.get("AAPL"), 0.01);
    assertEquals(540.351823, expected.get("GOOG"), 0.01);
    assertEquals(556.726121, expected.get("testStockFormat"), 0.01);

    // check if it is written to file
    File emptybob = new File(testFolderPath + "/Empty Bob.csv");
    Scanner scan = new Scanner(emptybob);
    assertEquals("action,share,stock,timestamp,", scan.nextLine());
    assertEquals("buy,1000.0000,AAPL,2024-05-07,", scan.nextLine());
    assertEquals("buy,500.0000,GOOG,2024-05-07,", scan.nextLine());
    assertEquals("buy,25.0000,testStockFormat,2024-05-31,", scan.nextLine());

    // after rebalance
    assertEquals("buy,40.3518,GOOG,2024-05-30,", scan.nextLine());
    assertEquals("sell,514.2598,AAPL,2024-05-30,", scan.nextLine());
    assertEquals("buy,531.7261,testStockFormat,2024-05-30,", scan.nextLine());
  }

  /**
   *
   * @throws FileNotFoundException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRebalanceNotFullPercentage() throws FileNotFoundException {
    emptyBob.buyStock("AAPL", 1000, "2024-05-07");
    emptyBob.buyStock("GOOG", 500, "2024-05-07");
    emptyBob.buyStock("testStockFormat", 25, "2024-05-31");
    Map<String, Double> percentages = new HashMap<>();
    percentages.put("AAPL", 0.33);
    percentages.put("GOOG", 0.33);
    percentages.put("testStockFormat", 0.25);
    emptyBob.rebalance(percentages, "2024-05-30");
  }

  /**
   * tests the method getValue.
   */
  @Test
  public void testGetValue() {
    assertEquals(
            893553.66,
            bob.getValue("2024-06-03", testFolderPath),
            0.01
    );
    assertEquals(
            893553.66,
            bob.getValue("2024-06-15", testFolderPath),
            0.01
    );
    assertEquals(
            889147.88,
            bob.getValue("2024-05-30", testFolderPath),
            0.01
    );
    assertEquals(
            0.0,
            emptyBob.getValue("2024-05-30", testFolderPath),
            0.01
    );
    assertEquals(
            0.0,
            bob.getValue("2024-04-30", testFolderPath),
            0.01
    );
  }

  /**
   * tests the toString method.
   */
  @Test
  public void testToString() {
    assertEquals(
            "GOOG : 5000.000000\ntestStockFormat : 123.000000\n", bob.toString());
  }

  /**
   *
   */
  @Test
  public void testGetDistribution() {
    double valueOfPortfolio = bob.getValue("2024-04-04", testFolderPath);
    Map<String, Double> distribution = bob.getDistribution("2024-04-04");
    double totalValue = 0;
    for (Double val : distribution.values()) {
      totalValue += val;
    }
    assertEquals(new HashMap<>(), distribution);
    assertEquals(0.0, valueOfPortfolio, 0.01);
    assertEquals(valueOfPortfolio, totalValue, 0.01);

    distribution = bob.getDistribution("2024-06-03");
    Map<String, Double> expected = new HashMap<>();
    expected.put("GOOG", 872100.0);
    expected.put("testStockFormat", 21453.66);
    assertEquals(expected, distribution);

    valueOfPortfolio = bob.getValue("2024-06-03", testFolderPath);
    totalValue = 0.0;
    for (Double val : distribution.values()) {
      totalValue += val;
    }
    assertEquals(893553.66, valueOfPortfolio, 0.01);
    assertEquals(valueOfPortfolio, totalValue, 0.01);
  }


  /**
   * tests for the bar chart method.
   * test range months.
   */
  @Test
  public void testChartMonth() {
    cindy = new PortfolioWithImpl("cindy", testFolderPath, testFolderPath, false);
    cindy.buyStock("GOOG", 423.0, "2014-04-15");

    assertEquals("Performance of Portfolio cindy from 2024-02-03 to 2024-06-28\n" +
            "FEB 2024: ******\n" +
            "MAR 2024: *******\n" +
            "APR 2024: ********\n" +
            "MAY 2024: ********\n" +
            "JUN 2024: ********\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2024-02-03", "2024-06-28"));
  }

  /**
   *test for getChart when range 5 days.
   */
  @Test
  public void testChartDay() {
    assertEquals("Performance of Portfolio cindy from 2024-02-03 to 2024-02-07\n" +
            "FEB 03 2024: *******\n" +
            "FEB 04 2024: *******\n" +
            "FEB 05 2024: *******\n" +
            "FEB 06 2024: *******\n" +
            "FEB 07 2024: *******\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2024-02-03", "2024-02-07"));
  }

  /**
   *test for getChart when range is 2 days.
   */
  @Test
  public void testChartDay2() {
    assertEquals("Performance of Portfolio cindy from 2024-02-03 to 2024-02-04\n" +
            "FEB 03 2024: *******\n" +
            "FEB 04 2024: *******\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2024-02-03", "2024-02-04"));
  }

  /**
   * this is for the case when there are double and single date digits.
   * testing just for formatting.
   */
  @Test
  public void testChartDoubleDay() {
    assertEquals("Performance of Portfolio cindy from 2024-02-03 to 2024-02-13\n" +
            "FEB 03 2024: *******\n" +
            "FEB 04 2024: *******\n" +
            "FEB 05 2024: *******\n" +
            "FEB 06 2024: *******\n" +
            "FEB 07 2024: *******\n" +
            "FEB 08 2024: *******\n" +
            "FEB 09 2024: *******\n" +
            "FEB 10 2024: *******\n" +
            "FEB 11 2024: *******\n" +
            "FEB 12 2024: *******\n" +
            "FEB 13 2024: *******\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2024-02-03", "2024-02-13"));
  }

  /**
   * this for the case where the time span is in years.
   */
  @Test
  public void testChartYear() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2020-01-11\n" +
            "DEC 2014: ***\n" +
            "DEC 2015: ****\n" +
            "DEC 2016: ****\n" +
            "DEC 2017: *****\n" +
            "DEC 2018: *****\n" +
            "JAN 2020: *******\n" +
            "Scale: * = 100000", cindy.getChart("cindy", "2014-04-15", "2020-01-11"));
  }

  /**
   * this is for the case where the timespan is every three months.
   */
  @Test
  public void testChartMonthThree() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2016-01-11\n" +
            "APR 2014: ***********************\n" +
            "JUL 2014: *************************\n" +
            "OCT 2014: ************************\n" +
            "JAN 2015: ***********************\n" +
            "APR 2015: ***********************\n" +
            "JUL 2015: ***************************\n" +
            "OCT 2015: *******************************\n" +
            "JAN 2016: *******************************\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2014-04-15", "2016-01-11"));
  }

  /**
   * this is for the case where the timespan is less than 5 months more than a month.
   */
  @Test
  public void testChartMonthLess() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2014-04-15", "2014-07-18"));
  }


  /**
   * when the first date given is before the second (month).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongMonth() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2014-09-15", "2014-07-18"));
  }

  /**
   * when the first date given is before the second (year).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongYear() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2024-09-15", "2014-07-18"));
  }

  /**
   * when the first date given is before the second (day).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongDay() {
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", cindy.getChart("cindy", "2014-09-15", "2014-09-14"));
  }

  /**
   * this tests the getbarChart method in the barChart class.
   */
  @Test
  public void testGetBarChart() {
    List<Double> listOfValues = new ArrayList<>(Arrays.asList(23000.0, 33000.0, 43000.0, 33000.0, 43000.0));
    List<String> listOfDates = new ArrayList<>(Arrays.asList("2014-04-30", "2014-05-31", "2014-06-30", "2014-07-30", "2014-08-15"));
    DataChart dataChart = new DataChart("cindy", "2014-04-15", "2014-08-15", listOfValues, 122);
    MyDate firstDate = new MyDateWithImpl(15, 04, 2014);
    //getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues)
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-08-15\n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: *********************************\n" +
            "JUN 2014: *******************************************\n" +
            "JUL 2014: *********************************\n" +
            "AUG 2014: *******************************************\n" +
            "Scale: * = 1000", dataChart.getBarChart(firstDate, "month", listOfDates, listOfValues));
  }

  /**
   * getBarChart method but with year.
   */
  @Test
  public void testGetBarChartYears() {
    List<Double> listOfValues = new ArrayList<>(Arrays.asList(15000.0, 33000.0, 63000.0, 33000.0, 74000.0, 70000.0));
    List<String> listOfDates = new ArrayList<>(Arrays.asList("2014-12-31", "2015-12-31", "2016-12-31",
            "2017-12-31", "2018-12-31", "2019-08-15"));
    DataChart dataChart = new DataChart("cindy", "2014-04-15", "2019-08-15", listOfValues, 122);
    MyDate firstDate = new MyDateWithImpl(15, 04, 2014);
    //getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues)
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2019-08-15\n" +
            "DEC 2014: **\n" +
            "DEC 2015: ****\n" +
            "DEC 2016: *******\n" +
            "DEC 2017: ****\n" +
            "DEC 2018: ********\n" +
            "AUG 2019: *******\n" +
            "Scale: * = 10000", dataChart.getBarChart(firstDate, "month", listOfDates, listOfValues));
  }


}
