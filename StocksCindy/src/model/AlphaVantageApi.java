package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is a stock API that uses the Alpha Vantage API.
 */
public class AlphaVantageApi implements StockApi {

  @Override
  public void addStock(String ticker) {
    String apiKey = "QVCUWBQJ0HN2H0MW";
    URL url = null;

    try {
      url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&outputsize=full&symbol=" + ticker + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException var10) {
      throw new RuntimeException("the alphavantage API has either changed or no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();

      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException var11) {
      throw new IllegalArgumentException("No price data found for " + ticker);
    }

    String directory = "CSVFiles/";

    try {
      File fw = new File(directory + ticker + ".csv");
      FileWriter writer = new FileWriter(fw);
      writer.write(output.toString());
    } catch (IOException var9) {
    }

    System.out.println("Return value: ");
    System.out.println(output.toString());
  }
}
