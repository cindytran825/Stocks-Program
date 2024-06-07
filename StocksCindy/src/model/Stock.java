package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a normal stock.
 */
public class Stock implements Stocks {

  private final String ticker;
  private final DataFrameWithImpl data;

  /**
   * This constructor assigns a ticker to the stock and creates a data frame of all the available
   * stock data of the specified ticker.
   *
   * @param ticker stock ticker
   * @param directory   directory where the file would be stored
   */
  public Stock(String ticker, String directory) {
    this.ticker = ticker;
    this.data = new DataFrameWithImpl(directory + "/" + ticker + ".csv");
    // still have to figure out how to get it to automatically get new tickers and stuff

    // if statement before assigning Data, have it check if the csv file exists or not, then
    // either write a new file or just send it the csv filepath

    // idea is to have this checked in the Model that receives an input from the controller.
    // it will say, if it can't find the stock file, get one from the API
  }

  public Stock() {
    this.ticker = null;
    this.data = null;
  }

  private MyDate convertToDate(String date) {
    String[] dateSplit = date.split("-");
    MyDate newMyDate = new MyDateWithImpl(
            Integer.parseInt(dateSplit[2]),
            Integer.parseInt(dateSplit[1]),
            Integer.parseInt(dateSplit[0])
    );
    return newMyDate;
  }

  /**
   * this gets the closest available date with data points
   * @param date a date input
   * @param closestAfter true if look at the closest date after the input and false for the closest
   *                     before the input date
   * @return
   */
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
  public double getMovingAverage(String startDate, int lastX) throws IllegalArgumentException {

    List<String> tempDataClose = data.getColumn("close");

    double totalValue = 0;
    int startDateIndex = getClosestDateIndex(startDate, true);

    if (startDateIndex - lastX + 1 < 0) {
      List<String> dates = this.getTimestamp();
      throw new IllegalArgumentException(
              "There are not enough data points to fulfill request.\n"
      + "We only have data from " + dates.get(0) + " to " + dates.get(dates.size() - 1));
    }

    for (int i = startDateIndex; i > startDateIndex - lastX; i--) {
      totalValue += Double.parseDouble(tempDataClose.get(i));
    }
    return totalValue / lastX;
  }

  @Override
  public List<String> getCrossOver(String startDate, int lastX) {
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
