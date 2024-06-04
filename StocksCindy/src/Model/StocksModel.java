package Model;

import Model.Stocks;

public class StocksModel implements Stocks {
  String date;

  //exception that
  //checks if the given date is within the given dates(2024-2014)


  //this get the two dates and dates in between and puts them in list.
  //return an array
//  private String getDate(String date) {
//    return null;
//  }


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
