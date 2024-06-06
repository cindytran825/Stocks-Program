package Model;

import java.util.ArrayList;
import java.util.List;

public class Stock {

  private final String ticker;
  private final DataFrame data;
  public Stock(String ticker, String path) {
    this.ticker = ticker;
    // "StocksCindy/CSVFiles/" + ticker + ".csv"
    this.data = new DataFrame(path);
    // still have to figure out how to get it to automatically get new tickers and stuff

    // if statement before assigning Data, have it check if the csv file exists or not, then
    // either write a new file or just send it the csv filepath
  }

  // private Stock(String CSVLink) {}

  private MyDate convertToDate(String date) {
    String[] dateSplit = date.split("-");
    MyDate newMyDate = new MyDate(
            Integer.parseInt(dateSplit[2]),
            Integer.parseInt(dateSplit[1]),
            Integer.parseInt(dateSplit[0])
    );
    return newMyDate;
  }

  private int getClosestDateIndex(String date, boolean closestAfter) {
    List<String> dateList = data.getColumn("timestamp");

    MyDate origin = convertToDate(date);
    int advanceIncrement = closestAfter ? 1 : -1;

    int counter = 0;
    int index;
    while (counter <= 30) { //instead of counter limit, better implementation would be to enter time limits and check after each advance.
      index = dateList.indexOf(origin.toString());
      if (index != -1) {
        return index;
      } else {
        origin.advance(advanceIncrement);
        counter++;
      }
    }
    return -1;
  }

  public double calculateNetGain(String start, String end) throws IllegalArgumentException {
    // needs to test what happens when dates that are out of range are entered
    // individually test start and end alternating out of range, then out of range entirely.
    int startIndex = getClosestDateIndex(start, true);
    int endIndex = getClosestDateIndex(end, false);

    if (startIndex == -1 || endIndex == -1) { // having being out of range throw for now
      throw new IllegalArgumentException("There are no data available for that date range.");
    }

    List<String> tempData = data.getColumn("close");
    double startValue = Double.parseDouble(tempData.get(startIndex));
    double endValue = Double.parseDouble(tempData.get(endIndex));
    return endValue - startValue;
  }

  public double getMovingAverage(String startDate, int x) throws IllegalArgumentException {

    List<String> tempDataClose = data.getColumn("close");

    double totalValue = 0;
    int startDateIndex = getClosestDateIndex(startDate, true);

    for (int i = startDateIndex; i > startDateIndex - x; i--) {
      totalValue += Double.parseDouble(tempDataClose.get(i));
    }

    return totalValue / x;
  }


  public List<String> getCrossOver(String startDate, int x) {
    List<String> crossDays = new ArrayList<>();
    double avg;
    List<String> tempDataClose = data.getColumn("close");
    List<String> tempDataDate = data.getColumn("timestamp");
    int dateIndex = getClosestDateIndex(startDate, true);

    double closePrice;
    for (int i = 1; i <= x; i++) {
      avg = getMovingAverage(startDate, i);

      closePrice = Double.parseDouble(tempDataClose.get(dateIndex - i + 1));

      if (closePrice > avg) {
        crossDays.add(tempDataDate.get(dateIndex - i + 1));
      }
    }
    return crossDays;
  }

  public String getTicker() {
    return this.ticker;
  }
}
