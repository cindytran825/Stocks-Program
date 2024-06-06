import java.util.List;

import model.Model;
import model.Stock;
import model.StockApi;

public class ModelMock implements Model {

  @Override
  public Stock generateStock(StockApi api, String ticker) {
    return null;
  }

  @Override
  public void createPortfolio(String name) {

  }

  @Override
  public void managePortfolio(String name, String ticker, int shares) {

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
    return "";
  }

  @Override
  public String getPortfolio(String name) {
    return "";
  }
}
