import java.io.File;
import java.util.List;
import java.util.Objects;

import model.Model;
import model.MyDateWithImpl;
import model.Stock;

/**
 * Mock class of model.
 */
public class ModelMock implements Model {
  final StringBuilder log;

  /**
   * initializes the log with given StringBuilder.
   *
   * @param log any StringBuilder
   */
  public ModelMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * initialize log with StringBuilder when given no inputs.
   */
  public ModelMock() {
    this.log = new StringBuilder();
  }

  /**
   * mock for testing if file exists.
   * @param path path to the csv file
   * @return boolean.
   */
  @Override
  public boolean checkIfFileExist(String path) {
    File file = new File(path);
    if(file.exists()) {
      log.append("check file " + path + "\n");
    }
    else {
      log.append("Cannot find stock on file. ");
    }
    return file.exists();
  }

  /**
   * mock for testing date.
   * @param date a date input
   * @return boolean.
   */
  @Override
  public boolean checkIfDate(String date) {
    try {
      String[] dateInfo = date.split("-");
      // just initializing, no need to store
      new MyDateWithImpl(
              Integer.parseInt(dateInfo[2]),
              Integer.parseInt(dateInfo[1]),
              Integer.parseInt(dateInfo[0]));
      return true;
    } catch (Exception e) {
      log.append("Invalid date. ");
      return false;
    }
  }

  /**
   * check number mock.
   * @param n any string
   * @return boolean.
   */
  @Override
  public boolean checkIfNumber(String n) {
    log.append("check number " + n + "\n");
    return false;
  }

  /**
   * display upload stock mock.
   * @param ticker gets the ticker user input.
   * @param path   a path of the file.
   * @return string.
   */
  @Override
  public String uploadStock(String ticker, String path) {
    log.append("uploaded stock " + ticker + " from " + path + "\n");
    return "";
  }

  /**
   * for testing.
   * @param ticker the company user inputs.
   */
  @Override
  public void generateStock(String ticker) {
    log.append("generate " + ticker + "\n");
  }

  /**
   * create portfolio mock.
   * @param name is the name of the portfolio the user inputs.
   */
  @Override
  public void createPortfolio(String name) {
    log.append("Creating portfolio " + name + "\n");
  }

  /**
   * manageportfolio mock.
   * @param name   of the portfolio.
   * @param ticker of the company.
   * @param shares the user inputs.
   */
  @Override
  public void managePortfolio(String name, String ticker, double shares) {
    log.append(ticker + " " + shares);
  }

  /**
   * gets value for mock.
   * @param name the name that the user input for their portfolio.
   * @param date the date that the user inputs.
   * @return string.
   */
  @Override
  public String evaluatePortfolio(String name, String date) {
    log.append("Evaluating portfolio " + date);
    return "";
  }

  /**
   * gain/loss stock mock.
   * @param ticker    the ticker that the user input.
   * @param startDate the startDate.
   * @param endDate   the end date.
   * @return string.
   */
  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    log.append("Evaluating stock " + ticker + " " + startDate + " " + endDate);
    return "";
  }

  /**
   * moving average mock.
   * @param ticker    is the ticker of the company user inputs.
   * @param startDate the starting date user inputs.
   * @param lastX     integer user inputs.
   * @return string.
   */
  @Override
  public String movingAverage(String ticker, String startDate, double lastX) {
    log.append("Moving average " + ticker + " " + startDate + " " + lastX);
    Stock stock = new Stock(ticker, "StocksCindy/CSVFiles");
    return String.valueOf(stock.getMovingAverage(startDate, lastX));
//    return "";
  }

  /**
   * gets crossover days mock.
   * @param ticker    the ticker user inputs.
   * @param startDate start date the user inputs.
   * @param lastX     the intger user inputs.
   * @return string.
   */
  @Override
  public String getCrossoverDays(String ticker, String startDate, double lastX) {
    log.append("Stock crossover " + ticker + " " + startDate + " " + lastX);
    Stock stock = new Stock(ticker, "StocksCindy/CSVFiles");
    List<String> days = stock.getCrossOver(startDate, lastX);
    StringBuilder sb = new StringBuilder();
    for (String day : days) {
      sb.append(day + "\n");
    }

    return sb.toString();
//    return "";
  }

  /**
   * get portfolio names mock.
   * @return string.
   */
  @Override
  public String getPortfolioNames() {
    log.append("get portfolio names\n");
    return log.toString();
  }

  /**
   * get portfolio mock.
   * @param name the user inputs for the portfolio.
   * @return String.
   */
  @Override
  public String getPortfolio(String name) {
    log.append("get portfolio");
    return "";
  }

  /**
   * stock mock.
   * @return string.
   */
  @Override
  public String getStockNames() {
    log.append("get stock");
    return "";
  }

}
