import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.DataFrameWithImpl;

import static org.junit.Assert.assertEquals;

/**
 * test for dataframe class.
 */
public class DataFrameTest {

  DataFrameWithImpl stock;

  @Before
  public void setUp() {
    String path = "StocksCindy/test/testingCSV/testStockFormat.csv";
    stock = new DataFrameWithImpl(path);
  }

  @Test
  public void initializeTest() {

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");

    assertEquals(expectedKeys, stock.getColumnNames());

    List<String> expectedTimeValue = new ArrayList<>(
            List.of("2024-06-03", "2024-05-31", "2024-05-30"));
    assertEquals(expectedTimeValue, stock.getColumn("timestamp"));

    List<String> expectedOpen = new ArrayList<>(
            List.of("173.8800", "173.4000", "176.6900"));
    assertEquals(expectedOpen, stock.getColumn("open"));

    List<String> expectedHigh = new ArrayList<>(
            List.of("175.8600", "174.4200", "176.6900"));
    assertEquals(expectedHigh, stock.getColumn("high"));

    List<String> expectedLow = new ArrayList<>(
            List.of("172.4500", "170.9700", "173.2300"));
    assertEquals(expectedLow, stock.getColumn("low"));

    List<String> expectedClose = new ArrayList<>(
            List.of("174.4200", "173.9600", "173.5600"));
    assertEquals(expectedClose, stock.getColumn("close"));

    List<String> expectedVolume = new ArrayList<>(
            List.of("18376370", "28085151", "18844036"));
    assertEquals(expectedVolume, stock.getColumn("volume"));
  }

  @Test
  public void testColumnSize() {
    assertEquals(3, stock.getColumnSize());
  }

  @Test
  public void testToString() {
    String expected = "timestamp,open,high,low,close,volume\n"
            + "2024-06-03,173.8800,175.8600,172.4500,174.4200,18376370\n"
            + "2024-05-31,173.4000,174.4200,170.9700,173.9600,28085151\n"
            + "2024-05-30,176.6900,176.6900,173.2300,173.5600,18844036";
    assertEquals(expected, stock.toString());
  }

}