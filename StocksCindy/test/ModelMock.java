import java.io.File;
import java.util.Map;
import java.util.Objects;

import model.Model;

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
   *
   * @param path path to the csv file
   * @return boolean.
   */
  @Override
  public boolean checkIfFileExist(String path) {
    File file = new File(path);
    if (file.exists()) {
      log.append("check file " + path + "\n");
    } else {
      log.append("Cannot find stock on file. ");
    }
    return file.exists();
  }

  /**
   * mock for testing date.
   *
   * @param date a date input
   * @return boolean.
   */
  @Override
  public boolean checkIfDate(String date) {
    log.append("if date " + date + "\n");
    return false;
  }

  /**
   * check number mock.
   *
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
   *
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
   *
   * @param ticker the company user inputs.
   */
  @Override
  public void generateStock(String ticker) {
    log.append("generate " + ticker + "\n");
  }

  /**
   * create portfolio mock.
   *
   * @param name is the name of the portfolio the user inputs.
   */
  @Override
  public void createPortfolio(String name) {
    log.append("Creating portfolio " + name + "\n");
  }

  /**
   * gets value for mock.
   *
   * @param name the name that the user input for their portfolio.
   * @param date the date that the user inputs.
   * @return string.
   */
  @Override
  public String evaluatePortfolio(String name, String date) {
    log.append("Evaluating portfolio " + name + " " + date + "\n");
    return "";
  }

  /**
   * gain/loss stock mock.
   *
   * @param ticker    the ticker that the user input.
   * @param startDate the startDate.
   * @param endDate   the end date.
   * @return string.
   */
  @Override
  public String evaluateStock(String ticker, String startDate, String endDate) {
    log.append("Evaluating stock " + ticker + " " + startDate + " " + endDate + "\n");
    return "";
  }

  /**
   * moving average mock.
   *
   * @param ticker    is the ticker of the company user inputs.
   * @param startDate the starting date user inputs.
   * @param lastX     integer user inputs.
   * @return string.
   */
  @Override
  public String movingAverage(String ticker, String startDate, double lastX) {
    log.append("Moving average " + ticker + " " + startDate + " " + lastX + "\n");
    return "";

  }

  /**
   * gets crossover days mock.
   *
   * @param ticker    the ticker user inputs.
   * @param startDate start date the user inputs.
   * @param lastX     the intger user inputs.
   * @return string.
   */
  @Override
  public String getCrossoverDays(String ticker, String startDate, double lastX) {
    log.append("Stock crossover " + ticker + " " + startDate + " " + lastX + "\n");
    return "";
  }

  /**
   * get portfolio names mock.
   *
   * @return string.
   */
  @Override
  public String getPortfolioNames() {
    log.append("get portfolio names\n");
    return log.toString();
  }

  /**
   * stock mock.
   *
   * @return string.
   */
  @Override
  public String getStockNames() {
    log.append("get stock\n");
    return "";
  }

  @Override
  public void buyStock(String name, String ticker, String share, String date) {
    log.append("buy stock " + ticker + " " + share + " " + name + " " + date + "\n");
  }

  @Override
  public void sellStock(String name, String ticker, String share, String date) {
    log.append("sell stock " + name + " " + ticker + " " + share + " " + date + "\n");
  }

  @Override
  public Map<String, Double> getPortfolioStocks(String name) {
    log.append("get portfolio " + name + "\n");
    return null;
  }

  @Override
  public void balance(String date, String name, Map<String, Double> percentages) {
    log.append("balance " + date + " " + name + "\n");
  }

  @Override
  public String getPortfolioComposition(String name, String date) {
    log.append("get composition " + date + " " + name + "\n");
    return null;
  }

  @Override
  public String getPortfolioDistribution(String name, String date) {
    log.append("get distribution " + date + " " + name + "\n");
    return null;
  }

  @Override
  public boolean checkIfChronologicalPortfolio(String name, String inputDate) {
    log.append("check if chronological portfolio " + name + " " + inputDate + "\n");
    return false;
  }

  @Override
  public boolean checkIfStockDataExist(String ticker, String inputDate) {
    log.append("data exists " + ticker + " " + inputDate + "\n");
    return false;
  }

  @Override
  public boolean checkIfPortfolioChronologicalAndDataExist(String portfolioName, String inputDate) {
    log.append("port chronology and data " + portfolioName + " " + inputDate + "\n");
    return false;
  }

  @Override
  public boolean checkIfWholeNumber(String string) {
    log.append("check whole number " + string + "\n");
    return false;
  }

  @Override
  public boolean checkSharesNotEnough(String portName,
                                      String ticker, double shares) {
    log.append("check if shares not enough " + portName + " " + ticker + " " + shares + "\n");
    return false;
  }

  @Override
  public String barChartStockInitialized(String name,
                                         String firstDate, String lastDate, String ticker) {
    log.append(
            "stock bar chart: " + name + "  " + firstDate + " " + lastDate + " " + ticker + "\n"
    );
    return "";
  }

  @Override
  public String barChartPortfolioInitialized(String name,
                                             String firstDate, String lastDate) {
    log.append(
            "port bar chart: " + name + " " + firstDate + " " + lastDate + "\n"
    );
    return "";
  }

}
