package model;

import java.util.ArrayList;
import java.util.List;

// TODO CREATE INTERFACE FOR DATACHART AND RENAME GETBARCHART TO GETCHART


public class BarChart implements DataChart {
  private String startDate;
  private String endDate;
  private String name;
  private String scale;
  private Analyzable analyzable;
  private final String stockDirectory;

  /**
   * constructor that takes in the start and end date that the user
   * input.
   *
   * @param startDate start date.
   * @param endDate   end date.
   */
  public BarChart(String name, String startDate, String endDate, Analyzable analyzable, String stockDirectory) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.name = name;
    this.scale = "Scale: * = 10";
    this.analyzable = analyzable;
    this.stockDirectory = stockDirectory;
  }

  @Override
  public String getChart(String name, String startDate, String endDate, Analyzable analyzable) {
    String[] dateInfo = startDate.split("-");
    MyDate firstDate = new MyDateWithImpl(
            Integer.parseInt(dateInfo[2]),
            Integer.parseInt(dateInfo[1]),
            Integer.parseInt(dateInfo[0]));
    String[] dateInfoOther = endDate.split("-");
    MyDate secondDate = new MyDateWithImpl(
            Integer.parseInt(dateInfoOther[2]),
            Integer.parseInt(dateInfoOther[1]),
            Integer.parseInt(dateInfoOther[0]));
    List<String> listOfDates = new ArrayList<>();
    int result = secondDate.compareTo(firstDate);

    BarChart barChart = new BarChart(name, startDate, endDate, analyzable, stockDirectory);
    String decide = barChart.decideTimespan(result);
    List<Double> listOfValues = barChart.timeValue(firstDate, secondDate, result, decide, listOfDates);

    return barChart.getBarChart(firstDate, decide, listOfDates, listOfValues);

  }

  /**
   * this scales the list of the values for the dates.
   * and returns a list with the number of asterisks instead of.
   * the original value.
   *
   * @return the scaled values in a list, this is used to display the.
   * amount of asterisks for the bar chart.
   */
  private String scaleList(List<Double> listOfValues) {

    for (int i = 0; i < listOfValues.size(); i++) {
      double numberOfAstrisks = listOfValues.get(i);
      numberOfAstrisks = Math.ceil(numberOfAstrisks / 10);
      listOfValues.set(i, numberOfAstrisks);
    }
    double maxValue = listOfValues.stream().max(Double::compareTo).get();
    StringBuilder sbScale = new StringBuilder();
    while (checkMaxValue(maxValue)) {
      for (int i = 0; i < listOfValues.size(); i++) {
        double numberOfAstrisks = listOfValues.get(i);
        numberOfAstrisks = Math.ceil(numberOfAstrisks / 10);
        listOfValues.set(i, numberOfAstrisks);
        maxValue = listOfValues.stream().max(Double::compareTo).get();
        checkMaxValue(maxValue);
      }
      sbScale.append("0"); //10000
      scale = "Scale: * = 10" + sbScale;
    }
    return scale;
  }

  private boolean checkMaxValue(double maxValue) {
    return maxValue > 50;
  }

  @Override
  public String decideTimespan(int result) {
    StringBuilder builder = new StringBuilder();
    String decide = "";
    //greater than 5 years
    if (result > 1825) {
      decide = "year";
      //call method that gets the value of last day of year
    } else if (result < 1825 && result > 365) {
      //less than 5 years, time span every three months
      decide = "3month";
    } else if (result > 30 && result < 365) {
      //between 5 - 12 months, time span is every month
      decide = "month";
    } else if (result < 30) {
      //month timespan, time span is in days.
      decide = "day";
    } else if (result < 0) {
      throw new IllegalArgumentException("Start date cannot be after end date.");
    }
    return decide;
  }


  public List<Double> timeValue(MyDate firstDate, MyDate secondDate, int result, String decide, List<String> listOfDates) {
    List<Double> listOfValues = new ArrayList<>();
//    Portfolio existingPortfolio = new PortfolioWithImpl(portfolioName, stockDirectory, reference, true);
//    double value = 0;
    if (decide == "year") {
      int endYearAmount = firstDate.getEndYear(firstDate);
      firstDate.advance(endYearAmount);
      for (int i = 0; i <= ((result / 365) - 1); i++) {
        double value = analyzable.getValue(firstDate.toString(), stockDirectory);
        String date = firstDate.toString();
        listOfDates.add(date);
        listOfValues.add(value);
        firstDate.advance(365);
      }

      double value = analyzable.getValue(secondDate.toString(), stockDirectory);
      String date = secondDate.toString();
      listOfDates.add(date);
      listOfValues.add(value);
//      listOfDates.set(listOfDates.size() - 1, date);
//      listOfValues.set(listOfValues.size() - 1, value);

    } else if (decide == "3month") {
      int addAmount = firstDate.getLastDate(firstDate);
      firstDate.advance(addAmount);
      for (int i = 0; i <= result / 90; i++) {
        double value = analyzable.getValue(firstDate.toString(), stockDirectory);
        String date = firstDate.toString();
        listOfDates.add(date);
        listOfValues.add(value);
        int length = firstDate.getNextMonth(firstDate.getMonth(), firstDate.getYear());
        firstDate.advance(length);
        int length2 = firstDate.getNextMonth(firstDate.getMonth(), firstDate.getYear());
        firstDate.advance(length2);
        int length3 = firstDate.getNextMonth(firstDate.getMonth(), firstDate.getYear());
        firstDate.advance(length3);
      }
      double value = analyzable.getValue(secondDate.toString(), stockDirectory);
      String date = secondDate.toString();
      listOfDates.set(listOfDates.size() - 1, date);
      listOfValues.set(listOfValues.size() - 1, value);

    } else if (decide == "month") {
      int addAmount = firstDate.getLastDate(firstDate);
      firstDate.advance(addAmount);
      for (int i = 0; i <= result / 30; i++) {
        double value = analyzable.getValue(firstDate.toString(), stockDirectory);
        String date = firstDate.toString();
        listOfDates.add(date);
        listOfValues.add(value);
        int length = firstDate.getMonthLength(firstDate.getMonth(), firstDate.getYear());
        firstDate.advance(length);
      }

    } else if (decide == "day") {
      for (int i = 0; i <= result; i++) {
        double value = analyzable.getValue(firstDate.toString(), stockDirectory);
        String date = firstDate.toString();
        listOfDates.add(date);
        listOfValues.add(value);
        firstDate.advance(1);
      }
    }

    return listOfValues;
  }


  /**
   * this creates the string of the bar chart that is going
   * to display in the view.
   *
   * @return a string that represents the bar chart.
   */
  public String getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues) {
    StringBuilder sb = new StringBuilder();
//    MyDate date = new MyDateWithImpl(firstDate);
    scale = scaleList(listOfValues);
    sb.append("Performance of Stock/Portfolio " + name + " from " + startDate + " to " + endDate + "\n");

    if (listOfDates.size() < 5 && decide != "day") {
      for (int i = 0; i < 5 - listOfDates.size(); i++) {
        String currentDate = listOfDates.get(i);
        int month = Integer.parseInt(currentDate.substring(5, 7));
        int year = Integer.parseInt(currentDate.substring(0, 4));

        String fakeMonth = firstDate.getStringMonth(month - 1);
        sb.append(fakeMonth + " " + year + ": \n");
      }
    }

    for (int i = 0; i < listOfDates.size(); i++) {
      String curr = listOfDates.get(i);
      int month = Integer.parseInt(curr.substring(5, 7));
      int year = Integer.parseInt(curr.substring(0, 4));
      String monthStr = firstDate.getStringMonth(month);
      if (decide != "day") {
        sb.append(monthStr + " " + year + ": ");
      } else {
        int day = Integer.parseInt(curr.substring(8, 10));
        String string = String.format("%02d", day);
        sb.append(monthStr + " " + string + " " + year + ": ");
      }

      for (int j = 1; j <= listOfValues.get(i); j++) {
        sb.append("*");
      }
      sb.append("\n");
    }

    sb.append(scale);

    return sb.toString();
  }
}
