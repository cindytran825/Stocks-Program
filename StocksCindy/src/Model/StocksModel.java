package Model;

import java.util.List;

import Model.Stocks;

public class StocksModel implements Stocks {
  Stocks stocks;
  String ticker;
  String date1;
  String date2;

  //exception that
  //checks if the given date is within the given dates(2024-2014)


  //this get the two dates and dates in between and puts them in list.
  //return an array
//  private String getDate(String date) {
//    return null;
//  }

  public StocksModel() {

  }

  StocksModel(String ticker, String date1, String date2) {
    this.ticker = ticker;
    this.date1 = date1;
    this.date2 = date2;
  }

  @Override
  public double calculateNetGain(String startDate, String endDate) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public double getMovingAverage(String startDate, int lastX) {
    return 0;
  }

  @Override
  public List<String> getCrossOver(String startDate, int lastX) {
    return List.of();
  }

  @Override
  public String getTicker() {
    return "";
  }

  @Override
  public List<String> getTimestamp() {
    return List.of();
  }

  @Override
  public List<Double> getOpen() {
    return List.of();
  }

  @Override
  public List<Double> getHigh() {
    return List.of();
  }

  @Override
  public List<Double> getLow() {
    return List.of();
  }

  @Override
  public List<Double> getClose() {
    return List.of();
  }

  @Override
  public List<Double> getVolume() {
    return List.of();
  }
}
