import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.MutableDataFrame;
import model.MutableDataFrameWithImpl;

import static org.junit.Assert.assertEquals;

/**
 * test class for mutable data frame.
 */
public class MutableDataFrameWithImplTest extends DataFrameTest {

  MutableDataFrame stock;

  @Before
  public void setUp() {
    String path = "StocksCindy/test/testingCSV/testStockFormat.csv";
    stock = new MutableDataFrameWithImpl(path);
  }

  @Test
  public void addRowTest() {
    Map<String, List<String>> input = new HashMap<>();
    input.put("timestamp", List.of("2024-06-19", "2024-06-20"));
    input.put("open", List.of("100", "101"));
    input.put("high", List.of("110", "111"));
    input.put("low", List.of("99", "98"));
    input.put("close", List.of("105", "105"));
    input.put("volume", List.of("10000", "10001"));
    stock.addLastRow(input);

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");
    assertEquals(expectedKeys, stock.getColumnNames());

    List<String> expectedTimeValue = new ArrayList<>(
            List.of("2024-06-03", "2024-05-31", "2024-05-30", "2024-06-19", "2024-06-20"));
    assertEquals(expectedTimeValue, stock.getColumn("timestamp"));

    List<String> expectedOpen = new ArrayList<>(
            List.of("173.8800", "173.4000", "176.6900", "100", "101"));
    assertEquals(expectedOpen, stock.getColumn("open"));

    List<String> expectedHigh = new ArrayList<>(
            List.of("175.8600", "174.4200", "176.6900", "110", "111"));
    assertEquals(expectedHigh, stock.getColumn("high"));

    List<String> expectedLow = new ArrayList<>(
            List.of("172.4500", "170.9700", "173.2300", "99", "98"));
    assertEquals(expectedLow, stock.getColumn("low"));

    List<String> expectedClose = new ArrayList<>(
            List.of("174.4200", "173.9600", "173.5600", "105", "105"));
    assertEquals(expectedClose, stock.getColumn("close"));

    List<String> expectedVolume = new ArrayList<>(
            List.of("18376370", "28085151", "18844036", "10000", "10001"));
    assertEquals(expectedVolume, stock.getColumn("volume"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addRowFail1() {
    Map<String, List<String>> input = new HashMap<>();
    input.put("timestamp", List.of("2024-06-19", "2024-06-20"));
    input.put("open", List.of("100", "101"));
    input.put("high", List.of("110", "111"));
    input.put("low", List.of("99", "98"));
    input.put("close", List.of("105", "105"));
    // missing volume
    stock.addLastRow(input);

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");
    assertEquals(expectedKeys, stock.getColumnNames());

    List<String> expectedTimeValue = new ArrayList<>(
            List.of("2024-06-03", "2024-05-31", "2024-05-30", "2024-06-19", "2024-06-20"));
    assertEquals(expectedTimeValue, stock.getColumn("timestamp"));

    List<String> expectedOpen = new ArrayList<>(
            List.of("173.8800", "173.4000", "176.6900", "100", "101"));
    assertEquals(expectedOpen, stock.getColumn("open"));

    List<String> expectedHigh = new ArrayList<>(
            List.of("175.8600", "174.4200", "176.6900", "110", "111"));
    assertEquals(expectedHigh, stock.getColumn("high"));

    List<String> expectedLow = new ArrayList<>(
            List.of("172.4500", "170.9700", "173.2300", "99", "98"));
    assertEquals(expectedLow, stock.getColumn("low"));

    List<String> expectedClose = new ArrayList<>(
            List.of("174.4200", "173.9600", "173.5600", "105", "105"));
    assertEquals(expectedClose, stock.getColumn("close"));

    List<String> expectedVolume = new ArrayList<>(
            List.of("18376370", "28085151", "18844036"));
    assertEquals(expectedVolume, stock.getColumn("volume"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addRowFail2() {
    Map<String, List<String>> input = new HashMap<>();
    input.put("timestamp", List.of("2024-06-19", "2024-06-20"));
    input.put("open", List.of("100", "101"));
    input.put("high", List.of("110", "111"));
    input.put("low", List.of("99", "98"));
    input.put("close", List.of("105", "105"));
    input.put("volume", List.of("10000", "10001"));
    input.put("extra column", List.of());
    stock.addLastRow(input);

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");
    assertEquals(expectedKeys, stock.getColumnNames());

    List<String> expectedTimeValue = new ArrayList<>(
            List.of("2024-06-03", "2024-05-31", "2024-05-30", "2024-06-19", "2024-06-20"));
    assertEquals(expectedTimeValue, stock.getColumn("timestamp"));

    List<String> expectedOpen = new ArrayList<>(
            List.of("173.8800", "173.4000", "176.6900", "100", "101"));
    assertEquals(expectedOpen, stock.getColumn("open"));

    List<String> expectedHigh = new ArrayList<>(
            List.of("175.8600", "174.4200", "176.6900", "110", "111"));
    assertEquals(expectedHigh, stock.getColumn("high"));

    List<String> expectedLow = new ArrayList<>(
            List.of("172.4500", "170.9700", "173.2300", "99", "98"));
    assertEquals(expectedLow, stock.getColumn("low"));

    List<String> expectedClose = new ArrayList<>(
            List.of("174.4200", "173.9600", "173.5600", "105", "105"));
    assertEquals(expectedClose, stock.getColumn("close"));

    List<String> expectedVolume = new ArrayList<>(
            List.of("18376370", "28085151", "18844036"));
    assertEquals(expectedVolume, stock.getColumn("volume"));

    assertEquals(List.of(), stock.getColumn("extra column"));
  }

  @Test
  public void addColumnTest() {

    stock.addNewColumn("Bobby", List.of("1", "2", "3"));

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");
    expectedKeys.add("Bobby");
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

    assertEquals(
            List.of("1", "2", "3"),
            stock.getColumn("Bobby")
    );
  }

  @Test(expected = IllegalArgumentException.class)
  public void addColumnTestFail() {

    stock.addNewColumn("Bobby", List.of("1", "2", "3", "4"));

    Set<String> expectedKeys = new HashSet<String>();
    expectedKeys.add("timestamp");
    expectedKeys.add("open");
    expectedKeys.add("high");
    expectedKeys.add("low");
    expectedKeys.add("close");
    expectedKeys.add("volume");
    expectedKeys.add("Bobby");
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

    assertEquals(
            List.of("1", "2", "3", "4"),
            stock.getColumn("Bobby")
    );
  }

}