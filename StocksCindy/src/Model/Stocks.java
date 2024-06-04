package Model;

public interface Stocks {

  //method that calculates the gain/loss
  //specific period
  public int gainLoss(String startDate, String endDate);

  //getters that get the end and start dates
  public String getEnd();

  public String getStart();


  //method that lets the person buy a stock ??
  //adds stock to the portfolio
  public Stocks addStock(String startDate, String endDate);




  //method that takes in the dates and returns the moving average
  //of the last x days
  public Stocks movingAverage(int date1, int date2);




}
