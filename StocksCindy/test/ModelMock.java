import java.util.List;
import java.util.Objects;

import model.Model;
import model.Portfolio;
import model.Stock;
import model.StockApi;


public class ModelMock implements Model {
  final StringBuilder log;


  ModelMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  ModelMock(){

    this.log = null;
  }

  public Stock generateStock(String ticker) {
    return new Stock();
  }

  @Override
  public void createPortfolio(String name) {
    log.append("Creating portfolio " + name + "\n");
  }

  @Override
  public void managePortfolio(String name, String ticker, int shares) {
    log.append(ticker + " " + shares);
  }

  @Override
  public String evaluatePortfolio(String name, String date) {
    log.append("Evaluating portfolio " + date);
    return "";
  }

  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    log.append("Evaluating stock " + ticker + " " + startDate + " " + endDate);
    return "";
  }

  @Override
  public String movingAverage(String ticker, String startDate, int lastX) {
    log.append("Moving average " + ticker + " " + startDate + " " + lastX);
    return "";
  }

  @Override
  public String getCrossoverDays(String ticker, String startDate, int lastX) {
    log.append("Stock crossover " + ticker + " " + startDate + " " + lastX);
    return "";
  }

  @Override
  public String getPortfolioNames() {
    log.append("wow\n");
    return log.toString();
  }

  @Override
  public String getPortfolio(String name) {return "";
  }

  public String getStockNames() {
    return "";
  }

}
