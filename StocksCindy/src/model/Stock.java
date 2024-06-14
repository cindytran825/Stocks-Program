package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a normal stock with timestamp, open, low, high, close, and volume data.
 * It also offers abilities to analyze the stocks data such as its value, moving average,
 * crossover days.
 */
public class Stock implements Stocks {

  private final String ticker;
  private final DataFrameWithImpl data;

  /**
   * This constructor assigns a ticker to the stock and creates a data frame of all the available.
   * stock data of the specified ticker.
   *
   * @param ticker    stock ticker
   * @param directory directory where the file would be stored
   */
  public Stock(String ticker, String directory) {
    this.ticker = ticker;
    this.data = new DataFrameWithImpl(directory + "/" + ticker + ".csv");
  }

  /**
   * this converts the date into string.
   *
   * @param date string user input.
   * @return a date.
   */
  private MyDate convertToDate(String date) {
    String[] dateSplit = date.split("-");
    MyDate newMyDate = new MyDateWithImpl(
            Integer.parseInt(dateSplit[2]),
            Integer.parseInt(dateSplit[1]),
            Integer.parseInt(dateSplit[0])
    );
    return newMyDate;
  }

  @Override
  public int getClosestDateIndex(String date, boolean closestAfter) {
    List<String> dateList = this.getTimestamp();

    MyDate origin = convertToDate(date);
    int advanceIncrement = closestAfter ? 1 : -1;

    int counter = 0;
    int index;
    while (counter <= 30) { // counter limit: if there's been no data up to a month, it's invalid.
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

  @Override
  public double calculateNetGain(String start, String end) throws IllegalArgumentException {
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

  @Override
  public double getMovingAverage(String startDate, double lastX) throws IllegalArgumentException {
    List<String> tempDataClose = data.getColumn("close");

    double totalValue = 0;
    int startDateIndex = getClosestDateIndex(startDate, true);

    if (startDateIndex - lastX + 1 < 0) {
      List<String> dates = this.getTimestamp();
      throw new IllegalArgumentException(
              "There are not enough data points to fulfill request.\n"
                      + "We only have data from " + dates.get(0)
                      + " to " + dates.get(dates.size() - 1));
    }

    for (int i = startDateIndex; i > startDateIndex - lastX; i--) {
      totalValue += Double.parseDouble(tempDataClose.get(i));
    }
    return totalValue / lastX;
  }

  @Override
  public List<String> getCrossOver(String startDate, double lastX) {
    List<String> crossDays = new ArrayList<>();
    double avg;
    List<String> tempDataClose = data.getColumn("close");
    List<String> tempDataDate = data.getColumn("timestamp");
    int dateIndex = getClosestDateIndex(startDate, true);

    double closePrice;
    for (int i = 1; i <= lastX; i++) {
      avg = getMovingAverage(startDate, i);

      closePrice = Double.parseDouble(tempDataClose.get(dateIndex - i + 1));

      if (closePrice > avg) {
        crossDays.add(tempDataDate.get(dateIndex - i + 1));
      }
    }
    return crossDays;
  }

  private boolean checkDateChronology(
          String date,
          List<String> dateList
  ) {
    MyDate inputDate = new MyDateWithImpl(date);
    // check if the date is an available data point
    return dateList.contains(inputDate.toString());
  }

  @Override
  public double getValue(String date, String stockDirectory) throws IllegalArgumentException {
    double result;
    List<String> time = this.getTimestamp();

    List<String> tempData = data.getColumn("close");
    if (!data.getColumnNames().contains("close")) {
      throw new IllegalArgumentException("Invalid input.");
    }
    if (!checkDateChronology(date, time)) {
      int next = getClosestDateIndex(date, false);
      result = Double.parseDouble(tempData.get(next));
    } else {
      result = Double.parseDouble(tempData.get(time.indexOf(date)));
    }
    return result;
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public List<String> getTimestamp() {
    return data.getColumn("timestamp");
  }

  private List<Double> toDouble(List<String> s) {
    List<Double> doubles = new ArrayList<>();
    for (String i : s) {
      doubles.add(Double.parseDouble(i));
    }
    return doubles;
  }

  @Override
  public List<Double> getOpen() {
    List<String> temp = data.getColumn("open");
    return toDouble(temp);
  }

  @Override
  public List<Double> getHigh() {
    List<String> temp = data.getColumn("high");
    return toDouble(temp);
  }

  @Override
  public List<Double> getLow() {
    List<String> temp = data.getColumn("low");
    return toDouble(temp);
  }

  @Override
  public List<Double> getClose() {
    List<String> temp = data.getColumn("close");
    return toDouble(temp);
  }

  @Override
  public List<Double> getVolume() {
    List<String> temp = data.getColumn("volume");
    return toDouble(temp);
  }
}
