import org.junit.Before;
import org.junit.Test;

import model.Analyzable;
import model.DataChart;
import model.Portfolio;
import model.PortfolioWithImpl;
import model.StocksModel;

import static org.junit.Assert.assertEquals;

public class DataChartTest {

  private String testFolderPath;

  @Before
  public void setUp() {
    String testFolderPath = "StocksCindy/test/testingCSV";
  }

  /**
   * tests for the bar chart method.
   * test range months.
   */
  @Test
  public void testChartMonth() {
    Portfolio cindy = new PortfolioWithImpl("cindy", testFolderPath, testFolderPath, false);
    cindy.buyStock("GOOG", 423.0, "2014-04-15");
    DataChart data = new DataChart("cindy", "2024-02-03", "2024-06-28", cindy);

    assertEquals("Performance of Portfolio cindy from 2024-02-03 to 2024-06-28\n" +
            "FEB 2024: ******\n" +
            "MAR 2024: *******\n" +
            "APR 2024: ********\n" +
            "MAY 2024: ********\n" +
            "JUN 2024: ********\n" +
            "Scale: * = 10000", data.getChart("cindy", "2024-02-03", "2024-06-28", cindy));
  }
}
