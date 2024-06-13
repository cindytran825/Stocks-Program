import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import model.Analyzable;
import model.DataChart;
import model.MyDate;
import model.MyDateWithImpl;
import model.Portfolio;
import model.PortfolioWithImpl;
import model.Stock;
import model.Stocks;
import model.StocksModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataChartTest {

  String testFolderPath;
  Portfolio cindy;
  Stock goog;

  @Before
  public void setUp() {
    testFolderPath = "StocksCindy/test/testingCSV/";
    cindy = new PortfolioWithImpl(
            "cindy",
            testFolderPath,
            testFolderPath,
            true);

    goog = new Stock("GOOG", "StocksCindy/test/testingCSV/");

  }





  /**
   * tests for the bar chart method.
   * test range months.
   */
  @Test
  public void testChartMonth() {
    cindy = new PortfolioWithImpl("cindy", testFolderPath, testFolderPath, false);
    cindy.buyStock("GOOG", 423.0, "2014-04-15");
    DataChart data = new DataChart("cindy", "2024-02-03", "2024-06-28", cindy, testFolderPath);

    assertEquals("Performance of Stock/Portfolio cindy from 2024-02-03 to 2024-06-28\n" +
            "FEB 2024: ******\n" +
            "MAR 2024: *******\n" +
            "APR 2024: ********\n" +
            "MAY 2024: ********\n" +
            "JUN 2024: ********\n" +
            "Scale: * = 10000", data.getChart("cindy", "2024-02-03", "2024-06-28", cindy));
  }

  /**
   //   *test for getChart when range 5 days.
   //   */
  @Test
  public void testChartDay() {
    DataChart data = new DataChart("cindy", "2024-02-03", "2024-02-07", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2024-02-03 to 2024-02-07\n" +
            "FEB 03 2024: *******\n" +
            "FEB 04 2024: *******\n" +
            "FEB 05 2024: *******\n" +
            "FEB 06 2024: *******\n" +
            "FEB 07 2024: *******\n" +
            "Scale: * = 10000", data.getChart("cindy", "2024-02-03", "2024-02-07", cindy));
  }

  /**
   *test for getChart when range is 2 days.
   */
  @Test
  public void testChartDay2() {
    DataChart data = new DataChart("cindy", "2024-02-03", "2024-02-04", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2024-02-03 to 2024-02-04\n" +
            "FEB 03 2024: *******\n" +
            "FEB 04 2024: *******\n" +
            "Scale: * = 10000", data.getChart("cindy", "2024-02-03", "2024-02-04", cindy));
  }

  /**
   * this is for the case when there are double and single date digits.
   * testing just for formatting.
   */
  @Test
  public void testChartDoubleDay() {
    DataChart data = new DataChart("cindy", "2024-02-03", "2024-02-13", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2024-02-03 to 2024-02-13\n" +
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
            "Scale: * = 10000", data.getChart("cindy", "2024-02-03", "2024-02-13", cindy));
  }

  /**
   * this for the case where the time span is in years.
   */
  @Test
  public void testChartYear() {
    DataChart data = new DataChart("cindy", "2014-04-15", "2020-01-11", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2020-01-11\n" +
            "DEC 2014: ***\n" +
            "DEC 2015: ****\n" +
            "DEC 2016: ****\n" +
            "DEC 2017: *****\n" +
            "DEC 2018: *****\n" +
            "JAN 2020: *******\n" +
            "Scale: * = 100000", data.getChart("cindy", "2014-04-15", "2020-01-11", cindy));
  }

  /**
   * this is for the case where the timespan is every three months.
   */
  @Test
  public void testChartMonthThree() {
    DataChart data = new DataChart("cindy", "2014-04-15",  "2016-01-11", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2016-01-11\n" +
            "APR 2014: ***********************\n" +
            "JUL 2014: *************************\n" +
            "OCT 2014: ************************\n" +
            "JAN 2015: ***********************\n" +
            "APR 2015: ***********************\n" +
            "JUL 2015: ***************************\n" +
            "OCT 2015: *******************************\n" +
            "JAN 2016: *******************************\n" +
            "Scale: * = 10000", data.getChart("cindy", "2014-04-15", "2016-01-11", cindy));
  }

//  /**
//   * this is for the case where the timespan is less than 5 months more than a month.
//   */
//  @Test
//  public void testChartMonthLess() {
//    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
//            "MAR 2014: \n" +
//            "APR 2014: ***********************\n" +
//            "MAY 2014: ************************\n" +
//            "JUN 2014: *************************\n" +
//            "JUL 2014: *************************\n" +
//            "Scale: * = 10000", cindy.getChart("cindy", "2014-04-15", "2014-07-18"));
//  }


  /**
   * when the first date given is before the second (month).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongMonth() {
    DataChart data = new DataChart("cindy", "2014-04-15",  "2014-03-11", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", data.getChart("cindy", "2014-04-15", "2014-03-11", cindy));
  }

  /**
   * when the first date given is before the second (year).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongYear() {
    DataChart data = new DataChart("cindy", "2024-09-15",  "2014-07-18", cindy, testFolderPath);
    assertEquals("Performance of Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", data.getChart("cindy", "2024-09-15", "2014-07-18", cindy));
  }

  /**
   * when the first date given is before the second (day).
   */
  @Test(expected = NoSuchElementException.class)
  public void testWrongDay() {
    DataChart data = new DataChart("cindy", "2014-09-15",  "2014-09-14", cindy, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", data.getChart("cindy", "2014-09-15", "2014-09-14", cindy));
  }

  /**
   * this tests the getbarChart method in the barChart class.
   */
  @Test
  public void testGetBarChart() {
    List<Double> listOfValues = new ArrayList<>(Arrays.asList(23000.0, 33000.0, 43000.0, 33000.0, 43000.0));
    List<String> listOfDates = new ArrayList<>(Arrays.asList("2014-04-30", "2014-05-31", "2014-06-30", "2014-07-30", "2014-08-15"));
    DataChart data = new DataChart("cindy", "2014-09-15",  "2014-09-14", cindy, testFolderPath);

    MyDate firstDate = new MyDateWithImpl(15, 04, 2014);
    //getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues)
    assertEquals("Performance of Stock/Portfolio cindy from 2014-09-15 to 2014-09-14\n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: *********************************\n" +
            "JUN 2014: *******************************************\n" +
            "JUL 2014: *********************************\n" +
            "AUG 2014: *******************************************\n" +
            "Scale: * = 1000", data.getBarChart(firstDate, "month", listOfDates, listOfValues));
  }

  /**
   * getBarChart method but with year.
   */
  @Test
  public void testGetBarChartYears() {
    List<Double> listOfValues = new ArrayList<>(Arrays.asList(15000.0, 33000.0, 63000.0, 33000.0, 74000.0, 70000.0));
    List<String> listOfDates = new ArrayList<>(Arrays.asList("2014-12-31", "2015-12-31", "2016-12-31",
            "2017-12-31", "2018-12-31", "2019-08-15"));
    DataChart dataChart = new DataChart("cindy", "2014-04-15", "2019-08-15",  cindy, testFolderPath);
    MyDate firstDate = new MyDateWithImpl(15, 04, 2014);
    //getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues)
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2019-08-15\n" +
            "DEC 2014: **\n" +
            "DEC 2015: ****\n" +
            "DEC 2016: *******\n" +
            "DEC 2017: ****\n" +
            "DEC 2018: ********\n" +
            "AUG 2019: *******\n" +
            "Scale: * = 10000", dataChart.getBarChart(firstDate, "month", listOfDates, listOfValues));
  }

  @Test
  public void testGetBarStock() {
    DataChart data = new DataChart("cindy", "2014-04-15", "2024-05-16",  goog, testFolderPath);
    assertEquals("Performance of Stock/Portfolio cindy from 2014-04-15 to 2014-07-18\n" +
            "MAR 2014: \n" +
            "APR 2014: ***********************\n" +
            "MAY 2014: ************************\n" +
            "JUN 2014: *************************\n" +
            "JUL 2014: *************************\n" +
            "Scale: * = 10000", data.getChart("cindy", "2014-04-15", "2024-05-16", goog));
  }



}
