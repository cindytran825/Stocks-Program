package model;

import java.util.List;

/**
 * represents the model. It does the implementations.
 * and it is called in the controller when it picks up the user inputs.
 */
public interface Model {

   Stock generateStock(StockApi api, String ticker);

   void createPortfolio(String name);

   void managePortfolio(String name, String ticker, int shares);

   String evaluatePortfolio(String name, String date);

   String evaluateStock(String ticker, String startDate, String endDate);

   String movingAverage(String ticker, String startDate, int lastX);

   String getCrossoverDays(String ticker, String startDate, int lastX);

  String getPortfolioNames();

  String getPortfolio(String name);

}
