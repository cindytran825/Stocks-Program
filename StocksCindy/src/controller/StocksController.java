package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.Model;
import view.View;

/**
 * this class represents the controller of the application.
 * it is connected the view where the user is able to type inputs.
 * and instructions.
 * It uses Scanner and Readable to read inputs.
 * and transmit outputs.
 */
public class StocksController implements Controller {
  private final View view;
  private final Readable readable;
  private final Model model;
  private final Scanner scan;
  private final String stockDirectory;
  private final String portfolioDirectory;

  private String day;
  private String userInput;
  private String date;
  private String startDate;
  private String endDate;
  private String ticker;
  private String name;
  private String month;
  private String shares;
  private String year;
  private String daysAfter;
  private boolean finish;

  /**
   * public constructor that takes in the model, view, and readable.
   * is called in the program display.
   *
   * @param model    model interface.
   * @param view     view interface.
   * @param readable reads the user inputs.
   */
  public StocksController(Model model, View view, Readable readable) {
    //null exception?
    this.view = view;
    this.model = model;
    this.readable = readable;
    this.scan = new Scanner(readable);
    this.stockDirectory = "StocksCindy/CSVFiles";
    this.portfolioDirectory = "StocksCindy/Portfolio";
  }

  /**
   * this is called in the view and is used to read.
   * the inputs that the user puts.
   * it also calls methods when there is a command from.
   * the user to display later in the view.
   *
   * @throws IllegalStateException is called when method isn't at an appropriate state.
   */
  public void goControl() throws IllegalStateException {
    boolean quit = false;
    view.welcomeMessage();

    while (!quit) {
      view.printMenu();
      view.userInput();
      userInput = scan.next();

      switch (userInput) {

        // create new portfolio
        case "port-create":
          portfolioCreate();
          break;

        // make changes to existing portfolio
        case "port-manage":
          portManage();
          break;

        // gets a list of all existing portfolios on file
        case "port-list":
          portfolioList();
          break;

        // initiates the analysis of portfolios
        case "port-view":
          portView();
          break;

        // initiates the analysis of stocks
        case "stock-view":
          view.printStockNames(model.getStockNames());
          view.getTicker();
          ticker = scan.next();
          if (checkFile(ticker, stockDirectory)) {
            break;
          }

          finish = false;
          while (!finish) {
            view.printStockViewMenu();
            view.userInput();
            userInput = scan.next();

            switch (userInput) {
              case "finish":
                finish = true;
                view.terminating();
                break;

              // get net growth of stock
              case "value":
                stockValue();
                break;

              case "moving-avg":
                stockMovingAverage();
                break;

              case "crossover":
                stockCrossover();
                break;

              case "bar-chart":
// TODO CINDY PLEASE DO THIS PART
                view.startDate();
                String startDate = getDatePeriodPart();
                if (checkDate(startDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
                  break;
                }
                view.endDate();
                String endDate = getDatePeriodPart();
                if (checkDate(endDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
                  break;
                }
                //////////////////////////
                view.emptyLine();
                break;


              default:
                view.invalidCommand();
            }
          }

          break;

        // view the list of stocks on file
        case "stock-list":
          stockList();
          break;

        // to download / update stock information
        case "stock-download":
          stockDownload();
          break;

        // upload stock csv file to add
        case "stock-upload":
          stockUpload();
          break;

        case "quit":
          quit = true;
          break;
        case "menu":
          view.printMenu();
          view.emptyLine();
          break;
        default:
          view.invalidCommand();
      }
    }
    view.goodbye();
  }

  private void stockValue() {
    view.startDate();
    startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      view.invalidDate();
      return;
    }
    view.endDate();
    endDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      view.invalidDate();
      return;
    }
    view.printNetGain(
            model.evaluateStock(ticker, startDate, endDate),
            startDate,
            endDate
    );
    view.emptyLine();
  }

