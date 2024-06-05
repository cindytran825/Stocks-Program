package Model;

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

  StocksModel() {

  }

  StocksModel(String ticker, String date1, String date2) {
    this.ticker = ticker;
    this.date1 = date1;
    this.date2 = date2;
  }


  @Override
  public int gainLoss(String startDate, String endDate) {
    return 0;
  }
  //
  @Override
  public String getEnd() {
    return "";
  }

  @Override
  public String getStart() {
    return "";
  }

  @Override
  public Stocks addStock(String startDate, String endDate) {
    return null;
  }

  @Override
  public Stocks movingAverage(int date1, int date2) {
    return null;
  }



}
