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

  @Override
  public Stock generateStock(StockApi api, String ticker) {
    return null;
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
    return "";
  }

  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    return "";
  }

  @Override
  public String movingAverage(String ticker, String startDate, int lastX) {
    return "";
  }

  @Override
  public String getCrossoverDays(String ticker, String startDate, int lastX) {
    return "";
  }

  @Override
  public String getPortfolioNames() {
    log.append("wow");
    return log.toString();
  }

  @Override
  public String getPortfolio(String name) {return "";
  }
}
