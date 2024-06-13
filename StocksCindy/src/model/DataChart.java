package model;

import java.util.List;

/**
 *
 */
public class DataChart extends StocksModel {
  private String startDate;
  private String endDate;
  private String name;
  private List<Double> listOfValues;
  private String scale;

  //  /**
//   * constructor that takes in the start and end date that the user.
//   * input.
//   * @param startDate start date.
//   * @param endDate end date.
//   */
  public DataChart(String name, String startDate, String endDate, List<Double> listOfValues, int result) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.name = name;
    this.listOfValues = listOfValues;
    this.scale = scale;
  }

//  public String getScaledString(String startDate, String endDate) {
//
//  }

  /**
   * this scales the list of the values for the dates.
   * and returns a list with the number of asterisks instead of.
   * the original value.
   *
   * @return the scaled values in a list, this is used to display the.
   * amount of asterisks for the bar chart.
   */
  private String scaleList(List<Double> listOfValues) {
    scale = this.scale;

    for (int i = 0; i < listOfValues.size(); i++) {
      double numberOfAstrisks = listOfValues.get(i);
      numberOfAstrisks = Math.ceil(numberOfAstrisks / 1000);
      listOfValues.set(i, numberOfAstrisks);
      scale = "Scale: * = 1000";
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
      scale = "Scale: * = 1000" + sbScale;
    }
    return scale;
  }

  private boolean checkMaxValue(double maxValue) {
    return maxValue > 50;
  }




  /**
   * this creates the string of the bar chart that is going.
   * to display in the view.
   *
   * @return a string that represents the bar chart.
   */
  public String getBarChart(MyDate firstDate, String decide, List<String> listOfDates, List<Double> listOfValues) {
    StringBuilder sb = new StringBuilder();
//    MyDate date = new MyDateWithImpl(firstDate);
    scale = scaleList(listOfValues);
    sb.append("Performance of Portfolio " + name + " from " + startDate + " to " + endDate + "\n");

//    if (listOfDates.size() < 5 && decide != "day") {
//      int month = Integer.parseInt(firstDate.toString().substring(5, 7));
//      int year = Integer.parseInt(firstDate.toString().substring(0, 4));
//      for (int i = 0; i < listOfDates.size(); i++) {
////        String curr = listOfDates.get(i);
//        String fakeMonth = firstDate.getStringMonth(month - 1);
//        sb.append(fakeMonth + " " + year + ": \n");
//      }
//    }

    for (int i = 0; i < listOfDates.size(); i++) {
      String curr = listOfDates.get(i);
      int month = Integer.parseInt(curr.substring(5, 7));
      int year = Integer.parseInt(curr.substring(0, 4));
      String monthStr = firstDate.getStringMonth(month);
      if(decide != "day") {
        sb.append(monthStr + " " + year + ": ");
      }
      else {
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
