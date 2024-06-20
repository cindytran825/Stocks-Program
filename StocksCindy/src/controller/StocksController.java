package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import model.Model;
import view.GUIView;
import view.TextView;

/**
 * this class represents the controller of the application.
 * it is connected the view where the user is able to type inputs
 * and instructions.
 * It uses Scanner and Readable to read inputs.
 * and transmit outputs.
 */
public class StocksController implements Controller {
  private final TextView view;
  private final Model model;
  private final Scanner scan;
  private final String stockDirectory;
  private final String portfolioDirectory;
  private final GUIView guiView;

  private String day;
  private String userInput;
  private String date;
  private String startDate;
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
  public StocksController(Model model, TextView view, Readable readable, GUIView guiView) {
    this.view = view;
    this.model = model;
    this.scan = new Scanner(readable);
    this.guiView = guiView;
    this.stockDirectory = "StocksCindy/CSVFiles";
    this.portfolioDirectory = "StocksCindy/UserPortfolio";
  }

  @Override
  public void goControl() throws IllegalStateException {
    mainMenu();
  }

  private void mainMenu() {
    boolean quit = false;
    view.welcomeMessage();
    mainMenuLoop(quit);
    view.goodbye();
  }

  private void mainMenuLoop(boolean quit) {
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
          stockView();
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
  }

  private void stockView() {
    view.printStockNames(model.getStockNames());
    view.getTicker();
    ticker = scan.next();
    if (checkFile(ticker, stockDirectory)) {
      return;
    }
    finish = false;
    stockViewMenu();
  }

  private void stockViewMenu() {
    while (!finish) {
      view.printStockViewMenu();
      view.userInput();
      userInput = scan.next();
      view.emptyLine();

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
          stockBarChart();
          break;

        default:
          view.invalidCommand();
      }
    }
  }

  private void stockBarChart() {
    view.startDate();
    String startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      return;
    }
    view.endDate();
    String endDate = getDatePeriodPart();
    if (checkDate(endDate)) {
      return;
    }
    if (!model.checkIfStockDataExist(ticker, date)) {
      view.notChronologicalOrDataInvalid();
      return;
    }
    view.print(model.barChartStockInitialized(name, startDate, endDate, ticker));
    view.emptyLine();
  }

  private void stockValue() {
    view.startDate();
    startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      view.invalidDate();
      return;
    }
    view.endDate();
    String endDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      view.invalidDate();
      return;
    }
    view.printNetGain(
            model.evaluateStock(ticker, startDate, endDate),
            startDate, endDate);
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
        // get bar chart
        case "bar-chart":
          portfolioBarChart();
          break;
        default:
          view.invalidCommand();
      }
    }
  }

  private void portfolioBarChart() {
    view.startDate();
    String startDate = getDatePeriodPart();
    if (checkDate(startDate)) {
      return;
    }
    view.endDate();
    String endDate = getDatePeriodPart();
    if (checkDate(endDate)) {
      return;
    }

    view.print(model.barChartPortfolioInitialized(name, startDate, endDate) + "\n");
    view.emptyLine();
  }

  private String getDatePeriodPart() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    String startDate = String.format("%s-%s-%s", year, month, day);
    return startDate;
  }

  private void portfolioValue() {
    if (checkEvaluationDate()) {
      return;
    }
    try {
      view.print("$" + model.evaluatePortfolio(name, date));
    } catch (Exception e) {
      view.print("Error");
    }
    view.emptyLine();
  }

  private void portfolioDistribution() {
    if (checkEvaluationDate()) {
      return;
    }
    try {
      view.print(model.getPortfolioDistribution(name, date));
    } catch (Exception e) {
      view.print("Error");
    }
    view.emptyLine();
  }

  private void portfolioComposition() {
    if (checkEvaluationDate()) {
      return;
    }
    try {
      view.print(model.getPortfolioComposition(name, date));
    } catch (Exception e) {
      view.print("Error.");
    }
    view.emptyLine();
  }

  private boolean checkEvaluationDate() {
    view.getDay();
    day = scan.next();
    view.getMonth();
    month = scan.next();
    view.getYear();
    year = scan.next();
    date = String.format("%s-%s-%s", year, month, day);
    return checkDate(date);
  }

  private boolean selectPortfolio() {
    view.getNameOfFile(model.getPortfolioNames());
    view.namePort();
    name = scan.next(); // needs catcher for invalid names
    return checkFile(name, portfolioDirectory);
  }

  private void portManage() {
    view.getNameOfFile(model.getPortfolioNames());
    name = getName();

    // checks if file exist
    if (checkFile(name, portfolioDirectory)) {
      return;
    }

    boolean finish = false;
    portManageMenu(finish);
    view.terminating();
    view.emptyLine();
  }

  private void portManageMenu(boolean finish) {
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
    if (checkChronologyAndData()) {
      return;
    }
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
    date = String.format("%4s-%2s-%2s", year, month, day);

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

