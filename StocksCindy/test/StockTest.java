import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Stock;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Stock.
 */
public class StockTest {

  Stock goog;
  Stock testStock;

  /**
   * setup for testing.
   */
  @Before
  public void setUp() {

    goog = new Stock("GOOG", "StocksCindy/test/testingCSV/GOOG.csv");
    testStock = new Stock("testStockFormat",
            "StocksCindy/test/testingCSV/testSTockFormat.csv");
  }

  /**
   * testing the getters.
   */
  @Test
  public void initializationTestAndGetters() {
    Stock testStock = new Stock("testStockFormat",
            "StocksCindy/test/testingCSV/testSTockFormat.csv");
    assertEquals("testStockFormat", testStock.getTicker());

    List<String> expectedTimestamp = List.of("2024-06-03", "2024-05-31", "2024-05-30");
    assertEquals(expectedTimestamp, testStock.getTimestamp());

    List<Double> expectedOpen = List.of(173.8800, 173.4000, 176.6900);
    assertEquals(expectedOpen, testStock.getOpen());

    List<Double> expectedHigh = List.of(175.8600, 174.4200, 176.6900);
    assertEquals(expectedHigh, testStock.getHigh());

    List<Double> expectedLow = List.of(172.4500, 170.9700, 173.2300);
    assertEquals(expectedLow, testStock.getLow());

    List<Double> expectedClose = List.of(174.4200, 173.9600, 173.5600);
    assertEquals(expectedClose, testStock.getClose());

    List<Double> expectedVolume = List.of(18376370.0, 28085151.0, 18844036.0);
    assertEquals(expectedVolume, testStock.getVolume());
  }

  /**
   * tests the net gain method.
   */
  @Test
  public void TestCalculateNetGain() {
    // 1217.5600 & 156.3300 : loss
    // 2020-04-13 (closest date) to 2024-04-15
    assertEquals(-1061.2300, goog.calculateNetGain("2020-04-10", "2024-04-15"), 0.01);

    // 536.4400 & 156.3300 : loss (buy date out of range)
    // 2014-04-15 (closest date) to 2024-04-15
    assertEquals(-380.1100, goog.calculateNetGain("2014-04-10", "2024-04-15"), 0.01);

    // 157.6600 & 174.4200 : gain (sell date out of range)
    // 2024-04-10 to 2024-06-03 (closest date)
    assertEquals(16.76, goog.calculateNetGain("2024-04-10", "2024-06-15"), 0.01);

    // 536.4400 & 174.4200 : loss (buy and sell date out of range)
    // 2014-04-15 (closest date) to 2024-06-03 (closest date)
    assertEquals(-362.02, goog.calculateNetGain("2014-04-10", "2024-06-15"), 0.01);
  }

  /**
   * tests when it is out of range (net/gain).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCalculateNetGainOutOfRange() {
    assertEquals(-362.02, goog.calculateNetGain("2027-04-10", "2030-06-15"), 0.01);
  }

  /**
   * test getaverage method.
   */
  @Test
  public void testAverage() {
    double avg2 = testStock.getMovingAverage("2024-05-28", 3);
    assertEquals(173.9800, avg2, 0.01);

    double avg1 = goog.getMovingAverage("2024-04-23", 29);
    assertEquals(171.6634, avg1, 0.01);
  }

  /**
   * check exception when it fails.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAverageFail() {
    double avg2 = testStock.getMovingAverage("2024-05-28", 10);
    assertEquals(173.9800, avg2, 0.01);
  }

  /**
   * test the crossover method.
   */
  @Test
  public void testCrossDays() {
    List<String> expectedCross1 = new ArrayList<>(
            List.of(
                    "2024-05-31",
                    "2024-06-03"
            )
    );
    assertEquals(expectedCross1, testStock.getCrossOver("2024-05-28", 3));

    List<String> expectedCross2 = new ArrayList<>(
            List.of(
                    "2024-04-24",
                    "2024-04-26",
                    "2024-04-29",
                    "2024-04-30",
                    "2024-05-01",
                    "2024-05-02",
                    "2024-05-03",
                    "2024-05-06",
                    "2024-05-07",
                    "2024-05-08",
                    "2024-05-09",
                    "2024-05-10",
                    "2024-05-13",
                    "2024-05-14",
                    "2024-05-15",
                    "2024-05-16",
                    "2024-05-17",
                    "2024-05-20",
                    "2024-05-21",
                    "2024-05-22",
                    "2024-05-23",
                    "2024-05-24",
                    "2024-05-28",
                    "2024-05-29",
                    "2024-05-30",
                    "2024-05-31",
                    "2024-06-03"
            )
    );
    assertEquals(expectedCross2, goog.getCrossOver("2024-04-23", 29));
  }

}