  private void stockCrossover() {
    if (dateAndDaysAfter()) {
      return;
    }
    view.printCrossover(
            model.getCrossoverDays(ticker, startDate, Double.parseDouble(daysAfter)));
    view.emptyLine();
  }

  private void stockMovingAverage() {
    if (dateAndDaysAfter()) {
      return;
    }
    view.movingAvg(
            model.movingAverage(ticker, startDate, Double.parseDouble(daysAfter)));
    view.emptyLine();
  }

  private void stockList() {
    view.printStockNames(model.getStockNames());
    view.emptyLine();
  }

  private void stockUpload() {
    view.getPath();
    String path = scan.next();
    if (!model.checkIfFileExist(path)) {
      view.invalidFile();
      view.terminating();
      view.emptyLine();
      return;
    }

    view.getNameFile();
    ticker = scan.next();
    model.uploadStock(ticker, path);
    view.printSuccessAddStock(ticker);
    view.emptyLine();
  }

  private void stockDownload() {
    view.getTicker();
    ticker = scan.next();
    model.generateStock(ticker);
    view.printSuccessAddStock(ticker);
    view.emptyLine();
  }

  private boolean dateAndDaysAfter() {
    view.startDate();
    startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      return true;
    }
    view.getDays();
    daysAfter = scan.next();
    if (checkDays(daysAfter)) {
      view.invalidNumber();
      return true;
    }
    return false;
  }

  private void portView() {
    if (selectPortfolio()) {
      return;
    }
    finish = false;
    while (!finish) {
      view.printPortfolioViewMenu();
      view.userInput();
      userInput = scan.next();

      switch (userInput) {
        case "finish":
          finish = true;
          view.terminating();
          break;
        // get portfolio composition
        case "composition":
          portfolioComposition();
          break;
        // get portfolio distribution
        case "distribution":
          portfolioDistribution();
          break;
        // get portfolio value
        case "value":
          portfolioValue();
          break;
        case "bar-chart": // TODO CINDY PLEASE DO THIS PART
          view.getWhichChart();
          String which = scan.next();

          view.startDate();
          String startDate = getDatePeriodPart();
          if (checkDate(startDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
            break;
          }
          view.endDate();
          String endDate = getDatePeriodPart();
          if (checkDate(endDate)) { // TODO CHECK IF DATE IS VALID AND IF THERE'S DATA POINTS AVAILABLE
            break;
          }
          switch (which) {
            case "portfolio":
              view.namePort();
              name = scan.next();
              view.print(model.barChartPortfolioInitialized(name, startDate, endDate));
              break;
            case "stock":
              view.tickerType();
              ticker = scan.next();
              view.print(model.barChartStockInitialized(name, startDate, endDate, ticker));
//              view.returnBarChartStock();
          }

          //////////////////////////
          break;

        default:
          view.invalidCommand();
      }
    }
  }

  private String getDatePeriodPart() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    String startDate = String.format("%s-%s-%s", day, month, year);
    return startDate;
  }

  private void portfolioValue() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.evaluatePortfolio(name, date));
    view.emptyLine();
  }

  private void portfolioDistribution() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.getPortfolioDistribution(name, date));
    view.emptyLine();
  }

  private void portfolioComposition() {
    if (checkEvaluationDate()) {
      return;
    }
    view.print(model.getPortfolioComposition(name, date));
    view.emptyLine();
  }

  private boolean checkEvaluationDate() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    date = String.format("%s-%s-%s", day, month, year);
    if (checkDate(date)) {
      return true;
    } else if (!model.checkIfStockDataExist(ticker, date)) {
      return true;
    }
    return false;
  }

  private boolean selectPortfolio() {
    view.getNameOfFile(model.getPortfolioNames());
    view.namePort();
    name = scan.next(); // needs catcher for invalid names
    if (checkFile(name, "StocksCindy/UserPortfolio")) {
      return true;
    }
    return false;
  }

  private void portManage() {

    view.getNameOfFile(model.getPortfolioNames());
    name = getName();

    // checks if file exist
    if (checkFile(name, "StocksCindy/UserPortfolio")) {
      return;
    }

    boolean finish = false;
    while (!finish) {
      view.printEditPortfolioMenu();
      view.userInput();
      userInput = scan.next();
      switch (userInput) {
        case "finish":
          finish = true;
          view.terminating();
          break;

        case "buy":
          portBuy();
          break;

        case "sell":
          portSell();
          break;

        case "balance":
          portBalance();
          break;

        default:
          view.invalidCommand();
      }
    }
    view.terminating();
    view.emptyLine();
  }

  private void portBuy() {
    // process ticker
    if (getAndCheckTicker()) {
      return;
    }

    if (checkSharesWholeNum()) {
      return;
    }

    // process dates
    if (checkChronologyAndData()) {
      return;
    }
    model.buyStock(name, ticker, shares, date);
    view.emptyLine();
  }

  private void portSell() {
    // process ticker
    if (getAndCheckTicker()) {
      return;
    }

    // process shares
    if (checkSharesWholeNum()) {
      return;
    } else if (checkSharesNotEnough()) {
      return;
    }

    // process date
    if (checkChronologyAndData()) {
      return;
    }
    model.sellStock(name, ticker, shares, date);
    view.emptyLine();
  }

  private void portBalance() {
    view.balanceFormat();

    // process dates
    if (checkChronologyAndData()) return;

    Set<String> stockNames = model.getPortfolioStocks(name).keySet();
    Map<String, Double> percentages = new HashMap<>();
    String percentage;
    for (String stockName : stockNames) {
      view.print(stockName + " percentage: ");
      percentage = scan.next();
      if (!model.checkIfNumber(percentage)) {
        view.invalidNumber();
        break;
      }
      percentages.put(stockName, Double.parseDouble(percentage));
    }

    double value = 0.0;
    for (double val : percentages.values()) {
      value += val;
    }
    if (value != 1.0) {
      view.invalidPercentage();
      return;
    }

    model.balance(date, name, percentages);
    view.balanceSuccess();
    view.emptyLine();
  }

  private boolean getAndCheckTicker() {
    view.getTicker();
    ticker = scan.next();
    if (!model.checkIfFileExist(stockDirectory + "/" + ticker + ".csv")) {
      view.invalidStock();
      view.emptyLine();
      return true;
    }
    return false;
  }

  private boolean checkSharesNotEnough() {
    view.insufficientShares();
    return model.checkSharesNotEnough(name, ticker, Double.parseDouble(shares));
  }

  private boolean checkSharesWholeNum() {
    view.getShares();
    shares = scan.next();
    if (!model.checkIfWholeNumber(shares)) {
      view.invalidShares();
      view.emptyLine();
      return true;
    }
    return false;
  }

  private boolean checkChronologyAndData() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    date = String.format("%s-%s-%s", day, month, year);

    if (!model.checkIfPortfolioChronologicalAndDataExist(name, date)) {
      view.notChronologicalOrDataInvalid();
      return true;
    }
    return false;
  }

  private void portfolioCreate() {
    String name;
    name = getName();
    model.createPortfolio(name);
    view.success();
    view.emptyLine();
  }

  private void portfolioList() {
    view.print(model.getPortfolioNames());
  }

  private String getName() {
    String name;
    view.newName();
    name = scan.next();
    return name;
  }

  private boolean checkDays(String lastDays) {
    if (!model.checkIfWholeNumber(lastDays)) {
      view.invalidNumber();
      view.emptyLine();
      return true;
    }
    return false;
  }

  //

  private boolean checkDate(String date1) {
    if (!model.checkIfDate(date1)) {
      view.invalidDate();
      view.emptyLine();
      return true;
    }
    return false;
  }

  private boolean checkFile(String ticker, String path) {
    if (!model.checkIfFileExist(path + "/" + ticker + ".csv")) {
      view.invalidFile();
      view.emptyLine();
      return true;
    }
    return false;
  }
}

