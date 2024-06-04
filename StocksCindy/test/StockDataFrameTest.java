import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import Model.StockDataFrame;

import static org.junit.Assert.*;

public class StockDataFrameTest {

  @Test
  public void initializeTest() {
    String path = "StocksCindy/test/testingCSV/testStockFormat.csv";
    StockDataFrame stock = new StockDataFrame(path);

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");

    assertEquals(expectedKeys, stock.getColumnNames());
  }

}